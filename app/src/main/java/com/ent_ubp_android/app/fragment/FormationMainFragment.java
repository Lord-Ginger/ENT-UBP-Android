package com.ent_ubp_android.app.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.adapter.ViewPagerAdapter;


public class FormationMainFragment extends Fragment {

    //Variables for TabLayout and ViewPager
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public FormationMainFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_formation_main, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tabsLayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        return view;
    }

    //Do Process
    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);

        //Set the text to display and the position in the tab
        tabLayout.addTab(tabLayout.newTab().setText("Afficher"), 0);
        tabLayout.addTab(tabLayout.newTab().setText("Ajouter"), 1);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(FragmentSwitcher.getSupportFragmentManager());

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(FragmentSwitcher.getActivity(), R.drawable.tab_layout_selector));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(FragmentSwitcher.getActivity(), R.color.indicator));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }

    @Override
    public void onResume (){
        super.onResume();
    }

    @Override
    public void onPause (){
        super.onPause();
    }

    @Override
    public void onStop (){
        super.onStop();
    }

    @Override
    public void onViewStateRestored (Bundle savedInstanceState){
        super.onViewStateRestored(savedInstanceState);
    }
}
