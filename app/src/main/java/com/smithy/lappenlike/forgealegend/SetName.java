package com.smithy.lappenlike.forgealegend;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.smithy.lappenlike.forgealegend.Models.Fire;
import com.smithy.lappenlike.forgealegend.Models.WoodInventory;

public class SetName extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseFirestore firestore;

    private DocumentReference userRef;

    private DatabaseReference databaseRef;

    private Button btn_submit;
    private EditText et_characterName;

    private TextView tv_faction_desc;
    private TextView tv_origin_desc;
    private TextView tv_faction_header;
    private TextView tv_origin_header;

    private RadioButton radio_fGoldenMonarchy;
    private RadioButton radio_fWorkersUnion;

    private RadioButton radio_oMerc;
    private RadioButton radio_oEld;
    private RadioButton radio_oFac;

    private static ProgressBar pb_progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_name_activity);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference("users/"+user.getUid());
        firestore = FirebaseFirestore.getInstance();

        userRef = firestore.collection("Users").document(user.getUid());

        tv_faction_desc = findViewById(R.id.tv_faction_desc);
        tv_origin_desc = findViewById(R.id.tv_origin_desc);
        tv_faction_header = findViewById(R.id.tv_faction_header);
        tv_origin_header = findViewById(R.id.tv_origin_header);

        btn_submit = findViewById(R.id.btn_submit);
        et_characterName = findViewById(R.id.et_characterName);
        radio_fGoldenMonarchy = findViewById(R.id.radio_fGoldenMonarchy);
        radio_fWorkersUnion = findViewById(R.id.radio_fWorkersUnion);
        radio_oEld = findViewById(R.id.radio_oEld);
        radio_oMerc = findViewById(R.id.radio_oMerc);
        radio_oFac = findViewById(R.id.radio_oFac);

        pb_progress = findViewById(R.id.pb_progressSet);

        initRadio();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitName();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() == null){
            finish();
            mAuth.signOut();
            startActivity(new Intent(getApplicationContext(),Login.class));
        } else if(mAuth.getCurrentUser().getDisplayName() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),Login.class));
        }
    }

    private void initRadio(){
        //Factions
        radio_fGoldenMonarchy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_faction_header.setText(R.string.fac_golden_monarchy);
                tv_faction_desc.setText(R.string.gom_desc);
            }
        });
        radio_fWorkersUnion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_faction_header.setText(R.string.fac_workers_union);
                tv_faction_desc.setText(R.string.wu_desc);
            }
        });


        //Origins
        radio_oEld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_origin_header.setText(R.string.elder_dwarfs);
                tv_origin_desc.setText(R.string.eld_desc);
            }
        });
        radio_oMerc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_origin_header.setText(R.string.resigned_mercenaries);
                tv_origin_desc.setText(R.string.merc_desc);
            }
        });
        radio_oFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_origin_header.setText(R.string.the_faceless);
                tv_origin_desc.setText(R.string.fac_desc);
            }
        });

        //Init TextViews
        radio_fGoldenMonarchy.performClick();
        radio_oEld.performClick();
    }

    private void submitName() {
        final String CHARACTERNAME = et_characterName.getText().toString().trim();

        if (CHARACTERNAME.isEmpty()) {
            et_characterName.setError("Character Name is required!");
            et_characterName.requestFocus();
            return;
        } else if (CHARACTERNAME.length() < 3) {
            et_characterName.setError("Character Name is too Short! (min. 3)");
            et_characterName.requestFocus();
            return;
        }

        if (user != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure this will be your name?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    pb_progress.setVisibility(View.VISIBLE);
                    UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(CHARACTERNAME).build();
                    user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                setAllDatabasePaths();

                                finish();
                                pb_progress.setVisibility(View.GONE);
                                startActivity(new Intent(getApplicationContext(), Profile.class));
                            }
                        }
                    });
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private void setAllDatabasePaths(){
        userRef.collection("Fire").add(new Fire(0,0,0));
        userRef.collection("WoodInventory").add(new WoodInventory(0,0,0,0,0,0,0,0,0,0,0,0));
    }

}
