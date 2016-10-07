package com.example.todob_000.demainleprintemps;


import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SelectionInscription extends ActionBarActivity {

    public final static String TYPE = "dlp.application.com.appdlp.TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_inscription);





        //TODO IMPORTATION LA FCT typeface qui permert de changer la police

        Typeface lobster = Typeface.createFromAsset(getAssets(),"fonts/lobster.otf");

        TextView titreInscription = (TextView) findViewById(R.id.titreSelecInscri);
        Button SP= (Button) findViewById(R.id.boutonSelecProf);
        Button SAE= (Button) findViewById(R.id.boutonSelecAncienEleve);
        Button SEA= (Button) findViewById(R.id.boutonSelecEleveActuel);
        Button SC= (Button) findViewById(R.id.boutonSelecCandidat);
        Button SF= (Button) findViewById(R.id.boutonSelecFan);
        titreInscription.setTypeface(lobster);
        SP.setTypeface(lobster);
        SAE.setTypeface(lobster);
        SEA.setTypeface(lobster);
        SC.setTypeface(lobster);
        SF.setTypeface(lobster);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selection_inscription, menu);
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

    /**
     * élèves actuels/anciens -> table etudiants (distinction masteriens/stagiaires ??? champ titre à creuser)
     * professeurs -> table membres (fonction = "prof" ou "ancien-prof")
     * type à affecter à l'utilsateur dans la table utiliseteur
     */

    // TODO CREATION D'UN CHAMP de type "eleveActuel" (booléen ou entier) dans la table etudiants [OK]
    // TODO Modifications a faire afin d'identifier si un eleve est a Minsk (voir s'il faut ajouter un champ) pour le cas ou un membre s'inscrit en tant qu'eleve actuel
    // -> il faut empecher les eleves qui ne sont pas a Minsk de s'inscrire en tant qu'eleve actuel
    // TODO Prevoir un bouton retour a la fin de l'inscription qui ferme les activites posterieures à l'ecran d'accueil

    // TODO modifier les intentInscriptionMembres en Prof/ancien eleve
    // TODO type a envoyer vers FinalisationInscriptionMembres
    public void lancerInscriptionProf(View view) {
        Intent intentInscriptionMembres = new Intent(this, InscriptionMembres.class);
        intentInscriptionMembres.putExtra(TYPE, "prof");
        startActivity(intentInscriptionMembres);
    }

    public void lancerInscriptionAncienEleve(View view) {
        Intent intentInscriptionMembres = new Intent(this, InscriptionMembres.class);
        intentInscriptionMembres.putExtra(TYPE, "ancienEleve");
        startActivity(intentInscriptionMembres);
    }

    public void lancerInscriptionEleveActuel(View view) {
        Intent intentInscriptionMembres = new Intent(this, InscriptionMembres.class);
        intentInscriptionMembres.putExtra(TYPE, "eleveAtuel");
        startActivity(intentInscriptionMembres);
    }

    public void lancerInscriptionCandidat(View view) {
        Intent intentInscriptionCandidat = new Intent(this, Inscription_Fan.class);
        intentInscriptionCandidat.putExtra(TYPE, "candidat");
        startActivity(intentInscriptionCandidat);
    }

    public void lancerInscriptionFan(View view) {
        Intent intentInscriptionFan = new Intent(this, Inscription_Fan.class);
        intentInscriptionFan.putExtra(TYPE, "fan");
        startActivity(intentInscriptionFan);
    }
}
