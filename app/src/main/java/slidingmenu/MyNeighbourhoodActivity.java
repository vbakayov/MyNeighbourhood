package slidingmenu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.viktor.myneighbourhood.Post;
import com.example.viktor.myneighbourhood.PostStorage;
import com.example.viktor.myneighbourhood.R;
import com.example.viktor.myneighbourhood.ShowPostActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyNeighbourhoodActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;
    public static final String PREFS_NAME = "Settings";
    private Marker homeMarker;
    // Since we are consuming the event this is necessary to
    // manage closing opened markers before opening new ones
    Marker lastOpened = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_neighbourhood2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.

     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        // Reading from SharedPreferences
        String value = settings.getString("homeAddress", "19 Murano Place, G20 7AD, Glasgow");
        // Add a marker in homelocaton and move the camera
        LatLng homelocatoin = getLatLongFromPlace(value);
        MarkerOptions marker= new MarkerOptions().position(homelocatoin).title("You are Here");
        marker.snippet("This is your home address");
        // Changing marker icon
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.googlemapmarker));

        homeMarker = mMap.addMarker(marker);
        homeMarker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(homelocatoin, 15.0f));
        setUpMarkersFromPost();
        mMap.setOnMarkerClickListener(this);
    }

    private void setUpMarkersFromPost() {

      ArrayList<Post> allPosts=  PostStorage.getInstance().getPosts();
        for(int post=0; post< allPosts.size(); post++){
           Post currentPost = allPosts.get(post);
            LatLng position = getLatLongFromPlace(currentPost.getHomeAddress());
             mMap.addMarker(new MarkerOptions().position(position).title(currentPost.getTitle()));
            ;
        }

    }


    public LatLng getLatLongFromPlace(String place) {
        LatLng position = null;
        try {
            Geocoder selected_place_geocoder = new Geocoder(this);
            List<Address> address;

            address = selected_place_geocoder.getFromLocationName(place, 5);

            if (address == null) {
//                d.dismiss();
            } else {
                Address location = address.get(0);
                Double lat= location.getLatitude();
                Double lng = location.getLongitude();
                position = new LatLng(lat, lng);

            }

        } catch (Exception e) {
            e.printStackTrace();
            fetchLatLongFromService fetch_latlng_from_service_abc = new fetchLatLongFromService(
                    place.replaceAll("\\s+", ""));
            fetch_latlng_from_service_abc.execute();

        }
    return position;
    }


//Sometimes happens that device gives location = null

    public class fetchLatLongFromService extends
            AsyncTask<Void, Void, StringBuilder> {
        String place;


        public fetchLatLongFromService(String place) {
            super();
            this.place = place;

        }

        @Override
        protected void onCancelled() {
            // TODO Auto-generated method stub
            super.onCancelled();
            this.cancel(true);
        }

        @Override
        protected StringBuilder doInBackground(Void... params) {
            // TODO Auto-generated method stub
            try {
                HttpURLConnection conn = null;
                StringBuilder jsonResults = new StringBuilder();
                String googleMapUrl = "http://maps.googleapis.com/maps/api/geocode/json?address="
                        + this.place + "&sensor=false";

                URL url = new URL(googleMapUrl);
                conn = (HttpURLConnection) url.openConnection();
                InputStreamReader in = new InputStreamReader(
                        conn.getInputStream());
                int read;
                char[] buff = new char[1024];
                while ((read = in.read(buff)) != -1) {
                    jsonResults.append(buff, 0, read);
                }
                String a = "";
                return jsonResults;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(StringBuilder result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            try {
                JSONObject jsonObj = new JSONObject(result.toString());
                JSONArray resultJsonArray = jsonObj.getJSONArray("results");

                JSONObject before_geometry_jsonObj = resultJsonArray
                        .getJSONObject(0);

                JSONObject geometry_jsonObj = before_geometry_jsonObj
                        .getJSONObject("geometry");

                JSONObject location_jsonObj = geometry_jsonObj
                        .getJSONObject("location");

                String lat_helper = location_jsonObj.getString("lat");
                double lat = Double.valueOf(lat_helper);


                String lng_helper = location_jsonObj.getString("lng");
                double lng = Double.valueOf(lng_helper);


                LatLng point = new LatLng(lat, lng);


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
        }


    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        // Check if there is an open info window
        if (lastOpened != null) {
            // Close the info window
            lastOpened.hideInfoWindow();

            // Is the marker the same marker that was already open
            if (lastOpened.equals(marker)) {
                // Nullify the lastOpened object

                if(!lastOpened.equals(homeMarker)) {
                    Intent openDetailIntent = new Intent(getBaseContext(), ShowPostActivity.class);
                    openDetailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    openDetailIntent.putExtra("title", marker.getTitle());
                    getBaseContext().startActivity(openDetailIntent);
                    lastOpened = null;
                }
                // Return so that the info window isn't opened again
                lastOpened = null;
                return true;
            }
        }

        // Open the info window for the marker
        marker.showInfoWindow();
        // Re-assign the last opened such that we can close it later
        lastOpened = marker;
        // Event was handled by our code do not launch default behaviour.
        return true;
    }




}
