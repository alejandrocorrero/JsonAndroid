package com.example.usuario.jsonandroid;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ListView lstJson;
    private static final String DATA_URL = "http://www.informaticasaladillo.es/datos.json";
    private static final int TIMEOUT = 5000;
    MainActivityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        lstJson = findViewById(R.id.lstJson);
        String content = null;
        ArrayList<User> data = null;
        adapter= new MainActivityAdapter(this,data);
        lstJson.setAdapter(adapter);
        try {
           new LoadStudentsAsyncTask().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }
    @SuppressLint("StaticFieldLeak")
    class LoadStudentsAsyncTask extends AsyncTask<Void, Void, ArrayList<User>> {

        private static final String DATA_URL = "http://www.informaticasaladillo.es/datos.json";
        private static final int TIMEOUT = 5000;

        @Override
        protected ArrayList<User> doInBackground(Void... voids) {
            String content = null;
            try {
                content = NetworkUtils.loadUrl(DATA_URL, TIMEOUT);
            } catch (IOException e) {
                e.printStackTrace();

            }
            ArrayList<User> data = null;
            if (content != null) {
                /*
                try {
                    data = parseJson(content);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return StudentsRequest.newErrorInstance(e);
                }
                */
                Gson gson = new Gson();
                Type studentListType = new TypeToken<List<User>>() {
                }.getType();
                data = gson.fromJson(content, studentListType);
                return data;

            }
            return data;
        }
        @Override
        protected void onPostExecute(ArrayList<User> data) {
        adapter.setData(data);
        }
    }


    }
