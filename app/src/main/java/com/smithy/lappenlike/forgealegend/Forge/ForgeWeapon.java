package com.smithy.lappenlike.forgealegend.Forge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smithy.lappenlike.forgealegend.BaseActivity;
import com.smithy.lappenlike.forgealegend.Keys;
import com.smithy.lappenlike.forgealegend.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ForgeWeapon extends Fragment {

    private ImageView iv_craftWeapon;
    private ImageView iv_weaponsLeft;
    private ImageView iv_weaponsRight;
    private int imageIndex = 1;

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

        initSelection();
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
        initSpinner();
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
