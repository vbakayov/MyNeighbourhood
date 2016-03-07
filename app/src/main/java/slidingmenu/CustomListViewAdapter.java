package slidingmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viktor.myneighbourhood.R;

import java.util.ArrayList;

/**
 * Created by Viktor on 4/1/2016.
 */
public class CustomListViewAdapter extends ArrayAdapter<String> {

    private static final String TAG = "Active posts list view";
    private  CallBackEditPostPostion callback;

    public CustomListViewAdapter(Context context, ArrayList<String> posts, CallBackEditPostPostion callback){
        super(context, R.layout.custom_post_row_layout, posts);
        this.callback = callback;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_post_row_layout, parent, false);

        String postItem = getItem(position);
        TextView mPostText = (TextView) customView.findViewById(R.id.mPostText);
        ImageView mEditIcon = (ImageView) customView.findViewById(R.id.mEditIcon);
        ImageView mDeleteIcon = (ImageView) customView.findViewById(R.id.mDeleteIcon);

        mPostText.setText(postItem);
        mEditIcon.setImageResource(R.drawable.edit);
        mDeleteIcon.setImageResource(R.drawable.delete);
        mEditIcon.setTag(position);
        mDeleteIcon.setTag(position);
        mEditIcon.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View arg0) {
                        int position=(Integer)arg0.getTag();
                        callback.passPosition(position, "edit");
                       // Log.i(TAG, "EDIT BUTTON CLICKED"+ Integer.toString(position));
                    }
                }
        );

        mDeleteIcon.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View arg0) {
                        int position=(Integer)arg0.getTag();
                        callback.passPosition(position,"delete");
                    }
                }
        );

        return customView;

    }

    public void register(CallBackEditPostPostion callback) {
        callback.passPosition(2, "delete");
    }


}
