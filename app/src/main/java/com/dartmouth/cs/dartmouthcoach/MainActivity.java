package com.dartmouth.cs.dartmouthcoach;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Dartmouth Coach");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Dartmouth Coach");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);

        //Create a schedule fragment to start the view on
        IntroScreenFragment iFrag = new IntroScreenFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, iFrag).commit();

    }

    //control the drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //inflate the menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    //set up the drawer menu and clicker
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
        fragment = null;

        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_schedule:
                fragmentClass = ScheduleFragment.class;
                toolbar.setTitle("Schedule");
                break;
            case R.id.nav_purchase_tickets:
                fragmentClass = PurchaseTicketFragment.class;
                toolbar.setTitle("Purchase Ticket");
                break;
            case R.id.nav_tracker:
                fragmentClass = TrackerFragment.class;
                toolbar.setTitle("Trip Tracker");
                break;
            case R.id.nav_my_tickets:
                fragmentClass = MyTicketFragment.class;
                toolbar.setTitle("My Tickets");
                break;
            case R.id.nav_profile:
                fragmentClass = ProfileFragment.class;
                toolbar.setTitle("Profile");
                break;
            case R.id.nav_payment_settings:
                fragmentClass = PaymentSettingsFragment.class;
                toolbar.setTitle("Payment Settings");
                break;
            case R.id.nav_help:
                fragmentClass = HelpFragment.class;
                toolbar.setTitle("Help");
                break;
            default:
                fragmentClass = ScheduleFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item, update the title, and close the drawer
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
