package com.example.ameym.androidqrcodescanner;

import android.content.Intent;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;





public class QuizActivity extends AppCompatActivity {


    TextView textViewQuestion;
    ProgressBar progressBar;
    Button buttonSubmit;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4,radioButton;
    DbHelper myDb;
    int questionCounter = 1,score = 0;
    Cursor res;
    String saveForPrevious;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        buttonSubmit=(Button)findViewById(R.id.b4);

        radioButton1 = (RadioButton) findViewById(R.id.radi1);
        radioButton2 = (RadioButton) findViewById(R.id.radio2);
        radioButton3 = (RadioButton) findViewById(R.id.radio3);
        radioButton4 = (RadioButton) findViewById(R.id.radio4);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        progressBar = (ProgressBar) findViewById(R.id.progressBar4);
        progressBar.setProgress(0);

        textViewQuestion = (TextView) findViewById(R.id.text_question);

        myDb = new DbHelper(this);
        //This method is called once when the activity is created. It loads one question.
        viewFirst();

        //This method is called on clicking next button
        viewAll();
    }




    public void  viewFirst(){
        int selected=radioGroup.getCheckedRadioButtonId();
        //this 1 indicates 1st entry in database(used as id)
        res = myDb.getAllData(1);
        while (res.moveToNext()){
            textViewQuestion.setText(res.getString(1));
            //this variable is used to compare answer with the one on the radio button. Since on clicking we would be getting new values, we have to store the previous value
            saveForPrevious = res.getString(6);
            radioButton1.setText(res.getString(2));
            radioButton2.setText(res.getString(3));
            radioButton3.setText(res.getString(4));
            radioButton4.setText(res.getString(5));
            radioGroup.clearCheck();
            radioButton=(RadioButton)findViewById(selected);
        }
    }

    public void viewAll(){

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected=radioGroup.getCheckedRadioButtonId();

                if(selected == -1){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_SHORT);
                    toast.show();

                    return;
                }

                radioButton=(RadioButton)findViewById(selected);

                progressBar.setProgress(progressBar.getProgress()+5);

                questionCounter++;
                //here we increment question counter before adding as 1st question is already added by viewFirst()
                res = myDb.getAllData(questionCounter);
                if(res.getCount() == 0){
                    Intent intent = new Intent(QuizActivity.this,ResultActivity.class);
                    String scoreResult =  Integer.toString(score);
                    intent.putExtra("score",scoreResult);
                    startActivity(intent);
                    return;
                }


                if(radioButton.getText().toString().equals(saveForPrevious))
                {
                    score++;
                }


                while (res.moveToNext()){
                    textViewQuestion.setText(res.getString(1));
                    saveForPrevious = res.getString(6);
                    radioButton1.setText(res.getString(2));
                    radioButton2.setText(res.getString(3));
                    radioButton3.setText(res.getString(4));
                    radioButton4.setText(res.getString(5));
                    radioGroup.clearCheck();
                }



            }
        });
    }
    // TODO: Add timer to questions




}


