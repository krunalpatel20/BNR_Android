package com.bignerdranch.android.nerdkart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by krunalpa on 3/16/17.
 */

public class KartFragment extends Fragment {

    private int mCash = 1000;
    private TextView mCashTextView;
    private WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kart, container, false);

        mCashTextView = (TextView) v.findViewById(R.id.cash_text_view);
        mWebView = (WebView) v.findViewById(R.id.web_view);

        updateCash();

        return v;
    }

    private void updateCash() {
        mCashTextView.setText("$" + mCash);
    }
}
