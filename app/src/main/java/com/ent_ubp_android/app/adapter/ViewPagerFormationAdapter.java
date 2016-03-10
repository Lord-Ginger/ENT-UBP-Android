package com.ent_ubp_android.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.ent_ubp_android.app.fragment.formation.FormationAddFragment;
import com.ent_ubp_android.app.fragment.formation.FormationDisplayFragment;

public class ViewPagerFormationAdapter extends FragmentStatePagerAdapter{

    private int numbOfTab;

    public ViewPagerFormationAdapter(FragmentManager fragmentManager, int numbOfTab) {
        super(fragmentManager);
        this.numbOfTab = numbOfTab;
    }

    //Return the fragment at the position in the tabLayout
    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new FormationDisplayFragment();

        if(position == 1)
            return new FormationAddFragment();

        return null;
    }

    //The numbers of fragment in the tab
    @Override
    public int getCount() {
        return numbOfTab;
    }
}
