package Class;

/**
 * Created by yanis on 20/02/2017.
 */

public class Constant {
    public static final String TABLE_PERSONNE = "table_personne";

    public static final String COL_ID = "id";
    public static final int NUM_COL_ID =0;

    public static final String COL_CIVILITE  ="civilite";
    public static final int NUM_COL_CIVILITE = 1;

    public static final String COL_NAME ="name";
    public static final int NUM_COL_NAME =2;

    public static final String COL_PRENOM ="prenom";
    public static final int NUM_COL_PRENOM = 3;


    public static final String COL_EMAIL ="email";
    public static final int NUM_COL_EMAIL = 4;

    public static final String COL_PASSWORD ="password";
    public static final int NUM_COL_PASSWORD = 5;

    public static final String COL_TELEPHONE ="telephone";
    public static final int NUM_COL_TELEPHONE = 6;

    // REGION PANIER ----------------------------------------------------------------
    public static final String TABLE_PANIER = "table_panier";

    public static final String COL_NAME_PANIER ="name";
    public static final int NUM_COL_NAME_PANIER =0;

    public static final String COL_ID_PANIER = "id_panier";
    public static final int NUM_COL_ID_PANIER =1;

    public static final String ID_LIVRE  ="idlivre";
    public static final int NUM_ID_LIVRE = 2;

    public static final String COL_TITRE ="titre";
    public static final int NUM_COL_TITRE =3;

    public static final String COL_QUANTITE ="quantite";
    public static final int NUM_COL_QUANTITE= 4;


    public static final String CREATE_BDD ="CREATE TABLE IF NOT EXISTS " + TABLE_PERSONNE +" (" + COL_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " +COL_CIVILITE + " TEXT NOT NULL,"+ COL_NAME + " TEXT NOT NULL, " + COL_PRENOM +
                                                " TEXT NOT NULL, "+ COL_EMAIL + " TEXT NOT NULL, "+ COL_PASSWORD + " TEXT NOT NULL, " + COL_TELEPHONE + " TEXT NOT NULL);";


    public static final String CREATE_BDD_PANIER ="CREATE TABLE IF NOT EXISTS " + TABLE_PANIER +" (" + COL_ID_PANIER
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " +ID_LIVRE + " TEXT NOT NULL,"+ COL_TITRE + " TEXT NOT NULL, " + COL_QUANTITE +
            " TEXT NOT NULL);";
}
