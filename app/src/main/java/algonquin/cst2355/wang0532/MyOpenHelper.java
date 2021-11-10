package algonquin.cst2355.wang0532;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class MyOpenHelper extends SQLiteOpenHelper {
    public static final String filename = "MyDatabase";
    public static final int version = 1;
    public static final String TABLE_NAME = "MyData";
    public static final String COL_ID = "_id";
    public static final String COL_MESSAGE = "Message";
    public static final String COL_SEND_RECEIVE = "SendOrReceive";
    public static final String COL_TIME_SENT = "TimeSent";

    public MyOpenHelper( Context context ) {
        super(context, filename, null, version);
    }

    //should be the creation statement
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table MyData
        db.execSQL(String.format("create table %s( %s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT, %s INTEGER, %s TEXT );"
                , TABLE_NAME, COL_ID,                                           COL_MESSAGE, COL_SEND_RECEIVE, COL_TIME_SENT ));
    }
//delete current
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TABLE_NAME );
        //create a new table:
        this.onCreate(db); //calls function on line 26

    }
}
