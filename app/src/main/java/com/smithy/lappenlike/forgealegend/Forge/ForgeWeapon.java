package com.smithy.lappenlike.forgealegend.Forge;

import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smithy.lappenlike.forgealegend.BaseActivity;
import com.smithy.lappenlike.forgealegend.Keys;
import com.smithy.lappenlike.forgealegend.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ForgeWeapon extends Fragment {

    private ImageView iv_craftWeapon;
    private ImageView iv_weaponsLeft;
    private ImageView iv_weaponsRight;
    private int imageIndex = 1;

    private ImageView iv_SpringSteel;
    private ImageView iv_highCarbonSteel;
    private ImageView iv_toolSteel;
    private ImageView iv_alloySteel;
    private ImageView iv_duralumin;
    private ImageView iv_nichrome;
    private ImageView iv_bronze;

    private ImageView iv_craftMetal1;
    private ImageView iv_craftMetal2;
    private ImageView iv_craftMetal3;
    private ImageView iv_craftMetal4;
    private int countSockets = 0;

    private TextView tv_descHand;
    private TextView tv_descWeight;
    private TextView tv_descDamage;
    private TextView tv_descSpeed;
    private TextView tv_descCrit;

    private Spinner sp_weapons;

    private Map<Integer, String> weaponsMap = new TreeMap<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.forge_weapon_craft, container, false);

        iv_craftWeapon = view.findViewById(R.id.iv_craftWeapon);
        iv_weaponsLeft = view.findViewById(R.id.iv_weaponsLeft);
        iv_weaponsRight = view.findViewById(R.id.iv_weaponsRight);

        sp_weapons = view.findViewById(R.id.sp_weapons);

        iv_SpringSteel = view.findViewById(R.id.iv_springSteel);
        iv_highCarbonSteel = view.findViewById(R.id.iv_highCarbonSteel);
        iv_toolSteel = view.findViewById(R.id.iv_toolSteel);
        iv_alloySteel = view.findViewById(R.id.iv_alloySteel);
        iv_duralumin = view.findViewById(R.id.iv_duralumin);
        iv_nichrome = view.findViewById(R.id.iv_nichrom);
        iv_bronze = view.findViewById(R.id.iv_bronze);

        iv_craftMetal1 = view.findViewById(R.id.iv_craftMetal1);
        iv_craftMetal2 = view.findViewById(R.id.iv_craftMetal2);
        iv_craftMetal3 = view.findViewById(R.id.iv_craftMetal3);
        iv_craftMetal4 = view.findViewById(R.id.iv_craftMetal4);

        tv_descHand = view.findViewById(R.id.tv_descHand);
        tv_descWeight = view.findViewById(R.id.tv_descWeight);
        tv_descDamage = view.findViewById(R.id.tv_descDamage);
        tv_descSpeed = view.findViewById(R.id.tv_descSpeed);
        tv_descCrit = view.findViewById(R.id.tv_descCrit);

        initSelection();
        initDragAndDrop();
        return view;
    }

    private void initSelection(){
        weaponsMap.put(Keys.AXE, getText(R.string.axe).toString());
        weaponsMap.put(Keys.HALBERD, getText(R.string.halberd).toString());
        weaponsMap.put(Keys.LANCE, getText(R.string.lance).toString());
        weaponsMap.put(Keys.SPEAR, getText(R.string.spear).toString());
        weaponsMap.put(Keys.FLAIL, getText(R.string.flail).toString());
        weaponsMap.put(Keys.SCYTHE, getText(R.string.scythe).toString());
        weaponsMap.put(Keys.CLAYMORE, getText(R.string.claymore).toString());
        weaponsMap.put(Keys.GLAIVE, getText(R.string.glaive).toString());
        weaponsMap.put(Keys.MACE, getText(R.string.mace).toString());
        weaponsMap.put(Keys.HAMMER, getText(R.string.hammer).toString());
        weaponsMap.put(Keys.DAGGER, getText(R.string.dagger).toString());
        weaponsMap.put(Keys.RAPIER, getText(R.string.rapier).toString());

        weaponsMap.put(Keys.BUCKLER, getText(R.string.buckler).toString());
        weaponsMap.put(Keys.KITESHIELD, getText(R.string.kite).toString());
        weaponsMap.put(Keys.SCUTUM, getText(R.string.scutum).toString());

        List<String> spinnerList = new ArrayList<>(weaponsMap.values());
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_spinner_item, spinnerList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_weapons.setAdapter(spinnerAdapter);

        initClicks();
        initMaterialViews();
        initSpinner();
    }

    private void initDragAndDrop(){
        final List<Integer> socketsx2 = Arrays.asList(1,4,5,9,11,12);
        iv_craftMetal1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent event) {
                if(socketsx2.contains(imageIndex)){
                    dragCase(event.getAction(), 1);
                }
                return true;
            }
        });
    }

    private void dragCase(int dragEvent, int metalPosition){
        if(metalPosition <= 2 || Keys.listTwoHand.contains(imageIndex)){
            ImageView materialView;
            switch(metalPosition){
                case 1:
                    materialView = iv_craftMetal1;
                    break;
                case 2:
                    materialView = iv_craftMetal2;
                    break;
                case 3:
                    materialView = iv_craftMetal3;
                    break;
                case 4:
                    materialView = iv_craftMetal4;
                    break;
                default:
                    //sollte nie auftreten...
                    materialView = iv_craftMetal1;
                    break;
            }
            switch (dragEvent){
                case R.id.iv_alloySteel:

                    break;
                default:
                    break;
            }
        } else{
            //nichts tun wenn Socket nicht initialisiert ist!
        }
    }

    private void initClicks(){
        iv_weaponsLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageIndex(-1);
                //imageIndex von 1-15 -> array index 0-11 -> -1
                sp_weapons.setSelection(imageIndex-1);
            }
        });

        iv_weaponsRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageIndex(1);
                //imageIndex von 1-15 -> array index 0-11 -> -1
                sp_weapons.setSelection(imageIndex-1);
            }
        });
    }

    public void initMaterialViews(){
        //free, random -> beige, cyan, black, purple, turquoise, yellow, red, blue green

        switch (imageIndex){
            case 1 :
                countSockets = 2;
                iv_craftMetal1.setImageResource(R.drawable.socketfree);
                iv_craftMetal2.setImageResource(R.drawable.socketrandom);
                iv_craftMetal3.setImageResource(android.R.color.transparent);
                iv_craftMetal4.setImageResource(android.R.color.transparent);
                break;
            case 2:
                countSockets = 4;
                iv_craftMetal1.setImageResource(R.drawable.socketfree);
                iv_craftMetal2.setImageResource(R.drawable.socketrandom);
                iv_craftMetal3.setImageResource(R.drawable.socketfree);
                iv_craftMetal4.setImageResource(R.drawable.socketrandom);
                break;
            case 3:
                countSockets = 4;
                iv_craftMetal1.setImageResource(R.drawable.socketrandom);
                iv_craftMetal2.setImageResource(R.drawable.socketrandom);
                iv_craftMetal4.setImageResource(android.R.color.transparent);
                break;
            case 4:
                countSockets = 2;
                iv_craftMetal1.setImageResource(R.drawable.socketfree);
                iv_craftMetal2.setImageResource(R.drawable.socketrandom);
                iv_craftMetal3.setImageResource(android.R.color.transparent);
                iv_craftMetal4.setImageResource(android.R.color.transparent);
                break;
            case 5:
                countSockets = 2;
                iv_craftMetal1.setImageResource(R.drawable.socketfree);
                iv_craftMetal2.setImageResource(R.drawable.socketrandom);
                iv_craftMetal3.setImageResource(android.R.color.transparent);
                iv_craftMetal4.setImageResource(android.R.color.transparent);
                break;
            case 6:
                countSockets = 4;
                iv_craftMetal1.setImageResource(R.drawable.socketfree);
                iv_craftMetal2.setImageResource(R.drawable.socketrandom);
                iv_craftMetal3.setImageResource(R.drawable.socketfree);
                iv_craftMetal4.setImageResource(R.drawable.socketwhite);
                break;
            case 7:
                countSockets = 4;
                iv_craftMetal1.setImageResource(R.drawable.socketrandom);
                iv_craftMetal2.setImageResource(R.drawable.socketrandom);
                iv_craftMetal3.setImageResource(R.drawable.socketrandom);
                iv_craftMetal4.setImageResource(R.drawable.socketrandom);
                break;
            case 8:
                countSockets = 4;
                iv_craftMetal1.setImageResource(R.drawable.socketfree);
                iv_craftMetal2.setImageResource(R.drawable.socketrandom);
                iv_craftMetal3.setImageResource(R.drawable.socketfree);
                iv_craftMetal4.setImageResource(R.drawable.socketrandom);
                break;
            case 9:
                countSockets = 2;
                iv_craftMetal1.setImageResource(R.drawable.socketrandom);
                iv_craftMetal2.setImageResource(R.drawable.socketrandom);
                iv_craftMetal3.setImageResource(android.R.color.transparent);
                iv_craftMetal4.setImageResource(android.R.color.transparent);
                break;
            case 10:
                countSockets = 4;
                iv_craftMetal1.setImageResource(R.drawable.socketcyan);
                iv_craftMetal2.setImageResource(R.drawable.socketrandom);
                iv_craftMetal3.setImageResource(R.drawable.socketcyan);
                iv_craftMetal4.setImageResource(R.drawable.socketrandom);
                break;
            case 11:
                countSockets = 2;
                iv_craftMetal1.setImageResource(R.drawable.socketfree);
                iv_craftMetal2.setImageResource(R.drawable.socketfree);
                iv_craftMetal3.setImageResource(android.R.color.transparent);
                iv_craftMetal4.setImageResource(android.R.color.transparent);
                break;
            case 12:
                countSockets = 2;
                iv_craftMetal1.setImageResource(R.drawable.socketfree);
                iv_craftMetal2.setImageResource(R.drawable.socketgreen);
                iv_craftMetal3.setImageResource(android.R.color.transparent);
                iv_craftMetal4.setImageResource(android.R.color.transparent);
                fillWeaponDescription(Keys.RAPIER);
                break;
            default:
                break;
        }
    }

    private void fillWeaponDescription(int weapon){
        switch (weapon){
            case 1:
                tv_descHand.setText(R.string.h1);
                tv_descWeight.setText("1kg");
                tv_descDamage.setText("30 - 40");
                tv_descSpeed.setText("0");
                tv_descCrit.setText("10%");
                break;
            case 2:
                tv_descHand.setText(R.string.h2);
                tv_descWeight.setText("1kg");
                tv_descDamage.setText("30 - 40");
                tv_descSpeed.setText("0");
                tv_descCrit.setText("10%");
                break;
            case 3:
                tv_descHand.setText(R.string.h2);
                tv_descWeight.setText("1kg");
                tv_descDamage.setText("30 - 40");
                tv_descSpeed.setText("0");
                tv_descCrit.setText("10%");
                break;
            case 4:
                tv_descHand.setText(R.string.h1);
                tv_descWeight.setText("1kg");
                tv_descDamage.setText("30 - 40");
                tv_descSpeed.setText("0");
                tv_descCrit.setText("10%");
                break;
            case 5:
                tv_descHand.setText(R.string.h1);
                tv_descWeight.setText("1kg");
                tv_descDamage.setText("30 - 40");
                tv_descSpeed.setText("0");
                tv_descCrit.setText("10%");
                break;
            case 6:
                tv_descHand.setText(R.string.h2);
                tv_descWeight.setText("1kg");
                tv_descDamage.setText("30 - 40");
                tv_descSpeed.setText("0");
                tv_descCrit.setText("10%");
                break;
            case 7:
                tv_descHand.setText(R.string.h2);
                tv_descWeight.setText("1kg");
                tv_descDamage.setText("30 - 40");
                tv_descSpeed.setText("0");
                tv_descCrit.setText("10%");
                break;
            case 8:
                tv_descHand.setText(R.string.h2);
                tv_descWeight.setText("1kg");
                tv_descDamage.setText("30 - 40");
                tv_descSpeed.setText("0");
                tv_descCrit.setText("10%");
                break;
            case 9:
                tv_descHand.setText(R.string.h1);
                tv_descWeight.setText("1kg");
                tv_descDamage.setText("30 - 40");
                tv_descSpeed.setText("0");
                tv_descCrit.setText("10%");
                break;
            case 10:
                tv_descHand.setText(R.string.h2);
                tv_descWeight.setText("1kg");
                tv_descDamage.setText("30 - 40");
                tv_descSpeed.setText("0");
                tv_descCrit.setText("10%");
                break;
            case 11:
                tv_descHand.setText(R.string.h1);
                tv_descWeight.setText("1kg");
                tv_descDamage.setText("30 - 40");
                tv_descSpeed.setText("0");
                tv_descCrit.setText("10%");
                break;
            case 12:
                tv_descHand.setText(R.string.h1);
                tv_descWeight.setText("1kg");
                tv_descDamage.setText("30 - 40");
                tv_descSpeed.setText("0");
                tv_descCrit.setText("10%");
                break;
            default:
                break;
        }
    }


    private void initSpinner(){
        sp_weapons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageIndex = position+1;
                setImage();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setImage(){
        switch(imageIndex){
            case Keys.AXE:
                iv_craftWeapon.setImageResource(R.drawable.weaponaxe);
                break;
            case Keys.HALBERD:
                iv_craftWeapon.setImageResource(R.drawable.weaponhalberd);
                break;
            case Keys.LANCE:
                iv_craftWeapon.setImageResource(R.drawable.weaponlance);
                break;
            case Keys.SPEAR:
                iv_craftWeapon.setImageResource(R.drawable.weaponspear);
                break;
            case Keys.FLAIL:
                iv_craftWeapon.setImageResource(R.drawable.weaponflail);
                break;
            case Keys.SCYTHE:
                iv_craftWeapon.setImageResource(R.drawable.weaponscythe);
                break;
            case Keys.CLAYMORE:
                iv_craftWeapon.setImageResource(R.drawable.weaponclaymore);
                break;
            case Keys.GLAIVE:
                iv_craftWeapon.setImageResource(R.drawable.weaponglaive);
                break;
            case Keys.MACE:
                iv_craftWeapon.setImageResource(R.drawable.weaponmace);
                break;
            case Keys.HAMMER:
                iv_craftWeapon.setImageResource(R.drawable.weaponhammer);
                break;
            case Keys.DAGGER:
                iv_craftWeapon.setImageResource(R.drawable.weapondagger);
                break;
            case Keys.RAPIER:
                iv_craftWeapon.setImageResource(R.drawable.weaponrapier);
                break;
            case Keys.BUCKLER:
                iv_craftWeapon.setImageResource(R.drawable.shieldbuckler);
                break;
            case Keys.KITESHIELD:
                iv_craftWeapon.setImageResource(R.drawable.shieldkite);
                break;
            case Keys.SCUTUM:
                iv_craftWeapon.setImageResource(R.drawable.shieldscutum);
                break;
            default:
                break;
        }

        initMaterialViews();
    }

    private void setImageIndex(int add){
        imageIndex += add;
        if(imageIndex > 15){
            imageIndex = 1;
        } else if(imageIndex < 1){
            imageIndex = 15;
        }
        setImage();
    }
}
