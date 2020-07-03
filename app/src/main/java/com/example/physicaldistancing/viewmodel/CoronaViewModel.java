package com.example.physicaldistancing.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.physicaldistancing.model.CoronaModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class CoronaViewModel extends ViewModel {

    private MutableLiveData<ArrayList<CoronaModel>> ListCorona = new MutableLiveData<>();

    public void setCorona(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<CoronaModel> coronaArrayList = new ArrayList<>();
        String url = "https://api.kawalcorona.com/indonesia/";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = "";
                    JSONArray jsonArray = new JSONArray(result);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    CoronaModel corona = new CoronaModel(jsonObject);
                    coronaArrayList.add(corona);
                    ListCorona.postValue(coronaArrayList);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public LiveData<ArrayList<CoronaModel>> getCorona(){
        return ListCorona;
    }

}
