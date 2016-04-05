package com.dddp.fakedianpian.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dddp.fakedianpian.R;

/**
 * Created by Bruce J on 2016/4/5.
 */
public class FragmentDiscovery extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discovery_index, null );
        return view;
    }
}
