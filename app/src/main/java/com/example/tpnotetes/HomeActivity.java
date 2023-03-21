package com.example.tpnotetes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    String noteAdded="";
    ArrayList<Note>notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recyclerView);
        notes=new ArrayList<>();
        notes.add(new Note("This is the first note bla bla bla bla bla bla bla bla ", new Date(System.currentTimeMillis())));
        notes.add(new Note("This is the Second note bla bla bla bla bla bla bla bla ", new Date(System.currentTimeMillis())));
        notes.add(new Note("This is the Thrid note bla bla bla bla bla bla bla bla bla ", new Date(System.currentTimeMillis())));
        NoteRecyclerViewAdapter adapter= new NoteRecyclerViewAdapter(this);
        adapter.setNotes(notes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.manu_notes, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuIconId) {
            ShowDialogBox();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void ShowDialogBox (){
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_layout, null);
        alert.setView(mView);
        EditText editText = mView.findViewById(R.id.edtTxtNote);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCancelable(false);

        mView.findViewById(R.id.btnAddNote).setOnClickListener(v -> {
            noteAdded=editText.getText().toString();
            System.out.printf(noteAdded);
            notes.add(new Note(noteAdded, new Date(System.currentTimeMillis())));
            NoteRecyclerViewAdapter adapter= new NoteRecyclerViewAdapter(this);
            adapter.setNotes(notes);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            Toast.makeText(this, "The Note is "+editText.getText().toString(), Toast.LENGTH_SHORT).show();
            alertDialog.dismiss();
        });
        mView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

    }
}