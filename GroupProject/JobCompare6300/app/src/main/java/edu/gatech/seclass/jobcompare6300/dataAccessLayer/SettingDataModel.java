package edu.gatech.seclass.jobcompare6300.dataAccessLayer;

import android.content.Context;

import java.util.ArrayList;

import edu.gatech.seclass.jobcompare6300.ComparisonSettingActivity;
import edu.gatech.seclass.jobcompare6300.model.ComparisonSetting;
import edu.gatech.seclass.jobcompare6300.model.JobRecord;

public class SettingDataModel {

    private SettingDatabaseHelperModel settingDatabaseHelperModel;
    public static final String DATABASE_NAME = "comparisionSetting.db";
    private SettingDataModel settingDataModel;
    private JobDataModel jobDataModel;

    //Constructor 1
    public SettingDataModel(Context context){
        settingDatabaseHelperModel = new SettingDatabaseHelperModel(context,DATABASE_NAME);
    }

    //Constructor 2
    public SettingDataModel (Context context, String databaseName){
        settingDatabaseHelperModel = new SettingDatabaseHelperModel(context,databaseName);
    }

    public boolean Add(ComparisonSetting comparisonSetting){
        boolean result = false;
        if(comparisonSetting.id == 0){
            result = settingDatabaseHelperModel.insertSetting(comparisonSetting);
        }  else {
            result = settingDatabaseHelperModel.updateSetting(comparisonSetting);
        }
        return result;
    }

    public boolean Update(ComparisonSetting comparisonSetting){
        boolean result;
//        ComparisonSetting currentSetting = settingDatabaseHelperModel.getSettings();
        if(comparisonSetting.id > 0){
            comparisonSetting.id = comparisonSetting.id;
            result = settingDatabaseHelperModel.updateSetting(comparisonSetting);
        }
        else{
            result = settingDatabaseHelperModel.insertSetting(comparisonSetting);
        }
        if(result){
            jobDataModel.UpdateAllScores();
        }
        return result;
    }

    public ComparisonSetting Get(){
        return settingDatabaseHelperModel.getSettings();
    }



}
