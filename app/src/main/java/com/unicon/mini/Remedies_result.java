package com.unicon.mini;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Remedies_result extends AppCompatActivity {
    
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef;

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
    }

}