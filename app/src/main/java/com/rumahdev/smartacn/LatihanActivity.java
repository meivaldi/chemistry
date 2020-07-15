package com.rumahdev.smartacn;
import android.support.v7.app.AppCompatActivity;

public class LatihanActivity extends AppCompatActivity {

    public String pertanyaan[] = {
            "1. Larutan Elektrolit adalah...",
            "2. Suatu zat padat dilarutkan pada air, dan ternyata larutan itu dapat menghantarkan arus listrik. Pernyataan yang tepat untuk menerangkan peristiwa itu adalah...",
            "3. Larutan elektrolit dapat menghantarkan arus listrik karena didalam larutannya terdapat...",
            "4. Pernyataan yang benar tentang larutan elektrolit dan nonelektrolit adalah...",
            "5. Arus listrik dapat mengalir melalui larutan elektrolit karena...",

            "6. Pasangan yang termasuk larutan nonelektrolit adalah...",
            "7. Senyawa dibawah ini yang apabila dilarutkan dalam air akan terionisasi sempurna adalah...",
            "8. Larutan elektrolit lemah bisa diuji denga penguji elektrolit setidak tidaknya akan menunjukkan adanya...",
            "9. Senyawa dibawah ini yang tidak dapat menghantarkan arus listrik adalah...",
            "10. Diantara zat berikut, yang larutannya dalam air tidak dapat menghantarkan arus listrik adalah...",

    };

    public String jawaban[][] ={
            {"Larutan yang tidak menghasilkan gelembung gas", "Larutan yang tidak dapat digunakan sebagai infuse", "Larutan yang dapat menghasilkan arus listrik", "Hanya merupakan senyawa kovalen", "Hanya merupakan senyawa ion"},
            {"Dalam air, zat padat itu terurai menjadi ionnya","Dalam air, zat padat itu terurai menjadi atomnya","Dalam air, zat padat itu terurai menjadi molekulnya","Air menjadi mudah terionisasi bila ada zat padat didalamnya","Air menjadi konduktor listrik bila ada zat padat didalamnya"},
            {"Kation","Anion","Kation dan anion ","Molekul","Pelarut"},
            {"Elektrolit menghantarkan arus listrik, nonelektrolit menghasilkan gelembung gas","Elektrolit menghasilkan gelembung gas, nonelektrolit menghantarkan arus listrik","Elektrolit tidak terionisasi, nonelektrolit terionisasi","Elektrolit menghasilkan gelembung gas, nonelektrolit tidak menghasilkan listrik","Elektrolit terionisasi sempurna, nonelektrolit tidak terionisasi"},
            {"Arus mengalir dari kutub positif ke kutub negative","Media memungkinkan terjadinya aliran arus listrik","Lampu menyala saat listrik dialirkan","Terdapat ion ion di dalam larutan yang dapat bergerak bebas","Muncul gelembung gas"},

            {"Larutan area dan garam","Alcohol dan asam cuka","Garam dan asam cuka","Gula dan urea","Gula dan asam cuka"},
            {"Amoniak","Alcohol","Cuka","Garam dapur","Gula"},
            {"Tidak muncul gelembung gas","Lampu menyala terang","Lampu tidak menyala","Gelembung tidak terlihat","Muncul gelembung gas"},
            {"HCl(aq)","H<sub>2</sub>SO<sub>4</sub>(aq)","CO(NH<sub>2</sub>)<sub>2</sub>","KBr(l)","NaOH(aq)",},
            {"KOH","CaBr<sub>2</sub>","NaCl","CCl<sub>4</sub>","MgO"}
    };

    public String kunci[] ={
            "C",
            "A",
            "C",
            "D",
            "D",

            "D",
            "D",
            "E",
            "C",
            "D",
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
