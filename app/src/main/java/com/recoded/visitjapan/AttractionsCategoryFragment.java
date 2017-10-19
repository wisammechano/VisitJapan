package com.recoded.visitjapan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wisam on Oct 18 17.
 */

public class AttractionsCategoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ListView layout = (ListView) inflater.inflate(R.layout.fragment_category_attractions, container, false);
        String cat = getArguments().getString("Category");
        ArrayList<Location> locations = ((MainActivity) getActivity()).locations;
        ArrayList<Location> catLocations = new ArrayList<>();
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getCategory().equalsIgnoreCase(cat)) {
                catLocations.add(locations.get(i));
            }
        }

        MyArrayAdapter adapter = new MyArrayAdapter(getContext(), R.layout.location_item);
        adapter.setCollection(catLocations);
        layout.setAdapter(adapter);

        return layout;
    }

    private class MyArrayAdapter extends ArrayAdapter {

        private ArrayList<Location> collection;
        private int layoutRes;
        Context mContext;

        public MyArrayAdapter(@NonNull Context context, @LayoutRes int resource) {
            super(context, resource);
            layoutRes = resource;
            mContext = context;
        }

        public void setCollection(ArrayList<Location> collection) {
            this.collection = collection;
        }

        @Override
        public int getCount() {
            return collection.size();
        }

        @Override
        public String getItem(int position) {
            return collection.get(position).getName();
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(layoutRes, container, false);
            }
            ((ImageView) convertView.findViewById(R.id.loc_thumb)).setImageResource(collection.get(position).getImgResource());
            ((TextView) convertView.findViewById(R.id.loc_name)).setText(collection.get(position).getName());
            ((TextView) convertView.findViewById(R.id.loc_addr)).setText(collection.get(position).getAddress());
            ((TextView) convertView.findViewById(R.id.loc_tel)).setText(collection.get(position).getTelephoneNo());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LocationActivity.class);
                    intent.putExtra(getString(R.string.location_obj_extra_tag), collection.get(position));
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(getActivity(),v.findViewById(R.id.loc_thumb), getString(R.string.trans_name_loc_pic));
                    startActivity(intent, options.toBundle());
                }
            });

            return convertView;
        }
    }
}