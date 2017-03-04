package com.example.ameym.androidqrcodescanner;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import uk.co.senab.photoview.PhotoViewAttacher;

//implementing onclicklistener
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //View Objects
    private Button buttonScan,btnStartQuiz;

    public final static String ScanName = "Sname";
    //qr code scanner object
    private IntentIntegrator qrScan;
    private  Drawable bitmap;
    PhotoViewAttacher mAttacher;
    private String resultOfScan;

    DbHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         bitmap = getDrawable(R.drawable.mouse);
        //View objects
        buttonScan = (Button) findViewById(R.id.buttonScan);
        btnStartQuiz=(Button) findViewById(R.id.buttonStartQuiz);





        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //attaching onclick listener
        buttonScan.setOnClickListener(this);
        btnStartQuiz.setOnClickListener(this);
        myDb = new DbHelper(this);

    }



    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());



                    Intent intent = new Intent(this, QuizActivity.class);
                    intent.putExtra(ScanName, obj.getString("name"));
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast

                    if (result.getContents().contentEquals("mouse")){
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mouse);
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

                        File f = new File(Environment.getExternalStorageDirectory()
                                + File.separator + "mouse.jpg");
                        try {
                            f.createNewFile();
                            FileOutputStream fo = new FileOutputStream(f);
                            fo.write(bytes.toByteArray());
                            fo.close();
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }


//                        Uri path = Uri.fromFile(f);
                        Uri path = FileProvider.getUriForFile(MainActivity.this, getApplicationContext().getPackageName() + ".provider",f);

                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent.setDataAndType(path, "image/*");
                        startActivity(intent);
                    }
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonScan:
                qrScan.initiateScan();
                break;
            case R.id.buttonStartQuiz:
                Intent intent = new Intent(MainActivity.this,QuizLogin.class);
                startActivity(intent);
        }

    }
}