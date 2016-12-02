package com.kotan.printum.Ui.Dao;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.kotan.printum.Model.TrollToken;
import com.kotan.printum.Model.Users;

/**
 * Created by Kotan@JoyDainc on 23/11/2016.
 */

public class UserDao{
    public static final String TAG = "UserDAO";
    private Context mContext;
    // Database fields
    private SQLiteDatabase mDatabase;
    private DbHelper mDbHelper;
    private String[] mAllColumns = { DbHelper.COLUMN_USER_ID,
            DbHelper.COLUMN_USER_FIRST_NAME,
            DbHelper.COLUMN_USER_LAST_NAME, DbHelper.COLUMN_USER_ADDRESS,
            DbHelper.COLUMN_USER_EMAIL,
            DbHelper.COLUMN_USER_PHONE_NUMBER,DbHelper.COLUMN_USER_PHOTO
            ,
            DbHelper.COLUMN_USER_COMPANY_ID,DbHelper.COLUMN_USER_PK_TROL,
            DbHelper.COLUMN_USER_CITID,DbHelper.COLUMN_USER_DEPARTAMENTID,DbHelper.COLUMN_USER_PHYPHO};
    public UserDao(Context context) {
        mDbHelper = new DbHelper(context);
        this.mContext = context;
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

    public Users createEmploye(String firstName, String lastName,
                               String address, String email, String phoneNumber, String usernamep,byte ser ,
                               long companyId,long tro,int company,int comDe) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_USER_FIRST_NAME, firstName);
        values.put(DbHelper.COLUMN_USER_LAST_NAME, lastName);
        values.put(DbHelper.COLUMN_USER_ADDRESS, address);
        values.put(DbHelper.COLUMN_USER_EMAIL, email);
        values.put(DbHelper.COLUMN_USER_PHONE_NUMBER, phoneNumber);
        values.put(DbHelper.COLUMN_USER_PHOTO, usernamep);
        values.put(DbHelper.COLUMN_USER_COMPANY_ID, companyId);
        values.put(DbHelper.COLUMN_USER_PK_TROL, tro);
        values.put(DbHelper.COLUMN_USER_CITID, company);
        values.put(DbHelper.COLUMN_USER_DEPARTAMENTID, comDe);
        values.put(DbHelper.COLUMN_USER_PHYPHO, ser);
        long insertId = mDatabase
                .insert(DbHelper.TABLE_USER, null, values);
        Cursor cursor = mDatabase.query(DbHelper.TABLE_USER, mAllColumns,
                DbHelper.COLUMN_USER_ID + " = " + insertId, null, null,
                null, null);
        cursor.moveToFirst();
        Users newEmployee = cursorToEmploye(cursor);
        cursor.close();
        return newEmployee;
    }

    public void deleteEmployee(Users employee) {
        long id = employee.getUserId();
        System.out.println("the deleted employee has the id: " + id);
        mDatabase.delete(DbHelper.TABLE_USER, DbHelper.COLUMN_USER_ID
                + " = " + id, null);
    }
    public List<Users> getAllEmployees() {
        List<Users> listEmployees = new ArrayList<Users>();

        Cursor cursor = mDatabase.query(DbHelper.TABLE_USER, mAllColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Users employee = cursorToEmploye(cursor);
            listEmployees.add(employee);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listEmployees;
    }
    public List<Users> getEmployeesOfCompany(long companyId) {
        List<Users> listEmployees = new ArrayList<Users>();

        Cursor cursor = mDatabase.query(DbHelper.TABLE_USER, mAllColumns,
                DbHelper.COLUMN_COMPANY_ID + " = ?",
                new String[] { String.valueOf(companyId) }, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Users employee = cursorToEmploye(cursor);
            listEmployees.add(employee);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return listEmployees;
    }
    private Users cursorToEmploye(Cursor cursor) {
        Users employee = new Users();
        employee.setUserId(cursor.getLong(0));
        employee.setFirstName(cursor.getString(1));
        employee.setLastName(cursor.getString(2));
        employee.setUserPhone(cursor.getString(3));
        employee.setUserAddress(cursor.getString(4));
        employee.setUserPhoto(cursor.getString(5));
        employee.setUserName(cursor.getString(6));
        employee.setCompanyId(cursor.getInt(7));
        employee.setCityId(cursor.getInt(8));
        employee.setDepartmentId(cursor.getInt(9));
        // get The company by id
        long companyId = cursor.getLong(10);
        DaoTroller dao = new DaoTroller(mContext);
        TrollToken trollToken = dao.getTrollerById(companyId);
        if (trollToken != null)
            employee.setTrollToken(trollToken);
        employee.setImage(cursor.getBlob(11));
        return employee;
    }

}
