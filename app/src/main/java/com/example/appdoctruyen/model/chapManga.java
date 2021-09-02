package com.example.appdoctruyen.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class chapManga  implements Parcelable {
    private String idChap ;
    private String nameChap ;
    private String  dateSubmit ;

    protected chapManga(Parcel in) {
        idChap = in.readString();
        nameChap = in.readString();
        dateSubmit = in.readString();
    }

    public static final Creator<chapManga> CREATOR = new Creator<chapManga>() {
        @Override
        public chapManga createFromParcel(Parcel in) {
            return new chapManga(in);
        }

        @Override
        public chapManga[] newArray(int size) {
            return new chapManga[size];
        }
    };

    public String getIdChap() {
        return idChap;
    }

    public void setIdChap(String idChap) {
        this.idChap = idChap;
    }

    public chapManga(JSONObject o ){
       try {
           this.idChap = o.getString("id");
           this.nameChap = o.getString("nameChap");
           this.dateSubmit= o.getString("datesubmit");
       } catch (JSONException e) {
           e.printStackTrace();
       }

   }
    public chapManga(String nameChap, String dateSubmit) {
        this.nameChap = nameChap;
        this.dateSubmit = dateSubmit;
    }

    public String getNameChap() {
        return nameChap;
    }

    public void setNameChap(String nameChap) {
        this.nameChap = nameChap;
    }

    public String getDateSubmit() {
        return dateSubmit;
    }

    public void setDateSubmit(String dateSubmit) {
        this.dateSubmit = dateSubmit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idChap);
        dest.writeString(nameChap);
        dest.writeString(dateSubmit);

    }
}
