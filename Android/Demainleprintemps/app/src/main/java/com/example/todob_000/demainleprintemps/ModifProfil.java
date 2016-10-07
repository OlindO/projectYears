package com.example.todob_000.demainleprintemps;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ModifProfil extends Activity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_profil);

        //IMPORTATION LA FCT typeface qui permert de changer la police
        Typeface lobster = Typeface.createFromAsset(getAssets(),"fonts/lobster.otf");
        //IMPORTATION DES COMPOSANT DU XML

        EditText nvxEmail = (EditText) findViewById(R.id.MailAModifier);
        EditText mdpAmodif = (EditText) findViewById(R.id.AncienMdp);
        EditText mdpModifier = (EditText) findViewById(R.id.NoveauMpd);
        EditText CmdpModifier = (EditText) findViewById(R.id.ConfirmationMdp);
        EditText nvxLogin = (EditText) findViewById(R.id.NvxLogin);
        EditText Nationalité = (EditText) findViewById(R.id.nation);
        // Attribution de la police desirer au composant
        nvxEmail.setTypeface(lobster);
        mdpAmodif.setTypeface(lobster);
        mdpModifier.setTypeface(lobster);
       CmdpModifier.setTypeface(lobster);
       nvxLogin.setTypeface(lobster);

    }
   /* public void modifTelephone(View view )
    {
        Intent intent = getIntent();

        String teleRecup = intent.getStringExtra("editTelephone");


//Recuperation des données saisi par l'utilisateur//
        EditText loginAmodif = (EditText) findViewById(R.id.editTelephone);
        EditText nvxLogin = (EditText) findViewById(R.id.editTelephone);


        //String telnvx = teleRecup.getText().toString();
        //String telmodif = telnvx;

        //.........................................//

// Recuperation de l'url du php permettant deffectuer la requete//
        HttpPost httppost = new HttpPost("http://ecole-theatrale.fr/Tests_Appli-mobile/Modificationprofil_Login.php");
//Envoie les variables dans le fichier php//
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("telmodif", telmodif));
        nameValuePair.add(new BasicNameValuePair("telnvx", telnvx));


        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            Log.e("info",telmodif);
        } catch (UnsupportedEncodingException e) {
            Log.e("info",telmodif);
            e.printStackTrace();
        }
        //Execution de la requete//

        HttpClient httpClient = new DefaultHttpClient();
        try {
            Log.e("info",telmodif);
            HttpResponse response = httpClient.execute(httppost);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            Log.e("info",telmodif);
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(), "Felicitation , votre téléphone a été modifié avec succés", Toast.LENGTH_LONG).show();
    }
*/
//Methode pour modifier la nationalité

    /*
  public void ModifNation(View view )
  {
      Intent intent = getIntent();

      String nationRecup = intent.getStringExtra("nation");


//Recuperation des données saisi par l'utilisateur//
      EditText loginAmodif = (EditText) findViewById(R.id.nation);
      EditText nvxLogin = (EditText) findViewById(R.id.nation);


      //String nationnvx = nationRecup.getText().toString();
      String nationAmodif = nationRecup;

      //.........................................//

// Recuperation de l'url du php permettant deffectuer la requete//
      HttpPost httppost = new HttpPost("http://ecole-theatrale.fr/Tests_Appli-mobile/Modificationprofil_Login.php");
//Envoie les variables dans le fichier php//
      List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
      nameValuePair.add(new BasicNameValuePair("nationAmodif", nationAmodif));
      nameValuePair.add(new BasicNameValuePair("nvxLogins", nationnvx));


      try {
          httppost.setEntity(new UrlEncodedFormEntity(nameValuePair));
          Log.e("info",nationAmodif);
      } catch (UnsupportedEncodingException e) {
          Log.e("info",nationAmodif);
          e.printStackTrace();
      }
      //Execution de la requete//

      HttpClient httpClient = new DefaultHttpClient();
      try {
          Log.e("info",nationAmodif);
          HttpResponse response = httpClient.execute(httppost);
      } catch (ClientProtocolException e) {
          // TODO Auto-generated catch block
          Log.e("info",nationAmodif);
          e.printStackTrace();
      } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }

      Toast.makeText(getApplicationContext(), "Felicitation , votre Nationnalité a été modifié avec succés", Toast.LENGTH_LONG).show();
  }
  */

  public  void ModifLogin(View view){


      Intent intent = getIntent();

      String loginRecupere = intent.getStringExtra("Login");


//Recuperation des données saisi par l'utilisateur//
            EditText loginAmodif = (EditText) findViewById(R.id.champLoginConn);
            EditText nvxLogin = (EditText) findViewById(R.id.NvxLogin);


            String nvxLogins = nvxLogin.getText().toString();
            String loginAMODIF = loginRecupere;

 //.........................................//

// Recuperation de l'url du php permettant deffectuer la requete//
            HttpPost httppost = new HttpPost("http://ecole-theatrale.fr/Tests_Appli-mobile/Modificationprofil_Login.php");
//Envoie les variables dans le fichier php//
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
            nameValuePair.add(new BasicNameValuePair("loginAMODIF", loginAMODIF));
            nameValuePair.add(new BasicNameValuePair("nvxLogins", nvxLogins));


            try {
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePair));
                Log.e("info",loginAMODIF);
            } catch (UnsupportedEncodingException e) {
                Log.e("info",loginAMODIF);
                e.printStackTrace();
            }
            //Execution de la requete//

            HttpClient httpClient = new DefaultHttpClient();
            try {
              Log.e("info",loginAMODIF);
                HttpResponse response = httpClient.execute(httppost);
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                Log.e("info",loginAMODIF);
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

      Toast.makeText(getApplicationContext(), "Felicitation , votre Login a été modifié avec succés", Toast.LENGTH_LONG).show();
        }
    public String CurrentDate()
    {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time =&gt; "+c.getTime());
        // On va formatter la date pour qu'elle soit sous forme d'année, mois, jours
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();
        return formattedDate;
    }
    //Methode pour modifier l'email de l'utilisateur//

    public  void ModifEmail(View view) {


        Intent intent = getIntent();
        String loginRecupere = intent.getStringExtra("Login");


        EditText loginAmodif = (EditText) findViewById(R.id.champLoginConn);
        EditText nvxEmail = (EditText) findViewById(R.id.MailAModifier);

        //
        String nvxEmails = nvxEmail.getText().toString();
        String loginAMODIF = loginRecupere;


// Recuperation de l'url du php permettant deffectuer la requete//
        HttpPost httppost2 = new HttpPost("http://ecole-theatrale.fr/Tests_Appli-mobile/Modificationprofil_Email.php");
//Envoie les variables dans le fichier php//
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("loginAMODIF", loginAMODIF));
        nameValuePair.add(new BasicNameValuePair("nvxEmails", nvxEmails));


        try {
            httppost2.setEntity(new UrlEncodedFormEntity(nameValuePair));
       Log.e("email",nvxEmails);
            Log.e("info",loginAMODIF);

        } catch (UnsupportedEncodingException e) {
            Log.e("erreur",nvxEmails);
            e.printStackTrace();
        }
        //Execution de la requete//

        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpResponse response = httpClient.execute(httppost2);

            Log.e("ok",nvxEmails);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            Log.e("email",nvxEmails);
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Felicitation , votre Email a été modifié avec succés", Toast.LENGTH_LONG).show();
    }
