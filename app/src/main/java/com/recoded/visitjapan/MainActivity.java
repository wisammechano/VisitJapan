package com.recoded.visitjapan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainNoSwipeViewPager mViewPager;

    public ArrayList<Image> gallery;
    public ArrayList<Location> locations;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0, true);
                    return true;
                case R.id.navigation_attraction:
                    mViewPager.setCurrentItem(1, true);
                    return true;
                case R.id.navigation_gallety:
                    mViewPager.setCurrentItem(2, true);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (MainNoSwipeViewPager) findViewById(R.id.mainViewPager);
        MainFragmentPagerAdapter mPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        try {
            locations = Utilities.parseLocationXML(this);
            gallery = Utilities.parseGalleryXML(this);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
            locations = new ArrayList<>();
            gallery = new ArrayList<>();
        }

    }
}
