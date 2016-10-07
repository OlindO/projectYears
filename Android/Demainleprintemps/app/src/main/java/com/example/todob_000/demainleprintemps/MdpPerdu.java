package com.example.todob_000.demainleprintemps;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MdpPerdu extends Activity {
    Button Valid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdp_perdu);


        Valid = (Button)findViewById(R.id.ValiderMdpPerdu);


        //TODO IMPORTATION LA FCT typeface qui permert de changer la police

        Typeface lobster = Typeface.createFromAsset(getAssets(),"fonts/lobster.otf");


        EditText motSecret = (EditText) findViewById(R.id.MotSecret);
        EditText mdpPerdu = (EditText) findViewById(R.id.MdpPerdu);
        EditText CmdpPerdu = (EditText) findViewById(R.id.ConfirmationMdpPerdu);
        TextView MDPPERDUS = (TextView) findViewById(R.id.MDPPERDU);



        motSecret.setTypeface(lobster);
        mdpPerdu.setTypeface(lobster);
        CmdpPerdu.setTypeface(lobster);
        MDPPERDUS.setTypeface(lobster);
    }


    public  void ModifMdpPerdu (View view) {

        EditText motSecret = (EditText) findViewById(R.id.MotSecret);
        EditText mdpPerdu = (EditText) findViewById(R.id.MdpPerdu);
        EditText CmdpPerdu = (EditText) findViewById(R.id.ConfirmationMdpPerdu);
        //
        String motSecrets = motSecret.getText().toString();
        String mdpPerdus = mdpPerdu.getText().toString();
        String CmdpPerdus = CmdpPerdu.getText().toString();

// Recuperation de l'url du php permettant deffectuer la requete//
        HttpPost httppost3 = new HttpPost("http://www.ecole-theatrale.fr/Tests_Appli-mobile/mdp.php");
//Envoie les variables dans le fichier php//
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);

        nameValuePair.add(new BasicNameValuePair("motSecrets" ,motSecrets));
        nameValuePair.add(new BasicNameValuePair("mdpPerdus",mdpPerdus));

        try {
          httppost3.setEntity(new UrlEncodedFormEntity(nameValuePair));
          Log.i("Motsecret",motSecrets);
          Log.i("MdpPerdus",mdpPerdus);
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        //Execution de la requete//

if(motSecrets.equals("")){

    AlertDialog.Builder boite2;
    boite2 = new AlertDialog.Builder(this);
    boite2.setTitle("Erreur");
    boite2.setIcon(R.drawable.logo);
    boite2.setMessage("Attention le champs est vide !!");
    boite2.show();

}



 else if(mdpPerdus.equals(CmdpPerdus)) {
    HttpClient httpClient = new DefaultHttpClient();



            try {

                HttpResponse response = httpClient.execute(httppost3);
                HttpEntity entite = response.getEntity();
                String reponse = EntityUtils.toString(entite);
                Log.i("eduardlasalope",reponse);
                entite.consumeContent();
                int reponseInt = Integer.parseInt(reponse);



            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block

                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), "Felicitation , votre mot de passe a été modifié avec succés", Toast.LENGTH_LONG).show();
        }

    else{


                AlertDialog.Builder boite2;
                boite2 = new AlertDialog.Builder(this);
                boite2.setTitle("Erreur");
                boite2.setIcon(R.drawable.logo);
                boite2.setMessage("La confirmation du mdp est eronnée");
                boite2.show();


        }    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mdp_perdu, menu);
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





