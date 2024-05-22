package com.example.ecogame;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class screen8_video4 extends AppCompatActivity {

    //Create Object
    VideoView videoView;
    MediaController mycontroller;
    Button botaoContinuar5;
    Button buttonBackS8Video4;

    private ImageButton imageButtonS8Video4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen8_video4);

        videoView = (VideoView) findViewById(R.id.videoViewS8Video4);
        mycontroller=new MediaController(this);
        mycontroller.setAnchorView(videoView);
        videoView.setMediaController(mycontroller);
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video4));

        //Start video
        videoView.start();

        botaoContinuar5 = findViewById(R.id.buttonGoS8Video4);
        botaoContinuar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_video4.this, screen8_game4.class);
                startActivity(in);
            }
        });

        //Return to the last screen
        buttonBackS8Video4 = findViewById(R.id.buttonBackS8Video4);
        buttonBackS8Video4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_video4.this, screen8_game4.class);
                startActivity(in);
            }
        });
        //Return to the beginning of the level/stage
        imageButtonS8Video4 = findViewById(R.id.imageButtonS8Video4);
        imageButtonS8Video4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_video4.this, screen7_endstep.class);
                startActivity(in);
            }
        });
    }
}