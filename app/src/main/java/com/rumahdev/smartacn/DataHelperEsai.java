package com.rumahdev.smartacn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelperEsai extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "skor.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelperEsai(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = "create table skor_esai(id integer primary key, nama text null, tgl text null, skor null, esai1 text null, esai2 text null, esai3 text null, esai4 text null, esai5 text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        //sql = "INSERT INTO biodata (no, nama, tgl, jk, alamat) VALUES ('1', 'Darsiwan', '1996-07-12', 'Laki-laki','Indramayu');";
        //db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}