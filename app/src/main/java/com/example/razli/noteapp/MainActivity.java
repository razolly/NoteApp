package com.example.razli.noteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ListView noteListView;
    static ArrayList<String> mNotes;
    static ArrayAdapter<String> arrayAdapter;
    int mIndexOfNoteToDelete;

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
                startEditNoteActivity(position);
            }
        });
        noteListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                mIndexOfNoteToDelete = position;

//                // todo: warning dialog should pop up. Note, this crashes the app
//                new AlertDialog.Builder(getApplicationContext())
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .setTitle("Warning")
//                        .setMessage("Are you sure you want to delete?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Delete the listView entry
//                                mNotes.remove(mIndexOfNoteToDelete);
//                                arrayAdapter.notifyDataSetChanged();
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Do nothing
//                            }
//                        })
//                        .show();

                // Delete the listView entry
                mNotes.remove(mIndexOfNoteToDelete);
                arrayAdapter.notifyDataSetChanged();

                Log.i(TAG, "onItemLongClick: long click detected!");
                return true;
            }
        });
    }

    // Note: If this function receives -1 as argument, means that user clicked "Add new note"
    public void startEditNoteActivity(int indexOfNote) {

        Intent intent = new Intent(getApplicationContext(), EditNoteActivity.class);

        // Put extras that hold String of note & Index of note
        if(indexOfNote >= 0) {
            intent.putExtra("noteString", mNotes.get(indexOfNote));
            intent.putExtra("noteIndex", indexOfNote);
        } else {
            intent.putExtra("noteIndex", indexOfNote);
        }

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.side_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.addNewNote:
                // Note: -1 is passed as note does not exist yet
                startEditNoteActivity(-1);
                break;
            default: return false;
        }

        return true;
    }
}
