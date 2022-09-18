package com.mirim.cheum_stac.Map.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.mirim.cheum_stac.MainActivity;
import com.mirim.cheum_stac.Map.Store;
import com.mirim.cheum_stac.Map.StoreList;
import com.mirim.cheum_stac.R;

import net.daum.mf.map.api.MapView;

public class ChildMapFragment extends Fragment {

    LinearLayout linearInfo;
    ViewGroup mapViewContainer;

    public static int storeId;
    TextView storeName, storeLoct;

    //추후 삭제
    EditText editSearch;
    ImageView imgSearch;

    public static ChildMapFragment newInstance() {
        return new ChildMapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_child_map, container, false);

        MapView mapView = new MapView(getActivity());
        mapViewContainer = (ViewGroup) v.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        //하단에 가게 정보
        storeName = v.findViewById(R.id.text_store_name);
        storeLoct = v.findViewById(R.id.text_store_location);

        //추후 삭제
        editSearch = v.findViewById(R.id.editTextFilter);
        imgSearch = v.findViewById(R.id.img_search_icon);

        Log.d("for문 들어가기 직전", Integer.toString(storeId));
        //가게 정보 가져와서 text 바꾸기
        Store s;
        for(int i = 0; i< StoreList.storeList.size(); i++){
            s = (Store) (StoreList.storeList.get(i));
            if(s.id == storeId){
                Log.d("여기입니다 여기", Integer.toString(s.id)+" "+Integer.toString(storeId));
                storeName.setText(s.title);
                storeLoct.setText(s.address);
            }
        }

        //임시방편
        editSearch.setText("검색어");
        editSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Toast.makeText(getContext(), "ChildSearchFragment로 이동", Toast.LENGTH_SHORT).show();
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editSearch.setText("");
                imgSearch.setBackgroundResource(R.drawable.map_search_icon);
            }
        });

        linearInfo = v.findViewById(R.id.linear_store_info);
        linearInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(com.mirim.cheum_stac.Map.Fragment.ChildResultFragment.newInstance());
                mapViewContainer.removeAllViews();
            }
        });
        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        mapViewContainer.removeAllViews();
    }

    //즐겨찾기 화면, 검색된 화면에서 값 받아오기
    public void displayMessage(String data){
        storeId = Integer.parseInt(data);
        Log.d("값 옮기기를 추적하자 -_-", "가게 아이디를 정상적으로 받았나요? 이곳은 메서드 안 storeId: "+storeId);
    }
}