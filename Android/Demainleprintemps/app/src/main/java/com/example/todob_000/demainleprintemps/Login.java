package com.example.todob_000.demainleprintemps;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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


public class Login extends Activity {

    /*
    On déclare les saisies récupérées afin de les rendre unique en cas d'intercations avec d'autres applications.
    L'élément entre parenthèses désigne le chemin de la variable.
    */
    public final static String EXTRA_MESSAGE_TEST = "dlp.application.com.appdlp.MESSAGE_TEST";
    public final static String LOGIN_SAISI = "dlp.application.com.appdlp.LOGIN_SAISI";
    public final static String MDP_SAISI = "dlp.application.com.appdlp.MDP_SAISI";
    public final static String RES_REQ = "dlp.application.com.appdlp.RES_REQ";

    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Permet au programme de gerer l'application pour pas qu'elle ne crash
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        pb = (ProgressBar)findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);

        // Modification des polices en Lobst    er en utilisant le Typeface
        Typeface lobster = Typeface.createFromAsset(getAssets(),"fonts/lobster.otf");
        TextView textViewCustom = (TextView) findViewById(R.id.titreConn);
        Button boutonCustom = (Button) findViewById(R.id.boutonConn);
        EditText LoginCon = (EditText) findViewById(R.id.champLoginConn);
        EditText MdpCon = (EditText) findViewById(R.id.champMdpConn);

        textViewCustom.setTypeface(lobster);
        boutonCustom.setTypeface(lobster);
        MdpCon.setTypeface(lobster);
        LoginCon.setTypeface(lobster);

        // todo

        TextView MdpOublié = (TextView) findViewById(R.id.MdpPerdu);

        MdpOublié.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                // Si la postion du Spiner = 3 , on lance une nouvelle activité qui correspond à l'activité"fan"//


                    Intent intent = new Intent(Login.this, MdpPerdu.class);
                    startActivity(intent);

                }
            });

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    /*
    *****       MÉTHODE PRINCIPALE      *****
    Lance une activité si la connexion aboutit.
    La méthode est appelée lorsque l'utilisateur clique sur le bouton Connexion,comme défini dans le fichier XML
    */

    public void testConnexion(View view) throws ExecutionException, InterruptedException {
        Intent intentConnexionReussie = new Intent(this, Menu.class);
        Intent intentLogin = new Intent(this, Login.class);


        // Récupération du contenu des champs login et mdp
        EditText loginConn = (EditText) findViewById(R.id.champLoginConn);
        EditText mdpConn = (EditText) findViewById(R.id.champMdpConn);
        String contenuChampLogin = loginConn.getText().toString();
        String contenuChampMdp = mdpConn.getText().toString();

        // Test sur la saisie
        String resultatRequete, chaineRequete;
        resultatRequete = new requeteConnexion().execute(contenuChampLogin, contenuChampMdp).get();
        chaineRequete = new requeteConnexion().execute(contenuChampLogin, contenuChampMdp).toString();

        // Ajoute les chaînes de caractères à l'intent
        intentConnexionReussie.putExtra(LOGIN_SAISI, contenuChampLogin);
        intentConnexionReussie.putExtra(MDP_SAISI, contenuChampMdp);

        intentLogin.putExtra(LOGIN_SAISI, contenuChampLogin);
        intentLogin.putExtra(MDP_SAISI, contenuChampMdp);


        // Décide s'il faut lancer l'activité
        // Comportement à définir si pas de connexion !!
        int resultatRequeteInt = Integer.parseInt(resultatRequete);
        if(resultatRequeteInt == 1) {
            intentConnexionReussie.putExtra(RES_REQ, "\n" + resultatRequete);
            startActivity(intentConnexionReussie);

        }
        else {

            AlertDialog.Builder boite2;
            boite2 = new AlertDialog.Builder(this);
            boite2.setTitle("Erreur");

            boite2.setIcon(R.drawable.logo);
            boite2.setMessage("Le login ou le Mdp sont eronés");
            boite2.show();
        }

    }

    /** Classe de type AsyncTask
     *  Permet d'effectuer la requête http en arrière plan.
     *  Obligatoire depuis Android 3.0 et supérieur
     */
    private class requeteConnexion extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... params) {
            // On exécute la méthode effectuant la requête, et qui prend en paramètres le login et le mot de passe
            return(requeteHttp(params[0], params[1]));
        }

        // Méthode permettant d'agir durant la requête
        protected void onProgressUpdate(Integer... progress) {
            pb.setProgress(progress[0]);
        }

        // Méthode (non utilisée) permettant de renvoyer un résultat
        protected void onPostExecute(Double result) {
            pb.setVisibility(View.GONE);
        }

        // La requête http en elle-même
        public String requeteHttp(String login, String mdp) {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://www.ecole-theatrale.fr/Tests_Appli-mobile/connexion_Java.php";
            String reponse = null;

            // specify the URL you want to post to
            HttpPost httppost = new HttpPost(url);
            try {
                // create a list to store HTTP variables and their values
                List nameValuePairs = new ArrayList();

                // add an HTTP variable and value pair
                nameValuePairs.add(new BasicNameValuePair("login", login));
                nameValuePairs.add(new BasicNameValuePair("mdp", mdp));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // send the variable and value, in other words post, to the URL
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entite = response.getEntity();

                reponse = EntityUtils.toString(entite);

                entite.consumeContent();
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
            return reponse;
        }
    }






}
