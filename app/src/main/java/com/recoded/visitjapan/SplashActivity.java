package com.recoded.visitjapan;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {
    ViewPager vp;
    Button nextButton, prevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        initiateViewPager();

        implementButtons();

    }

    private void implementButtons() {
        nextButton = (Button) findViewById(R.id.next_button);
        prevButton = (Button) findViewById(R.id.prev_button);

        nextButton.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        prevButton.getBackground().setColorFilter(0xFFBB0000, PorterDuff.Mode.MULTIPLY);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prevButton.getVisibility() == View.GONE) {
                    prevButton.setVisibility(View.VISIBLE);
                }
                if (vp.getCurrentItem() == vp.getChildCount()-1) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
                vp.setCurrentItem(vp.getCurrentItem() + 1, true);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vp.getCurrentItem() != 1) {
                    vp.setCurrentItem(vp.getCurrentItem() - 1, true);
                } else {
                    vp.setCurrentItem(vp.getCurrentItem() - 1, true);
                    v.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initiateViewPager() {
        vp = (ViewPager) findViewById(R.id.splash_view_pager);

        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                SplashImgFragment frgmnt = new SplashImgFragment();
                frgmnt.setPosition(position);
                return frgmnt;
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
        vp.setAdapter(pagerAdapter);
    }
}
