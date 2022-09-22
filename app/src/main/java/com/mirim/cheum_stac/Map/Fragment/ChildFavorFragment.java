package com.mirim.cheum_stac.Map.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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
    int storeId=-1; //가게 아이디 0부터 시작
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

            //리스트뷰에 데이터 추가
            Store store;
            int favorId=-1;
            for(int i = 0; i< StoreList.storeList.size(); i++){
                //즐겨찾기가 설정된 가게라면 favorId에 가게 아이디 주기
                if(FavorList.favorList[i]==1) favorId = i;

                //아이템 추가
                store = (Store) (StoreList.storeList.get(i));
                if(store.id == favorId) adapter.addItem(store.title, store.address, store.id);
            }

        //리스트뷰에 아이템 없을때 안내 텍스트
        TextView textEmpty = v.findViewById(R.id.text_empty);
        listData.setEmptyView(textEmpty);

        //즐겨찾기 가게 클릭시
        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem obj = (ListViewItem) parent.getAdapter().getItem(position);
                storeId = obj.getId(); //클릭한 가게의 아이디 받기

                fragmentListener.onCommand(1, Integer.toString(storeId)); //가게 아이디 다른 프래그먼트에 넘겨주기
                ParentFragment.btnFavoriteCheck.performClick();

                //키보드 내리는 코드 추가. 추후 수정
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