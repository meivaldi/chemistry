package com.rumahdev.smartacn;
import android.support.v7.app.AppCompatActivity;

public class PrActivity extends AppCompatActivity {

    public String pertanyaan[] = {
            "Percobaan dilakukan untuk mempelajari kinetika kimia reaksi :",
            "Jika ke dalam 100 mL larutan asam sulfat (H2SO4) 5 M ditambahkan air sebanyak 200 mL, maka konsentrasi larutan asam sulfat sekarang adalah :",
            "Pada suhu 20<sup>0</sup>C reaksi memerlukan waktu 18 detik. Waktu yang diperlukan jika reaksi berlangsung pada suhu 40<sup>0</sup>C adalah",


    };

    public String jawaban[][] ={
            {"laju = k[P]2[Q]", "laju = k[P]<sup>2</sup>[Q]", "laju = k[P][Q]<sup>2</sup>", "laju = k[Q]", " laju = k[Q]<sup>2</sup>"},
            {"2,5 M", "2 M", "0.2 M", "3 M", "5 M"},
            {"3 detik", "4,5 detik", "6 detik", "9 detik", "13,5 detik"},

    };

    public String kunci[] ={
            "E",
            "A",
            "B",
    };

    public String getPertanyaan(int x){
        String soal = pertanyaan[x];
        return soal;
    }

    public String getA(int x){
        String a = jawaban[x][0];
        return a;
    }

    public String getB(int x){
        String b = jawaban[x][1];
        return b;
    }

    public String getC(int x){
        String c = jawaban[x][2];
        return c;
    }
    public String getD(int x){
        String d = jawaban[x][3];
        return d;
    }
    public String getE(int x){
        String e = jawaban[x][4];
        return e;
    }

    public String getK(int x){
        String k = kunci[x];
        return k;
    }

}
