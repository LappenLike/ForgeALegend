package com.smithy.lappenlike.forgealegend;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.functions.FirebaseFunctions;
import com.smithy.lappenlike.forgealegend.Forge.Forge;
import com.smithy.lappenlike.forgealegend.Models.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class BaseActivity extends AppCompatActivity {

    public static FirebaseAuth mAuth;
    public static FirebaseUser user;
    public static String userId;
    public static DatabaseReference databaseRef;
    public static FirebaseFirestore firestore;

    public static String userid;
    public static CollectionReference weaponRef;

    public static Map<String, Weapon> weapons = new ArrayMap<>();

    private Boolean firstConn = true;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    public static final String WEAPONKEY = "weaponsMap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        userId = user.getUid();
        databaseRef = FirebaseDatabase.getInstance().getReference("users/"+user.getUid());
        firestore = FirebaseFirestore.getInstance();

        userid = user.getUid();
        weaponRef = firestore.collection("Users").document(userid).collection("Weapons");

        //logt den User aus wenn keine Verbindung zur Datenbank besteht (instant!)
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                } else {
                    if(firstConn){ //needed because first invocation always shows "false", so user gets logged off after logging in
                        firstConn = false;
                    } else {
                        finish();
                        mAuth.signOut();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.err.println("Base cancelled.");
            }
        });

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        initSidebar();
        initWeaponListener();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Wird in den andere Activities benötigt um die Seite zu initialisieren
    public void addContentView(int layoutId) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null, false);
        mDrawerLayout.addView(contentView, 0);
    }

    //holt die Waffen aus der Datenbank
    private void initWeaponListener(){
        weaponRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                weaponRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<DocumentSnapshot> snapshots = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot snapshot : snapshots){
                                weapons.put(snapshot.getId(), snapshot.toObject(Weapon.class));
                            }
                            createInventoryTable();
                        }
                    });
            }
        });
    }

    //Wenn Waffen aktualisiert wurden -> Aktualisiere auch Inventory Anzeige
    private void createInventoryTable(){
        LinearLayout weaponsLayout = findViewById(R.id.weaponsLayout);
        List<LinearLayout> layoutList = new ArrayList<>();

        LinearLayout.LayoutParams imageViewParams = new LinearLayout.LayoutParams(120, 120);
        imageViewParams.leftMargin = 10;
        imageViewParams.rightMargin = 10;
        imageViewParams.topMargin = 20;
        imageViewParams.weight = 1;

        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout newLin = new LinearLayout(this);
        newLin.setLayoutParams(linearParams);

        int layoutCounter = 0;
        for(Map.Entry<String, Weapon> loopWeapon : weapons.entrySet()){
            if(layoutCounter != 0 && layoutCounter % 6 == 0){
                layoutList.add(newLin);
                newLin = new LinearLayout(this);
                newLin.setLayoutParams(linearParams);
            }

            ImageView inventoryImage = new ImageView(this);
            inventoryImage.setLayoutParams(imageViewParams);
            inventoryImage.setImageResource(R.drawable.icon_crown);
            newLin.addView(inventoryImage);

            //Todo: Hier müssen dann die Inventory Icons mit Funktion versehen werden

            layoutCounter++;
        }

        int constMissingChilds = 6-newLin.getChildCount();
        for(int i = 0; i < constMissingChilds; i++){
            ImageView inventoryImage = new ImageView(this);
            inventoryImage.setLayoutParams(imageViewParams);
            newLin.addView(inventoryImage);
        }
        layoutList.add(newLin);

        for(LinearLayout layout : layoutList){
            weaponsLayout.addView(layout);
        }
    }

    //3 Striche oben Links toggeln die Sidebar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        } else{
            return false;
        }
    }

    private void initSidebar(){
        NavigationView menu = findViewById(R.id.menu);
        menu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case(R.id.nav_profile):
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        break;
                    case(R.id.nav_forge):
                        startActivity(new Intent(getApplicationContext(), Forge.class));
                        break;
                    case(R.id.nav_logout):
                        finish();
                        mAuth.signOut();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        break;
                }
                return true;
            }
        });
    }

}
