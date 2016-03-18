package com.ent_ubp_android.app.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.ent_ubp_android.app.R;
import com.ent_ubp_android.app.fragment.formation.FormationMainFragment;

public class FragmentSwitcher {

    private static FragmentSwitcher fragmentSwitcher;
    private static AppCompatActivity activity;

    public FragmentSwitcher(AppCompatActivity activity){
        FragmentSwitcher.activity = activity;
    }

    public static AppCompatActivity getActivity(){
        return activity;
    }

    public static FragmentManager getSupportFragmentManager(){
        return activity.getSupportFragmentManager();
    }

    public static final void startAnotherFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        String fragmentTag = fragment.getClass().getName();

        boolean isInTheStack = fragmentManager.popBackStackImmediate(fragmentTag, 0);
        if (!isInTheStack) {
            fragmentTransaction.replace(R.id.frame_container, fragment, fragmentTag);
        }

        fragmentTransaction.addToBackStack(fragmentTag);
        fragmentTransaction.commit();
    }

    public static FragmentSwitcher getFragmentSwither(){
        return fragmentSwitcher;
    }
}
