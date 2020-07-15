package com.rumahdev.smartacn;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static android.view.View.GONE;

public class ListQuizScore extends AppCompatActivity {

    ArrayList<String> nama, skor;
    // UI references.

    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    adapterListQuizScore adapter;
    ArrayList<HashMap<String, String>> inboxList;
    TextView empty;
    String id_quiz;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        empty = (TextView) findViewById(R.id.empty);

        nama=new ArrayList<String>();
        skor=new ArrayList<String>();

        Intent intent = getIntent();
        id_quiz = intent.getStringExtra("id_quiz");

        listView = (ListView) findViewById(android.R.id.list);

        //Toast.makeText(context, id_quiz, Toast.LENGTH_SHORT).show();

        //listView.setDivider(null);
        empty = (TextView) findViewById(R.id.empty);
        empty.setVisibility(GONE);

        //Toast.makeText(context, "http://emdastra.com/project-smartacn/quiz-list-score?id_quiz="+id_quiz, Toast.LENGTH_LONG).show();
        new GetData2().execute("http://emdastra.com/project-smartacn/quiz-list-score?id_quiz="+id_quiz);


        inboxList = new ArrayList<HashMap<String, String>>();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipediproses);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                nama.clear();
                skor.clear();
                new GetData2().execute("http://emdastra.com/project-smartacn/quiz-list-score?id_quiz="+id_quiz);
            }
        });



    }



    private class GetData2 extends AsyncTask<String, Void, String> {

        // Instansiasi class dialog
        ProgressDialog dialog = new ProgressDialog(ListQuizScore.this);

        String Content;
        String Error = null;
        // membuat object class JSONObject yang digunakan untuk menangkap data
        // dengan format json
        JSONObject jObject;
        // instansiasi class ArrayList
        ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();

        @Override
        protected String doInBackground(String... params) {
            try {
                Content = CustomHttpClient.executeHttpGet("http://emdastra.com/project-smartacn/quiz-list-score?id_quiz="+id_quiz);
            } catch (ClientProtocolException e) {
                Error = e.getMessage();
                cancel(true);
            } catch (IOException e) {
                Error = e.getMessage();
                cancel(true);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return Content;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // menampilkan dialog pada saat proses pengambilan data dari
            // internet
            this.dialog.setMessage("Loading Data..");
            this.dialog.show();
            dialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected void onPostExecute(String result) {
            // menutup dialog saat pengambilan data selesai
            //Toast.makeText(ListTeknisi.this, Content, Toast.LENGTH_LONG).show();
            this.dialog.dismiss();
            if (Error != null) {
                Toast.makeText(ListQuizScore.this, "Error Connection Internet",
                        Toast.LENGTH_LONG).show();
            } else {
                try {
                    // instansiasi kelas JSONObject
                    jObject = new JSONObject(Content);
                    //Toast.makeText(ListTeknisi.this, String.valueOf(Content), Toast.LENGTH_LONG).show();
                    // mengubah json dalam bentuk array
                    JSONArray menuitemArray = jObject.getJSONArray("select");



                    if(menuitemArray != null) {
                        for (int i = 0; i < menuitemArray.length(); i++) {
                            nama.add(menuitemArray.getJSONObject(i)
                                    .getString("nama").toString());
                            skor.add(menuitemArray.getJSONObject(i)
                                    .getString("skor").toString());
                        }
                        empty.setVisibility(View.GONE);
                    }

                    else{
                        empty.setVisibility(View.VISIBLE);
                    }


                    //jlh=menuitemArray.length();
                    adapter = new adapterListQuizScore(ListQuizScore.this, nama, skor);
                    listView.setAdapter(adapter);


                    // instansiasi class ListAdapter (Buka class ListAdapter)
                    /*adapter = new ListNotification(getActivity().getBaseContext(),id, status, jenis, tujuan);
                    setListAdapter(adapter);*/

                } catch (JSONException ex) {
                    /*Logger.getLogger(Beranda.class.getName()).log(
                            Level.SEVERE, null, ex);*/
                    Toast.makeText(getApplicationContext(), String.valueOf(Content), Toast.LENGTH_LONG).show();
                }

            }
        }
    }





    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

