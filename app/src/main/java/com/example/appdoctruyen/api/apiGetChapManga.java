package com.example.appdoctruyen.api;

import android.os.AsyncTask;

import com.example.appdoctruyen.interFace.getChapManga;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class apiGetChapManga extends AsyncTask<Void,Void,Void> {
    String data ;
    getChapManga getChapManga ;
    String idtruyen ;




    public apiGetChapManga(com.example.appdoctruyen.interFace.getChapManga getChapManga,String idtruyen) {
        this.getChapManga = getChapManga;
        getChapManga.Start();
        this.idtruyen = idtruyen ;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://levannghia2105.000webhostapp.com/layChap.php?id="+idtruyen).build();
        data=null ;
        try{
            Response response  = okHttpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            data = responseBody.string() ;
        }catch (IOException e) {
            data = null ;
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        if (data==null){
            getChapManga.TryCath();
        }else
            getChapManga.End(data);
    }
}
