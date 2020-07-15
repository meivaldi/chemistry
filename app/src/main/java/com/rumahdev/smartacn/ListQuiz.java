package com.rumahdev.smartacn;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import static android.view.View.GONE;

public class ListQuiz extends AppCompatActivity {

    ArrayList<String> id_quiz;
    // UI references.

    String id_teknisi;
    private static final String Register_url = "http://emdastra.com/project-smartacn/quiz-list";
    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    adapterListQuiz adapter;
    ArrayList<HashMap<String, String>> inboxList;
    TextView empty;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quiz);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        empty = (TextView) findViewById(R.id.empty);

        id_quiz = new ArrayList<String>();
        listView = (ListView) findViewById(android.R.id.list);

        //listView.setDivider(null);
        empty = (TextView) findViewById(R.id.empty);
        empty.setVisibility(GONE);

        new GetData2().execute("http://emdastra.com/project-smartacn/quiz-list");


        inboxList = new ArrayList<HashMap<String, String>>();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipediproses);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                id_quiz.clear();
                new GetData2().execute("http://emdastra.com/project-smartacn/quiz-list");
            }
        });



    }



    private class GetData2 extends AsyncTask<String, Void, String> {

        // Instansiasi class dialog
        ProgressDialog dialog = new ProgressDialog(ListQuiz.this);

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
                Content = CustomHttpClient.executeHttpGet("http://emdastra.com/project-smartacn/quiz-list");
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
                Toast.makeText(ListQuiz.this, "Error Connection Internet",
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
                            id_quiz.add(menuitemArray.getJSONObject(i)
                                    .getString("id_quiz").toString());
                        }
                    }


                    if(id_quiz.isEmpty()){
                        empty.setVisibility(View.VISIBLE);
                    }

                    else{
                        empty.setVisibility(View.GONE);
                    }


                    //jlh=menuitemArray.length();
                    adapter = new adapterListQuiz(ListQuiz.this, id_quiz);
                    listView.setAdapter(adapter);

                    listView.setClickable(true);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapter, View v, int position,
                                                long arg3) {
                            Intent i = new Intent(ListQuiz.this, ListQuizScore.class);
                            i.putExtra("id_quiz", String.valueOf(id_quiz.get(position)));
                            startActivity(i);
                        }
                    });

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

