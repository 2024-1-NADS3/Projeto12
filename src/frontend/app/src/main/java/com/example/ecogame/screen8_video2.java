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

public class screen8_video2 extends AppCompatActivity {

    //Create Object
    VideoView videoView;
    MediaController mycontroller;
    Button botaoContinuar5;
    Button buttonBackS8Video1;
    private ImageButton imageButtonS8Video1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen8_video2);

        videoView = (VideoView) findViewById(R.id.videoViewS8Video2);
        mycontroller=new MediaController(this);
        mycontroller.setAnchorView(videoView);
        videoView.setMediaController(mycontroller);
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video2));

        //Start video
        videoView.start();

        botaoContinuar5 = findViewById(R.id.buttonGoS8video2);
        botaoContinuar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_video2.this, screen8_game2.class);
                startActivity(in);
            }
        });

        //Return to the last screen
        buttonBackS8Video1 = findViewById(R.id.buttonBackS8Video2);
        buttonBackS8Video1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_video2.this, screen8_game2.class);
                startActivity(in);
            }
        });
        //Return to the beginning of the level/stage
        imageButtonS8Video1 = findViewById(R.id.imageButtonS8Video2);
        imageButtonS8Video1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_video2.this, screen7_endstep.class);
                startActivity(in);
            }
        });
    }
}