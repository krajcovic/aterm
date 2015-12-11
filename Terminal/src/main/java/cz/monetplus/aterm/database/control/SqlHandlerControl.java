package cz.monetplus.aterm.database.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cz.monetplus.aterm.base.Fid;
import cz.monetplus.aterm.base.MessageTemplate;
import cz.monetplus.aterm.base.Server;
import cz.monetplus.aterm.database.FidSqlLiteHelper;
import cz.monetplus.aterm.database.MessageSqlLiteHelper;
import cz.monetplus.aterm.database.ServerSqlLiteHelper;

/**
 * Created by krajcovic on 11/5/15.
 */
public class SqlHandlerControl {

    private final static String TAG = "SqlHandlerControl";

    private final static String DATABASE_NAME = "aterm.db";

    private FidSqlLiteHelper fidSqlLiteHelper;
    private MessageSqlLiteHelper messageSqlLiteHelper;
    private ServerSqlLiteHelper serverSqlLiteHelper;

    /**
     * Constructor.
     *
     * @param context
     */
    public SqlHandlerControl(Context context) {
        fidSqlLiteHelper = new FidSqlLiteHelper(context, DATABASE_NAME);
        messageSqlLiteHelper = new MessageSqlLiteHelper(context, DATABASE_NAME);
        serverSqlLiteHelper = new ServerSqlLiteHelper(context, DATABASE_NAME);
    }

    /**
     * Insert message template.
     *
     * @param messageTemplate
     * @return
     */
    public long insert(MessageTemplate messageTemplate) {
        // Gets the data repository in write mode
        SQLiteDatabase db = messageSqlLiteHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(MessageSqlLiteHelper.COLUMN_NAME, messageTemplate.getName());
        values.put(MessageSqlLiteHelper.COLUMN_DESCRIPTION, messageTemplate.getDescription());
        values.put(MessageSqlLiteHelper.COLUMN_TYPE, messageTemplate.getType().toString());
        values.put(MessageSqlLiteHelper.COLUMN_SUBTYPE, messageTemplate.getSubType().toString());
        values.put(MessageSqlLiteHelper.COLUMN_CODE, messageTemplate.getCode());
        values.put(MessageSqlLiteHelper.COLUMN_FLAG1, messageTemplate.getFlag());
        // values.put(FeedEntry.COLUMN_NAME_CONTENT, content);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(
                MessageSqlLiteHelper.TABLE_NAME,
                null,
                values);
        messageTemplate.setId(newRowId);

        db.close();

        return newRowId;
    }

    /**
     * Insert new FID.
     *
     * @param messageId
     * @param fid
     * @return
     */
    public long insert(long messageId, Fid fid) {
        // Gets the data repository in write mode
        SQLiteDatabase db = fidSqlLiteHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FidSqlLiteHelper.COLUMN_MESSAGE_ID, messageId);
        values.put(FidSqlLiteHelper.COLUMN_FID, fid.getFid());
        values.put(FidSqlLiteHelper.COLUMN_VALUE, fid.getValue());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(
                FidSqlLiteHelper.TABLE_NAME,
                null,
                values);

        db.close();

        return newRowId;
    }

    public long insert(Server server) {
        SQLiteDatabase db = serverSqlLiteHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(ServerSqlLiteHelper.COLUMN_HOST, server.getHost());
        values.put(ServerSqlLiteHelper.COLUMN_PORT, server.getPort());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(
                ServerSqlLiteHelper.TABLE_NAME,
                null,
                values);

        server.setId(newRowId);

        db.close();

        return newRowId;
    }

    /**
     * Testing data. Create handshake.
     */
    private void insertHandshake() {
        MessageTemplate messageTemplate = new MessageTemplate("EMV Handshake", "Ověření dostupnosti autorizačního switche.", 'A', 'O', 95, 0);

        long messageId = insert(messageTemplate);
    }

    /**
     * Insert fake data.
     */
    private void insertFake() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());

        MessageTemplate messageTemplate = new MessageTemplate("Test", "Testovaci data: " + currentDateandTime, 'F', 'O', 0, 0);
        //messageTemplate.setDescription("Testovaci data.");

        long messageId = insert(messageTemplate);
        insert(messageId, new Fid("A", "1"));
        insert(messageId, new Fid("B", "2"));
    }

    public void insertTestData() {

        insertHandshake();

//        for(int i = 20; i != 0; i--) {
//            insertFake();
//        }

    }

    public void upgradeTables() {
        // Gets the data repository in write mode
        SQLiteDatabase db = messageSqlLiteHelper.getWritableDatabase();
        messageSqlLiteHelper.onUpgrade(db, 1, 1);

        db = fidSqlLiteHelper.getWritableDatabase();
        fidSqlLiteHelper.onUpgrade(db, 1, 1);

        db = serverSqlLiteHelper.getWritableDatabase();
        serverSqlLiteHelper.onUpgrade(db, 1, 1);

    }

