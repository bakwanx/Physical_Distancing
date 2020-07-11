package com.example.physicaldistancing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.physicaldistancing.model.NewsModel;

public class Web_View extends AppCompatActivity {

    private WebView webView;
    public static final String kirimUrl = "kirim";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web__view);

        getSupportActionBar().hide();

        webView = findViewById(R.id.web_view);

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        NewsModel newsModel = getIntent().getParcelableExtra(kirimUrl);
        String getUrl = newsModel.getUrl();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(getUrl);
    }





}