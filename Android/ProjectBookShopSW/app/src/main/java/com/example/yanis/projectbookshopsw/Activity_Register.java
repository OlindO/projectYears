package com.example.yanis.projectbookshopsw;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Activity_Register extends AppCompatActivity {

    private RadioButton radiobutton_monsieur, radiobutton_madame, radiobutton_mademoiselle;
    private EditText edittext_nom, edittext_prenom, edittext_email, edittext_password;
    private String civilite, nom, prenom, email, password;
    private Button button_valider;
    private boolean formIsOk = true;

    // Widget
    private EditText titre, description, url_image;
    private Button button_enregistrer;
    private ProgressDialog progressDialog;
    //--
    private String titre_livre, description_livre, url_image_livre;
    private HashMap<String, String> postDataParams;
    private final String securiry = "okp5f46fds6qsfe815vsdlij9";
    private Intent intent;
    private String urlWS;
    private int idAction;

    private TraitementInscription traitRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // On récupere tout nos champs initaliser depuis notre methode
        initialiser();

        /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
*/

        radiobutton_monsieur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                civilite = buttonView.getText().toString();
            }
        });

        radiobutton_madame.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                civilite = buttonView.getText().toString();
            }
        });

        radiobutton_mademoiselle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                civilite = buttonView.getText().toString();
            }
        });


        button_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(civilite==null){
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.libelle_civilite_obligatoire), Toast.LENGTH_SHORT).show();
                }
                else{
                    nom = edittext_nom.getText().toString();
                    prenom = edittext_prenom.getText().toString();
                    email = edittext_email.getText().toString();
                    password = edittext_password.getText().toString();


                    if(nom.trim().length() == 0) {
                        edittext_nom.setError(getResources().getString(R.string.libelle_nom_obligatoire));
                        formIsOk = false;
                    }
                    if(prenom.trim().length() == 0) {
                        edittext_prenom.setError(getResources().getString(R.string.libelle_prenom_obligatoire));
                        formIsOk = false;
                    }
                    if(email.trim().length() == 0){
                        edittext_email.setError(getResources().getString(R.string.libelle_email_obligatoire));
                        formIsOk = false;
                    }
                    if(password.trim().length() == 0){
                        edittext_password.setError(getResources().getString(R.string.libelle_password_obligatoire));
                        formIsOk = false;
                    }


                    // Ici on lance notre methode d'ajout d'inscription
                    if(formIsOk) registerP();
                }
            }
        });
    }// -------------------------------------------FIN OnCreate-------------------------------------------


    private class TraitementInscription extends AsyncTask<Void, Void, String>{

        String codeRetour = "";
        URL url = null;
        HttpURLConnection httpURLConnection;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Activity_Register.this);
            progressDialog.setMessage("Traitement en cours, Veuillez patienter...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            try{
                url = new URL(urlWS);
                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                //Flush nous permet d'envoyer toute les information sans rien laisser
                writer.flush();

                writer.close();
                os.close();

                // OutInput de la reponse envoyer au serveur
                int responseCode = httpURLConnection.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK){
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    while ((line=br.readLine()) != null){
                        codeRetour+=line;
                    }
                }
            }
            catch (Exception e)
            {

            }

            httpURLConnection.disconnect();

            return codeRetour;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(!result.isEmpty()){
                Toast.makeText(Activity_Register.this, codeRetour, Toast.LENGTH_LONG).show();
            }

            progressDialog.dismiss();
        }
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder resultat = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if(first){
                first = false;
            }
            else {
                resultat.append("&");
            }
            resultat.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            resultat.append("=");
            resultat.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return resultat.toString();
    }

    private void registerP(){
        postDataParams = new HashMap<>();
        postDataParams.put("civilite", civilite);
        postDataParams.put("email", email);
        postDataParams.put("nom", nom);
        postDataParams.put("prenom", prenom);
        postDataParams.put("password", password);

        //urlWS ="http://"+getResources().getString(R.string.urlConnection) +"/bookshop/json/personne.php?action=inscription";
        urlWS ="http://"+getResources().getString(R.string.urlConnectionMaison) +
                "/bookshop/json/personne.php?action=inscription";

        // Voici notre premier parametre de notre asynctask
        traitRegister = new TraitementInscription();
        traitRegister.execute();
    }

    private void initialiser(){
        radiobutton_monsieur = (RadioButton)findViewById(R.id.radiobutton_monsieur);
        radiobutton_madame = (RadioButton)findViewById(R.id.radiobutton_madame);
        radiobutton_mademoiselle = (RadioButton)findViewById(R.id.radiobutton_mademoiselle);
        edittext_nom = (EditText) findViewById(R.id.edittext_nom);
        edittext_prenom = (EditText) findViewById(R.id.edittext_prenom);
        edittext_email = (EditText) findViewById(R.id.edittext_email);
        edittext_password = (EditText) findViewById(R.id.edittext_password);

        button_valider = (Button)findViewById(R.id.button_valider);
    }
}