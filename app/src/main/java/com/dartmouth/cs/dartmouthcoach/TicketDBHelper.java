package com.dartmouth.cs.dartmouthcoach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Donald on 2/22/2016.
 */
public class TicketDBHelper extends SQLiteOpenHelper {

    public static final String ENTRIES = "entry";
    public static final String KEY_ROWID = "key_id";
    public static final String DATE_TIME = "schedule";
    public static final String DEPARTURE_TIME = "departure_time";
    public static final String ARRIVAL_TIME = "arrival_time";
    public static final String DEPARTURE_LOC = "departure_location";
    public static final String ARRIVAL_LOC = "arrival_location";

    private static final String DATABASE_NAME = "exerciseentrydb";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_COMMENTS = "comments";

    public static final String TABLE =
            "CREATE TABLE IF NOT EXISTS "
                    + ENTRIES
                    + " ( "
                    + KEY_ROWID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + DATE_TIME
                    + " DATETIME NOT NULL, "
                    + DEPARTURE_TIME
                    + " STRING NOT NULL, "
                    + ARRIVAL_TIME
                    + " STRING NOT NULL, "
                    + DEPARTURE_LOC
                    + " STRING NOT NULL, "
                    + ARRIVAL_LOC
                    + " STRING NOT NULL " + ");";

    // Constructor
    public TicketDBHelper(Context context) {
        // DATABASE_NAME is, of course the name of the database, which is defined as a tring constant
        // DATABASE_VERSION is the version of database, which is defined as an integer constant
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table schema if not exists
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE);
    }

    // Insert a item given each column value
    public long insertEntry(TicketEntry entry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DATE_TIME, entry.getDateTime().getTimeInMillis());
        values.put(DEPARTURE_TIME, entry.getDepartureTime());
        values.put(ARRIVAL_TIME, entry.getArrivalTime());
        values.put(DEPARTURE_LOC, entry.getDepartureLocation());
        values.put(ARRIVAL_LOC, entry.getArrivalLocation());

        long result = db.insert(ENTRIES, null, values);
        db.close();

        //fix return
        return result;

    }

    // Remove an entry by giving its index
    public void removeEntry(long rowIndex) {
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            db.delete(ENTRIES, KEY_ROWID + " = ?", new String[]{String.valueOf(rowIndex)});
        } catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
    }

    // Query a specific entry by its index.
    public TicketEntry fetchEntryByIndex(long rowId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(ENTRIES, new String[] { KEY_ROWID,
                         DATE_TIME, DEPARTURE_TIME, ARRIVAL_TIME, DEPARTURE_LOC, ARRIVAL_LOC }, KEY_ROWID + "=?",
                new String[] { String.valueOf(rowId) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        TicketEntry indexEntry = new TicketEntry();

        Date date = new Date(Long.parseLong(cursor.getString(0)));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        indexEntry.setDateTime(cal);

        indexEntry.setDepartureTime(cursor.getString(1));
        indexEntry.setArrivalTime(cursor.getString(2));
        indexEntry.setDepartureLocation(cursor.getString(3));
        indexEntry.setArrivalLocation(cursor.getString(4));

        return indexEntry;
    }

    // Query the entire table, return all rows
    public ArrayList<TicketEntry> fetchEntries() {
        ArrayList<TicketEntry> eeList = new ArrayList<TicketEntry>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ENTRIES, null);

        if (cursor.moveToFirst() != false) {
            TicketEntry indexEntry = new TicketEntry();

            Date date = new Date(Long.parseLong(cursor.getString(1)));
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            indexEntry.setDateTime(cal);

            indexEntry.setDepartureTime(cursor.getString(2));
            indexEntry.setArrivalTime(cursor.getString(3));
            indexEntry.setDepartureLocation(cursor.getString(4));
            indexEntry.setArrivalLocation(cursor.getString(5));

            eeList.add(indexEntry);
        }

        while (cursor.moveToNext() != false) {
            TicketEntry indexEntry = new TicketEntry();

            Date date = new Date(Long.parseLong(cursor.getString(1)));
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            indexEntry.setDateTime(cal);

            indexEntry.setDepartureTime(cursor.getString(2));
            indexEntry.setArrivalTime(cursor.getString(3));
            indexEntry.setDepartureLocation(cursor.getString(4));
            indexEntry.setArrivalLocation(cursor.getString(5));

            eeList.add(indexEntry);
        }

        cursor.getCount();
        return (eeList);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all data");
        db.execSQL("DROP TABLE IF EXISTS ");

        onCreate(db);
    }

    public String getName() {
        return DATABASE_NAME;
    }
}

