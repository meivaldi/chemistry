package com.rumahdev.smartacn;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class SkorEsaiActivity extends AppCompatActivity {

    String nim, nama, skor, id_quiz, tes1, tes2, tes3, esai1, esai2, esai3, esai4, esai5, id;
    Button home,cobalagi;
    ImageView meme;
    TextView tSkor, tBenar, tesai1, tesai2, tesai3, tesai4, tesai5, namas, tgl;
    int nilai, skors;
    private static final String register_url = "http://emdastra.com/project-smartacn/quiz-score";
    SessionManagement session;
    DataHelper dbHelper;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor_esai);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DataHelper(this);

        Intent intent = getIntent();
        skor = intent.getStringExtra("skor");
        id = intent.getStringExtra("id");

        home = (Button)findViewById(R.id.home);
        cobalagi= (Button)findViewById(R.id.cobalagi);
        meme = (ImageView)findViewById(R.id.meme);

        namas = (TextView)findViewById(R.id.nama);
        tgl= (TextView)findViewById(R.id.tgl);
        tesai1 = (TextView)findViewById(R.id.esai1);
        tesai2 = (TextView)findViewById(R.id.esai2);
        tesai3 = (TextView)findViewById(R.id.esai3);
        tesai4 = (TextView)findViewById(R.id.esai4);
        tesai5 = (TextView)findViewById(R.id.esai5);

        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM skor_esai WHERE id = '" +
                getIntent().getStringExtra("id") + "'",null);
        cursor.moveToFirst();

        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            namas.setText(cursor.getString(1).toString());
            tgl.setText(cursor.getString(2).toString());
            tesai1.setText(cursor.getString(4).toString());
            tesai2.setText(cursor.getString(5).toString());
            tesai3.setText(cursor.getString(6).toString());
            tesai4.setText(cursor.getString(7).toString());
            tesai5.setText(cursor.getString(8).toString());
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(SkorEsaiActivity.this, MainActivity.class);
                main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(main);
            }
        });
        cobalagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(SkorEsaiActivity.this, LoginActivity.class);
                main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
                    Toast.makeText(SkorEsaiActivity.this, "Terima Kasih, Skor Anda Sudah Tersimpan", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SkorEsaiActivity.this, "Gagal menyimpan Skor", Toast.LENGTH_SHORT).show();
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
