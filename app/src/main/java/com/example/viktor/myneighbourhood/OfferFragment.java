package com.example.viktor.myneighbourhood;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Viktor on 2/26/2016.
 */
public class OfferFragment extends Fragment {private static final String ARG_POSITION = "position";

    private int position;

    public static OfferFragment newInstance(int position) {
        OfferFragment f = new OfferFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_tab,container,false);
        return v;
    }
}
