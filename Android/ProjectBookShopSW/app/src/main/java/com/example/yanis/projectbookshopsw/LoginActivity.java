package com.example.yanis.projectbookshopsw;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Class.Personne;

public class LoginActivity extends AppCompatActivity {

    // String
    private String userEmail;
    private String userPassword;
    // EditText
    private EditText email;
    private EditText password;
    // Button
    private Button signIn;
    private Button register;

    //TraitementAsyncTask
    private TraitementLog traitementLog;
    private HashMap<String, String> postDataParams;

    private ArrayList<Personne> arrayPersDao;
    private ProgressDialog progressDialog;

    //--
    private Intent intent;
    private Personne personne;

    // SharedPreferences
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;
    // Url
    private String urlWS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        email = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPass);

        register = (Button)findViewById(R.id.btnRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(LoginActivity.this, Activity_Register.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        signIn = (Button)findViewById(R.id.btnSingIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail= email.getText().toString().trim();
                userPassword= password.getText().toString().trim();
                if (userEmail.length() > 0 && userPassword.length() > 0)
                {
                    login();
                }
                else {
                    email.setError("Veuillez entrer une adresse mail valide");
                    password.setError("Veuillez entrer un mot de passe");
                }

            }
        });

    }
    private class TraitementLog extends AsyncTask<Void, Void, String> {

        String codeRetour = "";
        URL url = null;
        HttpURLConnection httpURLConnection;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(LoginActivity.this);
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

        // C'est lorsque les données on était envoyer au serveur on ensuite les affichées
        // @result va contenir notre valeur de retours depuis notre php ...
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

           // Log.e("tag", result);
            progressDialog.dismiss();

            // Si dans ntore valeurs de retours le mot erreur exist alors on affiche l'erreur dans un TOAST
            if(result.contains("Erreur"))
            {
                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
            }
            else
            {
                // Puisque que la reponse est récupérer sous forme de format JSon on a pas besoin de le convertir

                editor.putString("infoUserConnected", result);
                editor.commit();

                intent = new Intent(LoginActivity.this, Activity_Profil.class);
                startActivity(intent);

            }
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

    private void login()
    {
        postDataParams = new HashMap<>();
        postDataParams.put("email",userEmail);
        postDataParams.put("password", userPassword);

        urlWS ="http://"+getResources().getString(R.string.urlConnectionMaison) +"/bookshop/json/personne.php?action=connexion";
        //urlWS ="http://"+getResources().getString(R.string.urlConnection) +"/bookshop/json/personne.php?action=connexion";
        traitementLog = new TraitementLog();
        traitementLog.execute();
        //Toast.makeText(getApplicationContext(), "Login envoyer", Toast.LENGTH_SHORT).show();
    }

}
