package com.example.ameym.androidqrcodescanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ameym on 27-02-2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Quiz.db";
    public static final String TABLE_NAME = "quiz_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "QUESTION";
    public static final String COL_3 = "OPTIONA";
    public static final String COL_4 = "OPTIONB";
    public static final String COL_5 = "OPTIONC";
    public static final String COL_6 = "OPTIOND";

    public static final String COL_7 = "ANSWER";

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    //Creating the quiz table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2+" TEXT, "+COL_3+" TEXT, "+COL_4+" TEXT, "+COL_5+" TEXT, "+COL_6+" TEXT, "+COL_7+" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    //Inserting data
    public boolean insertData(String question, String optiona, String optionb , String optionc, String optiond  , String answer ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,question);
        contentValues.put(COL_3,optiona);
        contentValues.put(COL_4,optionb);
        contentValues.put(COL_5,optionc);
        contentValues.put(COL_6,optiond);
        contentValues.put(COL_7,answer);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1 ){
            return false;
        }
        return  true;

    }

    //getting result set

    public Cursor getAllData(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME + " where id = '"+i+"' ", null );
        return res;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME  , null );
        return res;
    }

}

