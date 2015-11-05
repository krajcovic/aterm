package cz.monetplus.aterm.database.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import cz.monetplus.aterm.base.Fid;
import cz.monetplus.aterm.base.MessageTemplate;
import cz.monetplus.aterm.database.FidSqlLiteHelper;
import cz.monetplus.aterm.database.MessageSqlLiteHelper;

/**
 * Created by krajcovic on 11/5/15.
 */
public class SqlControl {
    private final static String DATABASE_NAME = "aterm.db";

    FidSqlLiteHelper fidSqlLiteHelper;
    MessageSqlLiteHelper messageSqlLiteHelper;

    public SqlControl(Context context) {
        fidSqlLiteHelper = new FidSqlLiteHelper(context, DATABASE_NAME);
        messageSqlLiteHelper = new MessageSqlLiteHelper(context, DATABASE_NAME);
    }

    public long insert(MessageTemplate messageTemplate) {
        // Gets the data repository in write mode
        SQLiteDatabase db = messageSqlLiteHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(MessageSqlLiteHelper.COLUMN_NAME, messageTemplate.getMessageName());
        // values.put(FeedEntry.COLUMN_NAME_CONTENT, content);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                MessageSqlLiteHelper.TABLE_NAME,
                null,
                values);

        return newRowId;
    }

    public long insert(long messageId, Fid fid) {
        // Gets the data repository in write mode
        SQLiteDatabase db = fidSqlLiteHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FidSqlLiteHelper.COLUMN_MESSAGE_ID, messageId);
        values.put(FidSqlLiteHelper.COLUMN_FID, fid.getFid());
        values.put(FidSqlLiteHelper.COLUMN_VALUE, fid.getValue());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FidSqlLiteHelper.TABLE_NAME,
                null,
                values);
        return newRowId;
    }

    public void dropTables() {
        // Gets the data repository in write mode
        SQLiteDatabase db = messageSqlLiteHelper.getWritableDatabase();

        messageSqlLiteHelper.dropTable(db);
        fidSqlLiteHelper.dropTable(db);
    }

//    public Cursor select() {
//        SQLiteDatabase db = mDbHelper.getReadableDatabase();
//
//        // Define a projection that specifies which columns from the database
//        // you will actually use after this query.
//        String[] projection = {
//                FeedEntry._ID,
//                FeedEntry.COLUMN_NAME_TITLE,
//                FeedEntry.COLUMN_NAME_UPDATED,
//        ...
//        };
//
//        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                FeedEntry.COLUMN_NAME_UPDATED + " DESC";
//
//        Cursor c = db.query(
//                FeedEntry.TABLE_NAME,  // The table to query
//                projection,                               // The columns to return
//                selection,                                // The columns for the WHERE clause
//                selectionArgs,                            // The values for the WHERE clause
//                null,                                     // don't group the rows
//                null,                                     // don't filter by row groups
//                sortOrder                                 // The sort order
//        );
//    }
}
