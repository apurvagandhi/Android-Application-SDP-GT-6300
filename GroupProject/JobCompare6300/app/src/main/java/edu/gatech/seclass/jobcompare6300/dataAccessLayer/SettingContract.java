package edu.gatech.seclass.jobcompare6300.dataAccessLayer;

import android.provider.BaseColumns;

public class SettingContract {
    private SettingContract() {

    }

    public static class SettingEntry implements BaseColumns {
        public static final String TABLE_NAME = "comparisionSetting";
        public static final String COLUMN_NAME_YEARLY_SALARY_WEIGHT = "yearlySalaryWeight";
        public static final String COLUMN_NAME_YEARLY_BONUS_WEIGHT = "yearlyBonusWeight";
        public static final String COLUMN_NAME_TRAINING_DEV_FUND = "trainingDevFundWeight";
        public static final String COLUMN_NAME_TELEWORK_DAYS_PER_WEEK_WEIGHT = "teleworkDaysPerWeekWeight";
        public static final String COLUMN_NAME_LEAVE_TIME_WEIGHT = "leaveTimeWeight";

    }
}
