package com.recoded.visitjapan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wisam on Oct 15 17.
 */

public class SplashImgFragment extends Fragment {
    public void setPosition(int position) {
        if(position == 1) {
            imageId = R.drawable.fuji3;
            introTextId = R.string.intro2;
        }
    }

    int imageId = R.drawable.fuji2;
    int introTextId = R.string.intro1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.splash_img_fragment_layout, container, false);
        ImageView img = layout.findViewById(R.id.splash_img);
        TextView txt = layout.findViewById(R.id.intro_text);
        img.setImageResource(imageId);
        txt.setText(getResources().getString(introTextId));
        return layout;
    }

}
