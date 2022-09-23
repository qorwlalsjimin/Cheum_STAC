package com.mirim.cheum_stac;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mirim.cheum_stac.Product.HeartList;
import com.mirim.cheum_stac.Product.Product;
import com.mirim.cheum_stac.Product.ProductList;
import com.mirim.cheum_stac.util.FirebaseUtils;
import com.mirim.cheum_stac.util.UserUtils;

import java.util.ArrayList;
import java.util.List;

public class FillProduct extends Fragment {
    MainActivity activity;
    ImageView imgbtnHeart;
    ImageView productImg;
    TextView  titleText, priceText, mlText, hashText, contText;
    Product product;
    static int id;

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

    public FillProduct() {
        // Required empty public constructor
    }

    public static FillProduct newInstance() {
        return new FillProduct();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fill_product, container, false);

        productImg = v.findViewById(R.id.product_img);
        titleText = v.findViewById(R.id.tvTitle);
        priceText = v.findViewById(R.id.tvPrice);
        mlText = v.findViewById(R.id.tvml);
        hashText = v.findViewById(R.id.tvContentTitle);
        contText = v.findViewById(R.id.tvContent);

        product = (Product) (ProductList.productList.get(id));

        Button backBtn = v.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });


        FirebaseStorage storage = FirebaseStorage.getInstance("gs://stac-cheum.appspot.com/");
        StorageReference storageRef = storage.getReference();
        storageRef.child("product/"+Integer.toString(id) + ".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
              //이미지 로드 성공시
              Glide.with(getActivity())
                       .load(uri)
                      .into(productImg);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //이미지 로드 실패시
                Toast.makeText(getActivity(), "실패", Toast.LENGTH_SHORT).show();
            }
        });

        titleText.setText(product.name);
        String price = product.price;
        priceText.setText(price.substring(price.lastIndexOf(" ")+1));
        mlText.setText(price.substring(0,price.indexOf(" ")));
        hashText.setText(product.hash);
        contText.setText(product.cont);

        imgbtnHeart = v.findViewById(R.id.imgbtnHeart);

        //파이어베이스 실시간 DB 연동
        DatabaseReference reference = FirebaseUtils.getUserReference(); //reference는 user 속성을 받음
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {//dataSnapshot : user
                //찜 유무에 따른 boolean값 DB에 insert
                String path = UserUtils.getHash() + "/heart/" + Integer.toString(id);
                Boolean heart = false;
                if (dataSnapshot.child(path).exists()) {
                    heart = dataSnapshot.child(path).getValue(Boolean.class);
                    imgbtnHeart.setImageResource(getBGR(heart));
                }
                reference.child(path).setValue(heart);

                //찜 설정 유무 분별을 위한 setTag
                if (heart) imgbtnHeart.setTag("heart");
                else imgbtnHeart.setTag("heart_empty");

                //찜한 상품 리스트에 넣기
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
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                //에러 처리
            }
        });

        String path = UserUtils.getHash() + "/heart/" + Integer.toString(id);
        imgbtnHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean heart = !getBGRHeart(imgbtnHeart.getTag().toString());
                reference.child(path).setValue(heart);
                if(heart) imgbtnHeart.setTag("heart");
                else imgbtnHeart.setTag("heart_empty");
                imgbtnHeart.setImageResource(getBGR(heart));
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

    public void displayMessage(String data) {
        id = Integer.parseInt(data);
    }
}