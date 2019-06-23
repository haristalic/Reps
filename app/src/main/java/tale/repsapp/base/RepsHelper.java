package tale.repsapp.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TALIC on 10/29/2017.
 */

public class RepsHelper extends SQLiteOpenHelper {
    public RepsHelper(Context context) {
        super(context, RepsContract.DATABASE_NAME, null, RepsContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RepsContract.ItemTable.SQL_CREATE_REPS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(RepsContract.ItemTable.SQL_DELETE_REPS_TABLE);
        onCreate(db);

    }




    public void insertData(int minReps, int maxReps,int firstrun) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RepsContract.ItemTable.COLUMN_NAME_MINREPS, minReps);
        contentValues.put(RepsContract.ItemTable.COLUMN_NAME_MAXREPS, maxReps);
        contentValues.put(RepsContract.ItemTable.COLUMN_NAME_FIRSTRUN, firstrun);
         db.insert(RepsContract.ItemTable.TABLE_NAME, null, contentValues);
        db.close();


    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + RepsContract.DATABASE_NAME, null);
        return res;

    }




}
