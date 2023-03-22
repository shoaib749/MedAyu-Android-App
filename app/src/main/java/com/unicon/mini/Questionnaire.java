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
    ArrayList<String> symptoms = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare);
        B_submit = findViewById(R.id.B_submit);
        B_add = findViewById(R.id.B_add);
        layout = findViewById(R.id.LL_symptoms);
        Context context = this.getApplicationContext();

    

        //start for add button
        B_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = new EditText(getApplicationContext());
                editText.setId(i);
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

                editText.setLayoutParams(params);
//                editText.setLayoutParams(constraintLayoutParams);
                layout.addView(editText);
                i++;
            }
        });
        // end for add button
       B_submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                //getting symptoms text
                for(int j=0;j<i;j++){
                    EditText newEditText;
                    newEditText = findViewById(R.id.j);
                    String symp = String.valueOf(newEditText.getText()).trim();
                    if(symp.equals("")){
                        Toast.makeText(context, "Please enter diseases", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(context, symp, Toast.LENGTH_SHORT).show();
                        symptoms.add(symp);
                       
                    }
                }
                //sending data throw api
                String[] symptomsArray = symptoms.toArray(new String[symptoms.size()]);
                apirequest(symptomsArray);

               
           }
       });

    }
    private void apirequest(String[] symptoms){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
        "https://web-production-aeed.up.railway.app/EnterSymptoms",
        new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try{    
                    JSONObject jsonObject = new JSONObject(response);
                    result = jsonObject.getString("result");
                    ObjectMapper mapper = new ObjectMapper();
                    List<String> resultList = mapper.readValue(result, new TypeReference<List<String>>(){});
                    Intent intent = new Intent(getApplicationContext(), Questionnaire2.class);
                    intent.putExtra("symptoms",resultList);
                    startActivity(intent);

                }catch(Exception error){
                    Toast.makeText(getApplicationContext(),"errorInJSON:"+error,Toast.LENGTH_LONG).show();
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErroResponse(VolleyError Error){
                    Toast.makeText(getApplicationContext(),"errorInApiRequest:"+Error,Toast.LENGTH_LONG).show();
                }
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("user_symtoms",symptoms)
                return params;
            }
        }
    }


}