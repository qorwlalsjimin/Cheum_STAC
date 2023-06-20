package com.mirim.cheum_stac.intro_page;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class IntroAdapter extends FragmentStateAdapter {

    public int mCount;

    public IntroAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(index==0) return new Intro_Page1();
        else if(index==1) return new Intro_Page2();
        else if(index==2) return new Intro_Page3();
        else return new Intro_Page4();
    }

    @Override
    public int getItemCount() {
        return 2000;
    }

    public int getRealPosition(int position) { return position % mCount; }

}
