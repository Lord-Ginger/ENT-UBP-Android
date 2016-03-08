package com.ent_ubp_android.app.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.ent_ubp_android.app.R;

public class FragmentSwitcher {

    public static AppCompatActivity activity;

    public FragmentSwitcher(AppCompatActivity activity){
        FragmentSwitcher.activity = activity;
    }

    public static AppCompatActivity getActivity(){
        return activity;
    }

    public static FragmentManager getSupportFragmentManager(){
        return activity.getSupportFragmentManager();
    }

    public final void startAnotherFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        String fragmentTag = fragment.getClass().getName();

        if(!fragmentManager.popBackStackImmediate(fragmentTag, 0)){
            fragmentTransaction.replace(R.id.frame_container, fragment, fragmentTag);
        }
        fragmentTransaction.addToBackStack(fragmentTag);
        fragmentTransaction.commit();

        /*Fragment fragmentBis = fragmentManager.findFragmentByTag(fragmentTag);
        if(fragmentBis == null){
            fragmentTransaction.replace(R.id.frame_container, fragment, fragmentTag).addToBackStack(fragmentTag);
        }
        else{

                fragmentTransaction.replace(R.id.frame_container, fragment, fragmentTag);
*/
    }
}
