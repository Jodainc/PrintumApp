package com.kotan.printum.Ui.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kotan.printum.R;

public class webview extends AppCompatActivity {
    private String C8Codigo;
    private String c8epp;
    private String c8mASiNFO;
    private String C8pROTECCION;
    public webview(String c8codigo1,String c8epp1,String c8mASiNFO1,String c8pROTECCION1){
    this.C8Codigo =c8codigo1;
        this.c8epp= c8epp1;
            this.c8mASiNFO = c8mASiNFO1;
                this.C8pROTECCION= c8pROTECCION1;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(C8pROTECCION);
    }
}
