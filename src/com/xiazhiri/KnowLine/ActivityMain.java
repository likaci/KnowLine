package com.xiazhiri.KnowLine;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class ActivityMain extends Activity {
    /**
     * Called when the activity is first created.
     */
    Dialog authDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        authDialog = new Dialog(this);
        LinearLayout linerLayout = new LinearLayout(this);
        linerLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        WebView webView = new WebView(this);
        webView.setWebViewClient(new MyWebViewClient());

        String param2 = "http://sandbox.feedly.com/v3/auth/auth?client_id=sandbox&redirect_uri=http%3A%2F%2Fsandbox.feedly.com%2Ffeedly.html&scope=https%3A%2F%2Fcloud.feedly.com%2Fsubscriptions&response_type=code";
        webView.loadUrl(param2);
        linerLayout.addView(webView);
        authDialog.setContentView(linerLayout);
        authDialog.show();

    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.i("Url",url);
            Uri uri = Uri.parse(url);
            if (uri.getQueryParameter("code") != null) {
                Log.i("oauth OK",uri.getQueryParameter("code"));
                authDialog.hide();
            }
            super.onPageStarted(view, url, favicon);
        }
    }

}
