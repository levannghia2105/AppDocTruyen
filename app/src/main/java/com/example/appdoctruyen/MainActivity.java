package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appdoctruyen.api.apiGetManga;
import com.example.appdoctruyen.arapter.arapterManga;
import com.example.appdoctruyen.interFace.getManga;
import com.example.appdoctruyen.interFace.setClickManga;
import com.example.appdoctruyen.model.manga;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements getManga {

    RecyclerView recyManga ;
    arapterManga arapterManga ;
    SearchView searchView ;
    ArrayList<manga> arrayListManga ;
    ImageView imgUpdate ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         innit();
        anhxa();
        setUp();
        setClick();



    }



    private void innit() {
        arrayListManga = new ArrayList<>();
        new apiGetManga(this).execute();
        arapterManga = new arapterManga(MainActivity.this,arrayListManga);
    }
    private void anhxa() {
        recyManga = findViewById(R.id.recyManga);
        searchView = findViewById(R.id.searchView);
        imgUpdate = findViewById(R.id.imgUpdate);


    }
    private void setUp() {
        // set bố cục cho grilview
        recyManga.setAdapter(arapterManga);
        int spanCount =3 ;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,spanCount);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyManga.setLayoutManager(gridLayoutManager);

        //set hint cho seachView

        searchView.setQueryHint("hãy tìm kiếm tên truyện ");
    }
    private void setClick() {
      // set sự kiện cho nút searchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                 arapterManga.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arapterManga.getFilter().filter(newText);
                return false;
            }
        });
         //xử lí sự kiện trên nút bấm chọn truyện
        arapterManga.setSetClickManga(new setClickManga() {
            @Override
            public void ListennerManga(int pos , manga mManga) {
                Intent intent = new Intent(MainActivity.this,SelectChap.class);
                intent.putExtra("objManga" ,mManga);
                startActivity(intent);
            }
        });


    }


    @Override
    public void Start() {
        Toast.makeText(this,"đang lấy dữ liệu trả về",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void End(String data) {
        try {
            arrayListManga.clear();
            JSONArray array  = new JSONArray(data);
            for (int i= 0 ; i< array.length();i++){
                JSONObject object = array.getJSONObject(i);
                arrayListManga.add(new manga(object));
            }
            arapterManga.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void TryCacth() {

    }

    public void update(View view) {
        new apiGetManga(this).execute();

    }
}