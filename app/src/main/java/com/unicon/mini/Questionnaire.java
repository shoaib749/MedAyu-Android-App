package com.unicon.mini;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Questionnaire extends AppCompatActivity {

    Button B_submit,B_add;
    LinearLayout layout;
    EditText ET_symptoms;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare);
        B_submit = findViewById(R.id.B_submit);
        B_add = findViewById(R.id.B_add);
//        ET_symptoms = findViewById(R.id.ET_symptoms);
        layout = findViewById(R.id.LL_symptoms);
        Context context = this.getApplicationContext();

//        start of testing code for dynamic rendering of EditText
//        ET_symptoms.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                //no  need to be used
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                //Check if the existing EditText is not empty
//                if(!TextUtils.isEmpty(charSequence)){
//                    //create a new EditText
//                    EditText newEditText = new EditText(getApplicationContext());
//                    newEditText.setLayoutParams(new LinearLayout.LayoutParams(
//                            LinearLayout.LayoutParams.MATCH_PARENT,
//                            LinearLayout.LayoutParams.WRAP_CONTENT
//                    ));
//                    newEditText.setHint("Symptoms");
//                    newEditText.setPadding(5,5,5,5);
//                    newEditText.setBackground(Drawable.createFromPath("@drawable/round_edit_text"));
//                    newEditText.setTextSize(20);
//                    newEditText.setInputType(InputType.TYPE_CLASS_TEXT);
//                    newEditText.setEms(10);
//                    newEditText.setMaxHeight(50);
//                    newEditText.setTextColor(Color.BLACK);
//                    //adding to linear layout
//                    layout.addView(newEditText);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                //no need
//            }
//        });
//        End of testing EditText

        //start for add button
        B_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = new EditText(getApplicationContext());
                editText.setId(View.generateViewId());
                editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
                editText.setPadding(padding, padding, padding, padding);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editText.setEms(10);
                editText.setHint("Enter Symptoms");
                // Set the background drawable
                Drawable backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.round_edit_text);
                editText.setBackground(backgroundDrawable);
                // Set layout params
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics()));
                params.bottomMargin = 16;
                params.topMargin = 16;
                params.height = 125;
//                editText.setLayoutParams(new LinearLayout.LayoutParams(
//                            LinearLayout.LayoutParams.MATCH_PARENT,
//                            LinearLayout.LayoutParams.WRAP_CONTENT
//                    ));
//                ConstraintLayout.LayoutParams constraintLayoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                constraintLayoutParams.bottomToBottom = R.id.parentView;
//                constraintLayoutParams.horizontalBias = 0.5f;
//                constraintLayoutParams.startToStart = R.id.parentView;
//                Set the layout params for the EditText
                editText.setLayoutParams(params);
//                editText.setLayoutParams(constraintLayoutParams);
                layout.addView(editText);
                i++;
            }
        });
        //end for add button
//        B_submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String symp = String.valueOf(ET_symptoms.getText()).trim();
//                if(symp.equals("")){
//                    Toast.makeText(context, "Please enter diseases", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(context, symp, Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(context, Questionnaire2.class);
//                    startActivity(intent);
//                }
//            }
//        });

    }


}