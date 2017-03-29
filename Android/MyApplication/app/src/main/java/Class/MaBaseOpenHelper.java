package Class;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import Class.Constant;
import android.util.Log;

import static Class.Constant.CREATE_BDD;
import static Class.Constant.CREATE_BDD_PANIER;

/**
 * Created by yanis on 20/02/2017.
 */

public class MaBaseOpenHelper extends SQLiteOpenHelper {


    public MaBaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
        db.execSQL(CREATE_BDD_PANIER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log.v(ShopBaseSqlite.class.getName(), "Upgrading databse from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");

    }
}
