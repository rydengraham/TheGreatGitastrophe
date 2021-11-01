package com.example.cmput_301_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity {

    TextView textView;
    TextView progValue;
    private int prog = 0;
    ProgressBar progressBar;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        progressBar= findViewById(R.id.progress_bar);
        Button updateButton= findViewById(R.id.updateButton);
        Button friendButton= findViewById(R.id.friendbutton);
        textView= findViewById(R.id.btName);
        progValue= findViewById(R.id.prg_value);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prog <= 90){
                    prog += 10;
                    updateProgress();
                }

            }
        });

        friendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prog > 0){
                    prog  -= 10;
                    updateProgress();

                }

            }
        });

    }
    public void updateProgress()
    {
        progressBar.setProgress(prog);
        progValue.setText(prog + "%");

    }




}
