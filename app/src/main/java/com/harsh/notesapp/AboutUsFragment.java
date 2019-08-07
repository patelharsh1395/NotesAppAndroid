package com.harsh.notesapp;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;

public class AboutUsFragment extends Fragment {

    private WebView webView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_us_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        this.webView = view.findViewById(R.id.myWebView);
        this.webView.setWebViewClient(new WebViewClient());
        this.webView.loadUrl("https://www.google.com");



         AssetManager mAssetManager = getActivity().getAssets(); //this.getAssets();
         try
          {
          InputStream mInputStream = mAssetManager.open("aboutus.html");
          int size = mInputStream.available();
          byte[] buffer = new byte[size];
         mInputStream.read(buffer);
          mInputStream.close();
          String content = new String(buffer, "UTF-8");

          Log.d("DATA", content);
         webView.loadData(content, "text/html", "utf-8");
          } catch (IOException e)
         {
         e.printStackTrace();
          }




    }


}
