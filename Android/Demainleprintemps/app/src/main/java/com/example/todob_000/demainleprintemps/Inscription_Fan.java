package com.example.todob_000.demainleprintemps;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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


public class Inscription_Fan extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription__fan);

        Typeface lobster = Typeface.createFromAsset(getAssets(), "fonts/lobster.otf");
        EditText loginSaisi = (EditText) findViewById(R.id.loginInscriMembres);
        EditText mdpSaisi1 = (EditText) findViewById(R.id.mdp1InscriMembres);
        EditText mdpSaisi2 = (EditText) findViewById(R.id.mdp2InscriMembres);
        EditText emailSaisi = (EditText) findViewById(R.id.emailInscriMembres);
        EditText telSaisi = (EditText) findViewById(R.id.telephone);
        EditText motSecretSaisi = (EditText) findViewById(R.id.motSecret);
        EditText nomSaisi = (EditText) findViewById(R.id.nomInscriFan);
        EditText prenomSaisi = (EditText) findViewById(R.id.prenomInscriFan);

// Lobster
        loginSaisi.setTypeface(lobster);
        mdpSaisi1.setTypeface(lobster);
        mdpSaisi2.setTypeface(lobster);
        emailSaisi.setTypeface(lobster);
        telSaisi.setTypeface(lobster);
        telSaisi.setTypeface(lobster);
        motSecretSaisi.setTypeface(lobster);
        nomSaisi.setTypeface(lobster);
        prenomSaisi.setTypeface(lobster);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finalisation_inscription_membres, menu);
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

    public void terminerInscriMembre(View view) throws ExecutionException, InterruptedException {
        // Recuperation des nom, prenom et url de la photo contenus dans l'intent
        Intent intentIncriptionFan =  getIntent();
        String type = intentIncriptionFan.getStringExtra(SelectionInscription.TYPE);

        // TODO Modifier l'email d'expediteur
        // TODO Variable type a ajouter a la requete, pour professeur/ancien etudiant/etudiant actuel
        Intent inscriptionTerminee = new Intent(this, inscriptionFanTerminer.class);

        // Declaration des champs afin de pouvoir recuperer les saisies
        EditText loginSaisi = (EditText) findViewById(R.id.loginInscriMembres);
        EditText mdpSaisi1 = (EditText) findViewById(R.id.mdp1InscriMembres);
        EditText mdpSaisi2 = (EditText) findViewById(R.id.mdp2InscriMembres);
        EditText emailSaisi = (EditText) findViewById(R.id.emailInscriMembres);
        EditText telSaisi = (EditText) findViewById(R.id.telephone);
        EditText motSecretSaisi = (EditText) findViewById(R.id.motSecret);
        EditText nomSaisi = (EditText) findViewById(R.id.nomInscriFan);
        EditText prenomSaisi = (EditText) findViewById(R.id.prenomInscriFan);
        String login = loginSaisi.getText().toString();
        String mdp1 = mdpSaisi1.getText().toString();
        String mdp2 = mdpSaisi2.getText().toString();
        String email = emailSaisi.getText().toString();
        String tel = telSaisi.getText().toString();
        String motSecret = motSecretSaisi.getText().toString();
        String nom = nomSaisi.getText().toString();
        String prenom = prenomSaisi.getText().toString();


        // Declaration des TextView d'erreur afin de pouvoir les editer
        TextView erreurLogin = (TextView) findViewById(R.id.erreurLogin);
        TextView erreurMdp = (TextView) findViewById(R.id.erreurMdp);
        TextView erreurEmail = (TextView) findViewById(R.id.erreurMailInvalide);

        // Vidage des champs d'erreur, au cas ou le bouton est presse plus d'une fois
        erreurLogin.setText("");
        erreurMdp.setText("");
        erreurEmail.setText("");

        // Requete http et transtypage du resultat
        String verifLogin = new requeteLoginExistant().execute(login).get();
        int verifLoginInt = Integer.parseInt(verifLogin);

        // Intialisation des conditions permettant de lancer l'activite suivante
        boolean loginOk = true;
        boolean mdpOk = true;
        boolean emailOk = true;
        boolean nomOk = true;
        boolean prenomOk = true;
        //boolean isEntier = true;

        // Verification de la disponibilite du login (verifLoginInt == 0 et champ non vide)
        if (login.isEmpty()) {
            loginOk = false;
            erreurLogin.setText("Veuillez un login valide.");
        }
        else if (login.length() < 4) {
            loginOk = false;
            erreurLogin.setText("Le login doit comprendre au moins 4 caractères.");

        }
        else if (verifLoginInt == 1) {
            loginOk = false;
            erreurLogin.setText("Ce login est déjà utilisé.");
        }

// Verification des deux mots de passe (sont identiques et non vides)
        if(mdp1.length() < 6 || mdp2.length() < 6) {
            mdpOk = false;
            erreurMdp.setText("Le mot de passe doit comprendre au moins 6 caractères.");
        }

        if(mdp1.length() < 6 || mdp2.length() < 6) {
            mdpOk = true;
        }

        else if (!mdp1.equals(mdp2) || mdp1.equals("") || mdp2.equals("")) {
            mdpOk = false;
            erreurMdp.setText("Les mots de passe ne correspondent pas.");
        }
        // Verification de la validite de l'email (contient '@' et n'est pas vide)
        if (!email.contains("@") && !email.contains(".") && email.length() < 10) {
            emailOk = false;
            erreurEmail.setText("Veuillez saisir un email valide.");
        }
        if (nom.isEmpty()) {
            nomOk = false;
        }
        if (prenom.isEmpty()) {
            prenomOk = false;
        }

        //Log.i("verification", Boolean.toString(loginOk) + " " + Boolean.toString(mdpOk) + " " +  Boolean.toString(emailOk));
        // Execution de la requete d'insertion dans la bdd et lancement de l'activite si tous les champs sont valides (sauf le numero, qui peut rester vide)
        // TODO Mails a modifier dans le php
        if(loginOk && mdpOk && emailOk && nomOk && prenomOk) {
            new insertionBddEnvoiMail().execute(nom, prenom, login, mdp1, email, tel, motSecret, type).get();
            startActivity(inscriptionTerminee);
        }

    }


    private class requeteLoginExistant extends AsyncTask<String, Integer, String> {

        @Override
        // effectuer une opération en cours d'exécution de code de temps va dans ce procédé. Lorsque la méthode onClick est exécuté sur clic de bouton,
        // il appelle la méthode qui accepte paramètres exécuter et appelle automatiquement la méthode doInBackground avec les paramètres passés.
        protected String doInBackground(String... params) {
            // On exécute la méthode effectuant la requête, et qui prend en paramètre le login
            return(requeteHttp(params[0]));
        }
        protected void onProgressUpdate(Integer... progress) {}
        protected void onPostExecute(Double result) {}

        public String requeteHttp(String login) {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://www.ecole-theatrale.fr/Tests_Appli-mobile/verifLogin.php";
            String reponse = null;

            HttpPost httppost = new HttpPost(url);
            try {
                List nameValuePairs = new ArrayList();
                nameValuePairs.add(new BasicNameValuePair("loginAppli", login));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entite = response.getEntity();

                reponse = EntityUtils.toString(entite);

                entite.consumeContent();
                //Log.i("Reponse requete", reponse);
            } catch (IOException e) {
                // Auto-generated catch block
            }
            return reponse;
        }
    }

    private class insertionBddEnvoiMail extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            // On exécute la méthode effectuant la requête, et qui prend en paramètre le login
            return(requeteHttp(params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7]));
        }
        protected void onProgressUpdate(Integer... progress) {}
        protected void onPostExecute(Double result) {}

        public String requeteHttp(String nom, String prenom, String login, String mdp, String email, String tel, String motSecret, String type) {
            HttpClient httpclient = new DefaultHttpClient();
            String url = "http://www.ecole-theatrale.fr/Tests_Appli-mobile/terminerInscriFan.php";
            String reponse = null;

            HttpPost httppost = new HttpPost(url);
            try {
                List nameValuePairs = new ArrayList();
                nameValuePairs.add(new BasicNameValuePair("nomAppli", nom));
                nameValuePairs.add(new BasicNameValuePair("prenomAppli", prenom));
                nameValuePairs.add(new BasicNameValuePair("loginAppli", login));
                nameValuePairs.add(new BasicNameValuePair("mdpAppli", mdp));
                nameValuePairs.add(new BasicNameValuePair("emailAppli", email));
                nameValuePairs.add(new BasicNameValuePair("telAppli", tel));
                nameValuePairs.add(new BasicNameValuePair("motSecretAppli", motSecret));
                nameValuePairs.add(new BasicNameValuePair("typeAppli", type));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                Log.i("TestInfo", nom + " " + prenom + " " + login + " " + mdp + " " + email +  " " + tel +  " " + motSecret + " " + type);
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entite = response.getEntity();

                reponse = EntityUtils.toString(entite);
                Log.i("TestReponseHttp", reponse);
                entite.consumeContent();
            } catch (IOException e) {
                // Auto-generated catch block
            }
            return reponse;
        }
    }
}
//                     AsyncTask comporte quatre étapes:

//doInBackground :  effectuer une opération en cours d'exécution de code de temps va dans ce procédé. Lorsque la méthode onClick est exécuté sur clic de bouton,
// il appelle la méthode qui accepte le/le paramètres exécuter et appelle automatiquement la méthode doInBackground avec les paramètres passés.
//  onPostExecute :  Cette méthode est appelée après la méthode doInBackground termine le traitement. Résultat doInBackground est passé à cette méthode.
//  OnPreExecute :  Cette méthode est appelée avant la méthode doInBackground est appelé.
//  onProgressUpdate : Cette méthode est appelée en appelant publishProgress tout moment à partir doInBackground appeler cette méthode.