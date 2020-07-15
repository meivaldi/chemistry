package com.rumahdev.smartacn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KompetensiActivity extends AppCompatActivity {

    private String judul1, judul2, judul3;
    TextView materi_1, materi_2, materi_3;
    Intent main;
    public String [] materi_satu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kompetensi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        LinearLayout materi_pertama = (LinearLayout)findViewById(R.id.materi_pertama);
        LinearLayout materi_kedua = (LinearLayout)findViewById(R.id.materi_kedua);
        LinearLayout materi_ketiga = (LinearLayout)findViewById(R.id.materi_ketiga);

        materi_1 = (TextView)findViewById(R.id.materi_1);
        materi_2 = (TextView)findViewById(R.id.materi_2);
        materi_3 = (TextView)findViewById(R.id.materi_3);

        judul1 = "Kompetensi Inti";
        judul2 = "Kompetensi Dasar";
        judul3 = "Tujuan Pembelajaran";

        materi_1.setText(judul1);
        materi_2.setText(judul2);
        materi_3.setText(judul3);

        main = new Intent(KompetensiActivity.this, DetailMateriActivity.class);

        materi_pertama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.putExtra("id","k_1");
                main.putExtra("judul",judul1);
                startActivity(main);
            }
        });

        materi_kedua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.putExtra("id","k_2");
                main.putExtra("judul",judul2);
                startActivity(main);
            }
        });

        materi_ketiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.putExtra("id","k_3");
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
