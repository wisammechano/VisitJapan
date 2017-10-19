package com.recoded.visitjapan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by wisam on Oct 16 17.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private Fragment homeFrag, attractionsFrag, galleryFrag;

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        homeFrag = new MainHomeFragment();
        attractionsFrag = new MainAttractionsFragment();
        galleryFrag = new MainGalleryFragment();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return homeFrag;
        } else if (position == 1) {
            return attractionsFrag;
        } else {
            return galleryFrag;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
