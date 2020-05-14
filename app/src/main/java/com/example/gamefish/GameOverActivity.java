package com.example.gamefish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    private Button strtagain;
    private TextView disscr,black_ball_count,green_ball_count,yellow_ball_count;
    private String score,bscore,yscore,gscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        score=getIntent().getExtras().get("score").toString();
        bscore=getIntent().getExtras().get("black").toString();
        yscore=getIntent().getExtras().get("yellow").toString();
        gscore=getIntent().getExtras().get("green").toString();

        strtagain=findViewById(R.id.play_button);
        disscr=findViewById(R.id.score_textview);
        black_ball_count=findViewById(R.id.score_black);
        green_ball_count=findViewById(R.id.score_green);
        yellow_ball_count=findViewById(R.id.score_yellow);

        strtagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainAct= new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(mainAct);
            }
        });

        disscr.setText(" Score  " +score );
        black_ball_count.setText("black  ball  "+bscore);
        yellow_ball_count.setText(" yellow ball "+yscore);
        green_ball_count.setText("green  ball  "+gscore);
    }
}
