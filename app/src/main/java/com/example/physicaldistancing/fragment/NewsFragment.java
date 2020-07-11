package com.example.physicaldistancing.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.physicaldistancing.R;
import com.example.physicaldistancing.adapter.NewsAdapter;
import com.example.physicaldistancing.model.NewsModel;
import com.example.physicaldistancing.viewmodel.NewsViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsViewModel newsViewModel;
    private NewsAdapter newsAdapter;
    private ProgressBar progressBar;
    private AnyChartView anyChartView;
    private ProgressBar progressBarCorona;
    private String getSembuh,getPositif,getMeninggal;



    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = view.findViewById(R.id.tv_rc);
        progressBar = view.findViewById(R.id.progres_bar);
        progressBarCorona = view.findViewById(R.id.pb_corona);
        anyChartView = view.findViewById(R.id.any_chart_view);




        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "https://api.kawalcorona.com/indonesia/";
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Pie pie = AnyChart.pie();
                List<DataEntry> dataEntries = new ArrayList<>();
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    getPositif = jsonObject.getString("positif");
                    getMeninggal = jsonObject.getString("meninggal");
                    getSembuh = jsonObject.getString("sembuh");

                    getPositif = getPositif.replaceAll(",","");
                    getMeninggal = getMeninggal.replaceAll(",","");
                    getSembuh = getSembuh.replaceAll(",","");

                    Long L_Positif = Long.parseLong(getPositif);
                    Long L_Meninggal = Long.parseLong(getMeninggal);
                    Long L_Sembuh = Long.parseLong(getSembuh);

                    if (L_Positif==null){
                        progressBarCorona.setVisibility(View.VISIBLE);
                    }else {
                        progressBarCorona.setVisibility(View.GONE);
                        dataEntries.add(new ValueDataEntry("Positif",L_Positif));
                        dataEntries.add(new ValueDataEntry("Meninggal",L_Meninggal));
                        dataEntries.add(new ValueDataEntry("Sembuh",L_Sembuh));
                        pie.data(dataEntries);
                        anyChartView.setChart(pie);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonArrayRequest);

        getNews();

        return view;
    }

    private void getNews(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        newsAdapter = new NewsAdapter();
        newsAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(newsAdapter);
        newsViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(NewsViewModel.class);
        newsViewModel.getNews().observe(this, new Observer<ArrayList<NewsModel>>() {
            @Override
            public void onChanged(ArrayList<NewsModel> newsModels) {
                if (newsModels != null){
                    newsAdapter.setNews(newsModels);

                }
            }
        });
        newsViewModel.setNewsShow();

        NewsModel newsModel = new NewsModel();
        if (newsModel.getTitle()!=null){
            progressBar.setVisibility(View.INVISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }

    }






}