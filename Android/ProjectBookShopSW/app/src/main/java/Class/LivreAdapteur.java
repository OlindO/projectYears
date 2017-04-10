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

import com.example.yanis.projectbookshopsw.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

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
            view = layoutInflater.inflate(R.layout.item_image,null);
        }



        ImageView imgview = (ImageView)view.findViewById(R.id.imageView);
        final TextView txtTitre = (TextView)view.findViewById(R.id.idTitre);
        final TextView txtAuteur =(TextView)view.findViewById(R.id.idAuteur);


        txtTitre.setText(livres.get(position).getTitre());
        txtAuteur.setText(livres.get(position).getAuteur());

        // OPERATION ALTERNER
        // Pour largeur Si isDetails est vraie on fait 300 dans le cas contraire 200
        // Vice versa pour la hauteur

        int largeur = 200;
        int hauteur = 350;

        Picasso.with(activity).load(livres.get(position).getUrl()).resize(largeur,hauteur).into(imgview);
        return view;
    }
}
