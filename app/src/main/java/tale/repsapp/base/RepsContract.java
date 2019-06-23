package tale.repsapp.base;

import android.provider.BaseColumns;

/**
 * Created by TALIC on 10/29/2017.
 */

public class RepsContract {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "RepsDatabase";


    private RepsContract() {

    }

    public static class ItemTable implements BaseColumns {

        public static final String TABLE_NAME = "reps";
        public static final String  _ID = "id";
        public static final String COLUMN_NAME_MINREPS = "minReps";
        public static final String COLUMN_NAME_MAXREPS = "maxReps";
        public static final String COLUMN_NAME_FIRSTRUN = "firstRun";


        public static final String SQL_CREATE_REPS_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_MINREPS + " INTEGER," +
                        COLUMN_NAME_MAXREPS + " INTEGER,"+
                        COLUMN_NAME_FIRSTRUN + " INTEGER)";

        public static final String SQL_DELETE_REPS_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;



    }
}
