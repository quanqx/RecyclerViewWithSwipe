package com.example.quand.recyclerviewwithswipe;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private AboutFragment aboutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_main);

        initFragment();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.White));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                switch(menuItem.getItemId()){
                    case R.id.menu_search:
                        Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_profile:
                        Toast.makeText(getApplicationContext(), "profile", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_change_password:
                        Toast.makeText(getApplicationContext(), "change pass", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_logout:
                        Toast.makeText(getApplicationContext(), "logout", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        aboutFragment = new AboutFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment = null;
        switch(menuItem.getItemId()){
            case R.id.nav_item_home:
                selectedFragment = homeFragment;
                break;
            case R.id.nav_item_search:
                selectedFragment = searchFragment;
                break;
            case R.id.nav_item_about:
                selectedFragment = aboutFragment;
                break;
        }
        setFragment(R.id.fl_container, selectedFragment);
        return true;
    }

    private void setFragment(int layout, Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(layout, fragment);
        transaction.commit();
    }
}
