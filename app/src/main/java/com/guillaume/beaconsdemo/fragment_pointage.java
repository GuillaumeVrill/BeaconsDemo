package com.guillaume.beaconsdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.telephony.gsm.SmsManager;

import com.guillaume.beaconsdemo.R;

public class fragment_pointage extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View mainView = inflater.inflate(R.layout.fragment_pointage, container, false);

        //Envoie de SMS de confirmation de présence: (désactivé pour éviter une nuisance chez l'utilisateur):
        //SmsManager.getDefault().sendTextMessage("0668103109", null, "Vous avez correctement pointé.", null, null);

        return mainView;
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
