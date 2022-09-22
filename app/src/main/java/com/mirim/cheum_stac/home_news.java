package com.mirim.cheum_stac;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mirim.cheum_stac.News.News;
import com.mirim.cheum_stac.News.NewsList;
import com.mirim.cheum_stac.News.homeNews;
import com.mirim.cheum_stac.Product.Product;
import com.mirim.cheum_stac.Product.ProductList;
import com.mirim.cheum_stac.Product.fillProduct;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_news#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_news extends Fragment {
    MainActivity activity;
    RecyclerView recyclerView;
    RecyclerNewsAdapter adapter;
    GridLayoutManager layoutManager;
    ImageView imgSearch;
    EditText editSearch;
    String SearchWord;

    News news;
    ArrayList<homeNews> list;

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
    public home_news() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new home_news();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_news, container, false);

        //초기에 전체 내용
        list = new ArrayList<homeNews>() {{
            for(int i = 0; i< NewsList.newsList.size(); i++){
                news =  (News) (NewsList.newsList.get(i));
                add(new homeNews(news.mimg, news.best, news.title));
            }
        }};

        recyclerView = (RecyclerView)v.findViewById(R.id.home_recycler);
        adapter = new RecyclerNewsAdapter(getActivity().getApplicationContext(), list);

        layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 6);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int gridPosition = position % 2;
                switch (gridPosition) {
                    case 0:
                    case 1:
                        return 3;
                }
                return 0;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        imgSearch = v.findViewById(R.id.img_search_icon);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editSearch = v.findViewById(R.id.edit_search_text);
                SearchWord = editSearch.getText().toString();
                list = new ArrayList<homeNews>() {{
                    for(int i = 0; i< NewsList.newsList.size(); i++){
                        news =  (News) (NewsList.newsList.get(i));
                        if(news.title.contains(SearchWord)) {
                            add(new homeNews(news.mimg, news.best, news.title));
                        }
                    }
                }};


                recyclerView = (RecyclerView)v.findViewById(R.id.home_recycler);
                adapter = new RecyclerNewsAdapter(getActivity().getApplicationContext(), list);

                layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 6);
                layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int gridPosition = position % 2;
                        switch (gridPosition) {
                            case 0:
                            case 1:
                                return 3;
                        }
                        return 0;
                    }
                });

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

        });
        Button videoBtn = v.findViewById(R.id.videoBtn);
        videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(home_video.newInstance());
            }
        });
        Button newsBtn = v.findViewById(R.id.newsBtn);
        newsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(home_news.newInstance());
            }
        });

        RecyclerNewsAdapter.setOnItemClickListener(new RecyclerVIewAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View v, int pos)
            {

                ((MainActivity)getActivity()).replaceFragment(home_news_page.newInstance());
            }
        });


        return v;
    }
}