package raigar.ramnarayan.sqlitedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    // database name
    private static final String DB_NAME = "NAME.DB";
    // database version
    private static final int DB_VERSION = 1;
    // table name
    public static final String TBL_STUDENT = "STUDENT";
    // column name
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_ROLL_NO = "ROLL_NO";
    public static final String COL_CLASS = "CLASS";
    public static final String COL_MARKS = "MARKS";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        deleteTbl(db, TBL_STUDENT);
        onCreate(db);
    }

    // create table query
    private static final String CREATE_TABLE = "CREATE TABLE " + TBL_STUDENT + " ( " + COL_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT NOT NULL, "
            + COL_ROLL_NO + " INTEGER, " + COL_CLASS + " TEXT NOT NULL, " + COL_MARKS + " TEXT NOT NULL );";

    // delete table
    private static void deleteTbl(SQLiteDatabase db, String tableName) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }
}
