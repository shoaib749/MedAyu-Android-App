package com.unicon.mini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class Questionnaire3 extends AppCompatActivity {
    LinearLayout LL_symptoms;
    CheckBox checkBox;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire3);
        LL_symptoms = findViewById(R.id.LL_More_symptoms);
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Integer> listOfId = new ArrayList<>();
        submit = findViewById(R.id.submitSymp3);

        //TODO add data in to this list
        list.add("null");
        list.add("Congestive heart disease");
        list.add("Chorea");
        list.add("Glaucoma");
        list.add("Congestive heart disease");
        list.add("Chorea");
        list.add("Glaucoma");
        list.add("Congestive heart disease");
        list.add("Chorea");
        list.add("Glaucoma");list.add("Congestive heart disease");
        list.add("Chorea");
        list.add("Glaucoma");list.add("Congestive heart disease");
        list.add("Chorea");
        list.add("Glaucoma");list.add("Congestive heart disease");
        list.add("Chorea");
        list.add("Glaucoma");list.add("Congestive heart disease");
        list.add("Chorea");
        list.add("Glaucoma");
        list.add("Congestive heart disease");
        list.add("Chorea");
        list.add("Glaucoma");list.add("Congestive heart disease");
        list.add("Chorea");
        list.add("Glaucoma");


        for (int i = 0; i < list.size(); i++) {
            checkBox = new CheckBox(this.getApplicationContext());
            checkBox.setId(i);
            checkBox.setText(list.get(i));
            checkBox.setPaddingRelative(10,5,30,5);
            //checkBox.setGravity(Gravity.CENTER_VERTICAL);
            checkBox.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            checkBox.setTextSize(25);
            checkBox.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        listOfId.add(compoundButton.getId());
                    }
                    else {
                        listOfId.remove((Object)compoundButton.getId());
                    }
                }
            });
            LL_symptoms.addView(checkBox);
        }
        submit.setOnClickListener(v->{
            Toast.makeText(this, listOfId.toString(), Toast.LENGTH_SHORT).show();
            // TODO all ids of symptoms added in to listofId
            Intent i = new Intent(this.getApplicationContext(),Remedies_result.class);
            startActivity(i);
        });
    }
}