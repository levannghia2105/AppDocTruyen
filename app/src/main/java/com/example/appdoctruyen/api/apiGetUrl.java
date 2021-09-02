package com.example.appdoctruyen.api;

import android.os.AsyncTask;

import com.example.appdoctruyen.interFace.getUrlPageManga;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class apiGetUrl extends AsyncTask<Void,Void,Void> {
    String data ;
    getUrlPageManga getUrlPageManga ;
    String id ;


    public apiGetUrl(com.example.appdoctruyen.interFace.getUrlPageManga getUrlPageManga, String id) {
        this.getUrlPageManga = getUrlPageManga;
        getUrlPageManga.Start();
        this.id = id;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request
                .Builder()
                .url("https://levannghia2105.000webhostapp.com/layPageManga.php?id="+id)
                .build();
        data = null ;
        try{
            // lấy ra data trên internet về
            Response response = okHttpClient
                    .newCall(request)
                    .execute();
            // trả về cái body trong link
            ResponseBody body = response.body();
            data= body.string();

        } catch (IOException e) {
            data= null ;


        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        if (data == null) {
            getUrlPageManga.TryCath();
        }
        else
          getUrlPageManga.End(data);
    }
}
