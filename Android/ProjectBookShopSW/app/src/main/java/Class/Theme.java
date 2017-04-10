package Class;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yanis on 16/02/2017.
 */

public class Theme {
    public String theme;
    public Livre liv;

    public Theme(String theme)
    {
        this.theme = theme;
    }

    public Theme()
    {

    }



    // Methode nous permettant de récuprer notre liste de theme sous forme de tableau.
   /* public String [] listTheme()
    {
        ArrayList<String> listNew;
        String [] array= new String[listNew.size()];

        for(int i=0;i<listNew.size();i++)
        {
            array[i] = listNew.get(i);
        }
        return array;
    }*/

    // PROPERTIES
    public String getTheme() {
        return theme;
    }


    //METHODE

    /*
        @ArrayList Livres
        Cette methode va nous permettre de lister tout les themes, en évitant les
        doublons d'où l'utilisation d'un HashMap.
        On va en gros alimenter nos enfant Theme dans expandableListView
     */
    public ArrayList<String> arrayListTheme(ArrayList<Livre> livres)
    {
        ArrayList<String> result = new ArrayList<>();
        HashMap<String, String> controleur = new HashMap<>();

        for(int i=0; i<livres.size(); i++)
        {
            Livre livre = livres.get(i);
            String tab [] = livre.getTheme().split(",");
            if(tab.length >= 1)
            {
                for(int j=0; j<tab.length; j++)
                {
                    if(!controleur.containsKey(tab[j]))
                    {
                        controleur.put(tab[j].trim(), tab[j].trim());
                        result.add(tab[j]);
                    }
                }
            }
        }
        return result;
    }


    public ArrayList<Livre> listerLivreByTheme(ArrayList<Livre> livres, String theme)
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
            if(livres.get(i).getTheme().contains(theme.trim()))
            {
               /* Toast.makeText(, "", Toast.LENGTH_SHORT).show();*/
                rslt.add(livres.get(i));
            }

        }
        return rslt;
    }




    public ArrayList<Livre>rechercherThemeByString(ArrayList<Livre> livres, String theme)
    {
        ArrayList<Livre> onLivre = new ArrayList<>();
        ArrayList<String> newTitreByTheme = new ArrayList<>();

        for(int i=0; i<livres.size(); i++)
        {
            if(theme.equalsIgnoreCase(livres.get(i).getTheme()))
            {
               /* Toast.makeText(, "", Toast.LENGTH_SHORT).show();*/
                onLivre.add(livres.get(i));
            }

        }
        return onLivre ;
    }
}

