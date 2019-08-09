package com.harsh.notesapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import DataBase.SqlHelperClass;


public class NavigationDrawerAct extends AppCompatActivity{

    private WebView webView;

    Toolbar customToolBar;
    NavigationView nav_view;
    DrawerLayout nav_drawer;

    AddNotes addNotes;
    NotesList notesList;
    AboutUsFragment aboutUsFragment;
    ContactUsFragment contactUsFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        nav_view =(NavigationView) findViewById(R.id.nav_view);
       // this.setToolBar();


        this.nav_drawer = findViewById(R.id.nav_drawer);
        this.customToolBar = findViewById(R.id.Customtoolbar);
        setSupportActionBar(customToolBar);
        final ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("My title");
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.open_navigation_drawer);

        SqlHelperClass ob = new SqlHelperClass(NavigationDrawerAct.this);
        SQLiteDatabase sqldb = (SQLiteDatabase) ob.getWritableDatabase();
        this.addNotes = new AddNotes(this, sqldb);
        this.notesList = new NotesList();

        fragmentManager = getSupportFragmentManager();
        this.fragmentTransaction = fragmentManager.beginTransaction();

        this.nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction ft = NavigationDrawerAct.this.getSupportFragmentManager().beginTransaction();
                switch(menuItem.getItemId())
                {
                    case R.id.notes :
                        NavigationDrawerAct.this.nav_drawer.closeDrawer(Gravity.START);
                        ft.replace(R.id.frame_layout, notesList);
                        ft.commit();

                        break;

                    case R.id.note_add :
                        NavigationDrawerAct.this.nav_drawer.closeDrawer(Gravity.START);
                        ft.replace(R.id.frame_layout, addNotes);
                        ft.commit();
                        break;

                    case R.id.about_us :
                        if(aboutUsFragment == null)
                        {
                            aboutUsFragment = new AboutUsFragment();
                        }
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, aboutUsFragment);
                        fragmentTransaction.commit();
                        actionbar.setTitle("About US");
                        NavigationDrawerAct.this.nav_drawer.closeDrawer(Gravity.START, true);
                        Toast.makeText(NavigationDrawerAct.this, "About US" , Toast.LENGTH_LONG).show();
                        break;
                    case R.id.contact_us :
                        if(contactUsFragment == null)
                        {
                            contactUsFragment = new ContactUsFragment();
                        }
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, contactUsFragment);
                        fragmentTransaction.commit();
                        actionbar.setTitle("Contact Us");
                        NavigationDrawerAct.this.nav_drawer.closeDrawer(Gravity.START, true);
                        Toast.makeText(NavigationDrawerAct.this, "Contact Us" , Toast.LENGTH_LONG).show();
                        break;

                }
                return true;
            }
        });






    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.nav_drawer.openDrawer(Gravity.START);
                Toast.makeText(NavigationDrawerAct.this, "onOptionsMethod" , Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
