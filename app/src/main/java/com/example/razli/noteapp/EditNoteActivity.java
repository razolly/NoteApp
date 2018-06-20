package com.example.razli.noteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;

public class EditNoteActivity extends AppCompatActivity {

    private static final String TAG = "EditNoteActivity";
    String mNoteString;
    int mNoteIndex;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        editText = findViewById(R.id.editText);

        mNoteString = getIntent().getStringExtra("noteString");
        mNoteIndex = getIntent().getIntExtra("noteIndex", 0);

        editText.setText(mNoteString);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.i(TAG, "onKeyDown: Back button pressed");

            // Update the ArrayList so that the ListView is updated with any edited text
            MainActivity.mNotes.set(mNoteIndex, editText.getText().toString());
            MainActivity.arrayAdapter.notifyDataSetChanged();

            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
