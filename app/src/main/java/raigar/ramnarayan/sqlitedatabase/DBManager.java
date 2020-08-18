package raigar.ramnarayan.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(context);
            database = dbHelper.getWritableDatabase();
        }

        return this;
    }

    public void close() {
        if (dbHelper != null)
            dbHelper.close();
    }

    public long insertInStudent(String name, String rollNo, String classN, int marks) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COL_NAME, name);
        contentValues.put(DatabaseHelper.COL_ROLL_NO, rollNo);
        contentValues.put(DatabaseHelper.COL_CLASS, classN);
        contentValues.put(DatabaseHelper.COL_MARKS, marks);

        return database.insert(DatabaseHelper.TBL_STUDENT, null, contentValues);


    }

    public Cursor fetchFromStudent() {
        String[] columns = new String[] {DatabaseHelper.COL_NAME, DatabaseHelper.COL_ROLL_NO
        , DatabaseHelper.COL_CLASS, DatabaseHelper.COL_MARKS, DatabaseHelper.COL_ID};

        Cursor cursor = database.query(DatabaseHelper.TBL_STUDENT, columns, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public int updateIntoStudent(int id, String name) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COL_NAME, name);

        return database.update(DatabaseHelper.TBL_STUDENT, contentValues, DatabaseHelper.COL_ID + " = " + id, null);
    }

    public int deleteFromStudent(String id) {
       return database.delete(DatabaseHelper.TBL_STUDENT, DatabaseHelper.COL_ID + " = " + id, null);
    }
}
