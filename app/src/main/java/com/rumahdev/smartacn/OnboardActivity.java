package com.rumahdev.smartacn;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class OnboardActivity extends AppCompatActivity {

    private String snim, sid_quiz;
    EditText nim, id_quiz;
    Button mulai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_onboard);

        nim = (EditText) findViewById(R.id.nim);
        id_quiz= (EditText) findViewById(R.id.id_quiz);
        mulai = (Button)findViewById(R.id.mulai);


        mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(OnboardActivity.this, MulaiLatihanActivity.class);
                if(nim.getText().toString().isEmpty() && id_quiz.getText().toString().isEmpty()){
                    Toast.makeText(OnboardActivity.this, "Isikan ID anda Terlebh Dahulu", Toast.LENGTH_SHORT).show();
                }
                else{
                    main.putExtra("nim", String.valueOf(nim.getText().toString()));
                    main.putExtra("id_quiz", String.valueOf(id_quiz.getText().toString()));
                    Toast.makeText(OnboardActivity.this, "Selamat mengikuti Latihan", Toast.LENGTH_SHORT).show();
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
}
