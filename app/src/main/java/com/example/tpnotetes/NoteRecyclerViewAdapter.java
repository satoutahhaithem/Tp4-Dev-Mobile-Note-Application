package com.example.tpnotetes;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Note> notes =new ArrayList<>();

    public NoteRecyclerViewAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public NoteRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list_item,parent,false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.txtNote.setText(notes.get(position).getNote());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("noteDetail",notes.get(position).getNote());
                intent.putExtra("date",notes.get(position).getDate().toString());
                context.startActivity(intent);
            }
        });
        holder.rubish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes.remove(position);
                notifyItemRemoved(position);
                setNotes(notes);
            }
        });

        holder.editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(holder.itemView.getContext(),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNote;
        private CardView parent;
        private ImageView rubish;
        private ImageView editItem;
        private Button btnModify,btnCancel;
        private EditText editTextNoteModified;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNote =itemView.findViewById(R.id.txtNote);
            parent =itemView.findViewById(R.id.parent);
            rubish=itemView.findViewById(R.id.rubish);
            editItem=itemView.findViewById(R.id.editItem);
            btnModify=itemView.findViewById(R.id.btnModify);
            editTextNoteModified=itemView.findViewById(R.id.edtTxtNoteModified);
            btnCancel=itemView.findViewById(R.id.btnCancelModify);
        }
    }
    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    private void showDialog(Context context,int position) {
        // Inflate the dialog layout
        View view = LayoutInflater.from(context).inflate(R.layout.activity_dialog_modify, null);

        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        // Get references to the EditText and Button
        String contentEditText=notes.get(position).getNote();
        EditText editText = view.findViewById(R.id.edtTxtNoteModified);
        editText.setText(contentEditText);
        Button modify = view.findViewById(R.id.btnModify);
        Button btnCancel = view.findViewById(R.id.btnCancelModify);

        // Handle button click
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                notes.set(position,new Note(text,new Date(System.currentTimeMillis())));
                setNotes(notes);
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Display the dialog
        dialog.show();
    }
}
