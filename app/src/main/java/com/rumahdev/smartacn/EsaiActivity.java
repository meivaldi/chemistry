package com.rumahdev.smartacn;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class EsaiActivity extends AppCompatActivity {
    String nim, nama, skor, id_quiz, tes1, tes2, tes3, id;
    int nilai;
    private static final String TAG = "MainActivity";
    SessionManagement session;
    Button simpan;
    EditText jawab_esai_1, jawab_esai_2, jawab_esai_3, jawab_esai_4, jawab_esai_5;
    DataHelper dbHelper;
    LinearLayout button_hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esai);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        //skor = intent.getStringExtra("skor");
        //nilai = Integer.parseInt(String.valueOf(skor))*10/2;
        dbHelper = new DataHelper(this);
        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();

        nama = user.get(SessionManagement.USER_NAME);
        id = user.get(SessionManagement.USER_TES1);

        simpan = (Button)findViewById(R.id.simpan);
        jawab_esai_1 = (EditText)findViewById(R.id.jawab_esai_1);
        jawab_esai_2 = (EditText)findViewById(R.id.jawab_esai_2);
        jawab_esai_3 = (EditText)findViewById(R.id.jawab_esai_3);
        jawab_esai_4 = (EditText)findViewById(R.id.jawab_esai_4);
        jawab_esai_5 = (EditText)findViewById(R.id.jawab_esai_5);


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(jawab_esai_1.getText().toString().isEmpty() && jawab_esai_2.getText().toString().isEmpty() && jawab_esai_3.getText().toString().isEmpty() && jawab_esai_4.getText().toString().isEmpty() && jawab_esai_5.getText().toString().isEmpty()){
                    Toast.makeText(EsaiActivity.this, "Isikan jawaban kamu terlenbih dahulu", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent main = new Intent(EsaiActivity.this, MainActivity.class);
                    //session.createLoginSession("Siswa", String.valueOf(nilai), tes2, tes3, jawab_esai_1.getText().toString(), jawab_esai_2.getText().toString(), jawab_esai_3.getText().toString(), jawab_esai_4.getText().toString(), jawab_esai_5.getText().toString());
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("insert into skor_esai(id, nama, tgl, skor, esai1, esai2, esai3, esai4, esai5) values('" +
                            id + "','" +
                            nama + "','" +
                            getDateTime() + "','" +
                            "" + "','" +
                            jawab_esai_1.getText().toString() + "','" +
                            jawab_esai_2.getText().toString() + "','" +
                            jawab_esai_3.getText().toString() + "','" +
                            jawab_esai_4.getText().toString() + "','" +
                            jawab_esai_5.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Berhasil menyimpan jawaban PR esai", Toast.LENGTH_LONG).show();
                    //DataSkor.ma.RefreshList();
                    finish();
                    main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(main);
                }

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


    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }



}

