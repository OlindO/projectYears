package Class;

import android.app.Activity;
import android.content.Context;
import android.graphics.Interpolator;
import android.widget.Toast;

/*import com.example.yanis.myapplication.MainActivity;
import com.example.yanis.myapplication.R;*/

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;

public class Livre implements Serializable{
    private Theme th;
    private int id;
    private String theme;
    private String url;
    private Context context;
    private String titre;
    private String auteur;
    private String desc;
    private int quantite;
    private String isbn;
    private double prix;
    private String date;
    private int val;


    public Livre()
    {}
    public Livre(int id, String theme, String url, String auteur, String titre) {
        this.id = id;

        this.theme = theme;
        this.url = url;

        this.auteur = auteur;
        this.titre = titre;

    }
    public Livre(int id, String theme, String url, String auteur, String titre, String desc, int quantite) {
        this.id = id;

        this.theme = theme;
        this.url = url;

        this.auteur = auteur;
        this.titre = titre;
        this.desc = desc;
        this.quantite = quantite;

    }

    public Livre(int id, String theme, String url, String auteur, String titre, String desc, double prix) {
        this.id = id;

        this.theme = theme;
        this.url = url;

        this.auteur = auteur;
        this.titre = titre;
        this.desc = desc;
        this.prix = prix;
    }


    public Livre (int id,String titre,String auteur,String theme,String description,double prix,String isbn,String  photo, String date)
    {

        this.id = id;
        this.titre = titre;
        this.theme = theme;
        this.auteur = auteur;

        this.url = photo;
        this.isbn = isbn;
        this.prix = prix;
        this.desc = description;
        this.date = date;
    }

