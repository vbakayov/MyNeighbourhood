package com.example.viktor.myneighbourhood;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.gmariotti.cardslib.library.internal.CardExpand;

/**
 * Created by Viktor on 3/1/2016.
 */
public class CustomExpandCard extends CardExpand {

    String description;

    public CustomExpandCard(Context context, String postingDesciption) {
        super(context, R.layout.carddemo_example_native_inner_expand);
        this.description = postingDesciption;
    }

    //You can set you properties here (example buttons visibility)

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        if (view == null) return;

        //Retrieve TextView elements
        TextView tx1 = (TextView) view.findViewById(R.id.carddemo_expand_text1);
        TextView tx2 = (TextView) view.findViewById(R.id.carddemo_expand_text2);
        TextView tx3 = (TextView) view.findViewById(R.id.carddemo_expand_text3);
        TextView tx4 = (TextView) view.findViewById(R.id.carddemo_expand_text4);


        tx2.setText(description);

        }
}