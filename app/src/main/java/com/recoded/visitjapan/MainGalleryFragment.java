package com.recoded.visitjapan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by wisam on Oct 16 17.
 */

public class MainGalleryFragment extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        GridView gridView = (GridView) inflater.inflate(R.layout.fragment_gallery_main, container, false);
        gridView.setAdapter(new ImageAdapter(getActivity()));
        return gridView;
    }

    private class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private ArrayList<Image> gallery;

        ImageAdapter(Context c) {
            mContext = c;
            gallery = ((MainActivity) mContext).gallery;
        }

        @Override
        public int getCount() {
            return gallery.size();
        }

        @Override
        public Image getItem(int position) {
            return gallery.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final View v;
            if (convertView == null) {
                v = getActivity().getLayoutInflater().inflate(R.layout.gallery_image_thumb, parent, false);
            } else {
                v = convertView;
            }
            ImageView imageView = v.findViewById(R.id.main_img);
            imageView.setImageResource(gallery.get(position).getResId());
            imageView.setContentDescription(gallery.get(position).getTag());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ViewerActivity.class);
                    intent.putExtra(getString(R.string.image_id_extra_tag), gallery.get(position).getId());
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(getActivity(), v, getString(R.string.trans_name_zoom_in));
                    startActivity(intent, options.toBundle());
                }
            });
            return v;
        }
    }
}
