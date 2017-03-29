package Class;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by yanis on 28/03/2017.
 */

public class LivreAdapteur extends BaseAdapter {

    // Notre liste de type Livre, pour pouvoir l'instantier
    private ArrayList<Livre> livres;

    private Activity activity;
    private static LayoutInflater layoutInflater= null;
    private ImageView imageViewpanier ;


    private int count;
    private boolean isDetails = false;

    // REGION = SHARED
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;
    // Pour récupérer toute notre liste d'objet pour éviter l'écrasement
    private Panier panier;
    // ENDREGION

    // COLLECTION




    public LivreAdapteur(Activity activity, ArrayList<Livre> livres)
    {
        this.activity = activity;
        this.livres = livres;
        this.layoutInflater = (LayoutInflater)this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public LivreAdapteur(Activity activity, ArrayList<Livre> livres, boolean isDetails )
    {
        this.activity = activity;
        this.livres = livres;
        this.isDetails = isDetails;
        this.layoutInflater = (LayoutInflater)this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return livres.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // on travail sur la view pour pouvoir alimenter nos widget
        View view = convertView;

        if(view == null)
        {
            if(isDetails)
            {
                //view = layoutInflater.inflate(R.layout.item_image_detail,null);
            }
            else{
                // Au cas ou on a pas dde vue on va crée notre vue pour ensuite assigner
                // nos id a nos champ
                //view = layoutInflater.inflate(R.layout.item_image,null);
            }


        }

        /*ImageView imgview = (ImageView)view.findViewById(R.id.imageView);
        final TextView txtTitre = (TextView)view.findViewById(R.id.idTitre);
        final TextView txtAuteur =(TextView)view.findViewById(R.id.idAuteur);*/

        if(isDetails)
        {

            //imageViewpanier = (ImageView)view.findViewById(R.id.buy);
            imageViewpanier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity, "Ajouté  " , Toast.LENGTH_LONG).show();
                    // @livre contient sur qu'elle objet de la liste nous sommes
                    Livre livre = livres.get(position);
                    // Notre methode qui va ajouter à l'activité Panier
                    // notre objet
                    //ajouterLivreInPanier(livre);

                }
            });
        }

        //txtTitre.setText(livres.get(position).getTitre());
        //txtAuteur.setText(livres.get(position).getAuteur());

        // OPERATION ALTERNER
        // Pour largeur Si isDetails est vraie on fait 300 dans le cas contraire 200
        // Vice versa pour la hauteur

        int largeur = isDetails ? 300 : 200;
        int hauteur = isDetails ? 450 : 350;

        //Picasso.with(activity).load(livres.get(position).getUrl()).resize(largeur,hauteur).into(imgview);
        return view;
    }
}
