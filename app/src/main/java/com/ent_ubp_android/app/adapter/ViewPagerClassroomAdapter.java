package com.ent_ubp_android.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.ent_ubp_android.app.fragment.classroom.ClassroomAddFragment;
import com.ent_ubp_android.app.fragment.classroom.ClassroomDisplayFragment;

public class ViewPagerClassroomAdapter extends FragmentStatePagerAdapter {

    private final int nbOfTab;

    public ViewPagerClassroomAdapter(FragmentManager fm, int nbOfTab) {
        super(fm);
        this.nbOfTab = nbOfTab;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new ClassroomDisplayFragment();

        if(position == 1)
            return new ClassroomAddFragment();

        return null;
    }

    @Override
    public int getCount() {
        return nbOfTab;
    }
}
