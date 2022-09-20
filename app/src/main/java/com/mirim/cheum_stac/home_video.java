package com.mirim.cheum_stac;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_video#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_video extends Fragment {
    MainActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public home_video() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new home_video();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_video, container, false);
        Button videoBtn = v.findViewById(R.id.videoBtn);
        videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(home_video.newInstance());
            }
        });
        Button newsBtn = v.findViewById(R.id.newsBtn);
        newsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(home_news.newInstance());
            }
        });
        TextView uptitle = v.findViewById(R.id.uptitle);
        uptitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(HomeFragment.newInstance());
            }
        });
        TextView title = v.findViewById(R.id.title);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(HomeFragment.newInstance());
            }
        });
        ImageView video1 = v.findViewById(R.id.video1);
        video1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/85X8IpaoNu4"));
                startActivity(intent);
            }
        });
        ImageView video2 = v.findViewById(R.id.video2);
        video2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/85X8IpaoNu4"));
                startActivity(intent);
            }
        });
        return v;
    }
}