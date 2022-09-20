package com.mirim.cheum_stac.Map.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mirim.cheum_stac.FragmentListener;
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

//        adapter.addItem("테", "테스트라구욧", 1);
//        adapter.addItem("테스", "테스트라구욧", 2);
//        adapter.addItem("테스트", "테스트라구욧", 3);
//        adapter.addItem("테스트입", "테스트라구욧", 4);
//        adapter.addItem("테스트입니", "테스트라구욧", 5);
//        adapter.addItem("테스트입니다", "테스트라구욧", 6);
        LinearLayout linearEmpty = v.findViewById(R.id.empty);
        linearEmpty.setVisibility(View.VISIBLE);
        listData.setEmptyView(linearEmpty);
        //리스트뷰에 데이터 추가
        Store s;
        int favorId=-1;
        for(int i = 0; i< StoreList.storeList.size(); i++){
            if(FavorList.favorList[i]==1) favorId = i;
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
                ParentFragment.btnCheck.performClick();
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