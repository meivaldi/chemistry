package com.rumahdev.smartacn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class MulaiLatihanActivity extends AppCompatActivity {

    TextView titleBar;
    RadioGroup rgJawaban;
    RadioButton rdA, rdB, rdC, rdD, rdE;
    Button btnSubmit, btnNext;
    DataHelper dbHelper;
    String nim, nama, id_quiz, id;

    ImageView soalGambar, cara_jawab;

    int skor=0;
    int arr, nilai;
    int x;

    MediaPlayer mediaPlayer, mediaPlayerCorrect, mediaPlayerWrong;

    String k;

    LatihanActivity soal = new LatihanActivity();



    public static final String ANONYMOUS = "anonymous";
    private static final String TAG = "MainActivity";
    private SharedPreferences mSharedPreferences;
    private String mUsername;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private GoogleApiClient mGoogleApiClient;

    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_soal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DataHelper(this);
        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();

        nama = user.get(SessionManagement.USER_NAME);
        id = user.get(SessionManagement.USER_TES1);

        titleBar = (TextView)findViewById(R.id.titleBar);
        rgJawaban = (RadioGroup) findViewById(R.id.rgJawaban);
        rdA = (RadioButton) findViewById(R.id.rdA);
        rdB = (RadioButton) findViewById(R.id.rdB);
        rdC = (RadioButton) findViewById(R.id.rdC);
        rdD = (RadioButton) findViewById(R.id.rdD);
        rdE = (RadioButton) findViewById(R.id.rdE);

        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnNext = (Button)findViewById(R.id.btnNext);

        soalGambar = (ImageView)findViewById(R.id.soalGambar);
        cara_jawab = (ImageView)findViewById(R.id.cara_jawab);

        setKonten();

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.a_backsound);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        mediaPlayerCorrect = MediaPlayer.create(getApplicationContext(), R.raw.a_correct);
        mediaPlayerWrong = MediaPlayer.create(getApplicationContext(), R.raw.a_wrong);

        btnNext.setVisibility(View.GONE);

        cara_jawab.setVisibility(View.GONE);

        //menentukan aksi ketika button submit diklik
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekJawaban();
                btnNext.setVisibility(View.VISIBLE);
                btnSubmit.setVisibility(View.GONE);
                cara_jawab.setVisibility(View.VISIBLE);
            }
        });

        //menentukan aksi ketika button submit diklik
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnNext.setVisibility(View.GONE);
                btnSubmit.setVisibility(View.VISIBLE);
                cara_jawab.setVisibility(View.GONE);

                setKonten(); //update next konten

            }
        });

    }

    public void setKonten(){

        rgJawaban.clearCheck();
        arr = soal.pertanyaan.length;
        soalGambar.setImageResource(0);
        cara_jawab.setImageResource(0);
        if(x >= arr){
            /*
            Intent i = new Intent(MulaiLatihanActivity.this, EsaiActivity.class);
            //Toast.makeText(this, "Kamu benar: "+skor, Toast.LENGTH_SHORT).show();
            i.putExtra("skor", String.valueOf(skor));
            startActivity(i);
            */
            nilai = Integer.parseInt(String.valueOf(skor))*10/2;
            Intent main = new Intent(MulaiLatihanActivity.this, MainActivity.class);

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("insert into skor(id, nama, tgl, skor, esai1, esai2, esai3, esai4, esai5) values('" +
                    id + "','" +
                    nama + "','" +
                    getDateTime() + "','" +
                    String.valueOf(nilai) + "','" +
                    "" + "','" +
                    "" + "','" +
                    "" + "','" +
                    "" + "','" +
                    "" + "')");
            Toast.makeText(getApplicationContext(), "Berhasil menyimpan nilai", Toast.LENGTH_LONG).show();
            //DataSkor.ma.RefreshList();
            finish();
            main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(main);

        }
        else{

            if(x==0){
                soalGambar.setImageResource(0);
                cara_jawab.setImageResource(R.drawable.soal_1_kunci);
            }if(x==1){
                soalGambar.setImageResource(0);
                cara_jawab.setImageResource(R.drawable.soal_2_kunci);
            }if(x==2){
                soalGambar.setImageResource(0);
                cara_jawab.setImageResource(R.drawable.soal_3_kunci);
            }if(x==3){
                soalGambar.setImageResource(0);
                cara_jawab.setImageResource(R.drawable.soal_4_kunci);
            }if(x==4){
                soalGambar.setImageResource(R.drawable.soal_5);
                cara_jawab.setImageResource(R.drawable.soal_5_kunci);
            }if(x==5){
                soalGambar.setImageResource(0);
                cara_jawab.setImageResource(R.drawable.soal_6_kunci);
            }if(x==6){
                soalGambar.setImageResource(0);
                cara_jawab.setImageResource(R.drawable.soal_7_kunci);
            }if(x==7){
                soalGambar.setImageResource(0);
                cara_jawab.setImageResource(R.drawable.soal_8_kunci);
            }if(x==8){
                soalGambar.setImageResource(R.drawable.soal_9);
                cara_jawab.setImageResource(R.drawable.soal_9_kunci);
            }if(x==9){
                soalGambar.setImageResource(R.drawable.soal_10);
                cara_jawab.setImageResource(R.drawable.soal_10_kunci);
            }


            titleBar.setText(Html.fromHtml(soal.getPertanyaan(x)));

            rdA.setText(Html.fromHtml(soal.getA(x)));
            rdB.setText(Html.fromHtml(soal.getB(x)));
            rdC.setText(Html.fromHtml(soal.getC(x)));
            rdD.setText(Html.fromHtml(soal.getD(x)));
            rdE.setText(Html.fromHtml(soal.getE(x)));
            k = soal.getK(x);
        }

        x++;
    }

    public void cekJawaban(){
        if(rdA.isChecked()){ //jika radio button 1 diklik
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if(k=="A"){
                skor = skor + 1;
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
                mediaPlayerCorrect.start();

            }else{
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                mediaPlayerWrong.start();
            }
        }else if(rdB.isChecked()){
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if(k=="B"){
                skor = skor + 1;
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
                mediaPlayerCorrect.start();
            }else{
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                mediaPlayerWrong.start();
            }
        }else if(rdC.isChecked()){
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if(k=="C"){
                skor = skor + 1;
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
                mediaPlayerCorrect.start();
            }else{
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                mediaPlayerWrong.start();
            }
        }else if(rdD.isChecked()){
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if(k=="D"){
                skor = skor + 1;
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
                mediaPlayerCorrect.start();
            }else{
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                mediaPlayerWrong.start();
            }
        }else if(rdE.isChecked()){
            //jika text yang tertulis di radio button tsb = nilai dari var jawaban
            if(k=="E"){
                skor = skor + 1;
                Toast.makeText(this, "Jawaban Benar", Toast.LENGTH_SHORT).show(); //keluar notifikasi "Jawaban Benar"
                mediaPlayerCorrect.start();
            }else{
                Toast.makeText(this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                mediaPlayerWrong.start();
            }
        }else{
            Toast.makeText(this, "Ups, Silahkan pilih jawaban!", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();

    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
