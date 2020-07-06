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
import android.widget.Toast;

import com.example.physicaldistancing.R;
import com.example.physicaldistancing.adapter.NewsAdapter;
import com.example.physicaldistancing.model.CoronaModel;
import com.example.physicaldistancing.model.NewsModel;
import com.example.physicaldistancing.viewmodel.CoronaViewModel;
import com.example.physicaldistancing.viewmodel.NewsViewModel;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsViewModel newsViewModel;
    private NewsAdapter newsAdapter;
    private ProgressBar progressBar;

    private CoronaModel coronaModel = new CoronaModel();
    private CoronaViewModel coronaViewModel;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = view.findViewById(R.id.tv_rc);
        progressBar = view.findViewById(R.id.progres_bar);



        coronaViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(CoronaViewModel.class);
        coronaViewModel.getCorona().observe(this, new Observer<ArrayList<CoronaModel>>() {
            @Override
            public void onChanged(ArrayList<CoronaModel> coronaModels) {

            }
        });

        coronaViewModel.setCorona();

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
                    Loading(false);
                }
            }
        });

        newsViewModel.setNewsShow();
        return view;
    }

    private void Loading(Boolean progress){
        if (progress == true){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
}