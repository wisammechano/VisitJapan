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

public class ViewerImageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int resId = getArguments().getInt("ResId");
        String tag = getArguments().getString("Tag");
        View layout = inflater.inflate(R.layout.fragment_image_viewer, container, false);
        ImageView imageView = layout.findViewById(R.id.main_img);
        //TextView txt = layout.findViewById(R.id.desc_txt);
        imageView.setImageResource(resId);
        imageView.setContentDescription(tag);
        //txt.setText(tag);
        return layout;
    }
}
