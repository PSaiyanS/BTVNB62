package com.example.btvnb62;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        WebView webView = findViewById(R.id.webview);
        String newsUrl = getIntent().getStringExtra("NEWS_URL");

        // Load URL vào WebView
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(newsUrl);
    }
}
