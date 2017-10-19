package com.recoded.visitjapan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by wisam on Oct 18 17.
 */

public class ViewerImageFragmentsAdapter extends FragmentPagerAdapter {

    private ArrayList<Image> gallery;

    public ViewerImageFragmentsAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    public void setItems(ArrayList<Image> items){
        gallery = items;
    }

    @Override
    public Fragment getItem(int position) {
        ViewerImageFragment fragment = new ViewerImageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ResId", gallery.get(position).getResId());
        bundle.putString("Tag", gallery.get(position).getTag());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return gallery.size();
    }
}
