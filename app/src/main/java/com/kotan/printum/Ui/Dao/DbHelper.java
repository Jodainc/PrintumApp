package com.kotan.printum.Ui.Dao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by Kotan@JoyDainc on 23/11/2016.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String TAG = "DBHelper";

    // columns of the Troller table
    public static final String TABLE_TROLLER = "troller";
    public static final String COLUMN_TROLLER_ID="_id";
    public static final String COLUMN_TROLLER_USERNAME="username";
    public static final String COLUMN_TROLLER_PA ="pa";
    public static final String COLUMN_TROLLER_PASSWORKTYPE="passworkType";
    public static final String COLUMN_TROLLER_TROLLTOK="trollTok";
    public static  final String COLUMN_TROLLER_MIMPORT = "mimport";

    // columns of the companies table
    public static final String TABLE_COMPANIES = "companies";
    public static final String COLUMN_COMPANY_ID = COLUMN_TROLLER_ID;
    public static final String COLUMN_COMPANY_NAME = "company_name";
    public static final String COLUMN_COMPANY_ADDRESS = "address";
    public static final String COLUMN_COMPANY_WEBSITE = "website";
    public static final String COLUMN_COMPANY_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_COMPANY_PK_TROL = "troller_id";

    // columns of the employees table
    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = COLUMN_TROLLER_ID;
    public static final String COLUMN_USER_FIRST_NAME = "first_name";
    public static final String COLUMN_USER_DEPARTAMENTID = "departamentid";
    public static final String COLUMN_USER_CITID = "citid";
    public static final String COLUMN_USER_PHOTO = "userphot";
    public static final String COLUMN_USER_PHYPHO = "userphot1";
    public static final String COLUMN_USER_LAST_NAME = "last_name";
    public static final String COLUMN_USER_ADDRESS = COLUMN_COMPANY_ADDRESS;
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PHONE_NUMBER = COLUMN_COMPANY_PHONE_NUMBER;
    public static final String COLUMN_USER_COMPANY_ID = "company_id";
    public static final String COLUMN_USER_PK_TROL = "troller_id";

    private static final String DATABASE_NAME = "printum.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statement of the employees table creation
    private static final String SQL_CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USER_FIRST_NAME + " TEXT NOT NULL, "
            + COLUMN_USER_LAST_NAME + " TEXT NOT NULL, "
            + COLUMN_USER_ADDRESS + " TEXT NOT NULL, "
            + COLUMN_USER_EMAIL + " TEXT NOT NULL, "
            + COLUMN_USER_PHONE_NUMBER + " TEXT NOT NULL, "
            + COLUMN_USER_PHOTO + " TEXT NOT NULL, "
            + COLUMN_USER_PHYPHO+ " INTEGER , "
            + COLUMN_USER_DEPARTAMENTID + " INTEGER NOT NULL, "
            + COLUMN_USER_CITID + " INTEGER NOT NULL, "
            + COLUMN_USER_COMPANY_ID + " INTEGER NOT NULL, "
            + COLUMN_USER_PK_TROL + " INTEGER NOT NULL "
            +");";

    // SQL statement of the companies table creation
    private static final String SQL_CREATE_TABLE_COMPANIES = "CREATE TABLE " + TABLE_COMPANIES + "("
            + COLUMN_COMPANY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_COMPANY_NAME + " TEXT NOT NULL, "
            + COLUMN_COMPANY_ADDRESS + " TEXT NOT NULL, "
            + COLUMN_COMPANY_WEBSITE + " TEXT NOT NULL, "
            + COLUMN_COMPANY_PHONE_NUMBER + " TEXT NOT NULL, "
            + COLUMN_COMPANY_PK_TROL + " INTEGER NOT NULL "
            +");";

    // SQL statement of the troller table creation
    private static final String SQL_CREATE_TABLE_TROLLER = "CREATE TABLE " + TABLE_TROLLER + "("
            + COLUMN_TROLLER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TROLLER_USERNAME+ " TEXT NOT NULL, "
            + COLUMN_TROLLER_PA + " TEXT NOT NULL, "
            + COLUMN_TROLLER_PASSWORKTYPE + " TEXT NOT NULL, "
            + COLUMN_TROLLER_TROLLTOK + " TEXT NOT NULL, "
            + COLUMN_TROLLER_MIMPORT+ " INTEGER NOT NULL "
            +");";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE_TABLE_COMPANIES);
        database.execSQL(SQL_CREATE_TABLE_USER);
        database.execSQL(SQL_CREATE_TABLE_TROLLER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG,
                "Upgrading the database from version " + oldVersion + " to "+ newVersion);
        // clear all data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TROLLER);
        // recreate the tables
        onCreate(db);
    }

    public DbHelper(Context context, String name, CursorFactory factory,int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
}
