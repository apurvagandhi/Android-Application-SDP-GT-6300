package edu.gatech.seclass.jobcompare6300.dataAccessLayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.gatech.seclass.jobcompare6300.model.ComparisonSetting;

public class SettingDatabaseHelperModel extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static String DATABASE_NAME = "comparisionSetting.db";

    public SettingDatabaseHelperModel(Context context, String databaseName){
        super(context, databaseName, null, DATABASE_VERSION);
        DATABASE_NAME = databaseName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createSettingQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE comparisionSetting");
        onCreate(db);
    }

    private String createSettingQuery(){
        String result = "create table " + SettingContract.SettingEntry.TABLE_NAME + " (";
        result += "id integer primary key";
        result += ", " + SettingContract.SettingEntry.COLUMN_NAME_YEARLY_SALARY_WEIGHT + " integer";
        result += ", " + SettingContract.SettingEntry.COLUMN_NAME_YEARLY_BONUS_WEIGHT + " integer";
        result += ", " + SettingContract.SettingEntry.COLUMN_NAME_TRAINING_DEV_FUND + " integer";
        result += ", " + SettingContract.SettingEntry.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT + " integer";
        result += ", " + SettingContract.SettingEntry.COLUMN_NAME_LEAVE_TIME_WEIGHT + " integer";
        result += ")";
        return result;
    }

    public boolean insertSetting(ComparisonSetting comparisonSetting){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SettingContract.SettingEntry.COLUMN_NAME_YEARLY_SALARY_WEIGHT, comparisonSetting.yearlySalaryWeight);
        contentValues.put(SettingContract.SettingEntry.COLUMN_NAME_YEARLY_BONUS_WEIGHT, comparisonSetting.yearlyBonusWeight);
        contentValues.put(SettingContract.SettingEntry.COLUMN_NAME_LEAVE_TIME_WEIGHT, comparisonSetting.leaveTimeWeight);
        contentValues.put(SettingContract.SettingEntry.COLUMN_NAME_TRAINING_DEV_FUND, comparisonSetting.trainingDevFundWeight);
        contentValues.put(SettingContract.SettingEntry.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT, comparisonSetting.teleworkDaysPerWeekWeight);
        db.insert(SettingContract.SettingEntry.TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateSetting(ComparisonSetting comparisonSetting){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SettingContract.SettingEntry.COLUMN_NAME_YEARLY_SALARY_WEIGHT, comparisonSetting.yearlySalaryWeight);
        contentValues.put(SettingContract.SettingEntry.COLUMN_NAME_YEARLY_BONUS_WEIGHT, comparisonSetting.yearlyBonusWeight);
        contentValues.put(SettingContract.SettingEntry.COLUMN_NAME_LEAVE_TIME_WEIGHT, comparisonSetting.leaveTimeWeight);
        contentValues.put(SettingContract.SettingEntry.COLUMN_NAME_TRAINING_DEV_FUND, comparisonSetting.trainingDevFundWeight);
        contentValues.put(SettingContract.SettingEntry.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT, comparisonSetting.teleworkDaysPerWeekWeight);
        db.update(SettingContract.SettingEntry.TABLE_NAME, contentValues, "id = ?",new String[] { Integer.toString(1) });
        return true;
    }

    public ComparisonSetting getSettings(){
        System.out.println("I am in getSettings() inside SettingdatabaseHelperModel");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " + SettingContract.SettingEntry.TABLE_NAME + " where id="+1+"", null );
        cursor.moveToFirst();
        System.out.println("I am in getSettings() AGAIN inside SettingdatabaseHelperModel");

        ComparisonSetting result = new ComparisonSetting();
        System.out.println("I am in getSettings() AGAIN 1 inside SettingdatabaseHelperModel" + cursor);

        if(!cursor.isAfterLast()){
            System.out.println("I am in getSettings() AGAIN 2 inside SettingdatabaseHelperModel");
            result.yearlySalaryWeight = cursor.getInt(cursor.getColumnIndex(SettingContract.SettingEntry.COLUMN_NAME_YEARLY_SALARY_WEIGHT));
            result.yearlyBonusWeight = cursor.getInt(cursor.getColumnIndex(SettingContract.SettingEntry.COLUMN_NAME_YEARLY_BONUS_WEIGHT));
            result.leaveTimeWeight = cursor.getInt(cursor.getColumnIndex(SettingContract.SettingEntry.COLUMN_NAME_LEAVE_TIME_WEIGHT));
            result.trainingDevFundWeight = cursor.getInt(cursor.getColumnIndex(SettingContract.SettingEntry.COLUMN_NAME_TRAINING_DEV_FUND));
            result.teleworkDaysPerWeekWeight = cursor.getInt(cursor.getColumnIndex(SettingContract.SettingEntry.COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT));
            result.id = cursor.getInt(cursor.getColumnIndex("id"));
        }

        return result;
    }


}
