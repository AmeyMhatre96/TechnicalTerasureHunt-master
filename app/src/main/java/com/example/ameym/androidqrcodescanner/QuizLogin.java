package com.example.ameym.androidqrcodescanner;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QuizLogin extends AppCompatActivity {
    EditText editTextPass;
    Button buttonEnter;
    DbHelper myDb;
    Cursor res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizlogin);
        editTextPass= (EditText) findViewById(R.id.editpasswd);
        buttonEnter = (Button) findViewById(R.id.buttonquiz);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                res = myDb.getAllData(1);
                System.out.println(editTextPass.getText().toString());
                if(editTextPass.getText().toString().equals("1234")){

                    Intent intent = new Intent(getApplicationContext(),QuizActivity.class);
                    startActivity(intent);
                }
            }
        });
        myDb = new DbHelper(this);

        //Adding data
        Thread MyThread = new Thread(new AddDataClass());
        MyThread.start();

    }private class AddDataClass implements Runnable{

        @Override
        public void run() {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
            res = myDb.getAllData();

            //Change questions here. Questions will only be inserted if database is empty. This prevents duplicate entries
            if(res.getCount() ==0) {
                                 //Question                           option A              option B            option C        option D        Answer
                myDb.insertData("Who is the father of C language?", "Bjarne Stroustrup", "James A. Gosling", "Dennis Ritchie", "Dr. E.F. Codd", "Dennis Ritchie");
                myDb.insertData("C was primarily developed as ?", "System programming language", "General purpose language", "Data processing language", "None of the above", "System programming language");
                myDb.insertData("For 16-bit compiler allowable range for integer constants is  ?", "-3.4e38 to 3.4e38 ", "-32767 to 32768 ", "-32668 to 32667 ", "-32768 to 32767", "-32768 to 32767");
                myDb.insertData("Who is the father of C language?", "Bjarne Stroustrup", "James A. Gosling", "Dennis Ritchie", "Dr. E.F. Codd", "Dennis Ritchie");
                myDb.insertData("C was primarily developed as ?", "System programming language", "General purpose language", "Data processing language", "None of the above", "System programming language");
                myDb.insertData("For 16-bit compiler allowable range for integer constants is  ?", "-3.4e38 to 3.4e38 ", "-32767 to 32768 ", "-32668 to 32667 ", "-32768 to 32767", "-32768 to 32767");
                myDb.insertData("Who is the father of C language?", "Bjarne Stroustrup", "James A. Gosling", "Dennis Ritchie", "Dr. E.F. Codd", "Dennis Ritchie");
                myDb.insertData("C was primarily developed as ?", "System programming language", "General purpose language", "Data processing language", "None of the above", "System programming language");
                myDb.insertData("For 16-bit compiler allowable range for integer constants is  ?", "-3.4e38 to 3.4e38 ", "-32767 to 32768 ", "-32668 to 32667", "-32768 to 32767", "-32768 to 32767");
                myDb.insertData("Who is the father of C language?", "Bjarne Stroustrup", "James A. Gosling", "Dennis Ritchie", "Dr. E.F. Codd", "Dennis Ritchie");
                myDb.insertData("C was primarily developed as ?", "System programming language", "General purpose language", "Data processing language", "None of the above", "System programming language");
                myDb.insertData("For 16-bit compiler allowable range for integer constants is  ?", "-3.4e38 to 3.4e38 ", "-32767 to 32768", "-32668 to 32667", "-32768 to 32767", "-32768 to 32767");
                myDb.insertData("Who is the father of C language?", "Bjarne Stroustrup", "James A. Gosling", "Dennis Ritchie", "Dr. E.F. Codd", "Dennis Ritchie");
                myDb.insertData("C was primarily developed as ?", "System programming language", "General purpose language", "Data processing language", "None of the above", "System programming language");
                myDb.insertData("For 16-bit compiler allowable range for integer constants is  ?", "-3.4e38 to 3.4e38", "-32767 to 32768", "-32668 to 32667", "-32768 to 32767", "-32768 to 32767");
                myDb.insertData("Who is the father of C language?", "Bjarne Stroustrup", "James A. Gosling", "Dennis Ritchie", "Dr. E.F. Codd", "Dennis Ritchie");
                myDb.insertData("C was primarily developed as ?", "System programming language", "General purpose language", "Data processing language", "None of the above", "System programming language");
                myDb.insertData("For 16-bit compiler allowable range for integer constants is  ?", "-3.4e38 to 3.4e38", "-32767 to 32768", "-32668 to 32667", "-32768 to 32767", "-32768 to 32767");
                myDb.insertData("Who is the father of C language?", "Bjarne Stroustrup", "James A. Gosling", "Dennis Ritchie", "Dr. E.F. Codd", "Dennis Ritchie");
                myDb.insertData("C was primarily developed as ?", "System programming language", "General purpose language", "Data processing language", "None of the above", "System programming language");
                myDb.insertData("For 16-bit compiler allowable range for integer constants is  ?", "-3.4e38 to 3.4e38", "-32767 to 32768", "-32668 to 32667", " -32768 to 32767", "-32768 to 32767");
            }
        }

    }



}
