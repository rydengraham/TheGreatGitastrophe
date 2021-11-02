package com.example.cmput_301_project;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity {

    // textview represents text that changes depending on menu item clicked
    TextView textView;
    // progValue is a textview represents the % of progress bar filled out
    TextView progValue;
    // amount of circular progress bar filled out
    private int prog = 0;
    ProgressBar progressBar;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        //initialize circular progress bar
        progressBar= findViewById(R.id.progress_bar);
        // initialize updatebutton and friendbutton
        Button updateButton= findViewById(R.id.updateButton);
        Button friendButton= findViewById(R.id.friendbutton);
        textView= findViewById(R.id.btName);
        progValue= findViewById(R.id.prg_value);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // progress bar can be filled up in 10 point increments
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
    /*method to set the prog value i.e % of progress
    bar filled out */
    public void updateProgress()
    {
        progressBar.setProgress(prog);
        progValue.setText(prog + "%");
    }




}
