package slidingmenu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.viktor.myneighbourhood.EditPostActivity;
import com.example.viktor.myneighbourhood.Post;
import com.example.viktor.myneighbourhood.PostStorage;
import com.example.viktor.myneighbourhood.Profile;
import com.example.viktor.myneighbourhood.R;
import com.example.viktor.myneighbourhood.ShowPostActivity;

import java.util.ArrayList;

public class ProfileFragment extends AppCompatActivity implements CallBackEditPostPostion {
    private static final String TAG = "Profile Tab";

    private ArrayList<String> posts;
    private ViewGroup container;
    private ListView mPostList;
    private int deleteoffset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        {
            super.onCreate(savedInstanceState);
            this.container = container;
            String[] history = {"Car Repair Needed", "Sink Fix Please"};

            posts = new ArrayList<>();
            deleteoffset=0;



            setContentView(R.layout.fragment_profile);


            Profile profile = Profile.getInstance();


            ArrayList<Post> myPosts = profile.getPosts();

            for (int i = 0 ; i<myPosts.size(); i++ ){
                posts.add(myPosts.get(i).getTitle());
            }

            // adapter for posts
            ListAdapter adapter = new CustomListViewAdapter(getBaseContext(), posts, this);

            mPostList = (ListView) findViewById(R.id.mPostList);
            mPostList.setAdapter(adapter);
            // adapter for history
            ListAdapter historyAdapter = new CustomHistoryListAdapter(getBaseContext(), history);
            ListView mHistoryList = (ListView) findViewById(R.id.mHistoryPostList);
            mHistoryList.setAdapter(historyAdapter);

            mPostList.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String food = String.valueOf(parent.getItemAtPosition(position));

                            Toast.makeText(getApplicationContext(), food, Toast.LENGTH_LONG).show();
                        }
                    }
            );

            // rating bar
            RatingBar rb = (RatingBar) findViewById(R.id.mRating);
            rb.setRating(Float.parseFloat("3.4"));

            rb.setOnRatingBarChangeListener(
                    new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                            String numStars = String.valueOf(rating);
                            Toast.makeText(getApplicationContext(), "The new rating is " + numStars, Toast.LENGTH_SHORT).show();
                        }
                    }
            );


        }}

    @Override
    public void passPosition(int position, String action) {
        if (action.equals("delete")){
            posts.remove(position);
            ((BaseAdapter) mPostList.getAdapter()).notifyDataSetChanged();
            PostStorage.getInstance().getPosts().remove(position);
            Toast.makeText(getApplicationContext(), "Post Deleted!", Toast.LENGTH_LONG).show();
        }else{ //edit
            Log.i("Passed", String.valueOf(posts.get(position)));
            Intent openDetailIntent = new Intent(getBaseContext(), EditPostActivity.class);
            openDetailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            openDetailIntent.putExtra("title", posts.get(position));
            getBaseContext().startActivity(openDetailIntent);
        }

    }
}
