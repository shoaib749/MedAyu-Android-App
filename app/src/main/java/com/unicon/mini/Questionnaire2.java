package com.unicon.mini;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        list = (ArrayList<String>)getIntent().getSerializableExtra("symptoms");
        Toast.makeText(getApplicationContext(),list.toString(),Toast.LENGTH_LONG).show();
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
            String symptomsArray = TextUtils.join(",",check);
            Toast.makeText(getApplicationContext(), symptomsArray, Toast.LENGTH_SHORT).show();
            apirequest(symptomsArray,check);
        });
    }
    private void apirequest(String symptoms,ArrayList<String> check){
        //showing progress dailog
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Proessing...");
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
        "https://web-production-aeed.up.railway.app/db",
        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    ArrayList<String> resultList = new ArrayList<String>();
                    for (int i = 0; i < 10; i++) {
                        resultList.add(jsonArray.getString(i));
                    }
                    progressDialog.dismiss();
                    try {
                        Intent i = new Intent(Questionnaire2.this, Questionnaire3.class);
                        i.putExtra("symptoms_db", resultList);
                        i.putExtra("CheckList",check);
                        startActivity(i);
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), "errorInIntent" + e, Toast.LENGTH_LONG).show();
                    }


                } catch (Exception error) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "errorInJSON:" + error, Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"error"+error,Toast.LENGTH_LONG).show();
                    }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String,String>();
                params.put("request", symptoms);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}