package com.rumahdev.smartacn;

import android.app.Activity;
import android.text.Html;
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
public class adapterListQuiz extends ArrayAdapter<String> {
    private final Activity context;
    private ArrayList<String> id_quiz;
    String alamat;
    // private final Integer[] imageId;
    public adapterListQuiz(Activity context,
                             ArrayList<String> id_quiz) {
        super(context, R.layout.listview_quiz, id_quiz);
        this.context = context;
        this.id_quiz = id_quiz;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.item_quiz, null, true);
        TextView nama1 = (TextView) rowView.findViewById(R.id.nama);
        ImageView foto_profil1 = (ImageView) rowView.findViewById(R.id.foto_profil);
        //ImageView fo = (ImageView)rowView.findViewById(R.id.diprosesfoto);

        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        if(id_quiz.get(position).equals("0")){

        }
        else {
            nama1.setText(id_quiz.get(position));
        }
        return rowView;
    }

}