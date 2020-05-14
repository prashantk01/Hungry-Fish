package com.example.gamefish;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameStartActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button startb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        startb=findViewById(R.id.start_button);
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.honey);
        mediaPlayer.start();
        startb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                Intent mainAct= new Intent(GameStartActivity.this,MainActivity.class);
                startActivity(mainAct);
            }
        });
    }
}
