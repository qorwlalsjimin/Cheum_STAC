package com.mirim.cheum_stac.Map.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mirim.cheum_stac.FragmentListener;
import com.mirim.cheum_stac.MainActivity;
import com.mirim.cheum_stac.Map.FavorList;
import com.mirim.cheum_stac.Map.ListView.ListViewAdapter;
import com.mirim.cheum_stac.Map.ListView.ListViewItem;
import com.mirim.cheum_stac.Map.Store;
import com.mirim.cheum_stac.Map.StoreList;
import com.mirim.cheum_stac.R;

public class ChildFavorFragment extends Fragment {
    public static ChildFavorFragment newInstance(){
        return new ChildFavorFragment();
    }

    ListView listData; //즐겨찾기 보이는 리스트뷰
    int storeId=7; //ChildResultFragment로 값이 안 넘어가서 넣은 임의의 초기값
    FragmentListener fragmentListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_child_favor, container, false);

        //리스트뷰
        ListViewAdapter adapter;
        adapter = new ListViewAdapter() ;
        listData = (ListView) v.findViewById(R.id.list_favorite);
        listData.setAdapter(adapter);

        for(int i = 0; i<FavorList.favorList.length; i++)
            FavorList.favorList[i] = false;

        //파이어베이스 연동
//        DatabaseReference reference = FirebaseUtils.getUserReference(); //reference는 user 속성을 받음
//        //위에서 갖고온 store 주소값의 데이터를 읽어서 버튼 상태값 바꿔주기
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {//dataSnapshot : user
//                String path;
//                for(int i = 0; i<StoreList.storeList.size(); i++){
//                    path = UserUtils.getHash() + "/favorite/" + Integer.toString(i);
//                    if(dataSnapshot.child(path).getValue(Boolean.class))
//                        FavorList.favorList[i] = true;
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                //에러 처리
//            }
//        });

        FavorList.favorList[2] = true;
        FavorList.favorList[8] = true;
        FavorList.favorList[16] = true;
        FavorList.favorList[25] = true;

        //리스트뷰에 데이터 추가
        Store s;
        int favorId=-1;
        for(int i = 0; i< StoreList.storeList.size(); i++){
            if(FavorList.favorList[i]) favorId = i;

            s = (Store) (StoreList.storeList.get(i));
            if(s.id == favorId) adapter.addItem(s.title, s.address, s.id);
        }

        //즐겨찾기 가게 클릭시
        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem obj = (ListViewItem) parent.getAdapter().getItem(position);
                storeId = obj.getId();

                fragmentListener.onCommand(1, Integer.toString(storeId));
                Log.d("값 옮기기를 추적하자 -_-", "onCommand로 값을 보냇어요! storeId: "+storeId);

                ((MainActivity)getActivity()).replaceFragment(com.mirim.cheum_stac.Map.Fragment.ChildMapFragment.newInstance());
            }
        });


        return v;
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