package com.rumahdev.smartacn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MulaiPrActivity extends AppCompatActivity {

    TextView titleBar;
    RadioGroup rgJawaban;
    RadioButton rdA, rdB, rdC, rdD, rdE;
    Button btnSubmit, btnNext;

    String nim, nama, id_quiz;

    ImageView soalGambar, cara_jawab;

    int skor=0;
    int arr;
    int x;

    MediaPlayer mediaPlayer, mediaPlayerCorrect, mediaPlayerWrong;

    String k;

    PrActivity soal = new PrActivity();



    public static final String ANONYMOUS = "anonymous";
    private static final String TAG = "MainActivity";
    private SharedPreferences mSharedPreferences;
    private String mUsername;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_soal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
            Intent i = new Intent(MulaiPrActivity.this, SkorPrActivity.class);
            //Toast.makeText(this, "Kamu benar: "+skor, Toast.LENGTH_SHORT).show();
            i.putExtra("skor", String.valueOf(skor));
            startActivity(i);
        }
        else{

            if(x==0){
                soalGambar.setImageResource(R.drawable.pr_1);
                cara_jawab.setImageResource(R.drawable.pr_1_kunci);
            }
            if(x==1){
                soalGambar.setImageResource(0);
                cara_jawab.setImageResource(R.drawable.pr_2_kunci);
            }
            if(x==2){
                soalGambar.setImageResource(0);
                cara_jawab.setImageResource(R.drawable.pr_3_kunci);
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



}
