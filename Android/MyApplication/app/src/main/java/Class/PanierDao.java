package Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static Class.Constant.COL_ID_PANIER;
import static Class.Constant.COL_TITRE;
import static Class.Constant.COL_QUANTITE;
import static Class.Constant.COL_ID;

/**
 * Created by yanis on 28/02/2017.
 */

public class PanierDao {
    private SQLiteDatabase bdd;
    private MaBaseOpenHelper helper;
    private String NOM_BDD = "PanierBase";
    private static final int VERSION = 1;

    public PanierDao(SQLiteDatabase bdd)
    {
        this.bdd = bdd;
    }
    public PanierDao(Context context)
    {
        helper = new MaBaseOpenHelper(context, NOM_BDD, null, VERSION ) ;

    }

    public void openForWrite()
    {
        bdd = helper.getWritableDatabase();
    }
    public void openForRead()
    {
        bdd = helper.getReadableDatabase();
    }
    public void close()
    {
        bdd.close();
    }
    public SQLiteDatabase getBdd()
    {
        return bdd;
    }

    public long insertPanier (Livre livre)
    {

        ContentValues content = new ContentValues();


        content.put(COL_TITRE, livre.getTitre());

        content.put(COL_QUANTITE, livre.getQuantite());


        return bdd.insert(Constant.TABLE_PANIER, null, content);

    }

    public int updatePanier (int id, Livre livre)
    {
        ContentValues content = new ContentValues();

        content.put(COL_TITRE, livre.getTitre());

        content.put(COL_QUANTITE, livre.getQuantite());


        return bdd.update(Constant.TABLE_PERSONNE, content,
                Constant.COL_ID_PANIER + " = " + id,
                null);
    }

    public int deletePersonne(String name)
    {
        return bdd.delete(Constant.TABLE_PANIER,
                Constant.COL_NAME + " LIKE '" + name+ "'", null);
    }

    public Livre getLivreByID(int id)
    {
        Cursor c = bdd.query(Constant.TABLE_PANIER,
                new String[]{Constant.COL_ID_PANIER, Constant.COL_TITRE,
                        Constant.COL_QUANTITE},
                COL_ID + "=" + id ,null, null, null, COL_ID);
        return cursorPanier(c);
    }


    public Livre cursorPanier(Cursor c )
    {
        if(c.getCount() == 0)
        {
            c.close();
            return null;
        }


        Livre livre = new Livre();

        // Ici on affecte les valeurs qui se trouve
        // dans notre base de données dans les attributs de notre objet
        livre.setId(c.getInt(Constant.NUM_COL_ID_PANIER));
        livre.setTitre(c.getString(Constant.NUM_COL_TITRE));
        livre.setQuantite(c.getInt(Constant.NUM_COL_QUANTITE));

        return livre;


    }

    public Panier getAllPanier()
    {
        Cursor c = bdd.query(Constant.TABLE_PANIER,
                new String[]{Constant.COL_ID_PANIER,
                    Constant.COL_TITRE, Constant.COL_QUANTITE}
                    ,null, null, null,  null, null);

        if(c.getCount() == 0)
        {
            c.close();
            return null;
        }

        Panier panlist = new Panier();
        while(c.moveToNext())
        {
            Livre livre = new Livre();
            livre.setTitre(c.getString(Constant.NUM_COL_TITRE));
            livre.setQuantite(c.getInt(Constant.NUM_COL_QUANTITE));

            panlist.add(livre);
        }
        c.close();
        return panlist;
    }

}
