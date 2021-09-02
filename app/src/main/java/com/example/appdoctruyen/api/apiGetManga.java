package com.example.appdoctruyen.api;

import android.os.AsyncTask;

import com.example.appdoctruyen.interFace.getManga;

import java.io.IOException;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class apiGetManga extends AsyncTask<Void,Void,Void> {
    String data ;
    getManga getManga ;

    public apiGetManga(com.example.appdoctruyen.interFace.getManga getManga) {
        this.getManga = getManga;
        this.getManga.Start();
    }


    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request
                .Builder()
                .url("https://levannghia2105.000webhostapp.com/laytruyen.php")
                .build();
        data = null;
        try{
            // lấy ra data trên internet về
            Response response = okHttpClient
                    .newCall(request)
                    .execute();
            // trả về cái body trong link
            ResponseBody body = response.body();
            data= body.string();

        }catch (IOException e){
            data = null ;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        if (data==null){
            this.getManga.TryCacth();
        }
        else
            this.getManga.End(data);

        super.onPostExecute(unused);

    }
}
