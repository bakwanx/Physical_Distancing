package com.example.physicaldistancing.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsModel implements Parcelable {

    String name, author, title, description, publisedAt, content;
    String url, urlImage;
    String publish;

    protected NewsModel(Parcel in) {
        name = in.readString();
        author = in.readString();
        title = in.readString();
        description = in.readString();
        publisedAt = in.readString();
        content = in.readString();
        url = in.readString();
        urlImage = in.readString();
    }

    public NewsModel(JSONObject jsonObject){
        try {
            String getName = null;
            if (jsonObject.has("source")){
                JSONObject objSource = jsonObject.getJSONObject("source");
                 getName = objSource.getString("name");
            }

            String penulis = jsonObject.getString("author");
            String judul = jsonObject.getString("title");
            String deskripsi = jsonObject.getString("description");
            String urlNews = jsonObject.getString("url");
            String urlGambar = jsonObject.getString("urlToImage");
            String rilis = jsonObject.getString("publishedAt");
            String berita = jsonObject.getString("content");

            String cutRilis = rilis.substring(0,10);
            String New_Format_Rilis = "dd-MM-yyyy";
            String Old_Format_Rilis = "yyyy-MM-dd";

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Old_Format_Rilis);
            Date d = simpleDateFormat.parse(cutRilis);
            simpleDateFormat.applyPattern(New_Format_Rilis);
            String newDateString = simpleDateFormat.format(d);

            this.name = getName;
            this.author = penulis;
            this.title = judul;
            this.description = deskripsi;
            this.url = urlNews;
            this.urlImage = urlGambar;
            this.publisedAt = newDateString;
            this.content = berita;


        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static final Creator<NewsModel> CREATOR = new Creator<NewsModel>() {
        @Override
        public NewsModel createFromParcel(Parcel in) {
            return new NewsModel(in);
        }

        @Override
        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisedAt() {
        return publisedAt;
    }

    public void setPublisedAt(String publisedAt) {
        this.publisedAt = publisedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(author);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(publisedAt);
        parcel.writeString(content);
        parcel.writeString(url);
        parcel.writeString(urlImage);
    }
}

