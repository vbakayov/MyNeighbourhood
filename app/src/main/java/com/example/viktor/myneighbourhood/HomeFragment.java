package com.example.viktor.myneighbourhood;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.cards.material.utils.RoundCornersDrawable;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardExpandableListView;
import it.gmariotti.cardslib.library.view.CardListView;
import it.gmariotti.cardslib.library.view.CardView;

/**
 * Created by Viktor on 2/26/2016.
 */
public class HomeFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    private int position;

    public static HomeFragment newInstance(int position) {
        HomeFragment f = new HomeFragment();
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initCards();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Resumerd","CAlled");
        initCards();
    }




    private void initCards() {

        //Init an array of Cards
        ArrayList<Card> cards = new ArrayList<Card>();
        PostStorage storage= PostStorage.getInstance();
         ArrayList<Post> posts= storage.getPosts();
        for (int i = 0 ; i< posts.size();i++){
            //Create a Card
            Card card = init_standard_header_with_expandcollapse_button_custom_area(posts.get(i).getTitle(),posts.get(i).getDescription());
            cards.add(card);

        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(),cards);

        CardListView listView = (CardListView) getActivity().findViewById(R.id.carddemo_list_expand);
        if (listView!=null){
            listView.setAdapter(mCardArrayAdapter);
        }
    }

    /**
     * This method builds a standard header with a custom expand/collpase
     */
    private Card init_standard_header_with_expandcollapse_button_custom_area(String titleHeader,String desciption) {

        //Create a Card
        Card card = new Card(getActivity());

        //Create a CardHeader
        CardHeader header = new CardHeader(getActivity());

        //Set the header title
        header.setTitle(titleHeader);

        //Set visible the expand/collapse button
        header.setButtonExpandVisible(true);

        //Add Header to card
        card.addCardHeader(header);


        //This provides a simple (and useless) expand area
        CustomExpandCard expand = new CustomExpandCard(getActivity(),desciption);
        //Add Expand Area to Card
        card.addCardExpand(expand);

        //Create thumbnail
        CardThumbnail thumb = new CardThumbnail(getActivity());

        //Set ID resource
       // thumb.setDrawableResource(R.drawable.ic_pages);

        //Add thumbnail to a card
        card.addCardThumbnail(thumb);

        //Set card in the cardView
//        CardView cardView = (CardView) getActivity().findViewById(R.id.carddemo_thumb_id);
//        cardView.setCard(card);

        //Just an example to expand a card
//        if (i==2 || i==7 || i==9)
//            card.setExpanded(true);

        //Swipe
        card.setSwipeable(true);

        //Animator listener
        card.setOnExpandAnimatorEndListener(new Card.OnExpandAnimatorEndListener() {
            @Override
            public void onExpandEnd(Card card) {
                Toast.makeText(getActivity(), "Expand " + card.getCardHeader().getTitle(), Toast.LENGTH_SHORT).show();
            }
        });


        //Animator listener

        //Add ClickListener
        card.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getContext(), "Click Listener card=" + card.getCardHeader().getTitle(), Toast.LENGTH_SHORT).show();
                //Open more detailed Activity
                Intent openDetailIntent = new Intent(getActivity(), ShowPostActivity.class);
                openDetailIntent.putExtra("title", card.getCardHeader().getTitle());
                getActivity().startActivity(openDetailIntent);

            }
        });

        card.setOnCollapseAnimatorEndListener(new Card.OnCollapseAnimatorEndListener() {
            @Override
            public void onCollapseEnd(Card card) {
                Toast.makeText(getActivity(),"Collpase " +card.getCardHeader().getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        return card;
    }

}
