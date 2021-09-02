package com.example.appdoctruyen.arapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appdoctruyen.R;
import com.example.appdoctruyen.interFace.setClickManga;
import com.example.appdoctruyen.model.manga;

import java.util.ArrayList;

public class arapterManga extends RecyclerView.Adapter<arapterManga.holer> implements Filterable {
    Context mContext;
    ArrayList<manga> arrayListManga;
    ArrayList<manga> arrayListMangaOld;
    setClickManga setClickManga ;
    private manga mManga;

    public void setSetClickManga(com.example.appdoctruyen.interFace.setClickManga setClickManga) {
        this.setClickManga = setClickManga;
    }

    public arapterManga(Context mContext, ArrayList<manga> arrayListManga) {
        this.mContext = mContext;
        this.arrayListManga = arrayListManga;
        this.arrayListMangaOld = arrayListManga;
    }

    @NonNull
    @Override
    public holer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_manga, parent, false);

        return new holer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holer holder, int position) {
        manga mManga = arrayListManga.get(position);
        holder.mManga1 = mManga;
        holder.txtNameChap.setText(mManga.getNameChapManga());
        holder.txtNameManga.setText(mManga.getNameManga());
        Glide.with(mContext).load(mManga.getImgManga()).into(holder.imgManga);
    }

    @Override
    public int getItemCount() {
        if (arrayListManga != null) {
            return arrayListManga.size();
        } else
            return 0;

    }


    public class holer extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgManga;
        TextView txtNameManga;
        TextView txtNameChap;
        manga mManga1 ;

        public holer(@NonNull View itemView) {
            super(itemView);
            imgManga = itemView.findViewById(R.id.imgManga);
            txtNameManga = itemView.findViewById(R.id.txtNameManga);
            txtNameChap = itemView.findViewById(R.id.txtChapManga);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            setClickManga.ListennerManga(getAdapterPosition(),mManga1);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString().trim();
                if (strSearch.isEmpty()) {
                    arrayListManga = arrayListMangaOld;
                } else {
                    ArrayList<manga> arrayList = new ArrayList<>();
                    for (int i = 0; i < arrayListMangaOld.size(); i++) {
                        if (arrayListMangaOld.get(i).getNameManga().toLowerCase().trim().contains(strSearch.toLowerCase())) {
                            arrayList.add(arrayListMangaOld.get(i));

                        }
                        arrayListManga = arrayList;
                    }
                }
                FilterResults results = new FilterResults();
                results.values = arrayListManga;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arrayListManga = (ArrayList<manga>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
