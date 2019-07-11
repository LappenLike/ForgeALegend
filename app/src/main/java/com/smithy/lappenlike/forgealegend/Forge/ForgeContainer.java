package com.smithy.lappenlike.forgealegend.Forge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.firebase.firestore.FirebaseFirestore;
import com.smithy.lappenlike.forgealegend.BaseActivity;
import com.smithy.lappenlike.forgealegend.R;

public class ForgeContainer extends BaseActivity {

    private WeaponsFragementAdapter adapter;
    private ViewPager mViewPager;

    public FirebaseFirestore firestore;
    public String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.addContentView(R.layout.forge_weapon_container);
        adapter = new WeaponsFragementAdapter(getSupportFragmentManager(), 0);
        mViewPager = findViewById(R.id.forgePager);
        setupViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager){
        WeaponsFragementAdapter methodAdapter = new WeaponsFragementAdapter(getSupportFragmentManager(), 0);
        methodAdapter.addFragment(new ForgeFire(), "ForgeFire");
        methodAdapter.addFragment(new ForgeWeapon(), "ForgeWeapon");
        viewPager.setAdapter(methodAdapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }
}
