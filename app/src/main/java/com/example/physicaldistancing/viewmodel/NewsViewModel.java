package com.example.physicaldistancing.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.physicaldistancing.BuildConfig;
import com.example.physicaldistancing.model.NewsModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<NewsModel>> list = new MutableLiveData<>();


    public void setNewsShow(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<NewsModel> newsModelArrayList = new ArrayList<>();
        String url = "http://newsapi.org/v2/top-headlines?country=id&apiKey=2afb93176dcd4198b16ccae08f9ddc99";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String articles = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(articles);
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        NewsModel newsModel = new NewsModel(object);
                        newsModelArrayList.add(newsModel);
                    }list.postValue(newsModelArrayList);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("error",error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<NewsModel>> getNews(){
        return list;
    }
}
