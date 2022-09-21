package com.mirim.cheum_stac;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mirim.cheum_stac.Product.Product;
import com.mirim.cheum_stac.Product.ProductList;
import com.mirim.cheum_stac.Product.fillProduct;

import java.util.ArrayList;

public class fill_detail extends Fragment {

    MainActivity activity;
    EditText editSearch;
    String SearchWord;
    ImageView imgSearch;
    ArrayList<fillProduct> list;
    Product product;
    RecyclerView recyclerView;
    RecyclerVIewAdapter adapter;
    GridLayoutManager layoutManager;
    fillProduct fillProduct;

    int id;
    static String kate = "hair";
    int img;
    boolean best;
    String name;
    String price;

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

    public static fill_detail newInstance() {
        return new fill_detail();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fill_detail, container, false);
        ImageButton backBtn = v.findViewById(R.id.backBtn);

        //리사이클러 아이템 클릭 리스너
        RecyclerVIewAdapter.setOnItemClickListener(new RecyclerVIewAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View v, int pos)
            {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance());
            }
        });

        //초기에 전체 내용 나오게 하는 거 필요

        list = new ArrayList<fillProduct>() {{
            for(int i=0; i<ProductList.productList.size(); i++){
                product = (Product) (ProductList.productList.get(i));
                if(kate.equals(product.kate)) {
                    add(new fillProduct(product.img, product.best, product.name, product.price));
                }
            }
        }};

        recyclerView = (RecyclerView)v.findViewById(R.id.fill_recycler);
        adapter = new RecyclerVIewAdapter(getActivity().getApplicationContext(), list);

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
                list = new ArrayList<fillProduct>() {{
                    for(int i=0; i<ProductList.productList.size(); i++){
                        product = (Product) (ProductList.productList.get(i));
                        if(product.name.contains(SearchWord)&&kate.equals(product.kate)){
                            add(new fillProduct(product.img, product.best, product.name, product.price));
                        }
                    }
                }};


                recyclerView = (RecyclerView)v.findViewById(R.id.fill_recycler);
                adapter = new RecyclerVIewAdapter(getActivity().getApplicationContext(), list);

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


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(FillFragment.newInstance());
            }
        });

        return v;
    }

    public void displayMessage(String data) {
        kate = data;
    }
}