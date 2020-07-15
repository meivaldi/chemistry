package com.rumahdev.smartacn;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DataSkorEsai extends AppCompatActivity {
    String[] daftar; String[] id;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static DataSkorEsai ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_skor);
        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM skor_esai",null);
        daftar = new String[cursor.getCount()];
        id = new String[cursor.getCount()];

        if(cursor.getCount()==0){
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            Toast.makeText(this, "Belum ada PR esai yang tersimpan", Toast.LENGTH_SHORT).show();
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }

        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
            id[cc] = cursor.getString(0).toString();
        }
        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new OnItemClickListener() {


        public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
            final String selection = id[arg2]; //.getItemAtPosition(arg2).toString();
                Intent i = new Intent(getApplicationContext(), SkorEsaiActivity.class);
                i.putExtra("id", selection);
                startActivity(i);
        }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    }


}