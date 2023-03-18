package com.unicon.mini;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Questionnaire extends AppCompatActivity {

    Button B_submit;

    EditText ET_symptoms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare);
        B_submit = findViewById(R.id.B_submit);
        ET_symptoms = findViewById(R.id.ET_symptoms);

        Context context = this.getApplicationContext();

        B_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String symp = String.valueOf(ET_symptoms.getText()).trim();
                if(symp.equals("")){
                    Toast.makeText(context, "Please enter diseases", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(context, symp, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, Questionnaire2.class);
                    startActivity(intent);
                }
            }
        });

    }


}