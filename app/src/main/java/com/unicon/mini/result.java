package com.unicon.mini;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class result extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef;
    private TextView TVBName, TVAName, TVCategory, TVRegion, TVCondition, TVSide_Effects, TVConsume, TVConstraints, TVPart, TVVitamins, TVUsed;
    private ImageView IVtree, IVleaf;
    private String searchPlant = "Amaranthus Viridis";
    Handler handler = new Handler();
    ProgressDialog progressDialog;
    String BName, AName, Category, Condition, Consume, Side_Effects, Vitamins, Part, Region;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //taking intent data from qr activity
        Intent intent = getIntent();
        searchPlant = intent.getStringExtra("result");

        TVBName = findViewById(R.id.TVBName);
        TVAName = findViewById(R.id.TVAName);
        TVCategory = findViewById(R.id.cureBody);
        TVCondition = findViewById(R.id.ConditionBody);

        TVConsume = findViewById(R.id.ConsumedFormBody);
        TVPart = findViewById(R.id.PartsBody);
        TVRegion = findViewById(R.id.RegionsBody);
        TVSide_Effects = findViewById(R.id.SideEffectsBody);
        TVVitamins = findViewById(R.id.vitaminsBody);
        IVtree = findViewById(R.id.IVimage);

        getData();
    }

    private void getData() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = firebaseDatabase.getReference("plantDB");
        dbRef.child(String.valueOf(searchPlant)).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        try {
                            DataSnapshot dataSnapshot = task.getResult();
                            BName = dataSnapshot.child("Scientific_Name").getValue().toString();
                            AName = dataSnapshot.child("Comman_Name").getValue().toString();
                            Category = dataSnapshot.child("Category").getValue().toString();

                            try {
//                             Region  =dataSnapshot.child("Region").getValue().toString();

                            } catch (Exception e1) {
                                Toast.makeText(getApplicationContext(), "error C1" + e1, Toast.LENGTH_LONG).show();
                            }
                            Condition = dataSnapshot.child("Condition").getValue().toString();
                            Side_Effects = dataSnapshot.child("Side_Effects").getValue().toString();
                            Consume = dataSnapshot.child("Consume").getValue().toString();
                            Part = dataSnapshot.child("Part").getValue().toString();
                            Vitamins = dataSnapshot.child("Vitamins").getValue().toString();
                            String treeUrl = String.valueOf(dataSnapshot.child("Tree_link").getValue());
                            String leafUrl = String.valueOf(dataSnapshot.child("Leaf_link").getValue());


                            TVBName.setText(BName);
                            TVAName.setText(AName);
                            TVCategory.setText(Category);
                            TVCondition.setText(Condition);
                            TVConsume.setText(Consume);
                            TVPart.setText(Part);
//                            TVRegion.setText(treeUrl);
                            TVSide_Effects.setText(Side_Effects);
                            TVVitamins.setText(Vitamins);
                            Glide.with(getApplicationContext())
                                    .load(treeUrl)
                                    .into(IVtree);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "error ha" + e, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "DB data not found", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "DB failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}