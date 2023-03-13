package com.example.cloudlec1.NoteApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cloudlec1.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class Note_card extends AppCompatActivity  {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView Titel;
    TextView Description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_card);

        Titel = findViewById(R.id.Title);
    }
}