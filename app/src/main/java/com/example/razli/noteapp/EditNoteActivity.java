package com.example.razli.noteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {

    String mNoteString;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        editText = findViewById(R.id.editText);

        mNoteString = getIntent().getStringExtra("noteString");
        editText.setText(mNoteString);
    }
}
