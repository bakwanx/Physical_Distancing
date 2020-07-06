package com.example.physicaldistancing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.physicaldistancing.model.NewsModel;

public class Web_View extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web__view);
        webView = findViewById(R.id.web_view);


        NewsModel newsModel = getIntent().getParcelableExtra("kirimUrl");
        webView.loadUrl(newsModel.getUrl());
        webView.setWebViewClient(new WebViewClient());
    }


}