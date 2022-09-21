package com.mirim.cheum_stac;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Intro4 extends Fragment {

    Activity Viewpager_main;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Viewpager_main = (Viewpager_main) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Viewpager_main = null;
    }
    public Intro4() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new Intro4();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.intro_4p, container, false);

        return rootView;
    }
}