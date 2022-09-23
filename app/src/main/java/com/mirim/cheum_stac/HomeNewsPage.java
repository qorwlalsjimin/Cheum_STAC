package com.mirim.cheum_stac;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mirim.cheum_stac.News.News;
import com.mirim.cheum_stac.News.NewsList;

public class HomeNewsPage extends Fragment {
    MainActivity activity;
    static int id;
    TextView titleText, dateText;
    ImageView newsImg;
    News news;

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

    public HomeNewsPage() {
        // Required empty public constructor
    }

    public static HomeNewsPage newInstance() {
        return new HomeNewsPage();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_news_page, container, false);

        newsImg = v.findViewById(R.id.imgNews);
        titleText = v.findViewById(R.id.tvNTitle);
        dateText = v.findViewById(R.id.tvDate);

        news = (News) (NewsList.newsList.get(id));

        Button backBtn = v.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(HomeNews.newInstance());
            }
        });

        newsImg.setImageResource(news.news);
        titleText.setText(news.title);
        dateText.setText(news.days);

        return v;
    }

    public void displayMessage(String data) {
        id = Integer.parseInt(data);
    }
}