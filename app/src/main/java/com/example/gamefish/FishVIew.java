package com.example.gamefish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FishVIew extends View {
    MediaPlayer mediaPlayer;
    private Bitmap fish[] = new Bitmap[2];
    private int fishx = 10;
    private int fishy;
    private int fishspeed;
    private int canvaswidth, canvasHeight;


    private int yellowx, yellowy, yellowspeed = 15;
    private Paint yellowpaint = new Paint();

    private int blackx, blacky, blackspeed = 17;
    private Paint blackpaint = new Paint();

    private int greenx, greeny, greenspeed = 19;
    private Paint greenpaint = new Paint();

    private int redx, redy, redspeed = 30;
    private Paint redpaint = new Paint();

    private Paint linepaint = new Paint();


    private int score, lifecount;
    private int ycount = 0, gcount = 0, bcount = 0;

    private boolean touch = false;
    private Bitmap backgroundImage;
    private Paint scorePaint = new Paint();
    private Bitmap life[] = new Bitmap[2];

    public FishVIew(Context context) {
        super(context);
        mediaPlayer=MediaPlayer.create(getContext(),R.raw.music);
        mediaPlayer.start();
        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);


        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.backtwof);

        yellowpaint.setColor(Color.YELLOW);
        yellowpaint.setAntiAlias(false);

        blackpaint.setColor(Color.BLACK);
        blackpaint.setAntiAlias(false);

        greenpaint.setColor(Color.GREEN);
        greenpaint.setAntiAlias(false);

        redpaint.setColor(Color.RED);
        redpaint.setAntiAlias(false);

        linepaint.setColor(Color.BLUE);
        linepaint.setAntiAlias(false);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);

        fishy = 550;
        score = 0;
        lifecount = 3;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvaswidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(backgroundImage, 0, 0, null);

        int minFishy = fish[0].getHeight();
        int maxFishy = canvasHeight - fish[0].getHeight() * 3;

        fishy = fishy + fishspeed;
        if (fishy < minFishy) {
            fishy = minFishy;
        }
        if (fishy > maxFishy) {
            fishy = maxFishy;
        }

        fishspeed = fishspeed + 2;
        if (touch) {
            canvas.drawBitmap(fish[1], fishx, fishy, null);
            touch = false;
        } else {

            canvas.drawBitmap(fish[0], fishx, fishy, null);
        }

        yellowx = yellowx - yellowspeed;

        if (hitBallChecker(yellowx, yellowy)) {
            ycount++;
            score = score + 10;
            yellowx = -100;
        }

        if (yellowx < 0) {
            yellowx = canvaswidth + 21;
            yellowy = (int) Math.floor(Math.random() * (maxFishy - minFishy)) + minFishy;

        }
        canvas.drawCircle(yellowx, yellowy, 30, yellowpaint);


        blackx = blackx - blackspeed;

        if (hitBallChecker(blackx, blacky)) {
            bcount++;
            score = score + 15;
            blackx = -100;
        }

        if (blackx < 0) {
            blackx = canvaswidth + 21;
            blacky = (int) Math.floor(Math.random() * (maxFishy - minFishy)) + minFishy;

        }

        canvas.drawCircle(blackx, blacky, 35, blackpaint);


        greenx = greenx - greenspeed;

        if (hitBallChecker(greenx, greeny)) {

            gcount++;
            score = score + 20;
            greenx = -100;
        }

        if (greenx < 0) {
            greenx = canvaswidth + 21;
            greeny = (int) Math.floor(Math.random() * (maxFishy - minFishy)) + minFishy;

        }
        canvas.drawCircle(greenx, greeny, 40, greenpaint);

        if(score >= 200 && score < 300)
        {
            redspeed=35;
        } else if (score >=300 && score <400) {
            redspeed=40;
            //Toast.makeText(getContext(), "LEVEL UP : 2", Toast.LENGTH_SHORT).show();

        }
        else if(score >= 400 && score < 500){
            redspeed = 45;
        }
        else if (score >=500){
          //  Toast.makeText(getContext(), "LEVEL UP : 3", Toast.LENGTH_SHORT).show();
            redspeed=55;
        }
        else{
            redspeed=30;
        }

        redx = redx - redspeed;

        if (hitBallChecker(redx, redy)) {

            redx = -100;
            lifecount--;
            if (lifecount <= 0) {
                startIntent();
            }

        }

        if (redx < 0) {
            redx = canvaswidth + 21;
            redy = (int) Math.floor(Math.random() * (maxFishy - minFishy)) + minFishy;

        }
        canvas.drawCircle(redx, redy, 45, redpaint);


        canvas.drawText("Score : " + score, 20, 100, scorePaint);
        canvas.drawText("Life : "+lifecount, 440, 100, scorePaint);



       /* for (int i = 0; i < 3; i++) {
            int x = (int) (520 + life[0].getWidth() * 1.5 * i);
            int y = 30;

            if (i < lifecount) {
                canvas.drawText("Score : " + score, 20, 100, scorePaint);
                canvas.drawBitmap(life[0], x, y, null);
            } else {
                canvas.drawBitmap(life[1], x, y, null);
            }


        }*/

        canvas.drawLine(0, 1860, 1080, 1860, linepaint);


    }


    public boolean hitBallChecker(int x, int y) {
//        if (score >200 && lifecount <3)
//        {
//            lifecount++;
//        }
        if (fishx < x && x < (fishx + fish[0].getWidth()) && fishy < y && y < (fishy + fish[0].getHeight())) {
            return true;
        }

        return false;
    }

    public void startIntent() {
        mediaPlayer.stop();
        mediaPlayer.release();
        Toast.makeText(getContext(), "GAME OVER", Toast.LENGTH_SHORT).show();
        Intent gemeoverActivity = new Intent(getContext(), GameOverActivity.class);
        gemeoverActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        gemeoverActivity.putExtra("score", score);
        gemeoverActivity.putExtra("black", bcount);
        gemeoverActivity.putExtra("yellow", ycount);
        gemeoverActivity.putExtra("green", gcount);
        getContext().startActivity(gemeoverActivity);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (fishy > 1720)
                startIntent();
            touch = true;
            fishspeed = -30;

        }
        return true;
    }
}
