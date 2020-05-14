package com.example.gamefish;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toast.makeText(getApplicationContext(),"MADE BY :  PRASHANT KUMAR",Toast.LENGTH_LONG).show();
        Thread thread =new Thread(){
            @Override
            public void run ()
            {
                try
                {

                    sleep(4000);
                    //Toast.makeText(getApplicationContext(),"MADE BY "+R.string.madeby,Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {


                    e.printStackTrace();
                }
                finally {
                    //Toast.makeText(getApplicationContext(),"MADE BY "+R.string.madeby,Toast.LENGTH_SHORT).show();
                    Intent gameStart= new Intent(SplashActivity.this,GameStartActivity.class);
                    startActivity(gameStart);
                }

            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();

    }
}
