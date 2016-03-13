package com.ent_ubp_android.app.fragment.professeur;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.adapter.ViewPagerProfesseurAdapter;
import com.ent_ubp_android.app.fragment.FragmentSwitcher;


public class ProfesseurMainFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;

    public ProfesseurMainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_professeur_main, container, false);

        tabLayout = (TabLayout) v.findViewById(R.id.class_tabsLayout);
        viewPager = (ViewPager) v.findViewById(R.id.class_viewpager);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tabLayout.addTab(tabLayout.newTab().setText("Afficher"), 0);
        tabLayout.addTab(tabLayout.newTab().setText("Ajouter"), 1);

        ViewPagerProfesseurAdapter adapter = new ViewPagerProfesseurAdapter(FragmentSwitcher.getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        tabLayout.setTabTextColors(ContextCompat.getColorStateList(FragmentSwitcher.getActivity(), R.drawable.tab_layout_selector));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(FragmentSwitcher.getActivity(), R.color.indicator));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}
