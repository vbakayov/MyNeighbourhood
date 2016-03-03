package com.example.viktor.myneighbourhood;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Viktor on 3/2/2016.
 */
public class NewPostActivity extends AppCompatActivity {
    Button postButton;
    EditText descriptiopn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);



        postButton = (Button)findViewById(R.id.PostButton);
        descriptiopn   = (EditText)findViewById(R.id.editDescription);

        postButton.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    Log.v("EditText", descriptiopn.getText().toString());
                }
            });


    }
}
