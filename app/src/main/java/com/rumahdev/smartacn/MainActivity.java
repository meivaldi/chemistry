package com.rumahdev.smartacn;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    LinearLayout button_hasil;
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.slide_1, R.drawable.slide_2, R.drawable.slide_3, R.drawable.slide_4, R.drawable.slide_5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        LinearLayout button_materi = (LinearLayout)findViewById(R.id.button_materi);
        LinearLayout button_pr = (LinearLayout)findViewById(R.id.button_pr);
        LinearLayout button_latihan = (LinearLayout)findViewById(R.id.button_latihan);
        LinearLayout button_tentang = (LinearLayout)findViewById(R.id.button_profil);
        LinearLayout button_kompetensi = (LinearLayout)findViewById(R.id.button_kompetensi);
        LinearLayout button_praktikum = (LinearLayout)findViewById(R.id.button_praktikum);

        Button button = (Button)findViewById(R.id.button);

        button_kompetensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent main = new Intent(MainActivity.this, KompetensiActivity.class);
            startActivity(main);
            }
        });

        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent main = new Intent(MainActivity.this, DataSkor.class);
            startActivity(main);
            }
        });
        */

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Lihat hasil skor");
                // add a list
                String[] layanan = {"Latihan", "PR"};
                builder.setItems(layanan, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent main = new Intent(MainActivity.this, DataSkor.class);
                                startActivity(main);
                                break;
                            case 1:
                                Intent main2 = new Intent(MainActivity.this, DataSkorEsai.class);
                                startActivity(main2);
                                break;
                        }
                    }
                });
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        /*
        button_materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(MainActivity.this, MateriActivity.class);
                startActivity(main);
            }
        });
        */

        button_latihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(MainActivity.this, LoginActivity.class);
                main.putExtra("id","latihan");
                startActivity(main);
            }
        });

        button_pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(MainActivity.this, LoginActivity.class);
                main.putExtra("id","pr");
                startActivity(main);
            }
        });

        button_materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(MainActivity.this, DetailMateriActivity.class);
                main.putExtra("id","mat");
                main.putExtra("judul", "Materi");
                startActivity(main);
            }
        });

        button_kompetensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(MainActivity.this, DetailMateriActivity.class);
                main.putExtra("id","kom");
                main.putExtra("judul", "Kompetensi");
                startActivity(main);
            }
        });

        button_praktikum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(MainActivity.this, DetailPraktikumActivity.class);
                main.putExtra("id","prak");
                main.putExtra("judul", "Praktikum");
                startActivity(main);
            }
        });

        button_tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(MainActivity.this, DetailMateriActivity.class);
                main.putExtra("id","ten");
                main.putExtra("judul", "Tentang");
                startActivity(main);
            }
        });

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    };
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

