package com.rumahdev.smartacn;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class SkorPrActivity extends AppCompatActivity {

    String nim, nama, skor, id_quiz;
    Button home,cobalagi;
    ImageView meme;
    TextView tSkor, tBenar;
    int nilai;
    private static final String register_url = "http://emdastra.com/project-smartacn/quiz-score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor_pr);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        nim = intent.getStringExtra("nim");
        nama = intent.getStringExtra("nama");
        skor = intent.getStringExtra("skor");
        id_quiz = intent.getStringExtra("id_quiz");

        home = (Button)findViewById(R.id.home);
        meme = (ImageView)findViewById(R.id.meme);

        tSkor = (TextView)findViewById(R.id.tSkor);
        tBenar = (TextView)findViewById(R.id.tBenar);

        tBenar.setText(skor);

        Toast.makeText(this, "Kamu benar: "+skor, Toast.LENGTH_SHORT).show();
        nilai = Integer.parseInt(String.valueOf(skor))/3*100;
        tSkor.setText(String.valueOf(nilai));

        if(nilai<65){
            meme.setImageResource(R.drawable.bg_nilai_kalah);
        }
        else{
            meme.setImageResource(R.drawable.bg_nilai_menang);
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(SkorPrActivity.this, MainActivity.class);
                startActivity(main);
            }
        });


        //submit_skor(nim, nama, String.valueOf(nilai), id_quiz);

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

    private void submit_skor (String nim, String nama, String nilai, String id_quiz) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            RegisterUserClass ruc = new RegisterUserClass();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s.equals("1")) {
                    Toast.makeText(SkorPrActivity.this, "Terima Kasih, Skor Anda Sudah Tersimpan", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SkorPrActivity.this, "Gagal menyimpan Skor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String, String>();
                data.put("nim", params[0]);
                data.put("nama", params[1]);
                data.put("skor", params[2]);
                data.put("id_quiz", params[3]);


                String result = ruc.sendPostRequest(register_url, data);

                return result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(nim, nama, nilai, id_quiz);

    }
}
