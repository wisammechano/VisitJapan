package com.recoded.visitjapan;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class ViewerActivity extends AppCompatActivity {
    public ArrayList<Image> gallery;
    private TextView descTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int id = getIntent().getIntExtra(getString(R.string.image_id_extra_tag), 0) - 1; //-1 because ids start from 1 while indexes are zero based
        //((ImageView)findViewById(R.id.main_img)).setImageResource(getResources().getIdentifier("j_"+id, "drawable", getPackageName()));
        ViewPager vp = (ViewPager) findViewById(R.id.images_view_pager);
        descTv  = (TextView) findViewById(R.id.desc_txt);
        ViewerImageFragmentsAdapter pagerAdapter = new ViewerImageFragmentsAdapter(getSupportFragmentManager());
        try {
            gallery = Utilities.parseGalleryXML(this);
            pagerAdapter.setItems(gallery);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
            pagerAdapter.setItems(new ArrayList<Image>());
        }
        vp.setOffscreenPageLimit(1);
        vp.setAdapter(pagerAdapter);
        vp.setCurrentItem(id, false);
        descTv.setText(gallery.get(id).getTag());

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                descTv.setText(gallery.get(position).getTag());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item){
        supportFinishAfterTransition();
        return true;
    }
}
