package Class;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yanis.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yanis on 27/02/2017.
 */

public class PanierAdaptateur extends BaseAdapter{



    private Panier paniers;
    private Activity activity;
    private static LayoutInflater layoutInflater= null;
    private boolean isDetails = false;


    public PanierAdaptateur(Activity activity, ArrayList<Panier> livres)
    {
        this.activity = activity;
        this.paniers = paniers;
        this.layoutInflater = (LayoutInflater)this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return paniers.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // on travail sur la view pour pouvoir alimenter nos widget
        View view = convertView;

        if(view == null)
        {
            if(isDetails)
            {
                view = layoutInflater.inflate(R.layout.item_cart,null);
            }
            else{
                // Au cas ou on a pas dde vue on va crée notre vue pour ensuite assigner
                // nos id a nos champ
                view = layoutInflater.inflate(R.layout.item_cart,null);
            }


        }

        ImageView imgview = (ImageView)view.findViewById(R.id.imageView);
        TextView txtTitre = (TextView)view.findViewById(R.id.idTitre);
        TextView txtAuteur = (TextView)view.findViewById(R.id.auteur);

        txtTitre.setText(paniers.get(position).getTitre());
        txtAuteur.setText(paniers.get(position).getAuteur());

        Picasso.with(activity).load(paniers.get(position).getUrl()).resize(300,500).into(imgview);
        return view;
    }

    private class ViewHolder
    {
        private TextView txtNom;
        private TextView txtDescription;


    }
}
