package com.unicon.mini;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Remedies_result extends AppCompatActivity {
    
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef;
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
        ArrayList<String> plants = ArrayList<String>(getData(disase));
        //TODO connect with the UI
    }


    //function to get data from firebase
    private ArrayList<String> getData(String disease){
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = firebaseDatabase.getReference('diseaseDB');
        dbRef.child(String.valueOf(disease)).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>(){
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task){
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        try{
                            DataSnapshot dataSnapshot = task.getResult();
                            ArrayList<String> plants = new ArrayList<String>();
                            for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                    plants.add(snapshot.getValue().toString());
                            }
                            Log.e("data from DB:",plants);
                            return plants;


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