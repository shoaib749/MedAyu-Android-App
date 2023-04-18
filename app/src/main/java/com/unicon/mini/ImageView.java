package com.unicon.mini;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ImageView extends AppCompatActivity {
    private Button Bresult;
    private android.widget.ImageView imageView;
    Bitmap bitmap,thumbnail;
    String encodeImageString;
    String image_path;
    Bitmap photo;
    int code;
    //url for image picker
    Uri filepath;
    String download;
    String result;
    Long tsLong = System.currentTimeMillis()/1000;
    String ts = tsLong.toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        Bresult = findViewById(R.id.B_result);
        imageView = findViewById(R.id.IVimage);

        //taking intents data from previous activity
        Intent intent = getIntent();
        code = intent.getIntExtra("code",0);
        if(code == 0){
            getPermission();
        }else if(code == 1){
            getPermission_camera();
        }

        //Result button click activity
        Bresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFire();
            }
        });
    }
    private void getPermission() {
        Dexter.withActivity(ImageView.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response)
                    {
                        Intent intent=new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"Browse Image"),1);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }

                }).check();
    }

    private void encodeBitmapImage(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytesofimage=byteArrayOutputStream.toByteArray();
        encodeImageString=android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);
    }
    //camera functions and permission
    private void getPermission_camera() {
        Dexter.withActivity(ImageView.this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response)
                    {
                        //if permission given opening camera
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,2);
                    }
                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
//                        Toast.makeText(MainActivity.this, "Permission not Granted", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode==1)
            {
                filepath=data.getData();
                try
                {
                    Context applicationContext = getApplicationContext();
                    InputStream inputStream=applicationContext.getContentResolver().openInputStream(filepath);
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    imageView.setImageBitmap(bitmap);
                    encodeBitmapImage(bitmap);
                }catch (Exception ex)
                {

                }
            }
            else if(requestCode==2){
                try{
                    photo = (Bitmap) data.getExtras().get("data");
                    imageView.setImageBitmap(photo);
                }catch (Exception e){
                    Log.e("error Camera",e.toString());
                    Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_LONG).show();
                }
            }
        }

    }


    //code for uploading image to firebase
    private void uploadFire(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Processing...");
        progressDialog.show();


        //testing code for uploading thorught bitmap
        //// Get the data from an ImageView as bytes
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploder = storage.getReference().child("UserImage").child(ts);
        uploder.putBytes(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        final int[] flag = {0};
                        Toast.makeText(getApplicationContext(),"Image Uploaded",Toast.LENGTH_SHORT).show();
                        uploder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override

                            public void onSuccess(Uri uri) {
                                download = uri.toString();
                                Toast.makeText(getApplicationContext(),download,Toast.LENGTH_LONG).show();
                                flag[0] =  apirequest(download);
                            }
                        });
                        if(flag[0] == 1){
                            progressDialog.dismiss();
                        }

                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                    }
                });
    }
    //code for api request
    private int apirequest(String download){
        int flag = 1;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://192.168.43.89:5000/class",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Dialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            result = jsonObject.getString("class");
                            Log.e("result", result);
                            savetoDB(result,download);
                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                            int flag = 1;
                            Intent intent = new Intent(ImageView.this,result.class);
                            intent.putExtra("result",result);
                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"error"+e,Toast.LENGTH_LONG).show();
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
                Log.e("down",download);
                params.put("test_url",download);
                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        requestQueue.add(stringRequest);
        return flag;
    }

    public void savetoDB(String result,String download){
        FirebaseDatabase firebaseDatabase;
        DatabaseReference dbRef;
        DataHolder holder = new DataHolder(result, download);
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbRef = firebaseDatabase.getReference("userDB").child(ts);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dbRef.setValue(holder);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), " used DB Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

}