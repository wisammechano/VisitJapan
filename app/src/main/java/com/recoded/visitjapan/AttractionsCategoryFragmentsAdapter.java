package com.recoded.visitjapan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by wisam on Oct 18 17.
 */

public class AttractionsCategoryFragmentsAdapter extends FragmentPagerAdapter {
    private String[] categories = {"Nature", "History", "Industry", "Entertainment"};

    public AttractionsCategoryFragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categories[position];
    }

    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public Fragment getItem(int position) {
        AttractionsCategoryFragment frgmnt = new AttractionsCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Category", categories[position]);
        frgmnt.setArguments(bundle);
        return frgmnt;
    }


}
