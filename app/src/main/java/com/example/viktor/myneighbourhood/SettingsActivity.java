package com.example.viktor.myneighbourhood;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by Viktor on 3/3/2016.
 */
public class SettingsActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "Settings";
    private EditText homeAdress;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        saveButton = (Button)findViewById(R.id.SaveButton);
        homeAdress = (EditText) findViewById(R.id.homeAddressnEdit);

        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        saveButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Log.v("EditText", homeAdress.getText().toString());
                        // Writing data to SharedPreferences
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("homeAddress", homeAdress.getText().toString());
                        editor.apply();
                    }
                });



    }


}