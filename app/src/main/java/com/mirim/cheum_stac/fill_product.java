package com.mirim.cheum_stac;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mirim.cheum_stac.Product.HeartList;
import com.mirim.cheum_stac.Product.ProductList;
import com.mirim.cheum_stac.util.FirebaseUtils;
import com.mirim.cheum_stac.util.UserUtils;

import java.util.ArrayList;
import java.util.List;

public class fill_product extends Fragment {
    MainActivity activity;
    ImageButton imgbtnHeart;
    static int productId=7;

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

    public fill_product() {
        // Required empty public constructor
    }

    public static fill_product newInstance() {
        return new fill_product();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fill_product, container, false);
        imgbtnHeart = v.findViewById(R.id.imgbtnHeart);
        //파이어베이스 실시간 DB 연동
        DatabaseReference reference = FirebaseUtils.getUserReference(); //reference는 user 속성을 받음


        //  위에서 갖고온 store 주소값의 데이터를 읽어서 버튼 상태값 바꿔주기
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {//dataSnapshot : user
                //즐겨찾기 유무에 따른 boolean값 DB에 insert
                String path = UserUtils.getHash() + "/heart/" + Integer.toString(productId);
                boolean heart = false;
                if (dataSnapshot.child(path).exists()) {
                    heart = dataSnapshot.child(path).getValue(Boolean.class);
                    imgbtnHeart.setBackgroundResource(getBGR(heart));
                }
                reference.child(path).setValue(heart);

                //즐겨찾기 설정 유무 분별을 위한 setTag
                if (heart) imgbtnHeart.setTag("heart");
                else imgbtnHeart.setTag("heart_empty");

                //즐겨찾기한 가게 리스트에 넣기
                List<Integer> existArr = new ArrayList<>();
                for (int i = 0; i < ProductList.productList.size(); i++) {
                    String path3 = UserUtils.getHash() + "/heart/" + i;
                    if (dataSnapshot.child(path3).exists()) {
                        if (dataSnapshot.child(path3).getValue(Boolean.class)) {
                            existArr.add(Integer.valueOf(i));
                        }
                    }
                }
                for (int i = 0; i < existArr.size(); i++) {
                    String path2 = UserUtils.getHash() + "/heart/" + existArr.get(i).toString();
                    if (dataSnapshot.child(path2).getValue(Boolean.class) == Boolean.valueOf(true)) {
                        HeartList.heartList[existArr.get(i).intValue()] = 1;
                        //favoriteList 클래스 보고 HeartList 만들면 돼
                        //그 클래스 안에서 hearList까지 만들면 됨
                    }
                }
            }
                @Override
                public void onCancelled(DatabaseError error) {
                    //에러 처리
                }
            });
            String path = UserUtils.getHash() + "/heart/" + Integer.toString(productId);
        imgbtnHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Boolean heart = !getBGRHeart(imgbtnHeart.getTag().toString());
                    reference.child(path).setValue(heart);
                }
            });

            return v;

        }


    public Boolean getBGRHeart(String d) {
        return d.equals("heart") ? true : false;
    }

    public int getBGR(Boolean heart) {
        return heart ? R.drawable.heart : R.drawable.heart_empty;
    }
}