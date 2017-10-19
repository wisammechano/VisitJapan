package com.recoded.visitjapan;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wisam on Oct 16 17.
 */

public class MainAttractionsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_attractions_main, container, false);
        ViewPager vp = layout.findViewById(R.id.categories_view_pager);
        AttractionsCategoryFragmentsAdapter pagerAdapter = new AttractionsCategoryFragmentsAdapter(getFragmentManager());
        vp.setAdapter(pagerAdapter);
        ((TabLayout) layout.findViewById(R.id.tab_layout)).setupWithViewPager(vp);

        return layout;
    }
}
