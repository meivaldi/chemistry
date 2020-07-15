package com.rumahdev.smartacn;

import android.content.Intent;
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


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    LinearLayout button_hasil;
    EditText nama;
    String id, id_pr;
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nama = (EditText)findViewById(R.id.nama);

        Button buttonLogin = (Button)findViewById(R.id.buttonLogin);

        Intent intent = getIntent();
        id_pr = intent.getStringExtra("id");

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(nama.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this, "Maaf, kamu harus mendaftarkan nama untuk mengikuti latihan", Toast.LENGTH_LONG).show();
            }
            else{
                session.createLoginSession(nama.getText().toString(), getDateTime(), null, null, null, null, null, null, null);
                if(id_pr.equals("pr")){
                    Intent main = new Intent(LoginActivity.this, EsaiActivity.class);
                    startActivity(main);
                }
                else{
                    Intent main = new Intent(LoginActivity.this, MulaiLatihanActivity.class);
                    startActivity(main);
                }
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
        DateFormat dateFormat = new SimpleDateFormat("yymmddHHmmss");
        Date date = new Date();
        return dateFormat.format(date);
    }




}

