package com.mirim.cheum_stac;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MypageAfterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MypageAfterFragment extends Fragment {

    public MypageAfterFragment() {
        // Required empty public constructor
    }

    public static MypageAfterFragment newInstance(String param1, String param2) {
        MypageAfterFragment fragment = new MypageAfterFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mypage, container, false);
    }
}