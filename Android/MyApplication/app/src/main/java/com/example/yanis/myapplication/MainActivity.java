package com.example.yanis.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import Class.Livre;
import Class.Theme;
import Class.PersonneDao;
import Class.Personne;
import Class.LivreAdaptateur;
public class MainActivity extends AppCompatActivity /*implements SearchView.OnQueryTextListener*/{

    private Spinner spinner;
    private ListView listV;
    private ImageButton imButton;
    private Livre livre;
    private String itemSpinnerString;

    private LivreAdaptateur adapterSearchView;


    private ArrayAdapter<String> adapterSpinner;
    /*private ArrayAdapter<String> adaptaterListV;*/


    private LivreAdaptateur adaptateurLivre;


    private ArrayList<Personne> arrayPersonne;
    private ArrayList<Livre> resultatLivre;

    private Intent intent;

    private Theme theme;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_liste_des_livres);
        setTitle("Liste des livres ");
        /*afficheList();*/


     /*   Toast.makeText(getApplicationContext(), )*/

        listV =(ListView)findViewById(R.id.list_item);
        livre = new Livre();
        livre.setContext(MainActivity.this);
        /*livre.setDesc(getResources().getString(R.string.libelle_desc));*/

        //Récupération du Spinner déclaré dans le fichier main.xml de res/layout
        spinner = (Spinner) findViewById(R.id.spinner25);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                itemSpinnerString = adapterSpinner.getItem(position);
                resultatLivre = livre.RechercherTitreByString(itemSpinnerString);
                /*ArrayList<String> resultat = livre.RechercherTitreBy(itemSpinnerString.trim());*/
                /*Toast.makeText(MainActivity.this, "Resultat =" + itemSpinnerString, Toast.LENGTH_SHORT).show();*/
                adaptateurLivre= new LivreAdaptateur(
                        MainActivity.this,
                        resultatLivre
                );

                listV.setAdapter(adaptateurLivre);
                listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        intent = new Intent(MainActivity.this, ActivityDetails.class);
                        intent.putExtra("livre", resultatLivre.get(position));
                        startActivity(intent);

                    }
                });
                //Toast.makeText(getApplicationContext(), "Selection = " + adapterSpinner.getItem(position) , Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



		/*
		    Le Spinner a besoin d'un adapter pour sa presentation alors on lui
		    passe le context(this) et
            un fichier de presentation
            'spinner'
		    Avec la liste des elements
		*/

        adapterSpinner = new ArrayAdapter<>(
                MainActivity.this,
                R.layout.spinner, R.id.spinner,
                theme.affichTheme()
        );

        /*
            Enfin on passe l'adapter au Spinner et c'est tout
        */
        spinner.setAdapter(adapterSpinner);

    }

    public void addPersonne()
    {
        PersonneDao personneDao = new PersonneDao(this);
        personneDao.openForWrite();

        Personne pers = new Personne();
        pers.setName("Dugorie");
        pers.setPrenom("David");
        pers.setCivilite("M");
        personneDao.instertPersonne(pers);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*Toast.makeText(this, "ESSAYE", Toast.LENGTH_SHORT).show();*/
        return super.onTouchEvent(event);
    }
}
