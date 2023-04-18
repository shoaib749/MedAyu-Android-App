package com.unicon.mini;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Remedies_result extends AppCompatActivity {
    
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef,plantRef ;
    TextView TV_disease_name;
    ListView LL_remedies;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedies_result);


        //geting Data od disease
        Intent intent = getIntent();
        String disease = intent.getStringExtra("disease");
        //Getting data from firebase
        Toast.makeText(getApplicationContext(),"disease:"+disease,Toast.LENGTH_LONG).show();
        ArrayList<String> plants = new ArrayList<>();
        //TODO connect with the UI




        //fetching data
        getData(disease);
        Toast.makeText(getApplicationContext(), "out" + plants.toString(), Toast.LENGTH_LONG).show();
        Log.e("data out:",plants.toString());
        //fetching data for DB


        // names.add("jsdkjfd");
        // names.add("jsdkjfd");
        // names.add("jsdkjfd");
        // names.add("jsdkjfd");
        // names.add("jsdkjfd");
        // names.add("jsdkjfd");
        // names.add("jsdkjfd");
        // names.add("jsdkjfd");
        // names.add("jsdkjfd");
        // names.add("jsdkjfd");

        // description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        // description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        // description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        // description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        // description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        // description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        // description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        // description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        // description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");
        // description.add("dsjkfhdjkfhsdajhsdafjkhasdjfhsdjfhdsfjhasdjkfhasdfjhsdafjhf");

        // String uri= "https://static8.depositphotos.com/1002932/796/i/950/depositphotos_7964682-stock-photo-green-leaf.jpg";
        // for(int i=0;i<10;i++){
        //     images.add(uri);
        // }


    }


    //function to get data from firebase
    private void getData(String disease){
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = firebaseDatabase.getReference("diseaseDB");
        ArrayList<String> plants = new ArrayList<String>();
        dbRef.child(String.valueOf(disease)).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>(){
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task){
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        try{
                            DataSnapshot dataSnapshot = task.getResult();

                            for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                    plants.add(snapshot.getValue().toString());
                                    setPlants(plants);
                            }
                            Toast.makeText(getApplicationContext(), "Data" + plants.toString(), Toast.LENGTH_LONG).show();
                            Log.e("data from DB:",plants.toString());

                        }catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "error ha" + e, Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "DB data not found", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "DB failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void setPlants(ArrayList<String> plants){

        TV_disease_name = findViewById(R.id.TV_disease_name);
        LL_remedies =  findViewById(R.id.list_view);

        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> description = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();

        for(String plant : plants){
            firebaseDatabase = FirebaseDatabase.getInstance();
            plantRef = firebaseDatabase.getReference("plantDB");
            plantRef.child(String.valueOf(plant)).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task){
                    if(task.isSuccessful()){
                        if(task.getResult().exists()){
                            try{
                                DataSnapshot dataSnapshot = task.getResult();
                                String SName = dataSnapshot.child("Scientific_Name").getValue().toString();
                                String Consume = dataSnapshot.child("Consume").getValue().toString();
                                String treeUri = String.valueOf(dataSnapshot.child("Tree_link").getValue());

                                names.add(SName);
                                description.add(Consume);
                                images.add(treeUri);
                            }catch(Exception e){
                                Toast.makeText(getApplicationContext(), "error ha" + e, Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "DB data not found", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "DB failed", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        LL_remedies.setAdapter(new CustomListAdapter(this.getApplicationContext(),names,description,images));

        LL_remedies.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(this, names.get(i), Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(getApplicationContext(),result.class);
            intent1.putExtra("result",names.get(i));
            startActivity(intent1);
        });
    }

}