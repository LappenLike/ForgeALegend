package com.smithy.lappenlike.forgealegend.Forge;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.smithy.lappenlike.forgealegend.Models.Fire;
import com.smithy.lappenlike.forgealegend.Models.WoodInventory;
import com.smithy.lappenlike.forgealegend.R;

import java.util.Arrays;
import java.util.List;

import static com.smithy.lappenlike.forgealegend.BaseActivity.userId;

public class ForgeFire extends Fragment {

    private View view;
    private ForgeContainer forgeContainer;

    private FirebaseFirestore firestore;

    private Button btn_nextFragment;

    private TextView tv_fireLifetime;

    private ImageView iv_cedarBark;
    private ImageView iv_larkBark;
    private ImageView iv_pineBark;
    private ImageView iv_mahoganyBark;
    private ImageView iv_oakBark;
    private ImageView iv_beechBark;

    private ImageView iv_cedar;
    private ImageView iv_lark;
    private ImageView iv_pine;
    private ImageView iv_oak;
    private ImageView iv_mahogany;
    private ImageView iv_beech;

    private TextView tv_burnWood;
    private TextView tv_burnBark;

    private TextView counterCedarBark;
    private TextView counterLarkBark;
    private TextView counterPineBark;
    private TextView counterMahoganyBark;
    private TextView counterOakBark;
    private TextView counterBeechBark;

    private TextView counterCedar;
    private TextView counterLark;
    private TextView counterPine;
    private TextView counterMahogany;
    private TextView counterOak;
    private TextView counterBeech;

    private WoodInventory woodInventory;

    private Fire fire;
    private ImageView iv_fire;

    private CollectionReference fireRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
       view = inflater.inflate(R.layout.forge_weapon_fire, container, false);
       forgeContainer = (ForgeContainer) getActivity();

