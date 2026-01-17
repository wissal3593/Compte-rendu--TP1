package com.android.example.wordlistsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WordListOpenHelper extends SQLiteOpenHelper {

    // C'est une bonne idée de toujours définir une balise de journal comme celle-ci.
    private static final String TAG = WordListOpenHelper.class.getSimpleName();

    // doit être 1 la première fois ou l'application plantera
    private static final int DATABASE_VERSION = 1;
    private static final String WORD_LIST_TABLE = "word_entries";
    private static final String DATABASE_NAME = "wordlist";

    // Noms des colonnes...
    public static final String KEY_ID = "_id";
    public static final String KEY_WORD = "word";


    // ... et un tableau de chaînes de colonnes.
    private static final String[] COLUMNS = { KEY_ID, KEY_WORD };
    // Construisez la requête SQL qui crée la table.
    private static final String WORD_LIST_TABLE_CREATE =
            "CREATE TABLE " + WORD_LIST_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
                    // l'id s'incrémentera automatiquement si aucune valeur n'est passée
                    KEY_WORD + " TEXT );";
    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;




    public WordListOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private void fillDatabaseWithData(SQLiteDatabase db){
        // Créer un conteneur pour les données.
        ContentValues values = new ContentValues();
        String[] words = {"Android", "Adapter", "ListView", "AsyncTask",
                "Android Studio", "SQLiteDatabase", "SQLOpenHelper",
                "Data model", "ViewHolder","Android Performance",
                "OnClickListener"};
        for (int i=0; i < words.length; i++) {
            // Placer les paires colonne/valeur dans le conteneur.
            // put() remplace les valeurs existantes.
            values.put(KEY_WORD, words[i]);
            db.insert(WORD_LIST_TABLE, null, values);
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WORD_LIST_TABLE_CREATE);
        fillDatabaseWithData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(WordListOpenHelper.class.getName(),
                "Mise à niveau de la base de données de la version " + oldVersion + " vers "
                        + newVersion + ", ce qui détruira toutes les anciennes données");

        db.execSQL("DROP TABLE IF EXISTS " + WORD_LIST_TABLE);
        onCreate(db);
    }
    public long count(){
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }
        return DatabaseUtils.queryNumEntries(mReadableDB, WORD_LIST_TABLE);
    }
    public WordItem query(int position) {
        String query = "SELECT * FROM " + WORD_LIST_TABLE +
                " ORDER BY " + KEY_WORD + " ASC " +
                "LIMIT " + position + ",1";
        Cursor cursor = null;
        WordItem entry = new WordItem();
        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);
            if (cursor != null && cursor.moveToFirst()) {
                // Utiliser getColumnIndexOrThrow pour la sécurité
                int idIndex = cursor.getColumnIndexOrThrow(KEY_ID);
                int wordIndex = cursor.getColumnIndexOrThrow(KEY_WORD);

                entry.setId(cursor.getInt(idIndex));
                entry.setWord(cursor.getString(wordIndex));
            }
        }
        catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        }
        if (cursor != null) {
            cursor.close(); // Fermer le curseur ici
        }
        return entry;
    }
    public long insert(String word){
        long newId = 0;
        ContentValues values = new ContentValues();
        values.put(KEY_WORD, word);
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            newId = mWritableDB.insert(WORD_LIST_TABLE, null, values);
        } catch (Exception e) {
            Log.d(TAG, "INSERT EXCEPTION! " + e.getMessage());
        }
        return newId;
    }
    public int delete(int id) {
        int deleted = 0;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            deleted = mWritableDB.delete(WORD_LIST_TABLE,
                    KEY_ID + " = ? ", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d (TAG, "DELETE EXCEPTION! " + e.getMessage());
        }
        return deleted;
    }
    public int update(int id, String word) {
        int mNumberOfRowsUpdated = -1;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            ContentValues values = new ContentValues();
            values.put(KEY_WORD, word);
            mNumberOfRowsUpdated = mWritableDB.update(WORD_LIST_TABLE,
                    values,
                    KEY_ID + " = ?",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d (TAG, "UPDATE EXCEPTION! " + e.getMessage());
        }
        return mNumberOfRowsUpdated;
    }
}