//Methode pour modif le mdp de l'utilisateur
    public  void ModifMdp(View view) {


        // recuperation du login depuis la classe Login
        Intent intent = getIntent();
        String loginRecupere = intent.getStringExtra("Login");


        // recuperation des variable
        EditText loginAmodif = (EditText) findViewById(R.id.champLoginConn);
        EditText mdpAmodif = (EditText) findViewById(R.id.AncienMdp);
        EditText mdpModifier = (EditText) findViewById(R.id.NoveauMpd);
        EditText CmdpModifier = (EditText) findViewById(R.id.ConfirmationMdp);
        //conversion des variables
        String mdpsAmodif = mdpAmodif.getText().toString();
        String loginAMODIF = loginRecupere;
        String mdpsModifier = mdpModifier.getText().toString();
        String CmdpsModifier = CmdpModifier.getText().toString();


// Recuperation de l'url du php permettant deffectuer la requete//
        HttpPost httppost = new HttpPost("http://ecole-theatrale.fr/Tests_Appli-mobile/Modificationprofil_MDP.php");
//Envoie les variables dans le fichier php//
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
        nameValuePair.add(new BasicNameValuePair("loginAMODIF", loginAMODIF));
        nameValuePair.add(new BasicNameValuePair("mdpsAmodif", mdpsAmodif));
        nameValuePair.add(new BasicNameValuePair("mdpsModifier", mdpsModifier));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePair));

        Log.e(loginAMODIF,mdpsAmodif);
            Log.e(mdpsModifier,"$mdpsModifier");
            Log.e(mdpsAmodif,"$mdpsAmodif");
            Log.e(loginAMODIF,"$loginAmodif ");
            Log.e(CmdpsModifier,"confirmation mdp");

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }


        //Execution de la requete//
 // on testsi champs mdp et confirm mdp sont egaux<//
if(mdpsModifier.equals(CmdpsModifier)) {
    HttpClient httpClient = new DefaultHttpClient();

    try {
        HttpResponse response = httpClient.execute(httppost);
        HttpEntity entite = response.getEntity();

        String reponse = EntityUtils.toString(entite);

        entite.consumeContent();

        int reponses = Integer.parseInt(reponse);
        Log.e("resreq", reponse);
        Log.isLoggable("re", reponses);

       //Si le resultat de la requete est egale à 0 , le mdp rentré est faux et on affiche une boite de dialogue//

        if (reponses == 0) {

            AlertDialog.Builder boite2;
            boite2 = new AlertDialog.Builder(this);
            boite2.setTitle("Erreur");
            boite2.setIcon(R.drawable.logo);
            boite2.setMessage("Le mot de passe rentré est eronné veuillez verifier votre saisie");
            boite2.show();
        }


    } catch (ClientProtocolException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    Toast.makeText(getApplicationContext(), "Felicitation , votre Email a été modifié avec succés", Toast.LENGTH_LONG).show();
}//sinon on affiche "la confirm du mdp est eronné"//
else {

    AlertDialog.Builder boite2;
    boite2 = new AlertDialog.Builder(this);
    boite2.setTitle("Erreur");
    boite2.setIcon(R.drawable.logo);
    boite2.setMessage("La confirmation du mdp est eronné");
    boite2.show();

}
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modif_profil, menu);
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
