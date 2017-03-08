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
                if(editTextPass.getText().toString().equals("2456")){

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
                myDb.insertData("Which one of the following is not a reserved keyword for C? ", "auto", "main", "case", "default", "main");
                myDb.insertData("Output of : \nprintf(\"%d\",strcmp(\"strcmp()\",\"strcmp()\"));", "0", "1", "-1","invalid use of strcomp", "0");
                myDb.insertData("Output of : \nint a[] = {10, 20, 30}; \nprintf(\"%d\", *a+1);", "10", "20", "11", "21", "11");

                myDb.insertData("In Java, memory is reclaimed through ", "Serialization", "Garbage Collection", "Multithreading", "Runtime Exception", "Garbage Collection");
                myDb.insertData("Constructors have no return type not even?", "Object", "int", "void", "float", "void");
                myDb.insertData("Which is the invalid array declaration", "int[] a = new int[]{1,2};", "int a[5] = new int[5];", "int a[] = {1,2,3};", "int a[] = new int[5];", "int a[5] = new int[5];");

                myDb.insertData("The topology with highest reliability is:", "Bus topology", "Star topology", "Ring topology", "Mesh topology", "Mesh topology");

                myDb.insertData("Which of the SQL statements is correct?", "SELECT Username AND Password FROM Users", "SELECT Username, Password FROM Users", "SELECT Username, Password WHERE Username = 'user1'", "None of these", "SELECT Username, Password FROM Users");
                myDb.insertData("Which of the following is an Universal Selector in CSS?", "> {Color:#FFFFFF;}", ". {Color:#FFFFFF;}", "# {Color:#FFFFFF;}", "* {Color:#FFFFFF;}", "* {Color:#FFFFFF;}");
                myDb.insertData("The regular expression 0*(10*)* denotes the same set as", "(1*0)*1*", "0 + (0 + 10)*", "(0 + 1)* 10(0 + 1)*", "none of these", "(1*0)*1*");

                myDb.insertData("Poles : Magnet :: ? : Battery", "Energy", "Power", "Terminal", "Cell", "Terminal");
                myDb.insertData("Book : Cover :: Painting : ? ", "Example", "Wall", "Colour", "Frame", "Frame");
                myDb.insertData("Choose the word which is different from the rest.", "Cap", "Turban", "Helmet", "Veil", "Veil");

                myDb.insertData("In a row of trees, a tree is 7th from left end and 14th from right end. How many tree are there in the row ?", "18", "19", "20", "21", "20");
                myDb.insertData("B is twice as old as A but twice younger than F. C is half the age of A but is twice older than d. Who is the second oldest ?", "B", "F", "C", "D", "B");
                myDb.insertData(" A car stops and then starts accelerating uniformly at rate of 3 ms-2. speed of car after 20 seconds is ?", "40 ms^2", "60 ms^2", "100 ms^2", "30 ms^2", "60 ms^2");
                myDb.insertData("A stone is released from an elevator going up with acceleration 5 m/s2 . The acceleration of the stone after the release is:", "5 ms^2", "4.8 ms^2 upward", "4.8 down ward", "9.8 ms^2 down ward", "9.8 ms^2 down ward");

                myDb.insertData("If in a certain language PROSE is coded as PPOQE, how is FIGHT coded in that code ?", "FIGFT", "FGGHT", "FGGFT", "FLGFE", "FGGFT");
                myDb.insertData("If FIND becomes DGLB and WATER becomes UYRCP, then what will DIAGRAM be in that code ?", "BGYEPYK", "BGYPYEK", "GLPEYKB", "LKBGYPK", "BGYEPYK");


             }
        }

    }



}
