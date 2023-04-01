package com.soft.audiomaster.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.soft.audiomaster.Fragment.AboutFragment;
import com.soft.audiomaster.Fragment.HomeFragment;
import com.soft.audiomaster.Fragment.PlaylistFragment;
import com.soft.audiomaster.Fragment.SearchFragment;
import com.soft.audiomaster.Fragment.SettingFragment;
import com.soft.audiomaster.Fragment.ShareFragment;
import com.soft.audiomaster.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.am_toolbar);
        drawerLayout = findViewById(R.id.am_drawer_layout);
        bottomNavigationView = findViewById(R.id.am_bottomNavigationView);
        NavigationView navigationView = findViewById(R.id.am_nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,
        toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.am_fragment_container,
            new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }


        replaceFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.bottom_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.am_fragment_container
                            ,new HomeFragment()).commit();
                    break;
                case R.id.bottom_playlist:
                    getSupportFragmentManager().beginTransaction().replace(R.id.am_fragment_container
                            ,new PlaylistFragment()).commit();
                    break;
                case R.id.bottom_search:
                    getSupportFragmentManager().beginTransaction().replace(R.id.am_fragment_container
                            ,new SearchFragment()).commit();
                    break;
            }
            return true;
        });


    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.nav_home:
//                getSupportFragmentManager().beginTransaction().replace(R.id.am_fragment_container,new HomeFragment()).commit();
//                break;
//            case R.id.nav_share:
//                getSupportFragmentManager().beginTransaction().replace(R.id.am_fragment_container,new ShareFragment()).commit();
//                break;
//            case R.id.nav_settings:
//                getSupportFragmentManager().beginTransaction().replace(R.id.am_fragment_container,new SettingFragment()).commit();
//                break;
//            case R.id.nav_about:
//                getSupportFragmentManager().beginTransaction().replace(R.id.am_fragment_container,new AboutFragment()).commit();
//                break;
//            case R.id.nav_logout:
//                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
//                finish();
//                break;
//        }
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//
//    @Override
//    public void onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }else {
//            super.onBackPressed();
//        }
//    }


    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.am_fragment_container, fragment);
        fragmentTransaction.commit();
    }

}