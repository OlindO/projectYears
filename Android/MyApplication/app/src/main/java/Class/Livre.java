package Class;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.yanis.myapplication.MainActivity;
import com.example.yanis.myapplication.R;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public Livre(int id, String theme, String url, String auteur, String titre, String desc) {
        this.id = id;

        this.theme = theme;
        this.url = url;

        this.auteur = auteur;
        this.titre = titre;
        this.desc = desc;

    }

    public int getId() {
        return id;
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

    public ArrayList<Livre> liste(){
        ArrayList<Livre> listTheme = new ArrayList<>();
        String description = context.getResources().getString(R.string.libelle_desc);
        listTheme.add(new Livre(1, Theme.affichTheme().get(0), "https://ec56229aec51f1baff1d-185c3068e22352c56024573e929788ff.ssl.cf1.rackcdn.com/attachments/large/2/6/7/004371267.jpg", "Du côté de chez Swann", "Marcel Proust", description));
        listTheme.add(new Livre(2, Theme.affichTheme().get(0), "https://ec56229aec51f1baff1d-185c3068e22352c56024573e929788ff.ssl.cf1.rackcdn.com/attachments/large/3/4/9/004479349.jpg", "Le garçon", "Marcus Malte",description));
        listTheme.add(new Livre(3, Theme.affichTheme().get(1), "https://ec56229aec51f1baff1d-185c3068e22352c56024573e929788ff.ssl.cf1.rackcdn.com/attachments/large/8/1/6/004479816.jpg", "Bienvenue dans le pire des mondes", "Polony, Natacha", description));
        listTheme.add(new Livre(4, Theme.affichTheme().get(1), "https://ec56229aec51f1baff1d-185c3068e22352c56024573e929788ff.ssl.cf1.rackcdn.com/attachments/large/7/9/4/004428794.jpg", "La Fille du train", "Paula Hawkins",description));
        listTheme.add(new Livre(5, Theme.affichTheme().get(1), "https://ec56229aec51f1baff1d-185c3068e22352c56024573e929788ff.ssl.cf1.rackcdn.com/attachments/large/0/7/6/004514076.jpg", "Le Crime du Comte Neville", "Amélie Nothomb"));
        listTheme.add(new Livre(6, Theme.affichTheme().get(2), "https://ec56229aec51f1baff1d-185c3068e22352c56024573e929788ff.ssl.cf1.rackcdn.com/attachments/large/8/6/5/004396865.jpg", "Dernières nouvelles des trous noirs", "Stephen William Hawking",description));
        listTheme.add(new Livre(7, Theme.affichTheme().get(2), "https://ec56229aec51f1baff1d-185c3068e22352c56024573e929788ff.ssl.cf1.rackcdn.com/attachments/large/2/6/7/004197267.jpg", "Écoutez nos défaites", "Laurent Gaudé",description));
        listTheme.add(new Livre(8, Theme.affichTheme().get(2), "https://ec56229aec51f1baff1d-185c3068e22352c56024573e929788ff.ssl.cf1.rackcdn.com/attachments/large/6/4/8/004143648.jpg", "J'ai longtemps eu peur de la nuit", "Yasmine Ghata",description));
        listTheme.add(new Livre(9, Theme.affichTheme().get(2), "https://ec56229aec51f1baff1d-185c3068e22352c56024573e929788ff.ssl.cf1.rackcdn.com/attachments/large/4/8/9/004429489.jpg", "Comme une respiration", "Jean Teulé",description));
        listTheme.add(new Livre(10, Theme.affichTheme().get(2), "https://ec56229aec51f1baff1d-185c3068e22352c56024573e929788ff.ssl.cf1.rackcdn.com/attachments/large/4/5/0/004396450.jpg", "UX Design et ergonomie des interfaces - 6e éd.", "Jules Leclerc, Jean-François Nogier",description));
        return listTheme;
    }


    public ArrayList<String> afficherTitre() {
        ArrayList<Livre> listLivre = this.liste();
        // list vide
        ArrayList<String> colle = new ArrayList<>();

        for (int i = 0; i < listLivre.size(); i++) {
            colle.add(listLivre.get(i).getTitre());
        }
        return colle;
    }
    /* Pour retourner un tablneau de titre de livre
       @returns array
     */
    public String[] tabAfficherTitre()
    {
        ArrayList<String> listNew = this.afficherTitre();
        String [] array= new String[listNew.size()];

        for(int i=0;i<listNew.size();i++)
        {
            array[i] = listNew.get(i);
        }
        return array;
    }
   /*
   @params String* ->   pour récuperer le theme selectionner et le comparer
   @return ->           Une liste de type String
   */
    public ArrayList <String> RechercherTitreBy(String theme)
    {

        ArrayList<Livre> onLivre = this.liste();
        ArrayList<String> newTitreByTheme = new ArrayList<>();

        for(int i=0; i<onLivre.size(); i++)
        {
            if(theme.equalsIgnoreCase(onLivre.get(i).getTheme()))
            {
               /* Toast.makeText(, "", Toast.LENGTH_SHORT).show();*/
                newTitreByTheme.add(onLivre.get(i).getTitre());
            }

        }
        return newTitreByTheme;
    }

    public ArrayList <Livre> RechercherTitreByString(String theme)
    {

        ArrayList<Livre> onLivre = this.liste();
        ArrayList<Livre> newTitreByTheme = new ArrayList<>();

        // On va parcourir notre liste ()
        for(int i=0; i<onLivre.size(); i++)
        {
            // On va comparer si le theme passer en paramettre correspond
            // Au theme du livre en question , dans se cas on va ajouter
            // A notre nouvelle liste les livres en fonction de leur theme

            if(theme.equalsIgnoreCase(onLivre.get(i).getTheme()))
            {
               /* Toast.makeText(, "", Toast.LENGTH_SHORT).show();*/
                newTitreByTheme.add(onLivre.get(i));
            }

        }
        return newTitreByTheme;
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