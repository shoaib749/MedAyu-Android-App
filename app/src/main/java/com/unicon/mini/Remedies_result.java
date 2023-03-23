package com.unicon.mini;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Remedies_result extends AppCompatActivity {
    TextView TV_disease_name;
    ListView LL_remedies;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedies_result);
        TV_disease_name = findViewById(R.id.TV_disease_name);
        LL_remedies =  findViewById(R.id.list_view);
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> description = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();

        names.add("jsdkjfd");
        names.add("jsdkjfd");
        names.add("jsdkjfd");
        names.add("jsdkjfd");
        names.add("jsdkjfd");
        names.add("jsdkjfd");
        names.add("jsdkjfd");
        names.add("jsdkjfd");
        names.add("jsdkjfd");
        names.add("jsdkjfd");

        description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");

        String uri= "https://static8.depositphotos.com/1002932/796/i/950/depositphotos_7964682-stock-photo-green-leaf.jpg";
        for(int i=0;i<10;i++){
            images.add(uri);
        }

        LL_remedies.setAdapter(new CustomListAdapter(this.getApplicationContext(),names,description,images));

        LL_remedies.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(this, names.get(i), Toast.LENGTH_SHORT).show();
        });
    }
}