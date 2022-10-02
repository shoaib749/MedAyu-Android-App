package com.unicon.mini;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity {
    private Button BCamera,BUpload;
    private int code = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BCamera = findViewById(R.id.B_camera);
        BUpload = findViewById(R.id.B_upload);

        BUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code = 0;
               fun_intent();
            }
        });
        BCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code = 1;
                fun_intent();
            }
        });
    }
    private void fun_intent(){
        Intent intent = new Intent(MainActivity.this,ImageView.class);
        intent.putExtra("code",code);
        startActivity(intent);
    }
}