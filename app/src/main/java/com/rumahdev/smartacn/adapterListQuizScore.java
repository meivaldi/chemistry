package com.rumahdev.smartacn;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mloi on e/17/2017.
 */
public class adapterListQuizScore extends ArrayAdapter<String> {
    private final Activity context;
    private ArrayList<String> nama, skor;
    String alamat;
    // private final Integer[] imageId;
    public adapterListQuizScore(Activity context,
                                ArrayList<String> nama, ArrayList<String> skor) {
        super(context, R.layout.listview_quiz, nama);
        this.context = context;
        this.nama= nama;
        this.skor = skor;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.item_quiz_score, null, true);
        TextView nama1 = (TextView) rowView.findViewById(R.id.nama);
        TextView skor1 = (TextView) rowView.findViewById(R.id.skor);
        //ImageView fo = (ImageView)rowView.findViewById(R.id.diprosesfoto);

        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        if(nama.get(position).equals("0")){

        }
        else {
            nama1.setText(nama.get(position));
            skor1.setText(skor.get(position));
        }
        return rowView;
    }

}