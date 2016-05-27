package com.guillaume.beaconsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

public class MainActivity extends FragmentActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mLeftMenu;
    private PagerAdapter mPagerAdapter;
    private ViewPager pager;

    private BeaconManager beaconManager;
    private Region region;

    private String[] menuItems;

    /*  Temps d'attente entre 2 notifications (évite les spam)
     *  5 sec = 1
     *  1 min = 12
     *  2 min = 24 etc.
     */
    public static final int RELOAD_TIME = 12;

    private BeaconTimer mintCoktail;
    private BeaconTimer icyMarshmallow;
    private BeaconTimer blueberryPie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Récupération du menu:
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mLeftMenu = (ListView)findViewById(R.id.left_menu);
        menuItems = getResources().getStringArray(R.array.menu_items);
        // Set the adapter for the list view
        mLeftMenu.setAdapter(new ArrayAdapter<String>(this,
                R.layout.menu_list_item, menuItems));
        // Set the list's click listener
        mLeftMenu.setOnItemClickListener(new DrawerItemClickListener());

        //Gestion des beacons:
        beaconManager = new BeaconManager(this);
        region = new Region("ranged region",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);

        beaconManager.setForegroundScanPeriod(5000, 0);

        // add this below:
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {

            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {
                    //for(int i=0; i<list.size(); i++) {
                        //Beacon nearestBeacon = list.get(i);
                        Beacon nearestBeacon = list.get(0);
                        String beaconDetected = nearestBeacon.getMajor() + ":" + nearestBeacon.getMinor();
                        int fragNum = -1;

                        double PowerByRssi = (double) nearestBeacon.getMeasuredPower() / (double) nearestBeacon.getRssi();
                        if (Double.toString(PowerByRssi).length() > 5) {
                            PowerByRssi = Double.parseDouble(Double.toString(PowerByRssi).substring(0, 5));
                        }

                        //Choix du fragment:
                        switch (beaconDetected) {
                            //Mint Cocktail - Moyen
                            case "1857:60524":
                                //Toast.makeText(getApplicationContext(), "Vert: Rssi/Power = "+PowerByRssi+ " mètres", Toast.LENGTH_SHORT).show();
                                if (PowerByRssi >= 1.0) {
                                    //fragNum = 0;
                                    if (mintCoktail != null) {
                                        if (mintCoktail.isAvailable()) {
                                            fragNum = 0;
                                        } else {
                                            fragNum = -1;
                                            mintCoktail.setTime(mintCoktail.getTime() - 1);
                                            Log.v("TIMELEFTMINT", "Time left mint:" + mintCoktail.getTime());
                                        }
                                    } else {
                                        fragNum = 0;
                                        mintCoktail = new BeaconTimer(nearestBeacon, RELOAD_TIME);
                                    }
                                } else {
                                    fragNum = -1;
                                }
                                break;
                            //Icy Marshmallow - Tres loin
                            case "5526:19125":
                                //Toast.makeText(getApplicationContext(), "Bleu: Rssi/Power = "+PowerByRssi+ " mètres", Toast.LENGTH_SHORT).show();
                                if (PowerByRssi < 1.0 && PowerByRssi >= 0.5) {
                                    //fragNum = 1;
                                    if (icyMarshmallow != null) {
                                        if (icyMarshmallow.isAvailable()) {
                                            fragNum = 1;
                                        } else {
                                            fragNum = -1;
                                            icyMarshmallow.setTime(icyMarshmallow.getTime() - 1);
                                            Log.v("TIMELEFTICY", "Time left icy:" + icyMarshmallow.getTime());
                                        }
                                    } else {
                                        fragNum = 1;
                                        icyMarshmallow = new BeaconTimer(nearestBeacon, RELOAD_TIME);
                                    }
                                } else {
                                    fragNum = -1;
                                }
                                break;
                            //Blueberry Pie - Tres proche
                            case "17828:47111":
                                //Toast.makeText(getApplicationContext(), "Violet: Rssi/Power = "+PowerByRssi+ " mètres", Toast.LENGTH_SHORT).show();
                                if (PowerByRssi >= 1.42) { //Entre 1.42 et 1.45 minimum
                                    //fragNum = 2;
                                    if (blueberryPie != null) {
                                        if (blueberryPie.isAvailable()) {
                                            fragNum = 2;
                                        } else {
                                            fragNum = -1;
                                            blueberryPie.setTime(blueberryPie.getTime() - 1);
                                            Log.v("TIMELEFTBLUEBERRY", "Time left blueberry:" + blueberryPie.getTime());
                                        }
                                    } else {
                                        fragNum = 2;
                                        blueberryPie = new BeaconTimer(nearestBeacon, RELOAD_TIME);
                                    }
                                } else {
                                    fragNum = -1;
                                }
                                break;
                            default:
                                fragNum = -1;
                                break;
                        }

                        // TODO: update the UI here
                        //préparation de données dans un bundle, puis lancement d'une nouvelle Activity qui prendra en paramètres le beacon détecté, afin de créer ensuite un contenu personnalisé:
                        if (fragNum >= 0) {
                            Intent intent = new Intent(getApplicationContext(), Activity_Beacon.class);
                            intent.putExtra("fragNum", fragNum);
                            intent.putExtra("powerByRssi", PowerByRssi);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(intent);
                            //break;    //sortie de boucle
                        }
                        /*else{
                            continue;
                        }
                    }*/
                }
            }
        });

        // Création de la liste de Fragments que fera défiler le PagerAdapter
        List fragments = new Vector();

        // Ajout des Fragments dans la liste
        fragments.add(android.support.v4.app.Fragment.instantiate(this, fragment_accueil.class.getName()));
        fragments.add(android.support.v4.app.Fragment.instantiate(this, fragment_page_decouverte.class.getName()));
        fragments.add(android.support.v4.app.Fragment.instantiate(this, fragment_page_app_description.class.getName()));
        // Création de l'adapter qui s'occupera de l'affichage de la liste de
        // Fragments
        this.mPagerAdapter = new MyPagerAdapter(super.getSupportFragmentManager(), fragments);

        pager = (ViewPager) super.findViewById(R.id.pager);
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

    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    protected void onPause(){
        beaconManager.stopRanging(region);
        super.onPause();
    }

    //Gestion du menu:
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Highlight the selected item, update the title, and close the drawer
        pager.setCurrentItem(position, true);
        mDrawerLayout.closeDrawer(mLeftMenu);
    }

    @Override
    public void setTitle(CharSequence title) {
        getActionBar().setTitle(title);
    }


}
