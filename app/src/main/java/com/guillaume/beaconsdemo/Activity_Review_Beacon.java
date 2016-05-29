package com.guillaume.beaconsdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;


public class Activity_Review_Beacon extends FragmentActivity {

    private PagerAdapter mPagerAdapter;
    private ViewPager pager;
    private TextView txtActivityBeacons;
    private LinearLayout container;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_beacon);

        // Création de la liste de Fragments que fera défiler le PagerAdapter
        List fragments = new Vector();

        // Ajout des Fragments dans la liste
        fragments.add(android.support.v4.app.Fragment.instantiate(this, fragment_icy_marshmallow.class.getName()));
        fragments.add(android.support.v4.app.Fragment.instantiate(this, fragment_mint_cocktail.class.getName()));
        fragments.add(android.support.v4.app.Fragment.instantiate(this, fragment_blueberry_pie.class.getName()));
        // Création de l'adapter qui s'occupera de l'affichage de la liste de
        // Fragments
        this.mPagerAdapter = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);

        pager = (ViewPager) super.findViewById(R.id.pager_beacon_review);
        txtActivityBeacons = (TextView)findViewById(R.id.txt_acti_beacons_review);
        txtActivityBeacons.setText(R.string.title_beacon_IcyMarshmallow);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0: txtActivityBeacons.setText(R.string.title_beacon_IcyMarshmallow); break;
                    case 1: txtActivityBeacons.setText(R.string.title_beacon_MintCocktail); break;
                    case 2: txtActivityBeacons.setText(R.string.title_beacon_BlueberryPi); break;
                    default: txtActivityBeacons.setText("Title"); break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Affectation de l'adapter au ViewPager
        pager.setAdapter(this.mPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_settings:
                return true;
        }

        return false;
    }

}
