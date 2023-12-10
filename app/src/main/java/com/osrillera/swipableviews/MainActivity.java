package com.osrillera.swipableviews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.osrillera.swipableviews.adapter.AdapterViewPager;
import com.osrillera.swipableviews.fragment.FragmentInventory;
import com.osrillera.swipableviews.fragment.FragmentProfile;
import com.osrillera.swipableviews.fragment.FragmentUpload;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager2 pagerMain;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    BottomNavigationView bottomNav;
    private static final int IT_INVENTORY_ID = R.id.itInventory;
    private static final int IT_UPLOAD_ID = R.id.itUpload;
    private static final int IT_PROFILE_ID = R.id.itProfile;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pagerMain = findViewById(R.id.pagerMain);
        bottomNav = findViewById(R.id.bottomNav);
        fragmentArrayList.add(new FragmentInventory());
        fragmentArrayList.add(new FragmentUpload());
        fragmentArrayList.add(new FragmentProfile());

        AdapterViewPager adapterViewPager = new AdapterViewPager(this,fragmentArrayList);
        //setAdapter
        pagerMain.setAdapter(adapterViewPager);
        pagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:
                        bottomNav.setSelectedItemId(IT_INVENTORY_ID);
                        break;
                    case 1:
                        bottomNav.setSelectedItemId(IT_UPLOAD_ID);
                        break;
                    case 2:
                        bottomNav.setSelectedItemId(IT_PROFILE_ID);
                        break;
                }

                super.onPageSelected(position);
            }
        });
        bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.itInventory) {
                pagerMain.setCurrentItem(0);
            } else if (item.getItemId() == R.id.itUpload) {
                pagerMain.setCurrentItem(1);
            } else if (item.getItemId() == R.id.itProfile) {
                pagerMain.setCurrentItem(2);
            }
            return true;
        });

    }
}