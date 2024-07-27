package edu.gatech.seclass.jobcompare6300.dataAccessLayer;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import edu.gatech.seclass.jobcompare6300.model.JobRecord;

public class JobDatabaseHelperModel extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "JobCompare.db";

    public JobDatabaseHelperModel(Context context, String databaseName){
        super(context, databaseName, null, DATABASE_VERSION);
        DATABASE_NAME = databaseName;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GetCreateJobQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE jobRecords");
        db.execSQL("DROP TABLE settings");
        onCreate(db);
    }

    private String GetCreateJobQuery(){
        String result = "create table " + JobContract.JobEntry.TABLE_NAME + " (";
        result += "id integer primary key";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_TITLE + " text";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_COMPANY + " text";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_LOCATION + " text";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_COSTOFLIVINGINDEX + " real";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_YEARLYSALARY + " real";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_YEARLYBONUS + " real";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_LEAVETIME + " integer";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_SCORE + " real";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_CURRENT + " integer";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_FUNDS + " integer";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_TELEWORKDAYSPERWEEK + " integer";
        result += ")";
        return result;
    }

    public boolean insertJobRecord(JobRecord jobRecord){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_TITLE, jobRecord.title);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_COMPANY, jobRecord.company);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_LOCATION, jobRecord.location);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_COSTOFLIVINGINDEX, jobRecord.costOfLivingIndex);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_YEARLYSALARY, jobRecord.yearlySalary);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_YEARLYBONUS, jobRecord.yearlyBonus);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_LEAVETIME, jobRecord.leaveTime);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_SCORE, jobRecord.score);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_CURRENT, jobRecord.isCurrentJob);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_FUNDS, jobRecord.trainingDevFund);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_TELEWORKDAYSPERWEEK, jobRecord.teleWorkDaysPerWeek);
        db.insert(JobContract.JobEntry.TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateJobRecord(JobRecord jobRecord){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_TITLE, jobRecord.title);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_COMPANY, jobRecord.company);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_LOCATION, jobRecord.location);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_COSTOFLIVINGINDEX, jobRecord.costOfLivingIndex);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_YEARLYSALARY, jobRecord.yearlySalary);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_YEARLYBONUS, jobRecord.yearlyBonus);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_LEAVETIME, jobRecord.leaveTime);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_SCORE, jobRecord.score);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_CURRENT, jobRecord.isCurrentJob);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_FUNDS, jobRecord.trainingDevFund);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_TELEWORKDAYSPERWEEK, jobRecord.teleWorkDaysPerWeek);
        db.update(JobContract.JobEntry.TABLE_NAME, contentValues, "id = ?",new String[] { Integer.toString(jobRecord.id) });
        return true;
    }

    public JobRecord getJobRecord(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " + JobContract.JobEntry.TABLE_NAME + " where id="+id+"", null );
        cursor.moveToFirst();

        JobRecord result = new JobRecord();
        if(cursor.isAfterLast() == false){
            result = SetJobRecordProperties(cursor);
        }

        return result;
    }

    public ArrayList<JobRecord> getAllJobRecords(){
        ArrayList<JobRecord> result = new ArrayList<JobRecord>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " + JobContract.JobEntry.TABLE_NAME, null );
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            JobRecord jobRecord = SetJobRecordProperties(cursor);
            result.add(jobRecord);
            cursor.moveToNext();
        }
        return result;
    }

    @SuppressLint("Range")
    private JobRecord SetJobRecordProperties(Cursor cursor){
        JobRecord result = new JobRecord();
        result.id  = cursor.getInt(cursor.getColumnIndex("id"));
        result.title = cursor.getString(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_TITLE));
        result.company = cursor.getString(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_COMPANY));
        result.location = cursor.getString(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_LOCATION));
        result.costOfLivingIndex = cursor.getFloat(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_COSTOFLIVINGINDEX));
        result.yearlySalary= cursor.getFloat(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_YEARLYSALARY));
        result.yearlyBonus = cursor.getFloat(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_YEARLYBONUS));
        result.leaveTime = cursor.getInt(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_LEAVETIME));
        result.score = cursor.getFloat(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_SCORE));
        if(cursor.getInt(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_CURRENT)) >= 1){
            result.isCurrentJob = true;
        }
        result.trainingDevFund = cursor.getFloat(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_FUNDS));
        result.teleWorkDaysPerWeek = cursor.getInt(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_TELEWORKDAYSPERWEEK));
        return result;
    }

    public Integer deleteJobRecord(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(JobContract.JobEntry.TABLE_NAME,"id = ? ",new String[] { Integer.toString(id) });
    }
}
