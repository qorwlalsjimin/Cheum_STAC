package com.mirim.cheum_stac;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mirim.cheum_stac.Map.Store;
import com.mirim.cheum_stac.Map.StoreList;
import com.mirim.cheum_stac.Product.Product;
import com.mirim.cheum_stac.Product.ProductList;

public class fill_detail extends Fragment {
    Product product;
    MainActivity activity;
    EditText editSearch;
    String SearchWord;
    ImageView imgSearch;
    int id;
    String kate;
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

        editSearch = v.findViewById(R.id.edit_search_text);
        SearchWord = editSearch.getText().toString();
        imgSearch = v.findViewById(R.id.img_search_icon);
//        String kate = getArguments().getString("kate");

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i< ProductList.productList.size(); i++){
                    product = (Product) (ProductList.productList.get(i));
                    //if(kate.equals(product.kate)){
                        if(product.name.contains(SearchWord)){
                            //새로 생성하는 코드 필요
                            Toast.makeText(getActivity(), product.name, Toast.LENGTH_LONG).show();
                        } else {
                            continue;
                        }
                    //}
                }
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(FillFragment.newInstance());
            }
        });
        ImageView news1 = v.findViewById(R.id.news1);
        news1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news1_1 = v.findViewById(R.id.news1_1);
        news1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news1_2 = v.findViewById(R.id.news1_2);
        news1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news1_3 = v.findViewById(R.id.news1_3);
        news1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });

        ImageView news2 = v.findViewById(R.id.news2);
        news2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news2_1 = v.findViewById(R.id.news2_1);
        news2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news2_2 = v.findViewById(R.id.news2_2);
        news2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news2_3 = v.findViewById(R.id.news2_3);
        news2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });

        ImageView news3 = v.findViewById(R.id.news3);
        news3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news3_1 = v.findViewById(R.id.news3_1);
        news3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news3_2 = v.findViewById(R.id.news3_2);
        news3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news3_3 = v.findViewById(R.id.news3_3);
        news3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });

        ImageView news4 = v.findViewById(R.id.news4);
        news4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news4_1 = v.findViewById(R.id.news4_1);
        news4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news4_2 = v.findViewById(R.id.news4_2);
        news4_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news4_3 = v.findViewById(R.id.news4_3);
        news4_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });

        ImageView news5 = v.findViewById(R.id.news5);
        news5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news5_1 = v.findViewById(R.id.news5_1);
        news4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news5_2 = v.findViewById(R.id.news5_2);
        news5_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news5_3 = v.findViewById(R.id.news5_3);
        news5_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });

        ImageView news6 = v.findViewById(R.id.news6);
        news6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news6_1 = v.findViewById(R.id.news6_1);
        news6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news6_2 = v.findViewById(R.id.news6_2);
        news6_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        TextView news6_3 = v.findViewById(R.id.news6_3);
        news6_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product.newInstance(null,null));
            }
        });
        return v;
    }
}