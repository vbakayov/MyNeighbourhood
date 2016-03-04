package com.example.viktor.myneighbourhood;

/**
 * Created by Viktor on 3/4/2016.
 */

import android.app.AlertDialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Viktor on 3/3/2016.
 */
public class EditPostActivity extends AppCompatActivity {
    TextView descriptiopn;
    TextView title;
    private ImageView imagePost;
    private String postTitle;
    private Button EditButton;
    private ImageView imagePost2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);


        Bundle extras = getIntent().getExtras();
        postTitle = extras.getString("title");
        Log.d("TitleFromPass", postTitle);

        final Post post = findPost(postTitle);


        EditButton = (Button)findViewById(R.id.EditPostButton);
        descriptiopn   = (TextView)findViewById(R.id.editDescription);
        title   = (TextView)findViewById(R.id.title);
        imagePost = (ImageView) findViewById(R.id.imgAdd);
        imagePost2 = (ImageView) findViewById(R.id.imgAdd2);

        title.setText(post.getTitle());
        descriptiopn.setText(post.getDescription());
        //fill up the pictures if pictures are available
        if(post.getPicturePath() !=null)  imagePost.setImageBitmap(BitmapFactory.decodeFile(post.getPicturePath()));
        if(post.getPicturePath2() !=null)  imagePost2.setImageBitmap(BitmapFactory.decodeFile(post.getPicturePath2()));

        EditButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        post.setTitle(title.getText().toString());
                        post.setDescription(descriptiopn.getText().toString());
                        showAlertDialog("Successful","Your post was edited successfully", true);
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

    /**
     * Function to display simple Alert Dialog
     * @param title - alert dialog title
     * @param message - alert message
     * @param status - success/failure (used to set icon)
     * */
    public void showAlertDialog( String title, String message, Boolean status) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        // Setting Dialog Message
        builder.setMessage(message);
        // Setting alert dialog icon
        builder.setIcon((status) ? R.drawable.success : R.drawable.fail);

        builder.show();
    }



}
