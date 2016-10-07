package com.example.todob_000.demainleprintemps;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class SelectionPhoto extends ActionBarActivity {
    //Adresse o� se trouve l'ensemble des images gif (num�rot�es de 1 � 21).
    private final static String SERVER_IM = "http://ecole-theatrale.fr/uploaded/";
    public final static String URL_PHOTO = "dlp.application.com.appdlp.URL_PHOTO";
    private String urlPhoto;
    public final static String NOM_INSCRI_MEMBRES = "dlp.application.com.appdlp.NOM_INSCRI_MEMBRES";
    public final static String PRENOM_INSCRI_MEMBRES = "dlp.application.com.appdlp.PRENOM_INSCRI_MEMBRES";


    // GUI
    private Gallery gallery;
    private ImageView imgView;

    //Data
    private ArrayList<URL> mListImages;
    private Drawable mNoImage;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_photo);
        // Permet au programme de gerer l'application pour pas qu'elle ne crash
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //R�cup�ration d'une image par d�faut � afficher en cas d'erreur ou de liste vide
        mNoImage = this.getResources().getDrawable(R.drawable.logo);

        //Construction des URL pour les images
        mListImages = buildListImages();

        //R�cup�ration du composant affichant l'image en grand
        imgView = (ImageView) findViewById(R.id.imageview);

        //On lui met une image par d�faut (la premiere de la liste ou � d�faut l'image d'erreur)
        if (mListImages.size() <= 0) {
            imgView.setImageDrawable(mNoImage);
        } else {
            setImage(imgView, mListImages.get(0));
        }
        //R�cup�ration du composant affichant la liste des vignettes

        /* GALERIE
        gallery = (Gallery) findViewById(R.id.gallery);
        //On lui donne notre adapter qui s'occup�ra de l'alimenter en vignette
        gallery.setAdapter(new AddImgAdp(this));
        //Espacement entre les vignette
        gallery.setSpacing(10);

        //Lors d'un clic sur une des vignettes, on affiche l'image correspondante en grand
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                setImage(imgView, mListImages.get(position));
            }
        });
        */

    }


    /**
     * Permet de construire la liste des urls pour les images
     *
     * @return
     */
    private ArrayList<URL> buildListImages() {
        HttpClient httpclient = new DefaultHttpClient();
        String url = "http://ecole-theatrale.fr/AppliMobile/photo_etudiant.php";
        String reponse = null;

        // specify the URL you want to post to
        HttpPost httppost = new HttpPost(url);
        try {
            Intent intentSelectionPhoto = getIntent();
            String nom = intentSelectionPhoto.getStringExtra(InscriptionMembres.NOM_INSCRI_MEMBRES);
            String prenom = intentSelectionPhoto.getStringExtra(InscriptionMembres.PRENOM_INSCRI_MEMBRES);

            List nameValuePairs = new ArrayList();
            nameValuePairs.add(new BasicNameValuePair("nomAppli", nom));
            nameValuePairs.add(new BasicNameValuePair("prenomAppli", prenom));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // send the variable and value, in other words post, to the URL
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entite = response.getEntity();

            reponse = EntityUtils.toString(entite);
            urlPhoto = reponse;

            entite.consumeContent();

            /* Affichage test sur nom et prenom
            TextView vue = (TextView) findViewById(R.id.textView3);
            vue.setText(reponse + "ihefhfehhiehfieie" + nom + prenom);
            ********************************/

        } catch (IOException e) {}



        int nbTotalImage = 1;
        ArrayList<URL> listFic = new ArrayList<URL>();
        for (int i = 1; i <= nbTotalImage; i++) {
            try {
                listFic.add(new URL(SERVER_IM + reponse));
                Log.e("reponse", reponse);

            } catch (MalformedURLException e) {
                Log.e("DVP Gallery", "Erreur format URL : " + SERVER_IM + reponse);
                e.printStackTrace();

                Log.e("REPONSE", reponse);
            }
        }

        return listFic;
    }


    /**
     * Notre adapter qui g�re la liste des vignettes
     *
     * @author Micka�l Le Trocquer
     */
    public class AddImgAdp extends BaseAdapter {
        int GalItemBg;
        private Context cont;

        public AddImgAdp(Context c) {
            cont = c;
            TypedArray typArray = obtainStyledAttributes(R.styleable.Gallery1);

            typArray.recycle();
        }

        public int getCount() {
            return mListImages.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ImageView imgView = null;
            //R�cyclage du composant
            if (convertView == null) {
                imgView = new ImageView(cont);
            } else {
                imgView = (ImageView) convertView;
            }
            //On affecte notre image � la vignette
            if (mListImages.size() <= 0) {
                imgView.setImageDrawable(mNoImage);
            } else {
                setImage(imgView, mListImages.get(position));
            }
            //On d�fini la taille de l'image
            imgView.setLayoutParams(new Gallery.LayoutParams(200, 150));
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
            //On fixe un arri�re plan plus sympa
            imgView.setBackgroundResource(GalItemBg);

            return imgView;
        }
    }


    /**
     * M�thode permettant de t�l�charger une image depuis une URL et de l'affecter � un composant de type ImageView
     *
     * @param aView
     * @param aURL
     */
    public void setImage(ImageView aView, URL aURL) {
        try {
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();

            // Bufferisation pour le t�l�chargement
            BufferedInputStream bis = new BufferedInputStream(is, 8192);

            // Cr�ation de l'image depuis le flux des donn�es entrant
            Bitmap bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();


            // Fixe l'image sur le composant ImageView
            aView.setImageBitmap(bm);
            Log.e("DVP Gallery", aURL.toString());


        } catch (IOException e) {
            aView.setImageDrawable(mNoImage);
            Log.e("DVP Gallery", "Erreur téléchargement image URL : " + aURL.toString());
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selection_photo, menu);
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

    public void finaliserInscriptionMembre(View view) {
        Intent intentInscriptionMembres = getIntent();
        String nom = intentInscriptionMembres.getStringExtra(InscriptionMembres.NOM_INSCRI_MEMBRES);
        String prenom = intentInscriptionMembres.getStringExtra(InscriptionMembres.PRENOM_INSCRI_MEMBRES);
        Intent intentFinalisationInscriMembre = new Intent(this, FinalisationInscriptionMembres.class);
        intentFinalisationInscriMembre.putExtra(URL_PHOTO, urlPhoto);
        intentFinalisationInscriMembre.putExtra(NOM_INSCRI_MEMBRES, nom);
        intentFinalisationInscriMembre.putExtra(PRENOM_INSCRI_MEMBRES, prenom);
        startActivity(intentFinalisationInscriMembre);
    }
}