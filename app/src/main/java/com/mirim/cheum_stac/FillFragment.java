package com.mirim.cheum_stac;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class FillFragment extends Fragment {
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fill, container, false);
        ImageButton hariBtn = v.findViewById(R.id.hariBtn);
        hariBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        ImageView news1 = v.findViewById(R.id.news1);
        news1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });
        TextView news1_1 = v.findViewById(R.id.news1_1);
        news1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });
        TextView news1_2 = v.findViewById(R.id.news1_2);
        news1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });
        TextView news1_3 = v.findViewById(R.id.news1_3);
        news1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });

        ImageView news2 = v.findViewById(R.id.news2);
        news2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });
        TextView news2_1 = v.findViewById(R.id.news2_1);
        news2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });
        TextView news2_2 = v.findViewById(R.id.news2_2);
        news2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });
        TextView news2_3 = v.findViewById(R.id.news2_3);
        news2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });

        ImageView news3 = v.findViewById(R.id.news3);
        news3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });
        TextView news3_1 = v.findViewById(R.id.news3_1);
        news3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });
        TextView news3_2 = v.findViewById(R.id.news3_2);
        news3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });
        TextView news3_3 = v.findViewById(R.id.news3_3);
        news3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });

        ImageView news4 = v.findViewById(R.id.news4);
        news4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });
        TextView news4_1 = v.findViewById(R.id.news4_1);
        news4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });
        TextView news4_2 = v.findViewById(R.id.news4_2);
        news4_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });
        TextView news4_3 = v.findViewById(R.id.news4_3);
        news4_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(fill_product_only.newInstance(null,null));
            }
        });
        return v;
    }
}