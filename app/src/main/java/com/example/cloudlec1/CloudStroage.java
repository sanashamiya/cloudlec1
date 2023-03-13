package com.example.cloudlec1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CloudStroage extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView imageview;
     Button addimage;
    Button uploadingimg;
    Uri imageUri;

    ProgressBar ProgressBar;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_stroage);
        imageview = findViewById(R.id.imageview);
        addimage = findViewById(R.id.btn_add_image);
        uploadingimg = findViewById(R.id.btn_upload);
        ProgressBar =  findViewById(R.id.progressBar);

        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            selectImage();
            }
        });

        uploadingimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                upload();
                ProgressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    public void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==100 && data!=null && data.getData()!= null){
            imageUri = data.getData();
            imageview.setImageURI(imageUri);
        }
    }

    public void upload(){


        storageReference = FirebaseStorage.getInstance().getReference("image/");
        storageReference.putFile(imageUri)
        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageview.setImageURI(null);
                Toast.makeText(CloudStroage.this, "UPLOAD", Toast.LENGTH_SHORT).show();
                ProgressBar.setVisibility(View.INVISIBLE);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CloudStroage.this, "UPLOAD Faild", Toast.LENGTH_SHORT).show();
//
                    }
                });
    }
}