package com.mirim.cheum_stac.Map.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mirim.cheum_stac.FragmentListener;
import com.mirim.cheum_stac.MainActivity;
import com.mirim.cheum_stac.R;

public class ParentFragment extends Fragment implements View.OnClickListener {

    public static EditText editSearch;
    Toolbar toolSearch;
    ImageButton imgSearch;
    public static Button btnCheck, btnFavoriteCheck;
    FragmentListener fragmentListener;
    public static int storeId;

    public static ParentFragment newInstance() {
        return new ParentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_parent, container, false);

        //즐겨찾기로 이동
        Fragment fg;
        fg = com.mirim.cheum_stac.Map.Fragment.ChildFavorFragment.newInstance();
        setChildFragment(fg);

        editSearch = (EditText) v.findViewById(R.id.editTextFilter);
        toolSearch = v.findViewById(R.id.toolbar_search);
        imgSearch = v.findViewById(R.id.img_search_icon);
        btnCheck = v.findViewById(R.id.btn_listview_check);
        btnFavoriteCheck = v.findViewById(R.id.btn_favorite_check);

        //리스트뷰에서 아이템 클릭
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyBordHide();

                Fragment fg;
                fg = com.mirim.cheum_stac.Map.Fragment.ChildMapFragment.newInstance();
                setChildFragment(fg);

                imgSearch.setImageResource(R.drawable.x);
                imgSearch.setTag("x");

                keyBordHide();
            }
        });

        //지도 첫 화면에서 즐겨찾기 아이콘 클릭
        btnFavoriteCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).replaceFragment(ChildResultFragment.newInstance());

            }
        });

        //x 눌렀을때 검색 화면으로
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imgSearch.getTag().equals("x")){
                    keyBordShow();
                    imgSearch.setImageResource(R.drawable.map_search_icon);
                    imgSearch.setTag("o");
                    editSearch.setText("");
                }
                else if(imgSearch.getTag().equals("o")){
                    keyBordHide();
                    imgSearch.setImageResource(R.drawable.x);
                    imgSearch.setTag("x");
                }
            }
        });

        editSearch.setOnClickListener(oneListener);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //입력 전
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Fragment fg;
                fg = com.mirim.cheum_stac.Map.Fragment.ChildSearchFragment.newInstance();
                setChildFragment(fg);
            }
            @Override
            public void afterTextChanged(Editable s) {
                //입력했다가 다시 다 지웠을때 즐겨찾기 Fragment로
                if(editSearch.getText().length() == 0){
                    Fragment fg = com.mirim.cheum_stac.Map.Fragment.ChildFavorFragment.newInstance();
                    setChildFragment(fg);
                }
                else{
                    //데이터 보내기
                    fragmentListener.onCommand(0, editSearch.getText().toString());
                    com.mirim.cheum_stac.Map.Fragment.ChildSearchFragment childSearchFragment = new com.mirim.cheum_stac.Map.Fragment.ChildSearchFragment();
                    childSearchFragment.isSearch(true);
                }
            }
        });

        //검색어 입력 후 완료 버튼 눌렀을때
        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if(actionId == EditorInfo.IME_ACTION_DONE)
                {
                    imgSearch.setImageResource(R.drawable.x);
                    keyBordHide();
                    return true;
                }
                return false;
            }
        });
//        imgSearch.setOnClickListener(twoListener);

        return v;
    }



    //자식 Fragment로 이동(즐겨찾기 리스트뷰)
    View.OnClickListener oneListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment fg;
            fg = com.mirim.cheum_stac.Map.Fragment.ChildFavorFragment.newInstance();
            setChildFragment(fg);
        }
    };

    @Override
    public void onClick(View view) {}

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

    public void setChildFragment(Fragment child) {
        FragmentTransaction childFt = getChildFragmentManager().beginTransaction();

        if (!child.isAdded()) {
            childFt.replace(R.id.child_fragment_container, child);
            childFt.addToBackStack(null);
            childFt.commitAllowingStateLoss();
        }
    }

    //ParentFragment에서 값 받아오기
    public void displayMessage(String message){
        storeId = Integer.parseInt(message);
    }

    void keyBordHide() {
        Window window = getActivity().getWindow();
        new WindowInsetsControllerCompat(window, window.getDecorView()).hide(WindowInsetsCompat.Type.ime());
    }

    void keyBordShow() {
        Window window = getActivity().getWindow();
        new WindowInsetsControllerCompat(window, window.getDecorView()).show(WindowInsetsCompat.Type.ime());
    }

}