package slidingmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.viktor.myneighbourhood.R;

/**
 * Created by Nikolay on 18/11/2015.
 */
public class CustomHistoryListAdapter extends ArrayAdapter<String> {

    public CustomHistoryListAdapter(Context context, String[] history){
        super(context, R.layout.custom_history_row_layout, history);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_history_row_layout, parent, false);

        String postItem = getItem(position);
        TextView mHistoryText = (TextView) customView.findViewById(R.id.mHistoryText);

        mHistoryText.setText(postItem);


        return customView;
    }
}
