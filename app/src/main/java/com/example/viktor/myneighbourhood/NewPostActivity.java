package com.example.viktor.myneighbourhood;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by Viktor on 3/2/2016.
 */
public class NewPostActivity extends AppCompatActivity {
    Button postButton;
    EditText descriptiopn,title;
    CheckBox myhood, currentHood;
    public static final String PREFS_NAME = "Settings";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);



        postButton = (Button)findViewById(R.id.PostButton);
        descriptiopn   = (EditText)findViewById(R.id.editDescription);
        title   = (EditText)findViewById(R.id.title);
        myhood = (CheckBox) findViewById(R.id.chk_myHood);
        currentHood = (CheckBox) findViewById(R.id.chk_thisHood);

        postButton.setOnClickListener(
            new View.OnClickListener()
            {
                public void onClick(View view)
                {
                    Log.v("EditText", descriptiopn.getText().toString());
                    Log.v("EditTextTitle", title.getText().toString());

                    Log.v("CheckBoxmyHood", Boolean.toString(myhood.isChecked()));
                    Log.v("CheckBoxcurrentHood", Boolean.toString(currentHood.isChecked()));


                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                    String homeAdress = settings.getString("homeAddress", "Glasgow");
                    String currentAdress = "";
                    Log.d("Address from map", homeAdress);
                    PostStorage.addPost(new Post(title.getText().toString(), descriptiopn.getText().toString(), "" +
                            "pictureSource", null, null, myhood.isChecked(),currentHood.isChecked(), homeAdress,currentAdress ));
                }
            });


    }
}