       btn_nextFragment = view.findViewById(R.id.btn_nextFragment);
       btn_nextFragment.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ((ForgeContainer)getActivity()).setViewPager(1);
           }
       });
       firestore = FirebaseFirestore.getInstance();

       fireRef = firestore.collection("Users/"+userId+"/Fire");

       tv_fireLifetime =view.findViewById(R.id.tv_fireLifetime);
       iv_fire = view.findViewById(R.id.iv_fire);

        iv_cedarBark = view.findViewById(R.id.iv_cedarBark);
        iv_larkBark = view.findViewById(R.id.iv_larkBark);
        iv_pineBark = view.findViewById(R.id.iv_pineBark);
        iv_mahoganyBark = view.findViewById(R.id.iv_mahoganyBark);
        iv_oakBark = view.findViewById(R.id.iv_oakBark);
        iv_beechBark = view.findViewById(R.id.iv_beechBark);
        iv_cedar = view.findViewById(R.id.iv_cedar);
        iv_lark = view.findViewById(R.id.iv_lark);
        iv_pine = view.findViewById(R.id.iv_pine);
        iv_oak = view.findViewById(R.id.iv_oak);
        iv_mahogany = view.findViewById(R.id.iv_mahogany);
        iv_beech = view.findViewById(R.id.iv_beech);

       tv_burnWood = view.findViewById(R.id.tv_burnWood);
       tv_burnBark = view.findViewById(R.id.tv_burnBark);

        counterCedarBark = view.findViewById(R.id.counterCedarBark);
        counterLarkBark = view.findViewById(R.id.counterLarkBark);
        counterPineBark = view.findViewById(R.id.counterPineBark);
        counterMahoganyBark = view.findViewById(R.id.counterMahoganyBark);
        counterOakBark = view.findViewById(R.id.counterOakBark);
        counterBeechBark = view.findViewById(R.id.counterBeechBark);
        counterCedar = view.findViewById(R.id.counterCedar);
        counterLark = view.findViewById(R.id.counterLark);
        counterPine = view.findViewById(R.id.counterPine);
        counterMahogany= view.findViewById(R.id.counterMahogany);
        counterOak = view.findViewById(R.id.counterOak);
        counterBeech = view.findViewById(R.id.counterBeech);

       initFire();
       initInventory();
       initMaterialDrag();

       return view;
    }

   private void initFire(){
       fireRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
           @Override
           public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
               fireRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                   @Override
                   public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                       fire = queryDocumentSnapshots.toObjects(Fire.class).get(0);
                       tv_fireLifetime.setText(String.valueOf(fire.getLifetime()));
                       if(fire.getBark()!=0){
                           switch (fire.getBark()){
                               case 1:
                                   tv_burnBark.setText(R.string.cedarBark);
                                   break;
                               case 2:
                                   tv_burnBark.setText(R.string.larkBark);
                                   break;
                               case 3:
                                   tv_burnBark.setText(R.string.pineBark);
                                   break;
                               case 4:
                                   tv_burnBark.setText(R.string.mahoganyBark);
                                   break;
                               case 5:
                                   tv_burnBark.setText(R.string.oakBark);
                                   break;
                               case 6:
                                   tv_burnBark.setText(R.string.beechBark);
                                   break;
                           }
                       }

                       if(fire.getWood()!=0){
                           switch (fire.getBark()){
                               case 1:
                                   tv_burnWood.setText(R.string.cedar);
                                   break;
                               case 2:
                                   tv_burnWood.setText(R.string.lark);
                                   break;
                               case 3:
                                   tv_burnWood.setText(R.string.pine);
                                   break;
                               case 4:
                                   tv_burnWood.setText(R.string.mahogany);
                                   break;
                               case 5:
                                   tv_burnWood.setText(R.string.oak);
                                   break;
                               case 6:
                                   tv_burnWood.setText(R.string.beech);
                                   break;
                           }
                       }

                       if(fire.getLifetime() == 0){
                           iv_fire.setImageResource(R.drawable.fireout);
                       } else{
                           iv_fire.setImageResource(R.drawable.fire);
                       }
                   }
               });
           }
       });
    }

    private void initInventory(){
        final CollectionReference woodRef = firestore.collection("Users/"+userId+"/WoodInventory");
        woodRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                woodRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        woodInventory = queryDocumentSnapshots.toObjects(WoodInventory.class).get(0);
                        counterCedar.setText(String.valueOf(woodInventory.getCedar()));
                        counterCedarBark.setText(String.valueOf(woodInventory.getCedarBark()));
                        counterLark.setText(String.valueOf(woodInventory.getLark()));
                        counterLarkBark.setText(String.valueOf(woodInventory.getLarkBark()) );
                        counterPine.setText(String.valueOf(woodInventory.getPine()));
                        counterPineBark.setText(String.valueOf(woodInventory.getPineBark()));
                        counterMahogany.setText(String.valueOf(woodInventory.getMahogany()));
                        counterMahoganyBark.setText(String.valueOf(woodInventory.getMahoganyBark()));
                        counterOak.setText(String.valueOf(woodInventory.getOak()));
                        counterOakBark.setText(String.valueOf(woodInventory.getOakBark()));
                        counterBeech.setText(String.valueOf(woodInventory.getBeech()));
                        counterBeechBark.setText(String.valueOf(woodInventory.getBeechBark()));
                    }
                });
            }
        });
    }

    private void initMaterialDrag(){
        ImageView iv_fire = view.findViewById(R.id.iv_fire);

        List<ImageView> ivList = Arrays.asList(iv_cedarBark, iv_larkBark, iv_pineBark, iv_mahoganyBark, iv_oakBark, iv_beechBark, iv_cedar, iv_lark, iv_pine,
                iv_oak, iv_mahogany, iv_beech);

        view.findViewById(R.id.iv_water).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData cData = ClipData.newPlainText("","");
                View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
                v.startDragAndDrop(cData, myShadowBuilder, v, 0);
                return true;
            }
        });
        view.findViewById(R.id.iv_match).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData cData = ClipData.newPlainText("","");
                View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
                v.startDragAndDrop(cData, myShadowBuilder, v, 0);
                return true;
            }
        });

        for(ImageView loopView : ivList){
            loopView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipData cData = ClipData.newPlainText("","");
                    View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
                    v.startDragAndDrop(cData, myShadowBuilder, v, 0);
                    return true;
                }
            });
        }

        final Toast stillBurning =  Toast.makeText(getActivity(), R.string.fire_burning, Toast.LENGTH_SHORT);
        iv_fire.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int dragEvent = event.getAction();
                switch(dragEvent) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DROP:
                        final View view = (View) event.getLocalState();
                        switch(view.getId()) {

                            //Bark
                            case R.id.iv_match:
                                if(fire.getLifetime() == 0){
                                    if(fire.getWood() != 0){
                                        if(fire.getBark()!= 0){
                                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                                            builder.setTitle("Confirm");
                                            builder.setMessage(R.string.dial_lightFire);

                                            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    fire.setLifetime(3);
                                                    fireRef.add(fire);
                                                }
                                            });

                                            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });

                                            builder.show();
                                        } else{
                                            Toast.makeText(getActivity(), R.string.no_bark, Toast.LENGTH_SHORT).show();
                                        }
                                    } else{
                                        Toast.makeText(getActivity(), R.string.no_wood, Toast.LENGTH_SHORT).show();
                                    }
                                } else{
                                    stillBurning.show();
                                }
                                break;
                            case R.id.iv_cedarBark:
                                if(fire.getLifetime() == 0){
                                    if(Integer.parseInt(counterCedarBark.getText().toString())>0){
                                        giveBackResource(true);
                                        tv_burnBark.setText(R.string.cedarBark);
                                        fire.setBark(1);
                                        reduceCounter(counterCedarBark);
                                        woodInventory.setCedarBark(woodInventory.getCedarBark()-1);
                                    } else{
                                        Toast.makeText(getActivity(), R.string.resource_missing, Toast.LENGTH_SHORT).show();
                                    }
                                } else{
                                    stillBurning.show();
                                }
                                break;
                            case R.id.iv_larkBark:
                                if(fire.getLifetime() == 0) {
                                    if (Integer.parseInt(counterLarkBark.getText().toString()) > 0) {
                                        giveBackResource(true);
                                        tv_burnBark.setText(R.string.larkBark);
                                        fire.setBark(2);
                                        reduceCounter(counterLarkBark);
                                        woodInventory.setLarkBark(woodInventory.getLarkBark() - 1);
                                    } else {
                                        Toast.makeText(getActivity(), R.string.resource_missing, Toast.LENGTH_SHORT).show();
                                    }
                                } else{
                                    stillBurning.show();
                                }
                                break;
                            case R.id.iv_pineBark:
                                if(fire.getLifetime() == 0) {
                                    if (Integer.parseInt(counterPineBark.getText().toString()) > 0) {
                                        giveBackResource(true);
                                        tv_burnBark.setText(R.string.pineBark);
                                        fire.setBark(3);
                                        reduceCounter(counterPineBark);
                                        woodInventory.setPineBark(woodInventory.getPineBark() - 1);
                                    } else {
                                        Toast.makeText(getActivity(), R.string.resource_missing, Toast.LENGTH_SHORT).show();
                                    }
                                } else{
                                    stillBurning.show();
                                }
                                break;
                            case R.id.iv_mahoganyBark:
                                if(fire.getLifetime() == 0) {
                                    if (Integer.parseInt(counterMahoganyBark.getText().toString()) > 0) {
                                        giveBackResource(true);
                                        tv_burnBark.setText(R.string.mahoganyBark);
                                        fire.setBark(4);
                                        reduceCounter(counterMahoganyBark);
                                        woodInventory.setMahoganyBark(woodInventory.getMahoganyBark() - 1);
                                    } else {
                                        Toast.makeText(getActivity(), R.string.resource_missing, Toast.LENGTH_SHORT).show();
                                    }
                                } else{
                                    stillBurning.show();
                                }
                                break;
                            case R.id.iv_oakBark:
                                if(fire.getLifetime() == 0) {
                                    if (Integer.parseInt(counterOakBark.getText().toString()) > 0) {
                                        giveBackResource(true);
                                        tv_burnBark.setText(R.string.oakBark);
                                        fire.setBark(5);
                                        reduceCounter(counterOakBark);
                                        woodInventory.setOakBark(woodInventory.getOakBark() - 1);
                                    } else {
                                        Toast.makeText(getActivity(), R.string.resource_missing, Toast.LENGTH_SHORT).show();
                                    }
                                } else{
                                    stillBurning.show();
                                }
                                break;
                            case R.id.iv_beechBark:
                                if(fire.getLifetime() == 0) {
                                    if (Integer.parseInt(counterBeechBark.getText().toString()) > 0) {
                                        giveBackResource(true);
                                        tv_burnBark.setText(R.string.beechBark);
                                        fire.setBark(6);
                                        reduceCounter(counterBeechBark);
                                        woodInventory.setBeechBark(woodInventory.getBeechBark() - 1);
                                    } else {
                                        Toast.makeText(getActivity(), R.string.resource_missing, Toast.LENGTH_SHORT).show();
                                    }
                                } else{
                                    stillBurning.show();
                                }
                                break;

                                //Wood
                            case R.id.iv_cedar:
                                if(fire.getLifetime() == 0) {
                                    if (Integer.parseInt(counterCedar.getText().toString()) > 0) {
                                        giveBackResource(false);
                                        tv_burnWood.setText(R.string.cedar);
                                        fire.setWood(1);
                                        reduceCounter(counterCedar);
                                        woodInventory.setCedar(woodInventory.getCedar() - 1);
                                    } else {
                                        Toast.makeText(getActivity(), R.string.resource_missing, Toast.LENGTH_SHORT).show();
                                    }
                                } else{
                                    stillBurning.show();
                                }
                                break;
                            case R.id.iv_lark:
                                if(fire.getLifetime() == 0) {
                                    if (Integer.parseInt(counterLark.getText().toString()) > 0) {
                                        giveBackResource(false);
                                        tv_burnWood.setText(R.string.lark);
                                        fire.setWood(2);
                                        reduceCounter(counterLark);
                                        woodInventory.setLark(woodInventory.getLark() - 1);
                                    } else {
                                        Toast.makeText(getActivity(), R.string.resource_missing, Toast.LENGTH_SHORT).show();
                                    }
                                } else{
                                    stillBurning.show();
                                }
                                break;
                            case R.id.iv_pine:
                                if(fire.getLifetime() == 0) {
                                    if (Integer.parseInt(counterPine.getText().toString()) > 0) {
                                        giveBackResource(false);
                                        tv_burnWood.setText(R.string.pine);
                                        fire.setWood(3);
                                        reduceCounter(counterPine);
                                        woodInventory.setPine(woodInventory.getPine() - 1);
                                    } else {
                                        Toast.makeText(getActivity(), R.string.resource_missing, Toast.LENGTH_SHORT).show();
                                    }
                                } else{
                                    stillBurning.show();
                                }
                                break;
                            case R.id.iv_mahogany:
                                if(fire.getLifetime() == 0) {
                                    if (Integer.parseInt(counterMahogany.getText().toString()) > 0) {
                                        giveBackResource(false);
                                        tv_burnWood.setText(R.string.mahogany);
                                        fire.setWood(4);
                                        reduceCounter(counterMahogany);
                                        woodInventory.setMahogany(woodInventory.getMahogany() - 1);
                                    } else {
                                        Toast.makeText(getActivity(), R.string.resource_missing, Toast.LENGTH_SHORT).show();
                                    }
                                } else{
                                    stillBurning.show();
                                }
                                break;
                            case R.id.iv_oak:
                                if(fire.getLifetime() == 0) {
                                    if (Integer.parseInt(counterOak.getText().toString()) > 0) {
                                        giveBackResource(false);
                                        tv_burnWood.setText(R.string.oak);
                                        fire.setWood(5);
                                        reduceCounter(counterOak);
                                        woodInventory.setOak(woodInventory.getOak() - 1);
                                    } else {
                                        Toast.makeText(getActivity(), R.string.resource_missing, Toast.LENGTH_SHORT).show();
                                    }
                                } else{
                                    stillBurning.show();
                                }
                                break;
                            case R.id.iv_beech:
                                if(fire.getLifetime() == 0) {
                                    if (Integer.parseInt(counterBeech.getText().toString()) > 0) {
                                        giveBackResource(false);
                                        tv_burnWood.setText(R.string.beech);
                                        fire.setWood(6);
                                        reduceCounter(counterBeech);
                                        woodInventory.setBeech(woodInventory.getBeech() - 1);
                                    } else {
                                        Toast.makeText(getActivity(), R.string.resource_missing, Toast.LENGTH_SHORT).show();
                                    }
                                    tv_burnWood.setText(R.string.beech);
                                } else{
                                    stillBurning.show();
                                }
                                break;
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void reduceCounter (TextView counter){
        counter.setText(String.valueOf(Integer.parseInt(counter.getText().toString())-1));
    }

    private void upCounter(TextView counter){
        counter.setText(String.valueOf(Integer.parseInt(counter.getText().toString())+1));
    }

    //true == used bark
    //false == used wood
    //ACHTUNG: Hier muss angepasst werden, falls App lokalisiert wird!
    private void giveBackResource(Boolean bark){
        if(bark){
            switch(tv_burnBark.getText().toString()){
                case "CedarBark":
                    upCounter(counterCedarBark);
                    woodInventory.setCedarBark(woodInventory.getCedarBark()+1);
                    break;
                case "LarkBark":
                    upCounter(counterLarkBark);
                    woodInventory.setLarkBark(woodInventory.getLarkBark()+1);
                    break;
                case "PineBark":
                    upCounter(counterPineBark);
                    woodInventory.setPineBark(woodInventory.getPineBark()+1);
                    break;
                case "MahoganyBark":
                    upCounter(counterMahoganyBark);
                    woodInventory.setMahoganyBark(woodInventory.getMahoganyBark()+1);
                    break;
                case "OakBark":
                    upCounter(counterOakBark);
                    woodInventory.setOakBark(woodInventory.getOakBark()+1);
                    break;
                case "BeechBark":
                    upCounter(counterBeechBark);
                    woodInventory.setBeechBark(woodInventory.getBeechBark()+1);
                    break;
                default:
                    break;
            }
        }else{
            switch (tv_burnWood.getText().toString()){
                case "Cedar":
                    upCounter(counterCedar);
                    woodInventory.setCedar(woodInventory.getCedar()+1);
                    break;
                case "Lark":
                    upCounter(counterLark);
                    woodInventory.setLark(woodInventory.getLark()+1);
                    break;
                case "Pine":
                    upCounter(counterPine);
                    woodInventory.setPine(woodInventory.getPine()+1);
                    break;
                case "Mahogany":
                    upCounter(counterMahogany);
                    woodInventory.setMahogany(woodInventory.getMahogany()+1);
                    break;
                case "Oak":
                    upCounter(counterOak);
                    woodInventory.setOak(woodInventory.getOak()+1);
                    break;
                case "Beech":
                    upCounter(counterBeech);
                    woodInventory.setBeech(woodInventory.getBeech()+1);
                    break;
                default:
                    break;
            }

        }
    }

}
