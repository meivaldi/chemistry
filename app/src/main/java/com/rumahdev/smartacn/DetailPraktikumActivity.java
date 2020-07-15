package com.rumahdev.smartacn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailPraktikumActivity extends AppCompatActivity {

    private String id, judul;
    TextView titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_praktikum);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        judul = intent.getStringExtra("judul");

        titleBar = (TextView)findViewById(R.id.titleBar);
        titleBar.setText(judul);

        WebView materi = (WebView)findViewById(R.id.materi);

        if(id.equals("mat")){
            toolbar.setTitle("Materi");
            materi.loadUrl("file:///android_asset/materi.html");
        }
        else if(id.equals("1")){
            toolbar.setTitle(judul);
            materi.loadUrl("file:///android_asset/materi_penyangga.html");
        }
        else if(id.equals("2")){
            toolbar.setTitle(judul);
            materi.loadUrl("file:///android_asset/materi_a.html");
        }
        else if(id.equals("3")){
            toolbar.setTitle(judul);
            materi.loadUrl("file:///android_asset/materi_b.html");
        }
        else if(id.equals("4")){
            toolbar.setTitle(judul);
            materi.loadUrl("file:///android_asset/materi_c.html");
        }
        else if(id.equals("5")){
            toolbar.setTitle(judul);
            materi.loadUrl("file:///android_asset/materi_d.html");
        }
        else if(id.equals("6")){
            toolbar.setTitle(judul);
            materi.loadUrl("file:///android_asset/materi_e.html");
        }
        else if (id.equals("ref")){
            toolbar.setTitle("Referensi");
            materi.loadUrl("file:///android_asset/referensi.html");
        }

        else if (id.equals("k_1")){
            toolbar.setTitle(judul);
            materi.loadUrl("file:///android_asset/k_a.html");
        }

        else if (id.equals("k_2")){
            toolbar.setTitle(judul);
            materi.loadUrl("file:///android_asset/k_b.html");
        }

        else if (id.equals("k_3")){
            toolbar.setTitle(judul);
            materi.loadUrl("file:///android_asset/k_c.html");
        }

        else if (id.equals("kom")){
            toolbar.setTitle(judul);
            materi.loadUrl("file:///android_asset/kompetensi.html");
        }

        else if (id.equals("prak")){
            toolbar.setTitle(judul);
            materi.loadUrl("file:///android_asset/instrumen.html");
        }

        else{
            toolbar.setTitle("Tentang");
            materi.loadUrl("file:///android_asset/biodata.html");
        }


        Button button_praktikum = (Button)findViewById(R.id.button_video);
        button_praktikum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailPraktikumActivity.this, FullscreenDemoActivity.class);
                startActivity(i);
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
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}


