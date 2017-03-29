package com.example.yanis.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import Class.PanierAdaptateur;
import Class.LivreAdaptateur;
import org.w3c.dom.Text;

import java.util.ArrayList;

import Class.Livre;
import Class.Panier;

public class CartActivity extends AppCompatActivity {

    public ImageView but1, but2;
    public TextView txtCount, title;
    private PanierAdaptateur livAd;
    private ListView listView;
    private Livre liv;
    private ArrayList <Livre> livres;
    private ArrayList<Panier> panierArrayList;
    private int count;

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private Panier panier;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle("Votre Panier ");

    /*    sharedPreferences = getSharedPreferences("SHARED_PREFERENCES", getApplicationContext().MODE_PRIVATE);
        gson = new Gson();

        String json = sharedPreferences.getString("Panier", "");
        panier = gson.fromJson(json, Panier.class);*/

     /*   Toast.makeText(getApplicationContext(), "Titre = "+panier.getLivre().getTitre(),
                Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Auteur = "+panier.getLivre().getAuteur(),
                Toast.LENGTH_SHORT).show();*/
        //panierArrayList = ()
        /*Toast.makeText(getApplicationContext(), "Object adding "  ,
                Toast.LENGTH_LONG).show();*/
        /*bundle = getIntent().getExtras();
        if(bundle == null || !bundle.containsKey("livre"))
        {
            finish();
        }*/
        // On est obliger de le caster car on veux être sur que c'est un
        // objet de type livre



       /* Toast.makeText(getApplicationContext(), "SUPER ! : "+  liv.getTitre() , Toast.LENGTH_LONG);*/

/*        title = (TextView)findViewById(R.id.txtTitre);
        liv = (Livre)bundle.getSerializable("livre");
        livres = new ArrayList<>();
        livres.add(liv);


        liv = new Livre();
        ListView list = (ListView)findViewById(R.id.listV);
        TextView title =(TextView) findViewById(R.id.titre);
        TextView txtCount =(TextView) findViewById(R.id.txt);

        ImageView buttonInc= (ImageView) findViewById(R.id.button1);
        ImageView buttonDec= (ImageView) findViewById(R.id.button2);*/

/*        buttonInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count =0;
                count++;
                //txtCount.setText(String.valueOf(count));

            }
        });

        buttonDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                //txtCount.setText(String.valueOf(count));

            }
        });*/
    }
}
