package com.ent_ubp_android.app.adapter.viewpager;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.ent_ubp_android.app.fragment.professeur.ProfesseurAddFragment;
import com.ent_ubp_android.app.fragment.professeur.ProfesseurDisplayFragment;

public class ViewPagerProfesseurAdapter extends FragmentStatePagerAdapter {
    private final int nbOfTab;

    public ViewPagerProfesseurAdapter(FragmentManager fm, int nbOfTab) {
        super(fm);
        this.nbOfTab = nbOfTab;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return new ProfesseurDisplayFragment();

        if(position == 1)
            return new ProfesseurAddFragment();

        return null;
    }

    @Override
    public int getCount() {
        return nbOfTab;
    }
}
