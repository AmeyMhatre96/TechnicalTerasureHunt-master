package com.example.ameym.androidqrcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    TextView result, txtTimer, txtHint;
    int score, backPressed = 0;
    CountDownTimer countDownTimer;

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
         if (score<10) {
            txtTimer.setText("180");
            countDownTimer=
            new CountDownTimer(181000, 1000) {
                @Override
                public void onTick(long l) {
                    txtTimer.setText("Hint will appear in \n"+l / 1000);
                }

                @Override
                public void onFinish() {
                    txtTimer.setText("Go");
                }
            };
            countDownTimer.start();
        } else if (score<15){

            txtTimer.setText("120");
            countDownTimer=
                    new CountDownTimer(121000, 1000) {
                        @Override
                        public void onTick(long l) {
                            txtTimer.setText("Hint will appear in\n"+l / 1000);
                        }

                        @Override
                        public void onFinish() {
                            txtTimer.setTextSize(25);
                            txtTimer.setGravity(Gravity.FILL_VERTICAL);
                            txtTimer.setText("Goajdhkajsd hlkjh ajsdh ajdh ajdhs kajdh kaj dhkajdh aksdjhasdjahksjdkadhaksdj hasdk jhlasdkj hladkjshadjasd hask dja kdha kskj");
                        }
                    };
            countDownTimer.start();
        }else {
            txtTimer.setText("Go");
        }

    }

    @Override
    public void onBackPressed(){



     }
}
