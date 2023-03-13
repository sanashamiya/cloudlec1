package com.example.cloudlec1.NoteApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cloudlec1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddNote extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText titeliput;
    EditText descriptioninput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

         titeliput = findViewById(R.id.titleinput);
         descriptioninput = findViewById(R.id.descriptioninput);
        MaterialButton saveButton = findViewById(R.id.savebtn);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(HomeNote.this,AddNote.class));
            }
        });
    }
    public void saveToFirebase(View v) {

        String titel = titeliput.getText().toString();
        String desc = descriptioninput.getText().toString();
        Map<String, Object> Notes = new HashMap<>();
        if (!titel.isEmpty()) {
            Notes.put("Titel", titel);
            db.collection("Note")
                    .add(Notes)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                              @Override
                                              public void onSuccess(DocumentReference documentReference) {
                                                  openActivity2();
                                                  Log.e("TAG", "Data added successfully to database");
                                              }

                                          }
                    )

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("TAG", "Failed to add database");

                        }
                    });

        } else {
            Toast.makeText(this, "Please Fill fields", Toast.LENGTH_SHORT).show();
        }

    }
    public void openActivity2() {
        Intent intent = new Intent(this, HomeNote.class);
        startActivity(intent);
    }
}