package com.mirim.cheum_stac;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class MypageCustomer extends Fragment {

    public MypageCustomer() {
        // Required empty public constructor
    }

    public static MypageCustomer newInstance(String param1, String param2) {
        MypageCustomer fragment = new MypageCustomer();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mypage__customer, container, false);
    }
}