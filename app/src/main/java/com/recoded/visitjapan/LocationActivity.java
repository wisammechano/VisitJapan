package com.recoded.visitjapan;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.recoded.visitjapan.databinding.ActivityLocationBinding;

public class LocationActivity extends AppCompatActivity {
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLocationBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_location);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        location = getIntent().getParcelableExtra(getString(R.string.location_obj_extra_tag));
        setTitle(location.getName());
        binding.locPicture.setImageResource(location.getImgResource());
        binding.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + location.getTelephoneNo()));
                startActivityForResult(intent,0);
            }
        });

        binding.locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, location.getLocationUri());
                startActivity(intent);
            }
        });

        binding.websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(location.getUrl()));
                startActivity(intent);
            }
        });

        binding.setLocation(location);

        for (int i = 0; i < location.getGalleryArray().size(); i++) {
            View view = getLayoutInflater().inflate(R.layout.gallery_image_thumb, binding.smallGallery, false);
            ImageView img = (ImageView) view.findViewById(R.id.main_img);
            int resId = getResources().getIdentifier("j_" + location.getGalleryArray().get(i), "drawable", getPackageName());
            img.setImageResource(resId);

            final int temp = i;
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LocationActivity.this, ViewerActivity.class);
                    intent.putExtra(getString(R.string.image_id_extra_tag), location.getGalleryArray().get(temp)); //because there i is increased by 1;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation(LocationActivity.this, v, getString(R.string.trans_name_zoom_in));
                        startActivity(intent, options.toBundle());
                    } else {
                        startActivity(intent);
                    }

                }
            });
            binding.smallGallery.addView(view);
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        supportFinishAfterTransition();
        return true;
    }
}
