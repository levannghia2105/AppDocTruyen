package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.appdoctruyen.api.apiGetUrl;
import com.example.appdoctruyen.interFace.getUrlPageManga;
import com.example.appdoctruyen.model.chapManga;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ReadManga extends AppCompatActivity implements View.OnClickListener , getUrlPageManga {
ImageView imgBack , imgNext ,imgPageManga;
ArrayList<String> pageMangaList ;
int pos = 0 ;
    String id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_manga);
        innit();
        anhxa();
        setup();
        setClick();
        new apiGetUrl(this,id).execute();


    }

    private void setClick() {
        imgBack.setOnClickListener(this);
        imgNext.setOnClickListener(this);
    }

    private void setup() {
        if (pageMangaList.size()==0){
            return;
        }
        else
            Glide.with(this).load(pageMangaList.get(pos)).into(imgPageManga);
    }

    private void anhxa() {
        imgBack = findViewById(R.id.imgBack);
        imgNext = findViewById(R.id.imgNext);
        imgPageManga = findViewById(R.id.imgPageManga);
    }

    private void innit() {
        pageMangaList = new ArrayList<>();

        Intent intent = getIntent();
        id = intent.getStringExtra("stringIdChap");



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBack:
                backComic(pos);
                setup();
                break;
            case R.id.imgNext:
                nextComic(pos);
                setup();
                break;
        }

    }

    private void nextComic(int pos1) {
        int size = pageMangaList.size()-1;
        if (pos1 < size){
            pos1++;
            pos = pos1 ;


        }
        else {
            return;
        }
    }

    // thực hiện quay lại truyện
    private void backComic(int pos1) {
        if (pos1==0){
            return;
        }
        else
            pos1-- ;
            pos= pos1 ;
    }

    @Override
    public void Start() {
        Toast.makeText(this, "đang tải truyện vui lòng đợi",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void End(String data) {
        pageMangaList.clear();
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0 ; i<jsonArray.length();i++) {
                pageMangaList.add(jsonArray.getString(i));
            }
            Glide.with(this).load(pageMangaList.get(pos)).into(imgPageManga);



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void TryCath() {

    }
}