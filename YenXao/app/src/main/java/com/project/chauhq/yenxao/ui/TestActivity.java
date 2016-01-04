package com.project.chauhq.yenxao.ui;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.project.chauhq.yenxao.BaseActivity;
import com.project.chauhq.yenxao.R;
import com.project.chauhq.yenxao.api.RequestApi;
import com.project.chauhq.yenxao.model.Authentication;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by chauhq on 23/12/2015.
 */
@EActivity(R.layout.activity_test)
public class TestActivity extends BaseActivity {
    @ViewById(R.id.web)
    WebView web;

    @Override
    protected void afterViews() {
        if (Build.VERSION.SDK_INT >= 19) {
            web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("https://accounts.google.com/o/oauth2/v2/auth?scope=email%20profile&redirect_uri=https://www.google.com.vn&response_type=code&client_id=358485521771-db0fchbhrkbli8q03tmpf7t8gemimghf.apps.googleusercontent.com");
        web.setWebViewClient(new MyWebviewCLient());
    }

    private class MyWebviewCLient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Uri uri = Uri.parse(url);
            String code = uri.getQueryParameter("code");
            if(code != null) {
                Log.d("xxxxx", "xxxxx" + code);
                Call<Authentication> call = RequestApi.requestGoogleAPi().getAcessToken(code,
                        "358485521771-db0fchbhrkbli8q03tmpf7t8gemimghf.apps.googleusercontent.com",
                        "GnPFz9dtWfY8nfIun1UsEjE5",
                        "https://www.google.com.vn",
                        "authorization_code");
                call.enqueue(new Callback<Authentication>() {
                    @Override
                    public void onResponse(Response<Authentication> response, Retrofit retrofit) {

                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }

        }
    }
}
