package com.example.appdoctruyen.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class manga implements Parcelable {
    private String id ;
    private  String nameManga ;
    private String  nameChapManga ;
    private String imgManga ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public manga(JSONObject object){
        try {
            id = object.getString("id");
            nameManga = object.getString("nameManga");
            nameChapManga = object.getString("nameChapManga");
            imgManga = object.getString("imgManga");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public manga(String nameManga, String nameChapManga, String imgManga) {
        this.nameManga = nameManga;
        this.nameChapManga = nameChapManga;
        this.imgManga = imgManga;
    }

    protected manga(Parcel in) {
        id = in.readString();
        nameManga = in.readString();
        nameChapManga = in.readString();
        imgManga = in.readString();
    }

    public static final Creator<manga> CREATOR = new Creator<manga>() {
        @Override
        public manga createFromParcel(Parcel in) {
            return new manga(in);
        }

        @Override
        public manga[] newArray(int size) {
            return new manga[size];
        }
    };

    public String getNameManga() {
        return nameManga;
    }

    public void setNameManga(String nameManga) {
        this.nameManga = nameManga;
    }

    public String getNameChapManga() {
        return nameChapManga;
    }

    public void setNameChapManga(String nameChapManga) {
        this.nameChapManga = nameChapManga;
    }

    public String getImgManga() {
        return imgManga;
    }

    public void setImgManga(String imgManga) {
        this.imgManga = imgManga;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nameManga);
        dest.writeString(nameChapManga);
        dest.writeString(imgManga);


    }
}
