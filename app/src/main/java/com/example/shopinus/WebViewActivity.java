package com.example.shopinus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class WebViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        WebView webView = (WebView) findViewById(R.id.webview);
        Log.e("Position:",""+intent.getIntExtra("position",0));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(MainActivity.list.get(intent.getIntExtra("position",0)).getSourceURL());

    }
}
