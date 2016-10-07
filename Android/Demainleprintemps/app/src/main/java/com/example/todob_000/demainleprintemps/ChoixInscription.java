package com.example.todob_000.demainleprintemps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;


public class ChoixInscription extends ActionBarActivity {
    private Spinner mySpinner;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_inscription);

        // Recupere la valeur du bouton valider qui va permettre le lancement d'une nouvelle activité//
        final Button loginButton = (Button) findViewById(R.id.choix);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //On recuperer la postion des differents elements du Spinner//
                Spinner mySpinner =( Spinner ) findViewById ( R . id .identification );
                int spin= mySpinner . getSelectedItemPosition ();

                // Si la postion du Spiner = 3 , on lance une nouvelle activité qui correspond à l'activité"fan"//
                if(spin==3){

                    Intent intent = new Intent(ChoixInscription.this, Inscription_Fan.class);
                    startActivity(intent);

                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choix_inscription, menu);
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
