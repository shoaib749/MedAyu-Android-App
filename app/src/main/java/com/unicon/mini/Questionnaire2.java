package com.unicon.mini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Questionnaire2 extends AppCompatActivity {
    LinearLayout LL_symptoms;
    CheckBox checkBox;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire2);
        LL_symptoms = findViewById(R.id.LL_symptoms);
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> check = new ArrayList<>();
        submit = findViewById(R.id.submitSymp2);

        //TODO add data in to this list
        // list.add("null");
        // list.add("Congestive heart disease");
        // list.add("Chorea");
        // list.add("Glaucoma");
        // list.add("Congestive heart disease");
        // list.add("Chorea");
        // list.add("Glaucoma");
        // list.add("Congestive heart disease");
        // list.add("Chorea");
        // list.add("Glaucoma");
        // list.add("Congestive heart disease");
        // list.add("Chorea");
        // list.add("Glaucoma");
        // list.add("Congestive heart disease");
        // list.add("Chorea");
        // list.add("Glaucoma");list.add("Congestive heart disease");
        // list.add("Chorea");
        // list.add("Glaucoma");list.add("Congestive heart disease");
        // list.add("Chorea");
        // list.add("Glaucoma");
        // list.add("Congestive heart disease");
        // list.add("Chorea");
        // list.add("Glaucoma");list.add("Congestive heart disease");
        // list.add("Chorea");
        // list.add("Glaucoma");
        list = (ArrayList<String>)getIntent().getSerializableExtra("symptoms");
            
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
                    String value = String.valueOf(compoundButton.getText()).trim();
                    if(b){
                        check.add(String.valueOf(compoundButton.getText()).trim());
                    }
                    else {
                        check.remove(String.valueOf(compoundButton.getText()).trim());
                    }
                }
            });
            LL_symptoms.addView(checkBox);
        }
        submit.setOnClickListener(v->{
            Toast.makeText(this, listOfId.toString(), Toast.LENGTH_SHORT).show();
            // TODO all ids of symptoms added in to listofId
            String[] symptomsArray = check.toArray(new String[check.size()]);
            apirequest(symptomsArray);

            
        });
    }
    private void apirequest(String symptoms){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
        "https://web-production-aeed.up.railway.app/db",
        new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try{    
                    JSONObject jsonObject = new JSONObject(response);
                    result = jsonObject.getString("result");
                    ObjectMapper mapper = new ObjectMapper();
                    List<String> resultList = mapper.readValue(result, new TypeReference<List<String>>(){});
                    Intent i = new Intent(this.getApplicationContext(),Questionnaire3.class);
                    intent.putExtra("symptoms_db",resultList);
                    startActivity(i);
                    
                    

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
                params.put("request",symptoms)
                return params;
            }
        }
    }

}