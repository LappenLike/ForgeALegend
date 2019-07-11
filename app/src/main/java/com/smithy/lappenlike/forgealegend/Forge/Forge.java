package com.smithy.lappenlike.forgealegend.Forge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.smithy.lappenlike.forgealegend.BaseActivity;
import com.smithy.lappenlike.forgealegend.R;

public class Forge extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.addContentView(R.layout.forge_activity);

        initLinks();
    }

     private void initLinks(){
        Button btn_forgeWeapons = findViewById(R.id.btn_forgeWeapons);
        btn_forgeWeapons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgeContainer.class));
            }
        });
    }


}
