package com.smithy.lappenlike.forgealegend;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private static EditText et_email;
    private static EditText et_pw;
    private static TextView signup;
    private static Button btn_login;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        et_email = findViewById(R.id.et_email);
        et_pw = findViewById(R.id.et_password);
        progressBar = findViewById(R.id.pb_progress);

        loginButton();
        signupButton();
    }

    //Wenn der User bereits angemeldet ist, wird direkt auf das Profil weitergeleitet
    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){
            if(!user.getDisplayName().isEmpty() || user.getDisplayName() != null){
                finish();
                startActivity(new Intent(getApplicationContext(),Profile.class));
            } else {
                finish();
                startActivity(new Intent(getApplicationContext(),SetName.class));
            }

        }
    }

    //Setzt einen ClickListener auf den Login Button und führt validUser und registerUser aus
    private void loginButton(){
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(validUser()) {
                            loginTheUser();
                        }
                    }
                }
        );
    }

    //Registriert den User mit der Firebase Datenbank
    private void loginTheUser(){
        String username = et_email.getText().toString().trim();
        String password = et_pw.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    Toast.makeText(getApplicationContext(),"Erfolgreich angemeldet!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SetName.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //zurück führt nicht wieder zum login -> clear
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        progressBar.setVisibility(View.GONE);
    }

    //Prüft, ob die Eingabewerte in den 2 Feldern valide sind
    private boolean validUser(){
        boolean validLogin = true;

        String email = et_email.getText().toString().trim();
        String password = et_pw.getText().toString().trim();

        if(password.isEmpty()) {
            et_pw.setError("Username is required!");
            et_pw.requestFocus();
            validLogin = false;
        }

        if(email.isEmpty()){
            et_email.setError("E-Mail is required!");
            et_email.requestFocus();
            validLogin = false;;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("Not a valid E-Mail.");
            et_email.requestFocus();
            validLogin = false;;
        }
        return validLogin;
    }

    //Startet einen Intent auf die Signup-Page
    private void signupButton(){
        signup = findViewById(R.id.tv_signup);
        signup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       startActivity(new Intent(getApplicationContext(),SignUp.class));
                    }
                }

        );
    }
}
