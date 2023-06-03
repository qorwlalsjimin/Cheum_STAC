package com.mirim.cheum_stac.fill;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mirim.cheum_stac.FillProduct;
import com.mirim.cheum_stac.FragmentListener;
import com.mirim.cheum_stac.MainActivity;
import com.mirim.cheum_stac.Product.Product;
import com.mirim.cheum_stac.Product.ProductList;
import com.mirim.cheum_stac.Product.fillProduct;
import com.mirim.cheum_stac.R;
import com.mirim.cheum_stac.RecyclerVIewAdapter;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fill, container, false);
        ;

        //초기에 전체 내용
        list = new ArrayList<fillProduct>() {{
            for(int i=0; i<ProductList.productList.size(); i++){
                product = (Product) (ProductList.productList.get(i));
                add(new fillProduct(product.simg, product.best, product.name, product.price));
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

        //리사이클러 클릭 이벤트 처리
        RecyclerVIewAdapter.setOnItemClickListener(new RecyclerVIewAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View v, int pos)
            {
                id = pos;
                fragmentListener.onCommand(2, String.valueOf(id));
                ((MainActivity)getActivity()).replaceFragment(FillProduct.newInstance());
            }
        });

        //검색기능
        imgSearch = v.findViewById(R.id.img_search_icon);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editSearch = v.findViewById(R.id.edit_search_text);
                SearchWord = editSearch.getText().toString();
                list = new ArrayList<fillProduct>() {{
                    for(int i=0; i<ProductList.productList.size(); i++){
                        product = (Product) (ProductList.productList.get(i));
                        if(product.name.contains(SearchWord)){
                            add(new fillProduct(product.simg, product.best, product.name, product.price));

                        }
                    }
                    keyBordHide();
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
                fragmentListener.onCommand(3, "hair");
                ((MainActivity)getActivity()).replaceFragment(FillDetail.newInstance());
            }
        });
        ImageButton bodyBtn = v.findViewById(R.id.bodyBtn);
        bodyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentListener.onCommand(3, "body");
                ((MainActivity)getActivity()).replaceFragment(FillDetail.newInstance());
            }
        });
        ImageButton cosmeticBtn = v.findViewById(R.id.cosmeticBtn);
        cosmeticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentListener.onCommand(3, "cos");
                ((MainActivity)getActivity()).replaceFragment(FillDetail.newInstance());
            }
        });
        ImageButton lundryBtn = v.findViewById(R.id.lundryBtn);
        lundryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentListener.onCommand(3, "laund");
                ((MainActivity)getActivity()).replaceFragment(FillDetail.newInstance());
            }
        });
        ImageButton dishBtn = v.findViewById(R.id.dishBtn);
        dishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentListener.onCommand(3, "kit");
                ((MainActivity)getActivity()).replaceFragment(FillDetail.newInstance());
            }
        });
        ImageButton foodBtn = v.findViewById(R.id.foodBtn);
        foodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentListener.onCommand(3, "food");
                ((MainActivity)getActivity()).replaceFragment(FillDetail.newInstance());
            }
        });

        return v;
    }

    void keyBordHide() {
        Window window = getActivity().getWindow();
        new WindowInsetsControllerCompat(window, window.getDecorView()).hide(WindowInsetsCompat.Type.ime());
    }
}