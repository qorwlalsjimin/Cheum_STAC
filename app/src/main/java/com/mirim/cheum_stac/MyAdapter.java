package com.mirim.cheum_stac;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyAdapter extends FragmentStateAdapter {
    public int mCount;
    public MyAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(index==0) return new Intro1();
        else if(index==1) return new Intro2();
        else if(index==2) return new Intro3();
        else return new Intro4();
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    private int getRealPosition(int position) {
        return position % mCount;
    }


}
