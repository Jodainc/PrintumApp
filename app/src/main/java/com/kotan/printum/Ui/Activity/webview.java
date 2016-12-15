package com.kotan.printum.Ui.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kotan.printum.R;

public class Webview extends AppCompatActivity {
    public static final String TAG = "Certica";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        String C8pROTECCION = getIntent().getStringExtra("EXTRA_SESSION_ID");
        Log.d(TAG, "dataModelArrayList jsonparser" );
        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(C8pROTECCION);
    }
}
