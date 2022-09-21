package com.mirim.cheum_stac.Map.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mirim.cheum_stac.FragmentListener;
import com.mirim.cheum_stac.MainActivity;
import com.mirim.cheum_stac.Map.Store;
import com.mirim.cheum_stac.Map.StoreList;
import com.mirim.cheum_stac.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class ChildMapFragment extends Fragment {

    LinearLayout linearInfo;
    ViewGroup mapViewContainer;
    FragmentListener fragmentListener;

    public static int storeId;
    TextView storeName, storeLoct;

    ImageView imgPlus, imgMinus;

    public static Button btn;

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

        //하단에 가게 정보
        storeName = v.findViewById(R.id.text_store_name);
        storeLoct = v.findViewById(R.id.text_store_location);

        //가게 정보 가져오기
        Store s;
        Double latitude=0.0, longitude=0.0;
        for(int i = 0; i< StoreList.storeList.size(); i++){
            s = (Store) (StoreList.storeList.get(i));
            if(s.id == storeId){
                storeName.setText(s.title);
                storeLoct.setText(s.address);
                fragmentListener.onCommand(1, Integer.toString(s.id));
                latitude = s.lat;
                longitude = s.lug;
            }
        }

        //지도
        MapView mapView = new MapView(getActivity());
        mapViewContainer = (ViewGroup) v.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        //  현재 위치 추적 기능 off
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);

        //  검색 결과 위치 마커로 표시
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(storeName.getText().toString());
        marker.setTag(0);
        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        marker.setMapPoint(MARKER_POINT);
        marker.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.

        mapView.addPOIItem(marker);

        // 우측 하단 zoom level 변경하는 버튼
        imgPlus = v.findViewById(R.id.img_plus);
        imgMinus = v.findViewById(R.id.img_minus);

        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapView.zoomIn(true);
            }
        });
        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapView.zoomOut(true);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapViewContainer.removeAllViews();
    }

    //즐겨찾기 화면, 검색된 화면에서 값 받아오기
    public void displayMessage(String data){
        storeId = Integer.parseInt(data);
    }

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

}