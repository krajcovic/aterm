package cz.monetplus.aterm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by krajcovic on 11/5/15.
 */
public class MessageSqlLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "messages";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_SUBTYPE = "subtype";
    public static final String COLUMN_CODE = "trans_code";
    public static final String COLUMN_FLAG1 = "flag1";

    // private static final String DATABASE_NAME = "fids.db";
    private static final int DATABASE_VERSION = 1;


    public MessageSqlLiteHelper(Context context, String databaseName) {
        super(context, databaseName, null, DATABASE_VERSION);
    }

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null,"
            + COLUMN_DESCRIPTION + " text not null,"
            + COLUMN_TYPE + " text not null,"
            + COLUMN_SUBTYPE + " text not null,"
            + COLUMN_CODE + " text not null,"
            + COLUMN_FLAG1 + " text not null"
            + ");";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MessageSqlLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);

    }

    public void dropTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
}
