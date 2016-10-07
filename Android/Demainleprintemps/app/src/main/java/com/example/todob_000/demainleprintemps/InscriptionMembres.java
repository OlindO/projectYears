package com.example.todob_000.demainleprintemps;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class InscriptionMembres extends ActionBarActivity {

    public final static String NOM_INSCRI_MEMBRES = "dlp.application.com.appdlp.NOM_INSCRI_MEMBRES";
    public final static String PRENOM_INSCRI_MEMBRES = "dlp.application.com.appdlp.PRENOM_INSCRI_MEMBRES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_membres);

        //TODO IMPORTATION LA FCT typeface qui permert de changer la police

        Intent intentRecupereInscriptionMembres = getIntent();
        String type = intentRecupereInscriptionMembres.getStringExtra(SelectionInscription.TYPE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inscription_membres, menu);
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

    public void identificationMembre(View view) throws ExecutionException, InterruptedException {
        Intent intentSelectionPhoto = new Intent(this, SelectionPhoto.class);

        // Récupération du contenu des champs login et mdp
        EditText nomInscriMembres = (EditText) findViewById(R.id.champNomInscriptionMembres);
        EditText prenomInscriMembres = (EditText) findViewById(R.id.champPrenomInscriptionMembres);
        String nom = nomInscriMembres.getText().toString();
        String prenom = prenomInscriMembres.getText().toString();

        intentSelectionPhoto.putExtra(NOM_INSCRI_MEMBRES, nom);
        intentSelectionPhoto.putExtra(PRENOM_INSCRI_MEMBRES, prenom);

        // Test sur la saisie
        String resultatRequete = new requeteConnexion().execute(nom, prenom).get();

        // Décide s'il faut lancer l'activité
        int resultatRequeteInt = Integer.parseInt(resultatRequete);
        if(resultatRequeteInt == 1) {
            startActivity(intentSelectionPhoto);
        }
        else {
            TextView message = (TextView) findViewById(R.id.erreurIdentifiants);
            message.setText("Vos identifiants n'ont pas été trouvés. Veuillez les vérifier à nouveau.");
        }
    }

    private class requeteConnexion extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            // On exécute la méthode effectuant la requête, et qui prend en paramètres le nom et le prénom
            return(requeteHttp(params[0], params[1]));
        }
        protected void onProgressUpdate(Integer... progress) {}
        protected void onPostExecute(Double result) {}

        public String requeteHttp(String nom, String prenom) {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://www.ecole-theatrale.fr/Tests_Appli-mobile/verifNomPrenomInscriMembres.php";
            String reponse = null;

            HttpPost httppost = new HttpPost(url);
            try {
                List nameValuePairs = new ArrayList();
                nameValuePairs.add(new BasicNameValuePair("nomAppli", nom));
                nameValuePairs.add(new BasicNameValuePair("prenomAppli", prenom));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entite = response.getEntity();

                reponse = EntityUtils.toString(entite);

                entite.consumeContent();
            } catch (IOException e) {
                // Auto-generated catch block
            }
            return reponse;
        }
    }
}
