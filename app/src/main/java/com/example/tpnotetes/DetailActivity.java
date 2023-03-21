package com.example.tpnotetes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    private TextView detailTextView,dateTextView;

    TextToSpeech textToSpeech;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailTextView=findViewById(R.id.txtDetailNote);
        dateTextView = findViewById(R.id.txtDate);


        Intent intent = getIntent();
        String detailText = intent.getStringExtra("noteDetail");
        String date = intent.getStringExtra("date");


        detailTextView.setText(detailText);
        dateTextView.setText(date);


        imageView =findViewById(R.id.speech);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        textToSpeech = new TextToSpeech(getApplicationContext(), i -> {
            // if No error is found then only it will run
            if(i!=TextToSpeech.ERROR){
                // To Choose language of speech
                textToSpeech.setLanguage(Locale.UK);
            }
        });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textToSpeech.speak(detailTextView.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
                }
            });
    }
}