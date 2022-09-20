package com.mirim.cheum_stac;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mirim.cheum_stac.Product.Product;
import com.mirim.cheum_stac.Product.ProductList;
import com.mirim.cheum_stac.Product.fillProduct;

import java.util.ArrayList;

public class FillFragment extends Fragment{
    MainActivity activity;
    FragmentListener fragmentListener;
    FragmentManager fragmentManager;
    EditText editSearch;
    String SearchWord;
    ImageView imgSearch;
    RecyclerView recyclerView;
    RecyclerVIewAdapter adapter;
    GridLayoutManager layoutManager;
    fillProduct fillProduct;

    //상품 정보, 레이아웃 정보 list
    Product product;
    ArrayList<fillProduct> list;

    //상품 값
    int id;
    String kate;
    int img;
    boolean best;
    String name;
    String price;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof FragmentListener) fragmentListener = (FragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(fragmentListener != null) fragmentListener = null;
    }

    public FillFragment() {
        // Required empty public constructor
    }

    public static FillFragment newInstance() {
        return new FillFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance());
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fill, container, false);

        //초기에 전체 내용 나오게 하는 거 필요
        /*
        list = new ArrayList<fillProduct>() {{
            for(int i=0; i<ProductList.productList.size(); i++){
                product = (Product) (ProductList.productList.get(i));
                add(new fillProduct(product.img, product.best, product.name, product.price));
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
        */

        //검색기능
        editSearch = v.findViewById(R.id.edit_search_text);
        SearchWord = editSearch.getText().toString();
        imgSearch = v.findViewById(R.id.img_search_icon);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list = new ArrayList<fillProduct>() {{
                    for(int i=0; i<ProductList.productList.size(); i++){
                        product = (Product) (ProductList.productList.get(i));
                        if(product.name.contains(SearchWord)){
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





        ImageButton hariBtn = v.findViewById(R.id.hariBtn);
        hariBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                fill_detail fragment = new fill_detail(); // Fragment 생성
//                Bundle bundle = new Bundle();
//                bundle.putString("kate", "hair");
//                fragment.setArguments(bundle);
                ((MainActivity)getActivity()).replaceFragment(fill_detail.newInstance());
            }
        });
        ImageButton bodyBtn = v.findViewById(R.id.bodyBtn);
        bodyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_detail.newInstance());
            }
        });
        ImageButton cosmeticBtn = v.findViewById(R.id.cosmeticBtn);
        cosmeticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_detail.newInstance());
            }
        });
        ImageButton lundryBtn = v.findViewById(R.id.lundryBtn);
        lundryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_detail.newInstance());
            }
        });
        ImageButton dishBtn = v.findViewById(R.id.dishBtn);
        dishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_detail.newInstance());
            }
        });
        ImageButton foodBtn = v.findViewById(R.id.foodBtn);
        foodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_detail.newInstance());
            }
        });

       return v;
    }
}