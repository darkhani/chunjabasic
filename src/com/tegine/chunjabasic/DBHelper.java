package com.tegine.chunjabasic;

import com.tegine.utils.StringDefinition;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 
public class DBHelper {
 
    public static final String KEY_HANJA = "HANJA";
    public static final String KEY_MEAN = "MEAN";
    public static final String KEY_SOUND = "SOUND";
    public static final String KEY_IS_MEMORY = "false";
    public static final String KEY_ROWID = "_id";
    private static final String TAG = "NotesDbAdapter";
 
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
 
    /**
     *
     * Database creation sql statement
     */
 
    private static final String DATABASE_CREATE = "create table if not exists "+StringDefinition.DATABASE_TABLE+" (_id integer primary key autoincrement, "
            + "HANJA text not null, MEAN text not null,SOUND text not null,IS_MEMORY text not null);";
 
//    private static final String DATABASE_NAME = "chunja_data";
//    private static final String DATABASE_TABLE = "chunja_table";
    private static final int DATABASE_VERSION = 1;
    private final Context mCtx;
 
    private static class DatabaseHelper extends SQLiteOpenHelper {
 
        DatabaseHelper(Context context) {
            super(context, StringDefinition.DATABASE_NAME, null, DATABASE_VERSION);
        }
        
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }
 
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion
                    + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }
 
    public DBHelper(Context ctx) {
        this.mCtx = ctx;
    }
 
    public DBHelper open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
 
    public void close() {
        mDbHelper.close();
    }
 
    public long createNote(String hanja, String mean, String sound,String is_memory) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_HANJA, hanja);
        initialValues.put(KEY_MEAN, mean);
        initialValues.put(KEY_SOUND, sound);
        initialValues.put(KEY_IS_MEMORY, is_memory);
        return mDb.insert(StringDefinition.DATABASE_TABLE, null, initialValues);
    }
 
    public boolean deleteNote(long rowId) {
        Log.i("Delete called", "value__" + rowId);
        return mDb.delete(StringDefinition.DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
 
    public Cursor fetchAllNotes() {
        return mDb.query(StringDefinition.DATABASE_TABLE, new String[] { KEY_ROWID, KEY_HANJA, KEY_MEAN,KEY_SOUND,KEY_IS_MEMORY }, null, null, null, null, null);
    }
 
    public Cursor fetchNote(long rowId) throws SQLException {
 
        Cursor mCursor = mDb.query(false, StringDefinition.DATABASE_TABLE, new String[] { KEY_ROWID, KEY_HANJA, KEY_MEAN,KEY_SOUND,KEY_IS_MEMORY  }, KEY_ROWID
                + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
 
    public boolean updateNote(long rowId, String hanja, String mean, String sound,String is_memory) {
        ContentValues args = new ContentValues();
        args.put(KEY_HANJA, hanja);
        args.put(KEY_MEAN, mean);
        args.put(KEY_SOUND, sound);
        args.put(KEY_IS_MEMORY, is_memory);
        return mDb.update(StringDefinition.DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

	public void execSQL(String cREATE_FUELDATA_TABLE) {
		// TODO Auto-generated method stub
		mDb.execSQL(cREATE_FUELDATA_TABLE);
	}
 
}
