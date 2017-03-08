package com.example.ameym.androidqrcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    TextView result, txtTimer, txtHint;
    int score;
    CountDownTimer countDownTimer;
    boolean canGoBack=false;
    boolean timerFinished=false;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        Intent intent = getIntent();
        intent.getExtras();
        String scoreRe = intent.getStringExtra("score");
        result = (TextView) findViewById(R.id.text_result);
        txtTimer = (TextView) findViewById(R.id.timer);

        result.setText(scoreRe);
        score = Integer.parseInt(scoreRe);
        if (score < 10) {
            txtTimer.setText("180");
            countDownTimer =
                    new CountDownTimer(18000, 1000) {
                        @Override
                        public void onTick(long l) {
                            txtTimer.setText("Hint will appear in \n" + l / 1000);
                        }

                        @Override
                        public void onFinish() {
                            timerFinished=true;
                            txtTimer.setTextSize(25);
                            txtTimer.setText("Congratulations for successfully completing round 1! it's time to have some fun with the balloons.\n Ask the volunteer to learn more.");
                        }
                    };
            countDownTimer.start();
        } else if (score < 15) {

            txtTimer.setText("120");
            countDownTimer =
                    new CountDownTimer(121000, 1000) {
                        @Override
                        public void onTick(long l) {
                            txtTimer.setText("Hint will appear in\n" + l / 1000);
                        }

                        @Override
                        public void onFinish() {
                            timerFinished=true;
                            txtTimer.setTextSize(25);
                            txtTimer.setGravity(Gravity.FILL_VERTICAL);
                            txtTimer.setText("Congratulations for successfully completing round 1! it's time to have some fun with the balloons.\n Ask the volunteer to learn more.");
                        }
                    };
            countDownTimer.start();
        } else {
            txtTimer.setTextSize(25);
            txtTimer.setText("Congratulations for successfully completing round 1! it's time to have some fun with the balloons.\n Ask the volunteer to learn more.");
        }

    }

    @Override
    public void onBackPressed() {
        if (timerFinished) {
            if (canGoBack) {
              Intent intent=new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
            }
            canGoBack = true;
            Toast.makeText(this, "Please click BACK again to go home", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    canGoBack=false;
                }
            }, 2000);
        }
    }
}
