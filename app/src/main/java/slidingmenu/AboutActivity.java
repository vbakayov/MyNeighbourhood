package slidingmenu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viktor.myneighbourhood.R;

//this fragment is not yet implemented
public class AboutActivity extends Fragment {
	
	public AboutActivity(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.activoty_about, container, false);
         
        return rootView;
    }
}
