package com.ent_ubp_android.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.ent_ubp_android.app.fragment.FormationDisplayFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    //Return the fragment at the position in the tabLayout
    @Override
    public Fragment getItem(int position) {
        return new FormationDisplayFragment();
    }

    //The numbers of fragment in the tab
    @Override
    public int getCount() {
        return 2;
    }
}
