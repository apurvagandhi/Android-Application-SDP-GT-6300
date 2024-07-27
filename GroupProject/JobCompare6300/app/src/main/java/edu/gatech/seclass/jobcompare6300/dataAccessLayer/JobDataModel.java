package edu.gatech.seclass.jobcompare6300.dataAccessLayer;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.gatech.seclass.jobcompare6300.model.ComparisonSetting;
import edu.gatech.seclass.jobcompare6300.model.JobRecord;

public class JobDataModel {
    private JobDatabaseHelperModel jobDatabaseHelperModel;
    private SettingDatabaseHelperModel settingDatabaseHelperModel;

    public static final String DATABASE_NAME = "JobCompare.db";

    //Constructor 1
    public JobDataModel(Context context){
        jobDatabaseHelperModel = new JobDatabaseHelperModel(context,DATABASE_NAME);
        settingDatabaseHelperModel = new SettingDatabaseHelperModel(context,"comparisionSetting");

    }

    //Constructor 2
    public JobDataModel(Context context, String databaseName){
        jobDatabaseHelperModel = new JobDatabaseHelperModel(context,databaseName);
        settingDatabaseHelperModel = new SettingDatabaseHelperModel(context,DATABASE_NAME);
    }

    public boolean Add(JobRecord jobRecord){
        boolean result = false;
        jobRecord.score = CalculateScore(jobRecord);
        if(jobRecord.id == 0){
            if(jobRecord.isCurrentJob && GetCurrentJobRecordId() > 0){
                jobRecord.id = GetCurrentJobRecordId();
                result = jobDatabaseHelperModel.updateJobRecord(jobRecord);
            }
            else{
                result = jobDatabaseHelperModel.insertJobRecord(jobRecord);
            }
        }

        return result;
    }

    public boolean Delete(int id){
        boolean result = false;
        int deletedId = jobDatabaseHelperModel.deleteJobRecord(id);
        if(deletedId > 0){
            result = true;
        }
        return result;
    }


    public JobRecord Get(int id){
        return jobDatabaseHelperModel.getJobRecord(id);
    }

    public JobRecord GetCurrentJobRecord(){
        JobRecord jobRecord = Get(GetCurrentJobRecordId());
        jobRecord.isCurrentJob = true;
        return jobRecord;
    }

    public ArrayList<JobRecord> GetAll(){
        ArrayList<JobRecord> result = jobDatabaseHelperModel.getAllJobRecords();
        Collections.sort(result, new Comparator<JobRecord>() {
            @Override
            public int compare(JobRecord t1, JobRecord t2) {
                return (int)((t2.score - t1.score));
            }
        });
        return result;
    }


    public void UpdateAllScores(){
        ArrayList<JobRecord> jobs = GetAll();

        for(int i = 0; i < jobs.size(); i++){
            JobRecord tempJobRecord = jobs.get(i);
            tempJobRecord.score = CalculateScore(tempJobRecord);
            Update(tempJobRecord);
        }
    }

    public boolean Update(JobRecord jobRecord){
        boolean result = false;

        jobRecord.score = CalculateScore(jobRecord);

        if(jobRecord.id > 0){
            if(!(jobRecord.isCurrentJob && GetCurrentJobRecordId() != jobRecord.id)){
                result = jobDatabaseHelperModel.updateJobRecord(jobRecord);
            }
        }

        return result;
    }

    private int GetCurrentJobRecordId(){
        int currentJobId = -1;

        ArrayList<JobRecord> jobs = GetAll();
        for(int i = 0; i < jobs.size(); i++) {
            if(jobs.get(i).isCurrentJob){
                currentJobId = jobs.get(i).id;
            }
        }

        return currentJobId;
    }

    private double CalculateScore(JobRecord jobRecord) {
        double score = 0.0;
        ComparisonSetting weights = settingDatabaseHelperModel.getSettings();
        double AYS = jobRecord.yearlySalary * (100/ jobRecord.costOfLivingIndex);
        double AYB = jobRecord.yearlyBonus * (100/ jobRecord.costOfLivingIndex);
        int totalWeight = weights.yearlyBonusWeight + weights.yearlySalaryWeight + weights.leaveTimeWeight + weights.trainingDevFundWeight + weights.teleworkDaysPerWeekWeight;
        score = AYS * weights.yearlySalaryWeight / totalWeight;
        score += AYB * weights.yearlyBonusWeight / totalWeight;
        score += jobRecord.trainingDevFund * weights.trainingDevFundWeight / totalWeight;
        score += (jobRecord.leaveTime * AYS /260) * weights.leaveTimeWeight / totalWeight;
        score -= ((260 - 52 * jobRecord.teleWorkDaysPerWeek) * (AYS/260) / 8) * weights.teleworkDaysPerWeekWeight / totalWeight ;

        return score;
    }

}
