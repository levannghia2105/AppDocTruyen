package com.example.appdoctruyen.arapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen.R;
import com.example.appdoctruyen.interFace.setClickChapManga;
import com.example.appdoctruyen.model.chapManga;

import java.util.ArrayList;


public class arapterChapManga extends RecyclerView.Adapter<arapterChapManga.holer> implements setClickChapManga {
    Context mContext ;
    ArrayList<chapManga> chapMangaArrayList ;
    setClickChapManga setClickChapManga ;

    public void setSetClickChapManga(com.example.appdoctruyen.interFace.setClickChapManga setClickChapManga) {
        this.setClickChapManga = setClickChapManga;
    }

    public arapterChapManga(Context mContext, ArrayList<chapManga> chapMangaArrayList) {
        this.mContext = mContext;
        this.chapMangaArrayList = chapMangaArrayList;
    }

    @NonNull
    @Override
    public arapterChapManga.holer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(mContext).inflate(R.layout.custom_item_chap_manga,parent,false);

        return new  holer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull arapterChapManga.holer holder, int position) {
            chapManga chapManga = chapMangaArrayList.get(position);
            holder.mChapManga = chapManga ;
            holder.txtNameChap.setText(chapManga.getNameChap());
            holder.txtDateSubmit.setText(chapManga.getDateSubmit());
    }

    @Override
    public int getItemCount() {
        if (chapMangaArrayList!=null){
            return chapMangaArrayList.size() ;
        }
        else
        return 0;
    }

    @Override
    public void setClickChap(chapManga chap) {

    }

    public class holer extends RecyclerView.ViewHolder implements View.OnClickListener {
       TextView txtNameChap ;
       TextView txtDateSubmit ;
       chapManga mChapManga ;
        public holer(@NonNull View itemView) {
            super(itemView);
            txtNameChap = itemView.findViewById(R.id.txtNameChap);
            txtDateSubmit = itemView.findViewById(R.id.txtDateSubmit);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            setClickChapManga.setClickChap(mChapManga);
        }
    }
}
