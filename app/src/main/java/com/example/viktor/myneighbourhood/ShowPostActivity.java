package com.example.viktor.myneighbourhood;

import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Viktor on 3/3/2016.
 */
public class ShowPostActivity extends AppCompatActivity {
    TextView descriptiopn;
    TextView title;
    private ImageView imagePost;
    private String postTitle;
    private Button RequestDetailsButton;
    private ImageView imagePost2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);


        Bundle extras = getIntent().getExtras();
        postTitle = extras.getString("title");
        Log.d("TitleFromPass", postTitle);

       Post post = findPost(postTitle);


        RequestDetailsButton = (Button)findViewById(R.id.PostButton);
        descriptiopn   = (TextView)findViewById(R.id.editDescription);
        title   = (TextView)findViewById(R.id.title);
        imagePost = (ImageView) findViewById(R.id.imgAdd);
        imagePost2 = (ImageView) findViewById(R.id.imgAdd2);

        title.setText(post.getTitle());
        descriptiopn.setText(post.getDescription());
        //fill up the pictures if pictures are available
        if(post.getPicturePath() !=null)  imagePost.setImageBitmap(BitmapFactory.decodeFile(post.getPicturePath()));
        if(post.getPicturePath2() !=null)  imagePost2.setImageBitmap(BitmapFactory.decodeFile(post.getPicturePath2()));


        RequestDetailsButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view) {

                        Toast.makeText(ShowPostActivity.this, "Stub Request Details Button",
                                Toast.LENGTH_LONG).show();
                    }
                });


    }

    private  Post findPost(String postTitle){
        //get the corresponding Post TODO: use HashMap to find by name
        ArrayList<Post> posts=  PostStorage.getInstance().getPosts();
        for(Post post : posts){
            if(post.getTitle().equals(postTitle)){
                return post;
            }
        }
        return  null;
    }
}
