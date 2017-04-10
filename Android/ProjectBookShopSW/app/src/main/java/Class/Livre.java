package Class;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/*import com.example.yanis.myapplication.MainActivity;
import com.example.yanis.myapplication.R;*/

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

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

    private ArrayList<Livre> livreArrayList;
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


    /*public ArrayList<String> afficherTitre() {
        ArrayList<Livre> listLivre = this.liste();
        // list vide
        ArrayList<String> colle = new ArrayList<>();

        for (int i = 0; i < listLivre.size(); i++) {
            colle.add(listLivre.get(i).getTitre());
        }
        return colle;
    }*/
    /* Pour retourner un tablneau de titre de livre
       @returns array
     */
   /* public String[] tabAfficherTitre()
    {
        ArrayList<String> listNew = this.afficherTitre();
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
// METHOD permettant de retourner tout nos livre
    public ArrayList<Livre> listAllOfBook()
    {
        livreArrayList = new ArrayList<>();

        ArrayList<Livre> listLivre = livreArrayList;
        return listLivre;

    }

//
    public ArrayList<Livre> RechercherLivreByPrice(ArrayList<Livre> livres, double prixMin, double prixMax)
    {
        ArrayList<Livre> newL= new ArrayList<>();
        for(int i =0; i<livres.size();i++)
        {
            Livre livre = livres.get(i);
            if(livre.getPrix() >= prixMin && livre.getPrix() <= prixMax)
            {
                newL.add(livre);
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
          On va en gros alimenter nos enfant Date dans expandableListView
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

    public ArrayList<Livre> listerLivreByDateLivre(ArrayList<Livre> livres, String date)
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
            if(livres.get(i).getDate().equalsIgnoreCase(date))
            {
               /* Toast.makeText(, "", Toast.LENGTH_SHORT).show();*/
                rslt.add(livres.get(i));
            }

        }
        return rslt;
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
}