package com.rumahdev.smartacn;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MateriActivity extends AppCompatActivity {

    private String judul1, judul2, judul3, judul4, judul5, judul6;
    TextView materi_1, materi_2, materi_3, materi_4, materi_5, materi_6, materi_video;
    Intent main;
    public String [] materi_satu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout materi_pertama = (LinearLayout)findViewById(R.id.materi_pertama);
        LinearLayout materi_kedua = (LinearLayout)findViewById(R.id.materi_kedua);
        LinearLayout materi_ketiga = (LinearLayout)findViewById(R.id.materi_ketiga);
        LinearLayout materi_keempat = (LinearLayout)findViewById(R.id.materi_keempat);
        LinearLayout materi_kelima = (LinearLayout)findViewById(R.id.materi_kelima);
        LinearLayout materi_keenam = (LinearLayout)findViewById(R.id.materi_keenam);
        LinearLayout materi_video = (LinearLayout)findViewById(R.id.materi_video);

        materi_1 = (TextView)findViewById(R.id.materi_1);
        materi_2 = (TextView)findViewById(R.id.materi_2);
        materi_3 = (TextView)findViewById(R.id.materi_3);
        materi_4 = (TextView)findViewById(R.id.materi_4);
        materi_5 = (TextView)findViewById(R.id.materi_5);
        materi_6 = (TextView)findViewById(R.id.materi_6);

        judul1 = "Larutan Penyangga (Buffer)";
        judul2 = "Pengertian Larutan Penyangga";
        judul3 = "Komponen Larutan Penyangga";
        judul4 = "Cara kerja dan Pembuatan Larutan Penyangga";
        judul5 = "Menghitung pH Larutan Penyangga";
        judul6 = "Fungsi Larutan Pemyangga";

        materi_1.setText(judul1);
        materi_2.setText(judul2);
        materi_3.setText(judul3);
        materi_4.setText(judul4);
        materi_5.setText(judul5);
        materi_6.setText(judul6);

        main = new Intent(MateriActivity.this, DetailMateriActivity.class);

        materi_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MateriActivity.this, FullscreenDemoActivity.class);
                startActivity(i);
            }
        });


        materi_pertama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.putExtra("id","1");
                main.putExtra("judul",judul2);
                startActivity(main);
            }
        });

        materi_kedua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.putExtra("id","2");
                main.putExtra("judul",judul2);
                startActivity(main);
            }
        });

        materi_ketiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.putExtra("id","3");
                main.putExtra("judul",judul3);
                startActivity(main);
            }
        });

        materi_keempat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.putExtra("id","4");
                main.putExtra("judul",judul4);
                startActivity(main);
            }
        });

        materi_kelima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.putExtra("id","5");
                main.putExtra("judul",judul3);
                startActivity(main);
            }
        });

        materi_keenam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.putExtra("id","6");
                main.putExtra("judul",judul3);
                startActivity(main);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
