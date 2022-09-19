package com.mirim.cheum_stac.Map.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mirim.cheum_stac.MainActivity;
import com.mirim.cheum_stac.Map.Store;
import com.mirim.cheum_stac.Map.StoreList;
import com.mirim.cheum_stac.R;
import com.mirim.cheum_stac.util.FirebaseUtils;
import com.mirim.cheum_stac.util.UserUtils;

import net.daum.mf.map.api.MapView;

public class ChildResultFragment extends Fragment {

    public ChildResultFragment() {}

    public static ChildResultFragment newInstance() {
        return new ChildResultFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    ImageButton imgbtnDown;
    ImageButton imgbtnStar;
    ViewGroup mapViewContainer;
    TextView storeName, storeLoct, storeOper, storePage, storeDial;
    static int storeId=7;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_child_result, container, false);
        Log.d("storeId를 추적합니다. 쭈고 -_-", "onCreateView 실행되자마자 storeId: "+storeId);

        imgbtnStar = v.findViewById(R.id.imgbtn_star);
        storeName = v.findViewById(R.id.text_store_name);
        storeLoct = v.findViewById(R.id.text_store_location);
        storeOper = v.findViewById(R.id.text_store_operation);
        storePage = v.findViewById(R.id.text_store_page);
        storeDial = v.findViewById(R.id.text_store_dial);
        Log.d("파이어베이스를 추적하자 -_-", "가게 아이디"+Integer.toString(storeId));

        //가게 정보 가져와서 text 바꾸기
        Store s;
        for(int i = 0; i< StoreList.storeList.size(); i++){
            s = (Store) (StoreList.storeList.get(i));
            if(s.id == storeId){
                storeName.setText(s.title);
                storeLoct.setText(s.address);
            }
        }

        //파이어베이스 실시간 DB 연동
        Log.d("파이어베이스를 추적하자 -_-", "데이터베이스레퍼런스 연결 직전!");
        DatabaseReference reference = FirebaseUtils.getUserReference(); //reference는 user 속성을 받음
        Log.d("파이어베이스를 추적하자 -_-", "reference에 user 속성 받기");

        //위에서 갖고온 store 주소값의 데이터를 읽어서 버튼 상태값 바꿔주기
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {//dataSnapshot : user
                String path = UserUtils.getHash() + "/favorite/" + Integer.toString(storeId);
                Boolean favorite = false;
                if (dataSnapshot.child(path).exists()){
                    favorite = dataSnapshot.child(path).getValue(Boolean.class);
                    imgbtnStar.setBackgroundResource(getBGR(favorite));
                }
                reference.child(path).setValue(favorite);

                if(favorite) imgbtnStar.setTag("star");
                else imgbtnStar.setTag("star_empty");

                //즐겨찾기 리스트에 넣기 시도 중
//                List<Integer> existArr = new ArrayList<>();
//                for(int i = 0; i<42; i++){
//                    String path3 = UserUtils.getHash() + "/favorite";
//                    if(dataSnapshot.child(path3).getValue(Integer.class).equals(Integer.valueOf(i)))
//                        existArr.add(Integer.valueOf(i));
//                }
//
//                for(int i = 0; i<existArr.size(); i++){
//                    String path2 = UserUtils.getHash() + "/favorite/" + Integer.toString(existArr.get(i));
//                    if(dataSnapshot.child(path2).getValue(Boolean.class) == Boolean.valueOf(true))
//                        FavorList.favorList[existArr.get(i)] = 1;
//                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d("파이어베이스를 추적하자 -_-", "어머낫!!! 오류.");
                //에러 처리
            }
        });


        String path = UserUtils.getHash() + "/favorite/" + Integer.toString(storeId);
        imgbtnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean favorite = !getBGRFavorite(imgbtnStar.getTag().toString());
                Log.d("아니 너 여기 안 오니?", "onClickListener");
                reference.child(path).setValue(favorite);
            }
        });

        //지도 화면에 보이게 함
        MapView mapView = new MapView(getActivity());
        mapViewContainer = (ViewGroup) v.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        //상세정보 내리는 이미지 버튼
        imgbtnDown = v.findViewById(R.id.imgbtn_down);
        imgbtnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fragment창 닫는 코드로 추후 수정(FragmentManager)
                ((MainActivity)getActivity()).replaceFragment(ChildMapFragment.newInstance());
                mapViewContainer.removeAllViews();
            }
        });


        Log.d("storeId를 추적합니다. 쭈고 -_-", "reference에 user 연동 storeId: "+storeId);

        return v;
    }

    public Boolean getBGRFavorite(String d) {
        return d.equals("star") ? true : false;
    }

    public int getBGR(Boolean favorite) {
        return favorite ? R.drawable.star : R.drawable.star_empty;
    }

    @Override
    public void onPause() {
        super.onPause();
        //카카오맵 지도 2~3개 이상 열리지 않게 조치
        if(mapViewContainer != null) mapViewContainer.removeAllViews();
    }

    //즐겨찾기 화면, 검색된 화면에서 값 받아오기
    public void displayMessage(String data){
        storeId = Integer.parseInt(data);
        Log.d("값 옮기기를 추적하자 -_-", "가게 아이디를 정상적으로 받았나요? storeId: "+storeId);
    }

}