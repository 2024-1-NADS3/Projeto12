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

public class screen8_video3 extends AppCompatActivity {

    //Create Object
    VideoView videoView;
    MediaController mycontroller;
    Button botaoContinuar5;
    Button buttonBackS8Video3;
    private ImageButton imageButtonS8Video3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen8_video3);

        videoView = (VideoView) findViewById(R.id.videoViewS8Video3);
        mycontroller=new MediaController(this);
        mycontroller.setAnchorView(videoView);
        videoView.setMediaController(mycontroller);
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video3));

        //Start video
        videoView.start();

        botaoContinuar5 = findViewById(R.id.buttonGoS8Video3);
        botaoContinuar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_video3.this, screen8_game3.class);
                startActivity(in);
            }
        });

        //Return to the beginning of the level/stage
        imageButtonS8Video3 = findViewById(R.id.imageButtonS8Video3);
        imageButtonS8Video3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_video3.this, screen8_game3.class);
                startActivity(in);
            }
        });
        //Return to the last screen
        buttonBackS8Video3 = findViewById(R.id.buttonBackS8Video3);
        buttonBackS8Video3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_video3.this, screen7_endstep.class);
                startActivity(in);
            }
        });
    }
}