package com.example.ameym.androidqrcodescanner;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

//implementing onclicklistener
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //View Objects
    private Button buttonScan, btnStartQuiz;

    public final static String ScanName = "Sname";
    //qr code scanner object
    private IntentIntegrator qrScan;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    DbHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //View objects
        buttonScan = (Button) findViewById(R.id.buttonScan);
        btnStartQuiz = (Button) findViewById(R.id.buttonStartQuiz);
        verifyStoragePermissions(MainActivity.this);

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

                    int drawableResourceId = this.getResources().getIdentifier(result.getContents(), "drawable", this.getPackageName());

                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableResourceId);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

                    File f = new File(Environment.getExternalStorageDirectory()
                            + File.separator + result.getContents() + ".jpg");
                    try {
                        f.createNewFile();
                        FileOutputStream fo = new FileOutputStream(f);
                        fo.write(bytes.toByteArray());
                        fo.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }


//                        Uri path = Uri.fromFile(f);
                    Uri path = FileProvider.getUriForFile(MainActivity.this, getApplicationContext().getPackageName() + ".provider", f);

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setDataAndType(path, "image/*");
                    startActivity(intent);
                }
            }
        }
    }

    public static void verifyStoragePermissions(final Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );


        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonScan:
                qrScan.initiateScan();
                break;
            case R.id.buttonStartQuiz:

                Intent intent = new Intent(MainActivity.this, QuizLogin.class);
                startActivity(intent);
        }

    }
}