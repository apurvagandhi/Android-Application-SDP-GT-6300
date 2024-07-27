package edu.gatech.seclass.jobcompare6300.dataAccessLayer;
import android.provider.BaseColumns;

public class JobContract {
    private JobContract() {

    }

    public static class JobEntry implements BaseColumns {
        public static final String TABLE_NAME = "jobRecords";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_COMPANY = "company";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_COSTOFLIVINGINDEX = "costOfLivingIndex";
        public static final String COLUMN_NAME_YEARLYSALARY = "yearlySalary";
        public static final String COLUMN_NAME_YEARLYBONUS = "yearlyBonus";
        public static final String COLUMN_NAME_LEAVETIME = "leaveTime";
        public static final String COLUMN_NAME_SCORE = "score";
        public static final String COLUMN_NAME_CURRENT = "isCurrentJob";
        public static final String COLUMN_NAME_FUNDS = "funds";
        public static final String COLUMN_NAME_TELEWORKDAYSPERWEEK = "teleWorkDaysPerWeek";
    }
}
