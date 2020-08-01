package com.rumahdev.smartacn;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MateriActivity extends AppCompatActivity {

    private String id, judul;
    private TextView titleBar;

    private Button jawab2, jawab3a, jawab3b, jawab3c, jawab4;
    String status = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout homeBar = (LinearLayout)findViewById(R.id.homeBar);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        judul = intent.getStringExtra("judul");

        titleBar = (TextView)findViewById(R.id.titleBar);
        titleBar.setText(judul);

        jawab2 = findViewById(R.id.jawab_2);
        jawab3a = findViewById(R.id.jawab_3a);
        jawab3b = findViewById(R.id.jawab_3b);
        jawab3c = findViewById(R.id.jawab_3c);
        jawab4 = findViewById(R.id.jawab4);

        final Dialog answerDialog = new Dialog(MateriActivity.this);
        answerDialog.setCancelable(false);
        answerDialog.setContentView(R.layout.answer_dialog);
        answerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        final EditText answerET = answerDialog.findViewById(R.id.answer);
        Button send = answerDialog.findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = answerET.getText().toString();
                answerDialog.dismiss();

                SharedPreferences.Editor editor = getSharedPreferences("hani", MODE_PRIVATE).edit();
                editor.putString(status, answer);
                editor.apply();
            }
        });

        jawab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = "jawaban2";

                String answer = getSharedPreferences("hani", MODE_PRIVATE).getString(status, "");
                answerET.setText(answer);

                answerDialog.show();
            }
        });

        jawab3a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = "jawaban3a";

                String answer = getSharedPreferences("hani", MODE_PRIVATE).getString(status, "");
                answerET.setText(answer);

                answerDialog.show();
            }
        });

        jawab3b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = "jawaban3b";

                String answer = getSharedPreferences("hani", MODE_PRIVATE).getString(status, "");
                answerET.setText(answer);

                answerDialog.show();
            }
        });

        jawab3c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = "jawaban3c";

                String answer = getSharedPreferences("hani", MODE_PRIVATE).getString(status, "");
                answerET.setText(answer);

                answerDialog.show();
            }
        });

        jawab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = "jawaban4";

                String answer = getSharedPreferences("hani", MODE_PRIVATE).getString(status, "");
                answerET.setText(answer);

                answerDialog.show();
            }
        });

        WebView materi = (WebView)findViewById(R.id.materi);
        WebView materi2 = findViewById(R.id.materi2);

        if(id.equals("mat")){
            toolbar.setTitle("Materi");
            homeBar.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_materi));
            materi.loadUrl("file:///android_asset/materi.html");
            materi2.loadUrl("file:///android_asset/materi2.html");
        }
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
