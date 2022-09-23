package com.mirim.cheum_stac;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mirim.cheum_stac.Product.HeartList;
import com.mirim.cheum_stac.Product.Product;
import com.mirim.cheum_stac.Product.ProductList;
import com.mirim.cheum_stac.Product.fillProduct;

import java.util.ArrayList;


public class GoodProduct extends Fragment {
    MainActivity activity;
    static int productId = -1;
    ArrayList<fillProduct> list;
    Product product;
    RecyclerView recyclerView;
    RecyclerVIewAdapter adapter;
    GridLayoutManager layoutManager;
    FragmentListener fragmentListener;

    public GoodProduct() {
        // Required empty public constructor
    }


    public static GoodProduct newInstance(String param1, String param2) {
        return new GoodProduct();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_good_product, container, false);

        //뒤로 가는 버튼
        Button backBtn = v.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(MypageFragment.newInstance(null,null));
            }
        });

        //리사이클러 아이템 클릭 리스너
        RecyclerVIewAdapter.setOnItemClickListener(new RecyclerVIewAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View v, int pos)
            {
                fragmentListener.onCommand(3, Integer.toString(productId));
                ((MainActivity)getActivity()).replaceFragment(FillProduct.newInstance());
            }
        });

        //데이터 추가
        list = new ArrayList<fillProduct>() {{
            int favorId=-1;
            for(int i = 0; i< ProductList.productList.size(); i++){
                if(HeartList.heartList[i]==1) favorId = i;

                product = (Product) (ProductList.productList.get(i));
                if(HeartList.heartList[i]==1)
                    add(new fillProduct(product.simg, product.best, product.name, product.price));
            }
        }};

        recyclerView = (RecyclerView)v.findViewById(R.id.good_recycler);
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

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
        if(context instanceof FragmentListener) fragmentListener = (FragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(fragmentListener != null) fragmentListener = null;
        activity = null;
    }

    //ParentFragment에서 검색어 값 받아오기
    public void displayMessage(String message){
        productId = Integer.parseInt(message);
    }
}