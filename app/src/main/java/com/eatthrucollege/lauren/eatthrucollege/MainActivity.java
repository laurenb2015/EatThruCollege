package com.eatthrucollege.lauren.eatthrucollege;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
// do a title stack
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // start new home page fragment
            Fragment fragment = new FoodFragment();
            Bundle args = new Bundle();
            args.putInt(FoodFragment.ARG_CAT_NUMBER, R.id.nav_home);
            fragment.setArguments(args);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            // end of creating home page fragment

           /*
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
*/
            setPageTitle(R.id.nav_home);
  /*
            // hovering action button on lower right
            // goes to specific webpage of e-book
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            // navigation drawer
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            */
        }
        else {
            Fragment f = getFragmentManager().findFragmentById(R.id.content_frame);
            int id = f.getArguments().getInt(FoodFragment.ARG_CAT_NUMBER);
            setPageTitle(id);

        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setPageTitle(R.id.nav_home);
        // hovering action button on lower right
        // goes to specific webpage of e-book
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        //Fragment f = getFragmentManager().findFragmentById(R.id.content_frame);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getFragmentManager().getBackStackEntryCount() > 0) {

            getFragmentManager().popBackStack();
            getFragmentManager().executePendingTransactions();
            Fragment f = getFragmentManager().findFragmentById(R.id.content_frame);
            int id = f.getArguments().getInt(FoodFragment.ARG_CAT_NUMBER);
            setPageTitle(id);

            //Fragment f = getFragmentManager().findFragmentById(R.id.content_frame);
            //int id = f.getArguments().getInt(FoodFragment.ARG_CAT_NUMBER);
            //setPageTitle(id);
            Log.w("EatThruCollege", "popped from backstack");
         //   getSupportFragmentManager().executePendingTransactions();
        } else {
            Log.w("EatThruCollege", "did not add to backstack");
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // called when menu item in navigation drawer is chosen
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        // creates a new fragment object which
        // replaces fragment frame in content_main.xml
        Fragment fragment = new FoodFragment();
        Bundle args = new Bundle();

        // put id number of selected button
        // to replace fragment with chosen page
        args.putInt(FoodFragment.ARG_CAT_NUMBER, id);
        fragment.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.content_frame, fragment).commit();
        Log.w("EatThruCollege", "forward changing title below");
        setPageTitle(id);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.setExpanded(true,true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // makes app icon in navigation drawer bring user
    // to home page when clicked.
    // this method signature is required for
    // image onClick functions. used to return true if worked
    // image is in nav_header_main.xml
    public void onHomeImageClicked(View view) {
        // creates a new fragment object which
        // replaces fragment frame in content_main.xml
        Fragment fragment = new FoodFragment();
        Bundle args = new Bundle();

        // put id number home page into fragment
        args.putInt(FoodFragment.ARG_CAT_NUMBER, R.id.nav_home);
        fragment.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.content_frame, fragment).commit();

        Log.w("EatThruCollege", "forward changing title below");
        setPageTitle(R.id.nav_home);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.setExpanded(true, true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    // set title of page based on current selection
    // given id number to figure out which page
    public void setPageTitle(int id) {

        CollapsingToolbarLayout collapsingBar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        Log.w("EatThruCollege", "title changing from backstack");

        ImageView image = (ImageView) findViewById(R.id.header_image);


        if (id == R.id.nav_cat1) {
            // Handle the quick energy action
            collapsingBar.setTitle(getResources().getString(R.string.cat1));
            image.setImageResource(R.drawable.energy_header);
        } else if (id == R.id.nav_cat2) {
            collapsingBar.setTitle(getResources().getString(R.string.cat2));
            image.setImageResource(R.drawable.sleep_header);
        } else if (id == R.id.nav_cat3) {
            collapsingBar.setTitle(getResources().getString(R.string.cat3));
            image.setImageResource(R.drawable.brain_food);
        } else if (id == R.id.nav_cat4) {
            collapsingBar.setTitle(getResources().getString(R.string.cat4));
            image.setImageResource(R.drawable.protein_header);
        } else if (id == R.id.nav_cat5) {
            collapsingBar.setTitle(getResources().getString(R.string.cat5));
            image.setImageResource(R.drawable.endurance_header);
        } else if (id == R.id.nav_cat6) {
            collapsingBar.setTitle(getResources().getString(R.string.cat6));
            image.setImageResource(R.drawable.period_header);
        } else if (id == R.id.nav_cat7) {
            collapsingBar.setTitle(getResources().getString(R.string.cat7));
            image.setImageResource(R.drawable.healthy_header);
        } else if (id == R.id.nav_cat8) {
            collapsingBar.setTitle(getResources().getString(R.string.cat8));
            image.setImageResource(R.drawable.flu_header);
        } else if (id == R.id.nav_cat9) {
            collapsingBar.setTitle(getResources().getString(R.string.cat9));
            image.setImageResource(R.drawable.stress_header);
        } else if (id == R.id.nav_cat10) {
            collapsingBar.setTitle(getResources().getString(R.string.cat10));
            image.setImageResource(R.drawable.freshman15_header);
        } else if (id == R.id.nav_cat11) {
            collapsingBar.setTitle(getResources().getString(R.string.cat11));
            image.setImageResource(R.drawable.grinds_header);
        } else if (id == R.id.nav_search) { // other menu options from now on
            collapsingBar.setTitle(getResources().getString(R.string.menu1));
            image.setImageResource(R.drawable.energy_header);
        } else if (id == R.id.nav_site) {
            collapsingBar.setTitle(getResources().getString(R.string.menu2));
            image.setImageResource(R.drawable.website_header);
        } else if (id == R.id.nav_contact) {
            collapsingBar.setTitle(getResources().getString(R.string.menu3));
            image.setImageResource(R.drawable.contact_header);
        } else if (id == R.id.nav_home) {
            collapsingBar.setTitle("Intro");
            image.setImageResource(R.drawable.veggietemplate);
        }
        Log.w("EatThruCollege", "title changed");
    }
    /**
     * Fragment that appears in the "content_frame"
     * extra fragment class that allows one activity to show
     * multiple different content fragments
     */
    public static class FoodFragment extends Fragment {
        // key-value pair
        // key is string and value is button id
        public static final String ARG_CAT_NUMBER = "cat_number";
        public FoodFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int id = getArguments().getInt(ARG_CAT_NUMBER);
            View rootView = null;

            // BELOW ALL LAYOUT FRAGMENTS ARE CHANGED FROM (E.G.) CONTENT_QUICK_ENERGY
            // TO MAKE INDIVIDUAL TOOLBARS
            // 11 food categories
            // update the main content by replacing fragments
            // centralised so fragment names only here
            if (id == R.id.nav_cat1) {
                // Handle the quick energy action
                rootView = inflater.inflate(R.layout.content_quick_energy, container, false);
            } else if (id == R.id.nav_cat2) {
                rootView = inflater.inflate(R.layout.content_sleep, container, false);
            } else if (id == R.id.nav_cat3) {
                rootView = inflater.inflate(R.layout.content_brain_function, container, false);
            } else if (id == R.id.nav_cat4) {
                rootView = inflater.inflate(R.layout.content_protein, container, false);
            } else if (id == R.id.nav_cat5) {
                rootView = inflater.inflate(R.layout.content_stay_awake, container, false);
            } else if (id == R.id.nav_cat6) {
                rootView = inflater.inflate(R.layout.content_period, container, false);
            } else if (id == R.id.nav_cat7) {
                rootView = inflater.inflate(R.layout.content_healthy, container, false);
            } else if (id == R.id.nav_cat8) {
                rootView = inflater.inflate(R.layout.content_fight_cold, container, false);
            } else if (id == R.id.nav_cat9) {
                rootView = inflater.inflate(R.layout.content_reduce_stress, container, false);
            } else if (id == R.id.nav_cat10) {
                rootView = inflater.inflate(R.layout.content_fresh15, container, false);
            } else if (id == R.id.nav_cat11) {
                rootView = inflater.inflate(R.layout.content_grinds, container, false);
            } else if (id == R.id.nav_search) { // other menu options from now on
                rootView = inflater.inflate(R.layout.content_sleep, container, false);
            } else if (id == R.id.nav_site) {
                rootView = inflater.inflate(R.layout.content_site, container, false);
            } else if (id == R.id.nav_contact) {
                rootView = inflater.inflate(R.layout.content_contact, container, false);
            } else if (id == R.id.nav_home) {
                rootView = inflater.inflate(R.layout.content_home, container, false);
            }

            return rootView;
        }
    }
}
