package com.example.todob_000.demainleprintemps;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Accueil extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        // Permet au programme de gerer l'application pour pas qu'elle ne crash
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        // Modification des polices en Lobster en utilisant le Typeface
        // ATTENTION à l'emplacement de la police, peut faire crasher l'application
        Typeface lobster = Typeface.createFromAsset(getAssets(),"fonts/lobster.otf");
        TextView textViewCustom1 = (TextView) findViewById(R.id.titreAccueil);
        Button buttonCustom1 = (Button) findViewById(R.id.boutonConnAccueil);

        Button buttonCustom2 = (Button) findViewById(R.id.boutonInscriptionAccueil);
        textViewCustom1.setTypeface(lobster);
        buttonCustom1.setTypeface(lobster);

        buttonCustom2.setTypeface(lobster);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accueil, menu);
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

    public void lancerConnexion(View view) {
        Intent intentLogin = new Intent(this, Login.class);
        startActivity(intentLogin);
    }
    public void LancerInscription(View view) {
        Intent intentLogin = new Intent(this, SelectionInscription.class);
        startActivity(intentLogin);
    }





}
