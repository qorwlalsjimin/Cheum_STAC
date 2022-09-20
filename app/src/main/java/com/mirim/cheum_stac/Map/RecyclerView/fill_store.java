package com.mirim.cheum_stac.Map.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mirim.cheum_stac.MainActivity;
import com.mirim.cheum_stac.R;

public class fill_store extends Fragment {
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

    public fill_store() {
        // Required empty public constructor
    }

    public static fill_store newInstance() {
        return new fill_store();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.favorite_recycler_item, container, false);
        TextView textStoreName = v.findViewById(R.id.text_store_name);
        textStoreName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ChildMapFragment로 이동해야함
                Toast.makeText(activity, "터치됐습니다", Toast.LENGTH_SHORT).show();
//                ((MainActivity)getActivity()).replaceFragment(fill_detail.newInstance()); //기존코드
            }
        });
        return v;
    }
}