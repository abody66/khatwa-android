package com.khatwa.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * خطوة — MicroStep
 *
 * This activity is intentionally just a WebView. All of the app's
 * HTML/CSS/JS lives inside the APK itself under
 * app/src/main/assets/www, so it is loaded from file:///android_asset/
 * and never touches the network. All app data (tasks, streak, theme)
 * is written by the page's own JavaScript to the WebView's
 * localStorage, which Android stores on-device under this app's
 * private data directory — nothing leaves the phone.
 */
public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        webView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        // Required so the page's own localStorage calls persist to disk.
        settings.setDomStorageEnabled(true);

        // Keep navigation inside the app instead of handing off to a browser.
        webView.setWebViewClient(new WebViewClient());

        setContentView(webView);

        webView.loadUrl("file:///android_asset/www/index.html");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
