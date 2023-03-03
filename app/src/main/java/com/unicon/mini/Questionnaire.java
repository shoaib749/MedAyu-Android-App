package com.unicon.mini;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import 	com.google.android.material.switchmaterial.SwitchMaterial;

public class Questionnaire extends AppCompatActivity {

    Button B_submit;
    SwitchMaterial TB_cough,TB_diarrhea,TB_fatigue,TB_fever;
    EditText ET_disease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare);
        B_submit = findViewById(R.id.B_submit);
        ET_disease = findViewById(R.id.ET_disease);
        TB_cough = findViewById(R.id.TB_cough);
        TB_diarrhea = findViewById(R.id.TB_diarrhea);
        TB_fever = findViewById(R.id.TB_fever);
        TB_fatigue = findViewById(R.id.TB_fatigue);
        B_submit.setOnClickListener((view -> {
            Intent i = new Intent(this,Remedies_result.class);
            startActivity(i);
        }));

    }


}