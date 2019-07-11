package com.smithy.lappenlike.forgealegend.Forge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smithy.lappenlike.forgealegend.BaseActivity;
import com.smithy.lappenlike.forgealegend.R;

public class ForgeWeapon extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.forge_weapon_craft, container, false);

        return view;
    }
}
