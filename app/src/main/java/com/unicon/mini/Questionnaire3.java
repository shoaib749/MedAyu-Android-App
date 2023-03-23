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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        ArrayList<String> check = new ArrayList<>();
        submit = findViewById(R.id.submitSymp3);

        // //TODO add data in to this list
        // list.add("null");
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
        list = (ArrayList<String>)getIntent().getSerializableExtra("symptoms_db");

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
//            Toast.makeText(this, listOfId.toString(), Toast.LENGTH_SHORT).show();
            // TODO all ids of symptoms added in to listofId
            String[] symptomsArray = check.toArray(new String[check.size()]);
            apirequest(symptomsArray);
        });
    }
    private void apirequest(String[] symptoms){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
        "https://web-production-aeed.up.railway.app/disease",
        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    ArrayList<String> resultList = new ArrayList<String>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        resultList.add(jsonArray.getString(i));
                    }
                    Intent i = new Intent(getApplicationContext(), Questionnaire3.class);
                    i.putExtra("disease", resultList);
                    startActivity(i);


                } catch (Exception error) {
                    Toast.makeText(getApplicationContext(), "errorInJSON:" + error, Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"error"+error,Toast.LENGTH_LONG).show();
                    }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("syptoms", String.valueOf(symptoms));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}