//    public void dropTables() {
//        // Gets the data repository in write mode
//        SQLiteDatabase db = messageSqlLiteHelper.getWritableDatabase();
//
//        messageSqlLiteHelper.dropTable(db);
//        fidSqlLiteHelper.dropTable(db);
//
//    }

    /**
     *
     * @return
     */
    public List<MessageTemplate> fetchAllMessages() {
        SQLiteDatabase db = fidSqlLiteHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                MessageSqlLiteHelper.COLUMN_ID,
                MessageSqlLiteHelper.COLUMN_NAME,
                MessageSqlLiteHelper.COLUMN_DESCRIPTION,
                MessageSqlLiteHelper.COLUMN_TYPE,
                MessageSqlLiteHelper.COLUMN_SUBTYPE,
                MessageSqlLiteHelper.COLUMN_CODE,
                MessageSqlLiteHelper.COLUMN_FLAG1,
        };

//        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                MessageSqlLiteHelper.COLUMN_NAME + " ASC";

//        Cursor cursor = db.query(
//                MessageSqlLiteHelper.TABLE_NAME,  // The table to query
//                projection,                               // The columns to return
//                null,                                // The columns for the WHERE clause
//                null,                            // The values for the WHERE clause
//                null,                                     // don't group the rows
//                null,                                     // don't filter by row groups
//                sortOrder                                 // The sort order
//        );

//        Cursor cursor = db.rawQuery("SELECT " + MessageSqlLiteHelper.COLUMN_ID + ", "
//                + MessageSqlLiteHelper.COLUMN_NAME + ", " + MessageSqlLiteHelper.COLUMN_DESCRIPTION + " FROM " + MessageSqlLiteHelper.TABLE_NAME, null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + MessageSqlLiteHelper.TABLE_NAME, null);

        List<MessageTemplate> listMessages = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                listMessages.add(this.getMessageTemplate(cursor));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listMessages;
    }

    /**
     *
     * @return
     */
    public List<Server> fetchAllServers() {
        SQLiteDatabase db = fidSqlLiteHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                MessageSqlLiteHelper.COLUMN_ID,
                MessageSqlLiteHelper.COLUMN_NAME,
                MessageSqlLiteHelper.COLUMN_DESCRIPTION,
                MessageSqlLiteHelper.COLUMN_TYPE,
                MessageSqlLiteHelper.COLUMN_SUBTYPE,
                MessageSqlLiteHelper.COLUMN_CODE,
                MessageSqlLiteHelper.COLUMN_FLAG1,
        };

//        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                ServerSqlLiteHelper.COLUMN_HOST + " ASC";

        Cursor cursor = db.rawQuery("SELECT * FROM " + ServerSqlLiteHelper.TABLE_NAME, null);

        List<Server> servers = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                servers.add(this.getServer(cursor));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return servers;
    }

    /**
     *
     * @param cursor
     * @return
     */
    private MessageTemplate getMessageTemplate(Cursor cursor) {
        MessageTemplate message = new MessageTemplate();
        message.setId(cursor.getLong(cursor.getColumnIndex(MessageSqlLiteHelper.COLUMN_ID)));

        message.setName(cursor.getString(cursor.getColumnIndex(MessageSqlLiteHelper.COLUMN_NAME)));

        message.setDescription(cursor.getString(cursor.getColumnIndex(MessageSqlLiteHelper.COLUMN_DESCRIPTION)));
        try {
            message.setType(cursor.getString(cursor.getColumnIndex(MessageSqlLiteHelper.COLUMN_TYPE)).charAt(0));
        } catch (Exception e) {
            Log.e(TAG, "Undefined message type.");
        }


        try {
            message.setSubType(cursor.getString(cursor.getColumnIndex(MessageSqlLiteHelper.COLUMN_SUBTYPE)).charAt(0));
        } catch (Exception e) {
            Log.e(TAG, "Undefined message subtype.");
        }

        message.setCode(cursor.getInt(cursor.getColumnIndex(MessageSqlLiteHelper.COLUMN_CODE)));
        message.setCode(cursor.getInt(cursor.getColumnIndex(MessageSqlLiteHelper.COLUMN_FLAG1)));


        return message;
    }

    private Server getServer(Cursor cursor) {
        Server message = new Server();
        message.setId(cursor.getLong(cursor.getColumnIndex(ServerSqlLiteHelper.COLUMN_ID)));
        message.setHost(cursor.getString(cursor.getColumnIndex(ServerSqlLiteHelper.COLUMN_HOST)));
        message.setPort(cursor.getInt(cursor.getColumnIndex(ServerSqlLiteHelper.COLUMN_PORT)));
        return message;
    }

    /**
     *
     * @param item
     */
    public void remove(MessageTemplate item) {
        SQLiteDatabase db = fidSqlLiteHelper.getWritableDatabase();
        db.delete(fidSqlLiteHelper.TABLE_NAME, fidSqlLiteHelper.COLUMN_MESSAGE_ID + "=" + item.getId(), null);

        db = messageSqlLiteHelper.getWritableDatabase();
        db.delete(messageSqlLiteHelper.TABLE_NAME, messageSqlLiteHelper.COLUMN_ID + "=" + item.getId(), null);

    }

    /**
     *
     * @param item
     */
    public void remove(Server item) {
        SQLiteDatabase db = serverSqlLiteHelper.getWritableDatabase();

        db.delete(serverSqlLiteHelper.TABLE_NAME, serverSqlLiteHelper.COLUMN_ID + "=" + item.getId(), null);
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
