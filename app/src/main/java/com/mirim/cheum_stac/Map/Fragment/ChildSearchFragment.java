package com.mirim.cheum_stac.Map.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mirim.cheum_stac.FragmentListener;
import com.mirim.cheum_stac.Map.ListView.ListViewAdapter;
import com.mirim.cheum_stac.Map.ListView.ListViewItem;
import com.mirim.cheum_stac.Map.Store;
import com.mirim.cheum_stac.Map.StoreList;
import com.mirim.cheum_stac.R;

public class ChildSearchFragment extends Fragment {

    FragmentListener fragmentListener;
    static String strSearch = "초기값"; //검색어 string 타입
    EditText editSearch; //검색어 editText 타입
    ListView listData; //검색 결과 보이는 리스트뷰
    boolean isSearch = true;
    int storeId;

    public static ChildSearchFragment newInstance() {
        return new ChildSearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_child_search, container, false);

        //가게 검색 필터링 기능(listview) 구현
        ListViewAdapter adapter;
        adapter = new ListViewAdapter() ;

        listData = (ListView) v.findViewById(R.id.list_search);
        listData.setAdapter(adapter);

        //  리스트뷰에 전체 데이터 추가
        for(int i = 0; i<StoreList.storeList.size(); i++){
            Store s = (Store) (StoreList.storeList.get(i));
            adapter.addItem(s.title, s.address, s.id);
        }

        //  ParentFragment에서 검색어값 받아오기
        LayoutInflater layoutInflater = getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.fragment_parent, v.findViewById(R.id.editTextFilter));
        editSearch = layout.findViewById(R.id.editTextFilter);
        editSearch.setText(strSearch); //displayMessage()로 strSearch에 값이 들어있음

        //  ListView 필터링
        if(isSearch) ((ListViewAdapter) listData.getAdapter()).getFilter().filter(strSearch);

        //  ListView에 item이 없을때 안내 text
        TextView textEmpty = v.findViewById(R.id.text_empty);
        listData.setEmptyView(textEmpty);

        //  ListView item 온클릭
        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem obj = (ListViewItem) parent.getAdapter().getItem(position);
                storeId = obj.getId();

                // 가게 아이디 다른 프래그먼트로 보내고
                fragmentListener.onCommand(1, Integer.toString(storeId));
                // 검색 결과 프래그먼트로 이동
                ParentFragment.btnListCheck.performClick();
            }
        });

        return v;
    }

    //리스트뷰 필터링을 시작할 것인지의 boolean값 ParentFragment에서 받아오기
    public void isSearch(boolean b){
        isSearch = b;
    }

    //ParentFragment에서 검색어 값 받아오기
    public void displayMessage(String message){
        strSearch = message;
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