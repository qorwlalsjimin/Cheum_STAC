package com.mirim.cheum_stac;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class MypageCheck extends Fragment {
    public MypageCheck() {
        // Required empty public constructor
    }

    public static MypageCheck newInstance(String param1, String param2) {
        MypageCheck fragment = new MypageCheck();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mypage_check, container, false);
    }
}