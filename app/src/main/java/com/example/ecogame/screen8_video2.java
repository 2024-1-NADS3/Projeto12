package com.example.ecogame;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class screen8_video2 extends AppCompatActivity {

    //Create Object
    VideoView videoView;
    MediaController mycontroller;
    Button botaoContinuar5;
    Button botaoVoltar5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen8_video1);

        videoView = (VideoView) findViewById(R.id.videoView1);
        mycontroller=new MediaController(this);
        mycontroller.setAnchorView(videoView);
        videoView.setMediaController(mycontroller);
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.vidro));

        //Start video
        videoView.start();

        botaoContinuar5 = findViewById(R.id.buttonGo5);
        botaoContinuar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_video2.this, screen8_game1.class);
                startActivity(in);
            }
        });

        botaoVoltar5 = findViewById(R.id.buttonBack5);

        botaoVoltar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(screen8_video2.this, screen7_endstep.class);
                startActivity(in);
            }
        });

    }
}