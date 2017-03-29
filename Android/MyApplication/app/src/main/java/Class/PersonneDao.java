package Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static Class.Constant.COL_CIVILITE;
import static Class.Constant.COL_EMAIL;
import static Class.Constant.COL_NAME;
import static Class.Constant.COL_ID;
import static Class.Constant.COL_PASSWORD;
import static Class.Constant.COL_PRENOM;
import static Class.Constant.COL_NAME;
import static Class.Constant.COL_TELEPHONE;
import static Class.Constant.TABLE_PERSONNE;


/**
 * Created by yanis on 20/02/2017.
 */

public class PersonneDao {

    private SQLiteDatabase bdd;
    private MaBaseOpenHelper helper;
    private String NOM_BDD = "MyBase";
    private static final int VERSION = 1;

    public PersonneDao(SQLiteDatabase bdd)
    {
        this.bdd = bdd;
    }
    public PersonneDao(Context context)
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

    public long instertPersonne (Personne pers)
    {

        ContentValues content = new ContentValues();

        content.put(COL_CIVILITE, pers.getCivilite());
        content.put(COL_NAME, pers.getName());

        content.put(COL_PRENOM, pers.getPrenom());
        content.put(COL_EMAIL, pers.getEmail());
        content.put(COL_PASSWORD, pers.getPassword());

        content.put(COL_TELEPHONE, pers.getTelephone());

        return bdd.insert(Constant.TABLE_PERSONNE, null, content);

    }

    public int updatePersonne(int id, Personne pers)
    {
        ContentValues content = new ContentValues();

        content.put(COL_CIVILITE, pers.getCivilite());
        content.put(COL_NAME, pers.getName());

        content.put(COL_PRENOM, pers.getPrenom());
        content.put(COL_EMAIL, pers.getEmail());
        content.put(COL_PASSWORD, pers.getPassword());

        content.put(COL_TELEPHONE, pers.getTelephone());


        return bdd.update(Constant.TABLE_PERSONNE, content,
                            Constant.COL_ID + " = " + id,
                                null);
    }

    public int deletePersonne(String name)
    {
        return bdd.delete(Constant.TABLE_PERSONNE, Constant.COL_NAME + " LIKE '" + name+ "'", null);
    }

    public Personne getPersonneById(int id)
    {
        Cursor c = bdd.query(Constant.TABLE_PERSONNE, new String[]{Constant.COL_ID, Constant.COL_CIVILITE, Constant.COL_NAME, Constant.COL_PRENOM, Constant.COL_EMAIL, Constant.COL_PASSWORD, Constant.COL_TELEPHONE},
                COL_ID + "=" + id ,null, null, null, COL_ID);
        return cursorPersonne(c);
    }

    public Personne cursorPersonne(Cursor c )
    {
        if(c.getCount() == 0)
        {
            c.close();
            return null;
        }


        Personne pers = new Personne();


            pers.setId(c.getInt(Constant.NUM_COL_ID));
            pers.setCivilite(c.getString(Constant.NUM_COL_CIVILITE));
            pers.setName(c.getString(Constant.NUM_COL_NAME));
            pers.setPrenom(c.getString(Constant.NUM_COL_PRENOM));
            pers.setEmail(c.getString(Constant.NUM_COL_EMAIL));
            pers.setPassword(c.getString(Constant.NUM_COL_PASSWORD));
            pers.setTelephone(c.getString(Constant.NUM_COL_TELEPHONE));
            return pers;


    }

    public ArrayList<Personne> getAllPersonne()
    {
        Cursor c = bdd.query(Constant.TABLE_PERSONNE, new String[]{Constant.COL_ID,Constant.COL_CIVILITE, Constant.COL_NAME,
                                Constant.COL_PRENOM, Constant.COL_EMAIL, Constant.COL_PASSWORD , Constant.COL_TELEPHONE},null, null, null,  null, null);

        if(c.getCount() == 0)
        {
            c.close();
            return null;
        }

        ArrayList<Personne> persList = new ArrayList<>();
        while(c.moveToNext())
        {
            Personne perso = new Personne();
            perso.setId(c.getInt(Constant.NUM_COL_ID));
            perso.setCivilite(c.getString(Constant.NUM_COL_CIVILITE));
            perso.setName(c.getString(Constant.NUM_COL_NAME));
            perso.setPrenom(c.getString(Constant.NUM_COL_PRENOM));
            perso.setEmail(c.getString(Constant.NUM_COL_EMAIL));
            perso.setPassword(c.getString(Constant.NUM_COL_PASSWORD));
            perso.setTelephone(c.getString(Constant.NUM_COL_TELEPHONE));

            persList.add(perso);
        }
        c.close();
        return persList;
    }

}
