package Class;

import java.util.ArrayList;

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
    public static ArrayList<String> affichTheme()
    {

        ArrayList<String> listeT1 = new ArrayList<>();
        listeT1.add("Dramatique");
        listeT1.add("Comique");
        listeT1.add("Action");

        return listeT1;
    }


    // Methode nous permettant de récuprer notre liste de theme sous forme de tableau.
    public String [] listTheme()
    {
        ArrayList<String> listNew = this.affichTheme();
        String [] array= new String[listNew.size()];

        for(int i=0;i<listNew.size();i++)
        {
            array[i] = listNew.get(i);
        }
        return array;
    }

    // PROPERTIES
    public String getTheme() {
        return theme;
    }
}
