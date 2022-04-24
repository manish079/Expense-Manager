package com.manishprajapat.example.expensemanager;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ExpensesDB.db";
    public static final String TABLE_NAME = "expense_details";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_MOBILE = "mobile";
    public static final String COLUMN_INVOICE_NUMBER = "invoice_number";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_TOTAL_AMT = "total_amt";
    public static final String COLUMN_PAID_AMT = "paid_amt";
    public static final String COLUMN_BALANCE_AMOUNT = "balance_amount"; //new
    public static final String COLUMN_DAY_MONTH = "day_month";
    public static final String COLUMN_MONTH_YEAR = "month_year";
    public static final String COLUMN_YEAR = "year";

    public static final int DATABASE_VERSION = 1;

    DBHelper DBHelper;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = " CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_MOBILE + " TEXT,"
                + COLUMN_INVOICE_NUMBER + " TEXT,"
                + COLUMN_CATEGORY + " TEXT,"
                + COLUMN_TOTAL_AMT + " TEXT,"
                + COLUMN_PAID_AMT + " TEXT,"
                + COLUMN_BALANCE_AMOUNT + " TEXT,"
                + COLUMN_DAY_MONTH + " TEXT,"
                + COLUMN_MONTH_YEAR + " TEXT,"
                + COLUMN_YEAR + " TEXT)";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


    public void addExpenseDetails(String expenseInvoiceNumber, String expenseName, String expenseDate, String expenseMobile, String expenseCategory,
                                  String expenseTotalAmt, String expensePaidAmt, String balance_amount, String day_of_month,
                                  String month_of_year, String years) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_INVOICE_NUMBER, expenseInvoiceNumber);
        contentValues.put(COLUMN_NAME, expenseName);
        contentValues.put(COLUMN_MOBILE, expenseMobile);
        contentValues.put(COLUMN_CATEGORY, expenseCategory);
        contentValues.put(COLUMN_TOTAL_AMT, expenseTotalAmt);
        contentValues.put(COLUMN_PAID_AMT, expensePaidAmt);
        contentValues.put(COLUMN_DATE, expenseDate);
        contentValues.put(COLUMN_BALANCE_AMOUNT, balance_amount);
        contentValues.put(COLUMN_DAY_MONTH, day_of_month);
        contentValues.put(COLUMN_MONTH_YEAR, month_of_year);
        contentValues.put(COLUMN_YEAR, years);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public ArrayList<POJO> readExpenses() {

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<POJO> expensePojoArrayList = new ArrayList<>();

        if (cursor.moveToNext()) {

            do {
                expensePojoArrayList.add(new POJO(cursor.getString(1),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(8),
                        cursor.getString(2),
                        cursor.getString(7),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return expensePojoArrayList;
    }

    @SuppressLint("Range")
    public int getTotalExpansesMonth() {
        int total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<POJO> totalMonthList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT SUM(" + com.manishprajapat.example.expensemanager.DBHelper.COLUMN_TOTAL_AMT + ") as Total FROM "
                + com.manishprajapat.example.expensemanager.DBHelper.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                total = cursor.getInt(cursor.getColumnIndex("Total"));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return total;
    }

    @SuppressLint("Range")
    public int getTotalPendingExpansesMonth() {
        int total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<POJO> totalPendingMonthList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT SUM(" + com.manishprajapat.example.expensemanager.DBHelper.COLUMN_BALANCE_AMOUNT + ") as Total FROM "
                + com.manishprajapat.example.expensemanager.DBHelper.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                total = cursor.getInt(cursor.getColumnIndex("Total"));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return total;
    }
}
