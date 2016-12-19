package com.kotan.printum.Ui.Dao;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.kotan.printum.Model.Company;
import com.kotan.printum.Model.TrollToken;
import com.kotan.printum.Model.Users;
/**
 * Created by Kotan@JoyDainc on 23/11/2016.
 */
public class DaoTroller {
        public static final String TAG = "TrollerDAO";

        // Database fields
        private SQLiteDatabase mDatabase;
        private DbHelper mDbHelper;
        private Context mContext;
        private String[] mAllColumns = { DbHelper.COLUMN_TROLLER_ID,
                DbHelper.COLUMN_TROLLER_USERNAME, DbHelper.COLUMN_TROLLER_PA,
                DbHelper.COLUMN_TROLLER_PASSWORKTYPE,
                DbHelper.COLUMN_TROLLER_MIMPORT,DbHelper.COLUMN_TROLLER_TROLLTOK };

        public DaoTroller(Context context) {
            this.mContext = context;
            mDbHelper = new DbHelper(context);
            // open the database
            try {
                open();
            } catch (SQLException e) {
                Log.e(TAG, "SQLException on openning database " + e.getMessage());
                e.printStackTrace();
            }
        }

        public void open() throws SQLException {
            mDatabase = mDbHelper.getWritableDatabase();
        }

        public void close() {
            mDbHelper.close();
        }

        public TrollToken createCompany(String name, String pa, String PassType,
                                        int mImpor, String a1) {
            ContentValues values = new ContentValues();
            values.put(DbHelper.COLUMN_TROLLER_USERNAME, name);
            values.put(DbHelper.COLUMN_TROLLER_PA, pa);
            values.put(DbHelper.COLUMN_TROLLER_PASSWORKTYPE, PassType);
            values.put(DbHelper.COLUMN_TROLLER_MIMPORT, mImpor);
            values.put(DbHelper.COLUMN_TROLLER_TROLLTOK, a1);
            long insertId = mDatabase
                    .insert(DbHelper.TABLE_TROLLER, null, values);
            Cursor cursor = mDatabase.query(DbHelper.TABLE_TROLLER, mAllColumns,
                    DbHelper.COLUMN_TROLLER_ID+ " = " + insertId, null, null,null, null);
            cursor.moveToFirst();
            TrollToken newTroll = cursorToTroll(cursor);
            cursor.close();
            return newTroll;
        }

        public void deleteCompany(TrollToken company) {
            long id = company.getmID();
            // delete all employees of this company
            UserDao employeeDao = new UserDao(mContext);
            List<Users> listEmployees = employeeDao.getEmployeesOfCompany(id);
            if (listEmployees != null && !listEmployees.isEmpty()) {
                for (Users e : listEmployees) {
                    employeeDao.deleteEmployee(e);
                }
            }

            System.out.println("the deleted company has the id: " + id);
            mDatabase.delete(DbHelper.TABLE_TROLLER, DbHelper.COLUMN_COMPANY_ID
                    + " = " + id, null);
        }

        public List<TrollToken> getAllCompanies() {
            List<TrollToken> listCompanies = new ArrayList<TrollToken>();

            Cursor cursor = mDatabase.query(DbHelper.TABLE_TROLLER, mAllColumns,
                    null, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    TrollToken trollToken = cursorToTroll(cursor);
                    listCompanies.add(trollToken);
                    cursor.moveToNext();
                }
                // make sure to close the cursor
                cursor.close();
            }
            return listCompanies;
        }
        public TrollToken getTrollerById(long id) {
        Cursor cursor = mDatabase.query(DbHelper.TABLE_TROLLER, mAllColumns,
                DbHelper.COLUMN_COMPANY_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();
        }
        TrollToken trollToken = cursorToTroll(cursor);
        return trollToken;
    }
    public long getProfilesCount() {
        Cursor dataCount = mDatabase.rawQuery("select Count(*)  from " + DbHelper.TABLE_TROLLER, null);
        return dataCount.getCount();
    }

        public TrollToken getTrollerByName(String id) {
        Cursor cursor = mDatabase.query(DbHelper.TABLE_TROLLER, mAllColumns,
                DbHelper.COLUMN_TROLLER_USERNAME + " = ?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();
        }
        TrollToken trollToken = cursorToTroll(cursor);
        return trollToken;
    }
        protected TrollToken cursorToTroll(Cursor cursor) {
            TrollToken trollToken = new TrollToken();
            trollToken.setmID(cursor.getLong(0));
            trollToken.setUsername(cursor.getString(1));
            trollToken.setPassword(cursor.getString(2));
            trollToken.setPasswordType(cursor.getString(3));
            trollToken.setTrollTok(cursor.getString(5));
            trollToken.setMImport(cursor.getInt(4));
            return trollToken;
        }
    public void removeAll()
    {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Log.i("actualia","actual");
        mDbHelper.onUpgrade(db,1,2);

    }

    }

