package com.guillaume.beaconsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guillaume.beaconsdemo.R;

public class Activity_Beacon extends FragmentActivity {

    private TextView txtActivityBeacons;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon);

        Intent intent = getIntent();
        int fragNum = intent.getIntExtra("fragNum", -1);
        //Double powerByRssi = intent.getDoubleExtra("powerByRssi", 0);

        //DEBUG:
        //Log.v("POWER/RSSI", ""+powerByRssi);
        //Toast.makeText(getApplicationContext(), "Power/Rssi="+powerByRssi, Toast.LENGTH_SHORT).show();

        txtActivityBeacons = (TextView)findViewById(R.id.txt_acti_beacons);
        container = (LinearLayout)findViewById(R.id.beaconView);

        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            switch(fragNum){
                case 0:
                    txtActivityBeacons.setText("Message de Mère Nature");
                    container.setBackgroundColor(getResources().getColor(R.color.colorB1));
                    fragment_mere_nature frag_mere_nature = new fragment_mere_nature();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, frag_mere_nature).commit();
                    break;

                case 1:
                    txtActivityBeacons.setText("Bienvenue au Jardin");
                    container.setBackgroundColor(getResources().getColor(R.color.colorB2));
                    fragment_entree frag_ent = new fragment_entree();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, frag_ent).commit();
                    break;

                case 2:
                    txtActivityBeacons.setText("Votre passage est enregistré!");
                    container.setBackgroundColor(getResources().getColor(R.color.colorB3));
                    fragment_pointage frag_point = new fragment_pointage();
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, frag_point).commit();
                    break;

                default:
                    //Log.d("BEACON", "No beacon detected");
                    break;
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_beacon, menu);
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
}
