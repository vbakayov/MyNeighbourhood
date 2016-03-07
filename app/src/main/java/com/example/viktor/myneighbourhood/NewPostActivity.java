package com.example.viktor.myneighbourhood;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Viktor on 3/2/2016.
 */
public class NewPostActivity extends AppCompatActivity {
    Button postButton;
    EditText descriptiopn,title;
    CheckBox myhood, currentHood;
    private ImageView imagePost;
    private ImageView imagePost2;
    private String picturePath=null;
    private String picturePath2 = null;
    private RadioGroup radioTypeGroup;
    // Storage Permissions
    public static final String PREFS_NAME = "Settings";
    private static int RESULT_LOAD_IMAGE = 1;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private RadioButton radioTypeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);



        postButton = (Button)findViewById(R.id.PostButton);
        descriptiopn   = (EditText)findViewById(R.id.editDescription);
        title   = (EditText)findViewById(R.id.title);
        myhood = (CheckBox) findViewById(R.id.chk_myHood);
        currentHood = (CheckBox) findViewById(R.id.chk_thisHood);
        imagePost = (ImageView) findViewById(R.id.imgAdd);
        imagePost2 =  (ImageView) findViewById(R.id.imgAdd2);
        radioTypeGroup = (RadioGroup) findViewById(R.id.radioType);

        imagePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                verifyStoragePermissions(NewPostActivity.this);
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.putExtra("PictureID","1");
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        imagePost2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                verifyStoragePermissions(NewPostActivity.this);
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.putExtra("PictureID","2");
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });


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

                    if(!descriptiopn.getText().toString().equals("") && !title.getText().toString().equals(""))
                    {
                        // get selected radio button from radioGroup
                        int selectedId = radioTypeGroup.getCheckedRadioButtonId();
                        // find the radiobutton by returned id
                        radioTypeButton = (RadioButton) findViewById(selectedId);
                        //add the post to the central storage
                        PostStorage.addPost(new Post(title.getText().toString(),radioTypeButton.getText().toString(), descriptiopn.getText().toString(), "" +
                                "pictureSource", null, null, myhood.isChecked(), currentHood.isChecked(), homeAdress, currentAdress, picturePath,picturePath2));
                        //clear the fields
                        descriptiopn.setText("");
                        title.setText("");
                        //show the dialog
                        showAlertDialog("Post Successful", "Your posting has been succesfull", true);
                    }else{
                        showAlertDialog("Post Unsuccesful", "Please fill in all the details", false);
                    }

                }
            });
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
                Bundle bundle = data.getExtras();
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                ImageView imageView = (ImageView) findViewById(R.id.imgAdd);
                ImageView imageView2 = (ImageView) findViewById(R.id.imgAdd2);
                //qucik hack // TODO: change it to work with the bundle
                   if (picturePath== null){
                       picturePath = cursor.getString(columnIndex);
                       imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                   }

                    else{
                       picturePath2 = cursor.getString(columnIndex);
                       imageView2.setImageBitmap(BitmapFactory.decodeFile(picturePath2));
                   }

                cursor.close();

            }
        }

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
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
