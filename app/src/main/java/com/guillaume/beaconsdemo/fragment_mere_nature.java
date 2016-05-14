package com.guillaume.beaconsdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guillaume.beaconsdemo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class fragment_mere_nature extends Fragment {

    private TextView txtMereNature;
    private TextView txtMereNature2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View mainView = inflater.inflate(R.layout.fragment_mere_nature, container, false);

        txtMereNature = (TextView)mainView.findViewById(R.id.txt_mere_nature);
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("HH'h' mm");
        SimpleDateFormat fcompareHour = new SimpleDateFormat("HH");
        SimpleDateFormat fcompareMinute = new SimpleDateFormat("mm");
        int heures = Integer.parseInt(fcompareHour.format(d));
        int minutes = Integer.parseInt(fcompareMinute.format(d));

        String heureActuelle = f.format(d);

        String MereNatureMessage = "Message de Mère Nature: \r\n \r\n" +
                "il est " + heureActuelle + "\r\n";

        if((heures >= 8 && heures < 9 && minutes <= 30) || (heures >= 13 && heures <= 14 && minutes <= 30) || (heures < 8)){
            MereNatureMessage += "Vous êtes en avance! \r\n";
        }
        else if((heures >= 8 && heures < 9 && minutes >= 30) || (heures >= 13 && heures < 14 && minutes >= 30)){
            MereNatureMessage += "Vous êtes en retard! \r\n";
        }
        else if((heures >= 9 && heures < 10) || (heures >= 11 && heures < 12) || (heures >= 14 && heures < 15) || (heures >= 16 && heures < 17)){
            MereNatureMessage += "Que faites-vous dans les couloirs à cette heure? \r\n";
        }
        else if((heures >= 10 && heures < 11) || (heures >= 15 && heures < 16)){
            MereNatureMessage += "C\'est l'heure de la pause? \r\n Un verre d\'eau ne serait pas de refus!";
        }
        else if(heures >= 12 && heures <= 13){
            MereNatureMessage += "Bon appetit! \r\n";
        }
        else if(heures >= 17){
            MereNatureMessage += "Vous partez? Et bien bonne soirée! \r\n";
        }

        txtMereNature.setText(MereNatureMessage);

        txtMereNature2 = (TextView)mainView.findViewById(R.id.txt_mere_nature2);
        if(txtMereNature2 != null) {
            txtMereNature2.setMovementMethod(LinkMovementMethod.getInstance());
        }

        //Envoie d'un mail de confirmation si accès à internet:


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
