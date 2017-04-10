package com.example.yanis.projectbookshopsw;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import Class.Livre;
import Class.Theme;
import Class.LivreAdapteur;
import Class.MenuDeroulantAdapter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class AdvancedResearch extends AppCompatActivity {

    private LivreAdapteur livreAdapteur;
    private MenuDeroulantAdapter listAdapter;
    private ExpandableListView expListView;

    private HashMap<String, ArrayList<String>> listDataChild;
    private Theme theme;

    private ListView listV;

    private ArrayList<String> filsHeaderTheme;
    private ArrayList<String> filsHeaderPrix;
    private ArrayList<String> filsHeaderDateSortie;
    private ArrayList<String> listHeaderParent;

    private Livre livre;
    private ArrayList<Livre> collLivre;
    private ArrayList<Livre> collLivreSearchResult;
    private ArrayList<Livre> collLivreSearchResultPrice;
    private ArrayList<Livre> collLivreLivreByDateLivre;

    private ProgressBar progressBar;
    private TraiJson traiJson;

    private String itemChilSELECTED;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_research);

        setTitle("Recherche Avancé");

        livre = new Livre();
        theme = new Theme();
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        listV = (ListView)findViewById(R.id.listV);


        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listHeaderParent.get(groupPosition),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                /*Toast.makeText(getApplicationContext(),
                        listHeaderParent.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();*/

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                itemChilSELECTED = listDataChild.get(
                        listHeaderParent.get(groupPosition)).get(
                        childPosition);
                switch(listHeaderParent.get(groupPosition))
                {
                    case "Prix":
                        getSupportActionBar().setSubtitle
                                (listHeaderParent.get(groupPosition)
                                + " : " + itemChilSELECTED + " €");
                        break;
                    case "Thème":
                        getSupportActionBar().setSubtitle
                                (listHeaderParent.get(groupPosition)
                                + " : " + itemChilSELECTED);
                        break;
                    case "Date de sortie":
                        getSupportActionBar().setSubtitle
                                (listHeaderParent.get(groupPosition)
                                + " : " + itemChilSELECTED );
                        break;
                }
                if(listHeaderParent.get(groupPosition)
                        .equalsIgnoreCase(itemChilSELECTED))
                {

                }

                for(int i =0; i<listHeaderParent.size();i++)
                {
                    expListView.collapseGroup(i);
                }

                /*Toast.makeText(getApplicationContext(), "Item Selected = "
                + itemChilSELECTED
                        , Toast.LENGTH_SHORT).show();*/
            /*    Toast.makeText(
                        getApplicationContext(),
                        listHeaderParent.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listHeaderParent.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
             */

            switch(groupPosition)
            {
                case 0:
                    collLivreSearchResult = theme.
                            listerLivreByTheme(collLivre,itemChilSELECTED);
                    livreAdapteur = new LivreAdapteur(AdvancedResearch.this,
                            collLivreSearchResult);


                    break;
                case 1:
                    collLivreSearchResultPrice = livre.
                            listerLivreByPriceType(collLivre, itemChilSELECTED);
                    livreAdapteur = new LivreAdapteur(AdvancedResearch.this,
                            collLivreSearchResultPrice);
                    break;
                case 2:
                    collLivreLivreByDateLivre = livre.
                            listerLivreByDateLivre(collLivre, itemChilSELECTED);
                    livreAdapteur = new LivreAdapteur(AdvancedResearch.this,
                            collLivreLivreByDateLivre);
                    break;

            }
            listV.setAdapter(livreAdapteur);
                return false;
            }
        });
        traiJson = new TraiJson();
        traiJson.execute(getResources().getString(R.string.urlListerMaure));
    }

    /*
	 * Preparing the list data
	*/
    private void prepareListData() {
        listHeaderParent = new ArrayList<String>();
        listDataChild = new HashMap<String, ArrayList<String>>();

        // Adding child data
        listHeaderParent.add("Thème");
        listHeaderParent.add("Prix");
        listHeaderParent.add("Date de sortie");

       // Header, Child data
        listDataChild.put(listHeaderParent.get(0), filsHeaderTheme);
        listDataChild.put(listHeaderParent.get(1), filsHeaderPrix);
        listDataChild.put(listHeaderParent.get(2), filsHeaderDateSortie);
    }
    private void parseJsonFile(String jsonFile) throws Exception
    {
        JSONObject jsonObject = new JSONObject(jsonFile);
    }


    public class TraiJson extends AsyncTask<String, Integer, ArrayList<Livre>>
    {
        String result  ="";

        HttpURLConnection urlConnection = null;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected ArrayList<Livre> doInBackground(String... params) {
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }


            // collLivre est notre liste d'objet Livre retourner depuis notre
            //  JSon
            collLivre = new ArrayList<>();
            try {

                JSONArray jsonArray = new JSONArray(stringBuilder.toString());

                for(int i=0; i<jsonArray.length();i++)
                {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    int id = jsonObject1.getInt("id");
                    String titre = jsonObject1.getString("titre");
                    String nom_auteur= jsonObject1.getString("nom_auteur");
                    String libelle_theme = jsonObject1.getString("libelle_theme");
                    String description= jsonObject1.getString("description");
                    double  prix = jsonObject1.getDouble("prix");
                    String isbn = jsonObject1.getString("isbn");
                    String photo= jsonObject1.getString("photo");
                    String date= jsonObject1.getString("date");
                    /*
                       On ajoute a notre list de type Livre nos valeur récuperer depuis
                       Le JSon (JSON est un format texte complétement indépendant de tout
                       Langage sa structure en arborescence et sa syntaxe lui permet
                       de rester très léger et efficace
                    */
                    collLivre.add(new Livre(id,titre,nom_auteur,libelle_theme,
                            description,prix,isbn, photo,date));
                }

              /*      JSONArray jsonArray3= jsonObject.getJSONArray("AUTHOR");
                    for(int j=0; j<jsonArray2.length(); i++)
                    {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String firstName = jsonObject1.getString("FIRSTNAME");
                        String lastName = jsonObject1.getString("LASTNAME");
                        arrayList.add(new Livre(firstName, lastName));
                    }*/

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return collLivre;
        }

        @Override
        protected void onPostExecute(ArrayList<Livre> s) {
            super.onPostExecute(s);

            filsHeaderTheme = theme.arrayListTheme(s);
            filsHeaderPrix = livre.listLivreOfPrice(s);
            filsHeaderDateSortie = livre.listLivreOfDate(s);


            //listDataChild.put(theme.arrayListTheme(s);
            //HeaderPrix = livre.RechercherLivreByPrice(s)
          /*  listDataChild.put(listDataHeader.get(0), theme.arrayListTheme(s));
            listAdapter = new MenuDeroulantAdapter(AdvancedResearch.this, listDataChild);
            expListView.setAdapter(listAdapter);*/
            //listView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            prepareListData();
            listAdapter = new MenuDeroulantAdapter(AdvancedResearch.this,listHeaderParent,
                    listDataChild);
            expListView.setAdapter(listAdapter);
        }
    }

}



