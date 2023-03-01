package com.unicon.mini;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.unicon.mini.databinding.ActivityQuestionareBinding;

public class Questionare extends AppCompatActivity {

    Button B_submit;
    ToggleButton TB_cough,TB_diarrhea,TB_fatigue,TB_fever;
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


    }


}