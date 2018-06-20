package com.example.razli.noteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView noteListView;
    static ArrayList<String> mNotes;
    static ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteListView = findViewById(R.id.noteListView);

        mNotes = new ArrayList<String>();
        mNotes.add("Here is an Example Note");

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mNotes);
        noteListView.setAdapter(arrayAdapter);
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start new activity
                Intent intent = new Intent(getApplicationContext(), EditNoteActivity.class);

                // Put extra that holds String of note
                intent.putExtra("noteString", mNotes.get(position));
                intent.putExtra("noteIndex", position);

                startActivity(intent);
            }
        });
    }
}
