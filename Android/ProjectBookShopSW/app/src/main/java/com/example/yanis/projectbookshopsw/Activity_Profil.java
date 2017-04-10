package com.example.yanis.projectbookshopsw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import Class.Personne;
import fragments.CompteFragment;
import fragments.FactureFragment;
import fragments.FavorisFragment;
import fragments.PanierFragment;

import com.google.gson.Gson;

import org.w3c.dom.Comment;
import org.w3c.dom.Text;

import java.security.DigestInputStream;
import java.util.List;
import java.util.Vector;

public class Activity_Profil extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    private NavigationView navigationView;
    private MainPagerAdapter pagerAdapter;
    private MainViewPager pager;
    private List<Fragment> fragments;
    private int positionPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__profil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DigestInputStream dig;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Recherche Avancé ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(Activity_Profil.this, AdvancedResearch.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_compte);

        // Ajout des fragments
        fragments = new Vector<>();
        fragments.add(Fragment.instantiate(this, CompteFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, FactureFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, FavorisFragment.class.getName()));
        fragments.add(Fragment.instantiate(this, PanierFragment.class.getName()));
        this.pagerAdapter  = new MainPagerAdapter(super.getSupportFragmentManager(), fragments);

        // Initialisation des pages
        pager = (MainViewPager)super.findViewById(R.id.viewpager);
        pager.setAdapter(this.pagerAdapter);
        pager.setPagingEnabled(false);
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                positionPage = position;

                switch (positionPage){
                    case 0: // Mon Compte

                        break;

                    case 1: // Mes factures

                        break;

                    case 2: // Mon panier

                        break;

                    case 3: // Mes favoris

                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity__profil, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_compte) {
            pager.setCurrentItem(0);
        } else if (id == R.id.nav_factures) {
            pager.setCurrentItem(1);

        } else if (id == R.id.nav_panier) {
            pager.setCurrentItem(3);
        } else if (id == R.id.nav_favoris) {
            pager.setCurrentItem(2);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
