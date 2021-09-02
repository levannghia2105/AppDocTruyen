package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.appdoctruyen.api.apiGetChapManga;
import com.example.appdoctruyen.arapter.arapterChapManga;
import com.example.appdoctruyen.interFace.getChapManga;
import com.example.appdoctruyen.interFace.setClickChapManga;
import com.example.appdoctruyen.model.chapManga;
import com.example.appdoctruyen.model.manga;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SelectChap extends AppCompatActivity implements getChapManga , setClickChapManga {
ImageView imageManga ;
TextView txtManga ;
RecyclerView recyChap ;
manga mManga ;
arapterChapManga arapterChapManga ;
ArrayList<chapManga> chapMangaArrayList = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_chap);
        innit();
        anhXa();
        setUp();
        setClick();
        new apiGetChapManga(this,mManga.getId()).execute();
    }

    private void setClick() {
        arapterChapManga.setSetClickChapManga(this);

    }

    private void setUp() {

        if (mManga!=null){
            Glide.with(SelectChap.this).load(mManga.getImgManga()).into(imageManga);
            txtManga.setText(mManga.getNameManga()+"");
        }

        arapterChapManga = new arapterChapManga(SelectChap.this,chapMangaArrayList);
        recyChap.setAdapter(arapterChapManga);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyChap.setLayoutManager(linearLayoutManager);
    }

    private void anhXa() {
        txtManga = findViewById(R.id.txtName);
        imageManga=findViewById(R.id.imgNameManga);
        recyChap = findViewById(R.id.recyChap);
    }

    private void innit() {
        // trả dữ liệu bên activity truyền sang
        Intent intent = getIntent() ;
        mManga = intent.getParcelableExtra("objManga");


    }

    @Override
    public void Start() {
        Toast.makeText(getApplicationContext(),"Đang Lấy Chap Về ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void End(String data) {
                 chapMangaArrayList.clear();
        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i=  0 ; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                chapMangaArrayList.add(new chapManga(jsonObject));

            }
            arapterChapManga.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void TryCath() {

    }

    // set Sự kiện khi bấm vào chap
    @Override
    public void setClickChap(chapManga chap) {
        Intent intent = new Intent(SelectChap.this,ReadManga.class);
        intent.putExtra("stringIdChap",chap.getIdChap());
        startActivity(intent);
    }
}