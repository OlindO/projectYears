package com.example.todob_000.demainleprintemps;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class Menu extends ActionBarActivity {

    ActionMode.Callback myCallback;
    ActionMode myActionMode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // Permet au programme de gerer l'application pour pas qu'elle ne crash
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Récupère la saisie depuis l'intent
        Intent intentConnexionReussie = getIntent();
        String loginRecupere = intentConnexionReussie.getStringExtra(Login.LOGIN_SAISI);
        String mdpRecupere = intentConnexionReussie.getStringExtra(Login.MDP_SAISI);

        // Creation de la vue du WebView et parametrage de son contenu
        WebView actualites = (WebView) findViewById(R.id.webViewActualites);
        actualites.setWebViewClient(new WebViewClient());   // Sert à afficher la page dans l'application, sinon ouvre une boîte de dialogue
        actualites.loadUrl("http://www.ecole-theatrale.fr/m/page-actualite.php");




        myCallback = new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, android.view.Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, android.view.Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                myActionMode = null;
            }
        };



    }



    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_, menu);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_ModifPhoto:
                //On transmet les variables d'une classea l'autre grace au "intent"//
                //Recuperation des variables
                Intent intent4 = getIntent();
                String loginRecuperes = intent4.getStringExtra(Login.LOGIN_SAISI);

                //.......................//

                // creation d'un nouvelle intent , pour transmettre les variable à la class "ModifProfil"//
                Intent Modifimage = new Intent(Menu.this, modifierImage.class);
                Modifimage.putExtra("Login", loginRecuperes);
                startActivity(Modifimage);

                return true;


            case R.id.menu_ModifProfil:

                //On transmet les variables d'une classea l'autre grace au "intent"//
                //Recuperation des variables
                Intent intent3 = getIntent();
                String loginRecupere = intent3.getStringExtra(Login.LOGIN_SAISI);

                //.......................//

                // creation d'un nouvelle intent , pour transmettre les variable à la class "ModifProfil"//
                Intent ModifProfil = new Intent(Menu.this, ModifProfil.class);
                ModifProfil.putExtra("Login", loginRecupere);
                startActivity(ModifProfil);



                //...............................................................//




        }






        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebView actualites = (WebView) findViewById(R.id.webViewActualites);
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode)
            {
                case KeyEvent.KEYCODE_BACK:
                    if(actualites.canGoBack()){
                        actualites.goBack();
                    }else{
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }


}
