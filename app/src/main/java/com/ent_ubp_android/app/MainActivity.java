package com.ent_ubp_android.app;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;
import com.ent_ubp_android.app.fragment.agenda.AgendaFragment;
import com.ent_ubp_android.app.fragment.FragmentSwitcher;
import com.ent_ubp_android.app.fragment.classroom.ClassroomMainFragment;
import com.ent_ubp_android.app.fragment.professeur.ProfesseurMainFragment;
import com.ent_ubp_android.app.fragment.profil.ProfileFragment;
import com.ent_ubp_android.app.fragment.formation.FormationMainFragment;

/**
 * Activite permettant de gerer le Slider Menu.
 * Chaque entrer du menu référencera un fragment
 */
public class MainActivity
        extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                   FragmentManager.OnBackStackChangedListener {

    //Toolbar
    Toolbar toolbar;

    //Variables for Navigation View
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    //Stock FragmentTag
    FragmentSwitcher fragmentSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        fragmentSwitcher = new FragmentSwitcher(this);

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialise and set the navigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        setNavigationView();

        //This is the creation of the app
        //Preserve fragment's state when screen rotate
        if(savedInstanceState == null)
            navigationView.getMenu().performIdentifierAction(navigationView.getMenu().getItem(0).getItemId(), 0);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        //Display the profil fragment
        if (id == R.id.action_user){
            navigationView.setCheckedItem(R.id.menuDrawer_profile);
            fragmentSwitcher.startAnotherFragment(new ProfileFragment());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop (){
        super.onStop();
    }

    //Stock Data when activity is reloaded
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    //Stock data when acitivity is stopped or when the context has changed
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceSate){
        super.onSaveInstanceState(savedInstanceSate);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawers();

        }else
            super.onBackPressed();
    }

    //Set the action of the navigation Menu
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        //Checking if the item is in checked state or not, if not make it in checked state
        if(menuItem.isChecked())
            menuItem.setChecked(false);
        else
            menuItem.setChecked(true);

        //Closing drawer on item click
        drawerLayout.closeDrawers();

        Fragment fragment = null;
        //Check to see which item was being clicked and perform appropriate action
        switch (menuItem.getItemId()){
            case R.id.menuDrawer_profile:
                Toast.makeText(getApplicationContext(), R.string.navigation_menu_profil,Toast.LENGTH_SHORT).show();
                fragment = new ProfileFragment();
                getSupportActionBar().setTitle(R.string.navigation_menu_profil);
                break;

            case R.id.menuDrawer_agenda:
                Toast.makeText(getApplicationContext(), R.string.navigation_menu_agenda,Toast.LENGTH_SHORT).show();
                fragment = new AgendaFragment();
                getSupportActionBar().setTitle(R.string.navigation_menu_agenda);
                break;

            case R.id.menuDrawer_formation:
                Toast.makeText(getApplicationContext(), R.string.navigation_menu_formation, Toast .LENGTH_LONG).show();
                fragment = new FormationMainFragment();
                getSupportActionBar().setTitle(R.string.navigation_menu_formation);
                break;

            case R.id.menuDrawer_classroom:
                Toast.makeText(getApplicationContext(), R.string.navigation_menu_classroom, Toast.LENGTH_LONG).show();
                fragment = new ClassroomMainFragment();
                getSupportActionBar().setTitle(R.string.navigation_menu_classroom);
                break;

            case R.id.menuDrawer_professeur:
                Toast.makeText(getApplicationContext(), R.string.navigation_menu_professeur, Toast.LENGTH_LONG).show();
                fragment = new ProfesseurMainFragment();
                getSupportActionBar().setTitle(R.string.navigation_menu_professeur);
                break;

            default:
                Toast.makeText(getApplicationContext(),"Erreur lors du chargement du menu",Toast.LENGTH_SHORT).show();
                break;

        }

        if(fragment != null) {
            fragmentSwitcher.startAnotherFragment(fragment);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
            return false;
        }
        return true;
    }

    @Override
    public void onBackStackChanged() {
        FragmentManager fm = FragmentSwitcher.getSupportFragmentManager();
        Fragment current = fm.findFragmentById(R.id.frame_container);

        if(fm.getBackStackEntryCount() == 0)
            finish();

        if(current != null){
            updateHighLightNavigationDrawer(current);
        }


    }

    public void updateHighLightNavigationDrawer(Fragment current){
        String tag = current.getClass().getName();

        if(tag.equals(ProfileFragment.class.getName())){
            navigationView.getMenu().getItem(0).setChecked(true);
            getSupportActionBar().setTitle(R.string.navigation_menu_profil);

        }else if(tag.equals(AgendaFragment.class.getName())){
            navigationView.getMenu().getItem(1).setChecked(true);
            getSupportActionBar().setTitle(R.string.navigation_menu_agenda);

        }else if(tag.equals(FormationMainFragment.class.getName())){
            navigationView.getMenu().getItem(2).setChecked(true);
            getSupportActionBar().setTitle(R.string.navigation_menu_formation);
        }
        else if(tag.equals(ClassroomMainFragment.class.getName())){
            navigationView.getMenu().getItem(3).setChecked(true);
            getSupportActionBar().setTitle(R.string.navigation_menu_classroom);
        }

        else if(tag.equals(ProfesseurMainFragment.class.getName())){


            navigationView.getMenu().getItem(4).setChecked(true);
            getSupportActionBar().setTitle(R.string.navigation_menu_professeur);
        }
    }

    private void setNavigationView(){
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(this);

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar,R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else icon wont show up
        actionBarDrawerToggle.syncState();
    }
}
