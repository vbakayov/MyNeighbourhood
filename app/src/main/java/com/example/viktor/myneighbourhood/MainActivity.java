package com.example.viktor.myneighbourhood;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import adapter.NavDrawerListAdapter;
import slidingmenu.MyNeighbourhoodActivity;
import slidingmenu.NavDrawerItem;
import slidingmenu.AboutActivity;
import slidingmenu.ProfileActivity;
import slidingmenu.SettingsActivity;
import slidingmenu.myCurrentLocationActivity;

public class MainActivity extends AppCompatActivity {

    private PagerTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter2;
    private Profile myProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Initialize the ViewPager and set an adapter
        tabs = (PagerTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());



        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);


        pager.setCurrentItem(1);
        tabs.setBackgroundColor(getResources().getColor(R.color.green));
       // tabs.setIndicatorColor(R.color.tabsScrollColor);
        tabs.setTextColor(getResources().getColor(R.color.text_shadow_white));


        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Profile
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // My Hood
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1), true, "22"));
        // My Location
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1), true, "14"));
        // Settigs
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // About
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));


        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        // setting the nav drawer list adapter
        adapter2 = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        pager.setAdapter(adapter);
        pager.setCurrentItem(1);
       // tabs.setViewPager(pager);
       tabs.setTextSize(1, 20);
        tabs.setDrawFullUnderline(true);
        tabs.setTabIndicatorColorResource(R.color.tabsScrollColor);

        populatePostView();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(getBaseContext(), NewPostActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(myIntent);
                ;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = { "OFFER", "HOME", "WANTED", "INFO" };

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0: // Fragment # 1 - Offers
                    return OfferFragment.newInstance(0);
                case 1: // Fragment # 2 - Home Fragment
                    return HomeFragment.newInstance(1);
                case 2: // Fragment # 3 - Wanted Fragment
                    return WantedFragment.newInstance(2);
                case 3 : //Fragmern # 3 - InfoFragment
                    return InfoFragment.newInstance(3);

                default:
                    return null;

            }
        }
    }
    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            displayView(position);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* *
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        android.app.Fragment fragment = null;
        switch (position) {
            case 0:
                Log.d("TEST","CLicked Postion 0");
                Intent myIntent = new Intent(this, ProfileActivity.class);
                this.startActivity(myIntent);
                break;
            case 1:
                Intent myIntent2 = new Intent(this, MyNeighbourhoodActivity.class);
                this.startActivity(myIntent2);
                break;
            case 2:
                Intent myIntent3 = new Intent(this, myCurrentLocationActivity.class);
                this.startActivity(myIntent3);
                break;
            case 3:
                Intent myIntent4 = new Intent(this, SettingsActivity.class);
                this.startActivity(myIntent4);
                break;
            case 4:
                fragment = new AboutActivity();
                break;

            default:
                break;
        }

        if (fragment != null) {

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    //meyhod to populate the info to the storage
    private void populatePostView() {
       Profile myProfile=  Profile.getInstance();
        myProfile.setName("Viktor");
        myProfile.setName("4.2");

        PostStorage.addPost(new Post("Babysitter needed","offer", "I search for a babysitter during the day between 9 to 5 pm.", "picturesrc1", "picturesrc2", "owner", true, false, "148 Queen Margaret Dr, Glasgow G20 8NY", " ", null, null));
        PostStorage.addPost(new Post("Spanish tutoring ","offer", "I have a trouble wiht my spanish classes and I am looking for help", "picturesrc1", "picturesrc2", "owner", true, false, "Firhill Ct, Glasgow G20 7BB", " ", null, null));
        PostStorage.addPost(new Post("Second Year Economics book offer ","needed", "Hi there, I am looking for a book called principles of economics by Greg Mankin", "picturesrc1", "picturesrc2", "owner",true,false,"39 Dalmally St, Glasgow G20 6RN", " ", null, null));
        PostStorage.addPost(new Post("Big Dinner Table Needed","offer", "For my dining room I am looking for a black minimum 6 placed dinner table", "picturesrc1", "picturesrc2", "owner", true, false, "671 Garscube Rd, Glasgow G20 7JX", "  ", null, null));
        PostStorage.addPost(new Post("Great Gig today at Ubiquitous Chip","wanted", "We are having free burgers only today. Come and grab one !", "picturesrc1", "picturesrc2", "owner", true, false, "12 Ashton Ln, Glasgow G12 8SJ", "  ", null, null));
        PostStorage.addPost(new Post("Free meal this week at Casa Russo","wanted", "Rustic Alpine venue with wood paneling and lanterns serving refined French brasserie cuisine. ONLY TODAY", "picturesrc1", "picturesrc2", "owner", true, false, "360 Byres Road, Glasgow G12 8AY", "  ", null, null));

        myProfile.setPosts(PostStorage.getInstance().getPosts());
    }
}