    public Livre(int id, String theme, String url, Context context, String titre, String auteur, String desc, int quantite, String isbn, double prix, String date) {
        this.id = id;
        this.theme = theme;
        this.url = url;
        this.context = context;
        this.titre = titre;
        this.auteur = auteur;
        this.desc = desc;
        this.quantite = quantite;
        this.isbn = isbn;
        this.prix = prix;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public double getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

/*
* @liste : represente notre liste de livre récupérer depuis notre JSon
* On va ensuite retourner une liste de titre de Type String */
    public static ArrayList<String> afficherTitre(ArrayList<Livre> liste) {
        ArrayList<Livre> listLivre = liste;
        // list vide
        ArrayList<String> colle = new ArrayList<>();

        for (int i = 0; i < listLivre.size(); i++) {
            colle.add(listLivre.get(i).getTitre());
        }
        return colle;
    }
 /*    Pour retourner un tablneau de titre de livre
       @returns array*/
/*
    public String[] tabAfficherTitre(ArrayList<Livre> s)
    {
        ArrayList<String> listNew = this.afficherTitre(s);
        String [] array= new String[listNew.size()];

        for(int i=0;i<listNew.size();i++)
        {
            array[i] = listNew.get(i);
        }
        return array;
    }
*/

    public String getDate() {
        return date;
    }

//
    public ArrayList<Livre> RechercherLivreByPrice(ArrayList<Livre> livres, double prixMin, double prixMax)
    {
        ArrayList<Livre> newL= new ArrayList<>();
        for(int i =0; i<livres.size();i++)
        {
            /*Livre livre = livres.get(i);*/
            if(livres.get(i).getPrix() >= prixMin && livres.get(i).getPrix() <= prixMax)
            {
                newL.add(livres.get(i));
            }
        }
        return newL;
    }
    //METHODE

    /*
        @ArrayList Livres
        Cette methode va nous permettre de lister tout les prix,
        On va en gros alimenter nos enfant Prix dans expandableListView
     */

    public ArrayList<String> listLivreOfPrice(ArrayList<Livre> livres)
    {
        ArrayList<String> newLPrix = new ArrayList<>();
        for(int i =0; i<livres.size();i++)
        {
            Livre livre = livres.get(i);
            String formatPrix = String.format("%.2f", livre.getPrix());
            newLPrix.add(formatPrix);

        }
        return newLPrix;

    }

       /*
        @ArrayList Livres Notre List de Type Livre
        @return ArrayList
        Cette methode va nous permettre de lister tout les prix,
        On va en gros alimenter nos enfant Prix dans expandableListView
     */

    public ArrayList<Livre> listerLivreByPriceType(ArrayList<Livre> livres, String price)
    {
        ArrayList<Livre> rslt = new ArrayList<>();


        // On va parcourir notre liste ()
        for(int i=0; i<livres.size(); i++)
        {
            // On va comparer si le theme passer en paramettre correspond
            // Au theme du livre en question , dans se cas on va ajouter
            // A notre nouvelle liste les livres en fonction de leur theme

            // On utilise contains pour tester si il y à plusieurs theme
            // pour une section et afficher tout les livres
            if(livres.get(i).getPrix() == (Double.valueOf(price)))
            {
               /* Toast.makeText(, "", Toast.LENGTH_SHORT).show();*/
                rslt.add(livres.get(i));
            }

        }
        return rslt;
    }

    /*
          @ArrayList Livres
          Cette methode va nous permettre de lister tout les date,
          à l'affichage on aura toute les dates présente (10/15/2016 , 20/11/2016..)
          cette methode est fonctionnel mais pas assez optimisé
          On va en gros alimenter nos enfant Date dans expandableListView

          START DEPRECIE
          ↓
    */
    public ArrayList<String> listLivreOfDate(ArrayList<Livre> livres)
    {
        ArrayList<String> newL= new ArrayList<>();
        for(int i =0; i<livres.size();i++)
        {
            Livre livre = livres.get(i);

            newL.add(livre.getDate());
        }
        return newL;
    }
    //  END DEPRECIE


    public ArrayList<String> listLivreOfDateBETWEEN()
    {
        ArrayList<String> newL= new ArrayList<>();
        newL.add("Moins de 3 Moi");

        newL.add("Plus de 3 Moi");

        return newL;
    }


    // Cette methode sera intégrer a notre listView de notre activité AdvancedResearch
    public ArrayList<Livre> listerLivreByDateLivre(ArrayList<Livre> livres, String date)
    {
        ArrayList<Livre> rslt = new ArrayList<>();


        // On va parcourir notre liste ()
        for(int i=0; i<livres.size(); i++)
        {
            // On va comparer si le date passer en paramettre correspond
            // Au theme du livre en question , dans se cas on va ajouter
            // A notre nouvelle liste les livres en fonction de leur date

            // On utilise contains pour tester si il y à plusieurs theme
            // pour une section et afficher tout les livres
            if(livres.get(i).getDate().equalsIgnoreCase(date))
            {
               /* Toast.makeText(, "", Toast.LENGTH_SHORT).show();*/
                rslt.add(livres.get(i));
            }

        }
        return rslt;
    }

    /*
        Lister les livres en fonction de la date courante du system
        @
        @

    * */
    public ArrayList<Livre> listeDateMoreThan3Month(ArrayList<Livre> livres)
    {
        String date = getDateTime();

        ArrayList<Livre> rslt = new ArrayList<>();
        // On va parcourir notre liste
        for(int i=0; i<livres.size(); i++)
        {
            long diff = daybetween(date, livres.get(i).getDate());

            if(diff > 90)
            {
                rslt.add(livres.get(i));
            }

        }
        return rslt;

    }

    /*
       Lister les livres en fonction de la date courante du system
       @ArrayList <livres> notre collection de livre passer en
       paramètre depuis l'activité
   * */
    public ArrayList<Livre> listDateLessBy3Month(ArrayList<Livre> livres)
     {
         // CURRENT DATE
         String date = getDateTime();

         ArrayList<Livre> rslt = new ArrayList<>();
         // On va parcourir notre liste
        for(int i=0; i<livres.size(); i++)
        {
            long diff = daybetween(date, livres.get(i).getDate());

            if(diff < 90)
            {
                rslt.add(livres.get(i));
            }

        }
        return rslt;

    }

     private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public int getSizeOfArrayList(ArrayList<Livre> livres)
    {
        int totalElement = livres.size();
        Iterator<Livre> itLivre = livres.iterator();

        while(itLivre.hasNext())
        {
            // DO SOMETHING ON THE LIST
        }
        return totalElement;

    }
    /*
        @date1 represente la date courante
    *   @date2 represente la date du livre en question
    *   Cette methode va nous retourner le nombre de jours qu'il y à entre
    *   deux dates paramatré

    * */
    public int daybetween(String date1,String date2)
    {

        SimpleDateFormat sdf = new SimpleDateFormat();
        Date Date1 = null,Date2 = null;
        try{
            Date1 = sdf.parse(date1);
            Date2 = sdf.parse(date2);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        // 86 400 000 milliSeconde per Day
        if (Date2 != null && Date1 !=null) {
            val = (int)(Date2.getTime() - Date1.getTime())/(24*60*60*1000);
        }
        return val;

    }

    public String getTheme() {
        return theme;
    }

    public String getTitre() {
        return titre;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return this.getTitre();
    }
}