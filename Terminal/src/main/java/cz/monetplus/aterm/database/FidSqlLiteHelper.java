package cz.monetplus.aterm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by krajcovic on 11/5/15.
 */
public class FidSqlLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "fids";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MESSAGE_ID = "message_id";
    public static final String COLUMN_FID = "fid";
    public static final String COLUMN_VALUE = "value";

    // private static final String DATABASE_NAME = "fids.db";
    private static final int DATABASE_VERSION = 1;

    public FidSqlLiteHelper(Context context, String databaseName) {
        super(context, databaseName, null, DATABASE_VERSION);
    }

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_MESSAGE_ID + " integer not null"
            + COLUMN_FID + " text not null,"
            + COLUMN_VALUE + " text not null);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(FidSqlLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void dropTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}
