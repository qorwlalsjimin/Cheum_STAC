package com.mirim.cheum_stac.Map.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
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
import com.mirim.cheum_stac.Map.FavorList;
import com.mirim.cheum_stac.Map.Store;
import com.mirim.cheum_stac.Map.StoreList;
import com.mirim.cheum_stac.R;
import com.mirim.cheum_stac.util.FirebaseUtils;
import com.mirim.cheum_stac.util.UserUtils;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;

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
    TextView storeName, storeAddress, storeOper, storePage, storeDial;
    ImageView[] imgStore = new ImageView[3];
    static int storeId=-1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_child_result, container, false);

        keyBordHide();

        imgbtnStar = v.findViewById(R.id.imgbtn_star);
        storeName = v.findViewById(R.id.text_store_name);
        storeAddress = v.findViewById(R.id.text_store_location);
        storeOper = v.findViewById(R.id.text_store_operation);
        storePage = v.findViewById(R.id.text_store_page);
        storeDial = v.findViewById(R.id.text_store_dial);
        imgStore[0] = v.findViewById(R.id.img_store1);
        imgStore[1] = v.findViewById(R.id.img_store2);
        imgStore[2] = v.findViewById(R.id.img_store3);

        //?????? ?????? ???????????? text ?????????
        Store s;
        Double latitude=0.0, longitude=0.0;
        for(int i = 0; i< StoreList.storeList.size(); i++){
            s = (Store) (StoreList.storeList.get(i));
            if(s.id == storeId){
                storeName.setText(s.title);
                storeAddress.setText(s.address);
                storeOper.setText(s.opertime);
                storePage.setText(s.page);
                storeDial.setText(s.dial);
                latitude = s.lat;
                longitude = s.lug;

                //?????????????????? ?????????????????? ????????? ????????????
                FirebaseStorage storage = FirebaseStorage.getInstance("gs://stac-cheum.appspot.com/");
                StorageReference storageRef = storage.getReference();
                for(int j=1; j<=3; j++) {
                    int finalJ = j-1;
                    storageRef.child(Integer.toString(s.id) + "/i" + Integer.toString(s.id) + "_" + j + ".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //????????? ?????? ?????????
                            Glide.with(getActivity())
                                    .load(uri)
                                    .into(imgStore[finalJ]);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //????????? ?????? ?????????
                            Toast.makeText(getActivity(), "??????", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } //if
        } //for

        //?????? ????????????
        MapView mapView = new MapView(getActivity());
        mapViewContainer = (ViewGroup) v.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        //  ?????? ?????? ?????? ?????? off
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);

        //  ?????? ???????????? ?????? ?????????
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);

        //  Marker ??????
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(storeName.getText().toString());
        marker.setTag(0);
        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        marker.setMapPoint(MARKER_POINT);
        marker.setMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(marker);

        //?????????????????? ????????? DB ??????
        DatabaseReference reference = FirebaseUtils.getUserReference(); //reference??? user ????????? ??????
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //dataSnapshot : user
                //????????? ????????? store ???????????? ???????????? ????????? ?????? ????????? ????????????
                //  ???????????? ????????? ?????? boolean??? DB??? insert
                String path = UserUtils.getHash() + "/favorite/" + Integer.toString(storeId);
                Boolean favorite = false;
                if (dataSnapshot.child(path).exists()){
                    favorite = dataSnapshot.child(path).getValue(Boolean.class);
                    imgbtnStar.setBackgroundResource(getBGR(favorite));
                }
                reference.child(path).setValue(favorite);

                //  ???????????? ?????? ?????? ????????? ?????? setTag
                if(favorite) imgbtnStar.setTag("star");
                else imgbtnStar.setTag("star_empty");

                //??????????????? ?????? ???????????? ??????
                //  favorite ????????? ????????? ?????? ???????????? ?????? List (cf: true??? ????????? ?????? ????????? ????????????)
                List<Integer> existArr = new ArrayList<>();
                for(int i = 0; i<StoreList.storeList.size(); i++){
                    String path_extra = UserUtils.getHash() + "/favorite/"+i;
                    if (dataSnapshot.child(path_extra).exists())
                        if (dataSnapshot.child(path_extra).getValue(Boolean.class)) existArr.add(Integer.valueOf(i));
                }

                //  true?????? ?????? ?????????????????? favorList??? ??????
                for(int i = 0; i<existArr.size(); i++){
                    String path_extra = UserUtils.getHash() + "/favorite/" + existArr.get(i).toString();
                    if(dataSnapshot.child(path_extra).getValue(Boolean.class) == Boolean.valueOf(true))
                        FavorList.favorList[existArr.get(i).intValue()] = 1; // 0: false, 1: true
                }
            } //data change

            @Override
            public void onCancelled(DatabaseError error) {
                //?????? ??????
            }
        });

        //???????????? ????????? ??????
        String path = UserUtils.getHash() + "/favorite/" + Integer.toString(storeId);
        imgbtnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean favorite = !getBGRFavorite(imgbtnStar.getTag().toString()); //??????????????? true/false ?????? => ??? ????????? ?????? !?????????
                reference.child(path).setValue(favorite); // ??? ??????
            }
        });

        //???????????? ????????? ????????? ??????
        imgbtnDown = v.findViewById(R.id.imgbtn_down);
        imgbtnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ChildMapFragment()).addToBackStack(null).commit();
                mapViewContainer.removeAllViews();
            }
        });

        return v;
    }

    // Tag????????? true/false ??????
    public Boolean getBGRFavorite(String d) {
        return d.equals("star") ? true : false;
    }

    // bool?????? ???????????? ????????? ????????? ??????
    public int getBGR(Boolean favorite) {
        return favorite ? R.drawable.star : R.drawable.star_empty;
    }

    @Override
    public void onPause() {
        super.onPause();
        //???????????? ?????? 2~3??? ?????? ????????? ?????? ??????
        if(mapViewContainer != null) mapViewContainer.removeAllViews();
    }

    //???????????? ??????, ????????? ???????????? ?????? ????????? ??? ????????????
    public void displayMessage(String data){
        storeId = Integer.parseInt(data);
    }

    //????????? ?????????
    void keyBordHide() {
        Window window = getActivity().getWindow();
        new WindowInsetsControllerCompat(window, window.getDecorView()).hide(WindowInsetsCompat.Type.ime());
    }

}