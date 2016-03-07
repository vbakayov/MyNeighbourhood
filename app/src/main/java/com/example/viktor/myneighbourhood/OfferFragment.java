package com.example.viktor.myneighbourhood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by Viktor on 2/26/2016.
 */
public class OfferFragment extends Fragment {private static final String ARG_POSITION = "position";

    private int position;
    private CardListView listView;

    public static OfferFragment newInstance(int position) {
        OfferFragment f = new OfferFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v=  inflater.inflate(R.layout.offer_tab,container,false);
        listView = (CardListView) v.findViewById(R.id.carddemo_list_expand);
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
        initCards();
    }




    private void initCards() {

        //Init an array of Cards
        ArrayList<Card> cards = new ArrayList<Card>();
        PostStorage storage= PostStorage.getInstance();
        ArrayList<Post> posts= storage.getPosts();
        for (int i = 0 ; i< posts.size();i++){
            if (posts.get(i).getType().equals("offer")) {
                //Create a Card
                Card card = init_standard_header_with_expandcollapse_button_custom_area(posts.get(i).getTitle(), posts.get(i).getDescription());
                cards.add(card);

            }

        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(),cards);
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

        //Swipe
        card.setSwipeable(true);

        //Animator listener
        card.setOnExpandAnimatorEndListener(new Card.OnExpandAnimatorEndListener() {
            @Override
            public void onExpandEnd(Card card) {

            }
        });


        //Add ClickListener
        card.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {

                //Open more detailed Activity
                Intent openDetailIntent = new Intent(getActivity(), ShowPostActivity.class);
                openDetailIntent.putExtra("title", card.getCardHeader().getTitle());
                getActivity().startActivity(openDetailIntent);

            }
        });


        return card;
    }
}
