package com.kotan.printum.Ui.Adapter;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.kotan.printum.Model.TrollToken;
/**
 * Created by Kotan@JoyDainc on 21/11/2016.
 */
public class TrollAdapter {
    public static final String COL_ID="_id";
    public static final String COL_USERNAME="username";
    public static final String COL_PASSWORD ="passwork";
    public static final String COL_PASSWORKTYPE="passworkType";
    public static final String COL_TROLLTOK="trollTok";
    public static  final String COL_MIMPORT = "mimport";

    public static final int INDEX_ID =0;
    public static final int INDEX_USERNAME = INDEX_ID+1;
    public static final int INDEX_PASSWORK = INDEX_ID+2;
    public static final int INDEX_PASSWORKTYPE = INDEX_ID+3;
    public static final int INDEX_TROLLTOK = INDEX_ID+4;
    public static final int INDEX_MIMPORT = INDEX_ID+5;

    private static final String TAG = "TrollAdapter" ;
    private static final String DATABASE_NAME = "dba_printumMobile";
    private static final String TABLE_NAME = "tb1_trollToken";
    private static final int DATABASE_VERSION =1;
    private DbHelper mdbHelper;
    private SQLiteDatabase mDBhelper;
    private final Context cont;

    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists "+ TABLE_NAME+"("+COL_ID+"INTEGER PRIMARY KEY autoincrement,"+
                    COL_USERNAME+"TEXT , "+
                    COL_PASSWORD +"TEXT , "+
                    COL_PASSWORKTYPE+"INTEGER , "+
                    COL_TROLLTOK+"TEXT , "+
                    COL_MIMPORT+"INTEGER ); , ";

    public TrollAdapter(Context ctx){
        this.cont =ctx;
    }
    //open
    public void Open()throws SQLException{
        mdbHelper = new DbHelper(cont);
        mDBhelper = mdbHelper.getWritableDatabase();
    }
    //close
    public void close(){
        if (    mdbHelper != null){
            mdbHelper.close();
        }
    }
    public void createReminder(String username, String password,String passworkType,String trolltok , boolean importan){
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME,username);
        values.put(COL_PASSWORD,password);
        values.put(COL_PASSWORKTYPE,passworkType);
        values.put(COL_TROLLTOK,trolltok);
        values.put(COL_MIMPORT,importan? 1:0);
        mDBhelper.insert(TABLE_NAME,null,values);
    }
    public long createReminder(TrollToken trollToken){
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME,trollToken.getUsername());
        values.put(COL_PASSWORD,trollToken.getPassword());
        values.put(COL_PASSWORKTYPE,trollToken.getPasswordType());
        values.put(COL_TROLLTOK,trollToken.getTrollTok());
        values.put(COL_MIMPORT,trollToken.getMImport());
        return mDBhelper.insert(TABLE_NAME,null,values);
    }
    public TrollToken fecthReminderById(int id){
        Cursor cursor = mDBhelper.query(TABLE_NAME,new String[]{COL_ID,
                COL_USERNAME,COL_PASSWORD,COL_PASSWORKTYPE,COL_TROLLTOK,COL_MIMPORT},COL_ID+"=?"
                ,new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor != null)
            cursor.moveToFirst();
        return new TrollToken(cursor.getInt(INDEX_ID),
                cursor.getString(INDEX_USERNAME),
                cursor.getString(INDEX_PASSWORK),
                cursor.getString(INDEX_PASSWORKTYPE),
                cursor.getString(INDEX_TROLLTOK),
                cursor.getInt(INDEX_MIMPORT));
    }
    public Cursor fetchAllReminders(){
        Cursor cursor = mDBhelper.query(TABLE_NAME,new String[]{COL_ID,
                        COL_USERNAME,COL_PASSWORD,COL_PASSWORKTYPE,COL_TROLLTOK,COL_MIMPORT}
                ,null,null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();}
        return cursor;
    }
    public void updateReminder(TrollToken trollToken){
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME,trollToken.getUsername());
        values.put(COL_PASSWORD,trollToken.getPassword());
        values.put(COL_PASSWORKTYPE,trollToken.getPasswordType());
        values.put(COL_TROLLTOK,trollToken.getTrollTok());
        values.put(COL_MIMPORT,trollToken.getMImport());
         mDBhelper.update(TABLE_NAME,values,
                COL_ID+"=?",new String[]{String.valueOf(trollToken.getmID())});
    }
    public void deleteReminderById(int id){
        mDBhelper.delete(TABLE_NAME,COL_ID+"=?",new String[]{String.valueOf(id)});
    }
    public void deleteAllReminders(){
        mDBhelper.delete(TABLE_NAME,null,null);
    }
    private static class DbHelper extends SQLiteOpenHelper{
        public DbHelper(Context context) {
            super(context, DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.w(TAG,DATABASE_CREATE);
            sqLiteDatabase.execSQL(DATABASE_CREATE);
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            Log.w(TAG,"Cambiando Version"+ i +" a"+ i1+ "Borrando todo");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+ TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }

}
