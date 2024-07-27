package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.dataAccessLayer.JobDataModel;
import edu.gatech.seclass.jobcompare6300.dataAccessLayer.SettingDataModel;
import edu.gatech.seclass.jobcompare6300.model.ComparisonSetting;
import edu.gatech.seclass.jobcompare6300.model.JobRecord;

public class ComparisonSettingActivity extends AppCompatActivity {
    public EditText yearlySalaryWeightET;
    public EditText yearlyBonusWeightET;
    public EditText trainingDevFundWeightET;
    public EditText leaveTimeWeightET;
    public EditText daysPerWeekWeightET;
    SettingDataModel settingDataModel;
    ComparisonSetting comparisonSetting = new ComparisonSetting();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparison_set);

        //Take input and save it to a new job object?
        yearlySalaryWeightET = (EditText) findViewById(R.id.yearlySalaryWeight);
        yearlyBonusWeightET = (EditText) findViewById(R.id.yearlyBonusWeight);
        trainingDevFundWeightET = (EditText) findViewById(R.id.trainingDevFundWeight);
        leaveTimeWeightET = (EditText) findViewById(R.id.leaveTimeWeight);
        daysPerWeekWeightET = (EditText) findViewById(R.id.daysPerWeekWeight);

        settingDataModel = new SettingDataModel(this);
        comparisonSetting = settingDataModel.Get();
        viewCurrentComparisionSetting();
    }

    private void viewCurrentComparisionSetting(){
        yearlySalaryWeightET.setText(String.valueOf(comparisonSetting.yearlySalaryWeight));
        yearlyBonusWeightET.setText(String.valueOf(comparisonSetting.yearlyBonusWeight));
        trainingDevFundWeightET.setText(String.valueOf(comparisonSetting.trainingDevFundWeight));
        leaveTimeWeightET.setText(String.valueOf(comparisonSetting.leaveTimeWeight));
        daysPerWeekWeightET.setText(String.valueOf(comparisonSetting.teleworkDaysPerWeekWeight));
    }

    public void editCurrentComparisionSetting(View view){
        System.out.println("I am in editCurrentComparisionSetting");
        boolean yearlySalaryWeightinit = true;
        boolean yearlyBonusWeightinit = true;
        boolean trainingDevFundWeightinit = true;
        boolean leaveTimeWeightinit = true;
        boolean daysPerWeekWeightinit = true;

        if(yearlySalaryWeightET.getText().toString().equals("")){
            yearlySalaryWeightinit = false;
            yearlySalaryWeightET.setError("Required Value");
        }
        if(yearlyBonusWeightET.getText().toString().equals("")){
            yearlyBonusWeightinit = false;
            yearlyBonusWeightET.setError("Required Value");
        }
        if(trainingDevFundWeightET.getText().toString().equals("")){
            trainingDevFundWeightinit = false;
            trainingDevFundWeightET.setError("Required Value");
        }
        if(leaveTimeWeightET.getText().toString().equals("")){
            leaveTimeWeightinit = false;
            leaveTimeWeightET.setError("Required Value");
        }
        if(daysPerWeekWeightET.getText().toString().equals("")) {
            daysPerWeekWeightinit = false;
            daysPerWeekWeightET.setError("Required Value");
        }

        if(yearlySalaryWeightinit && yearlyBonusWeightinit && trainingDevFundWeightinit && leaveTimeWeightinit && daysPerWeekWeightinit) {
            comparisonSetting.yearlySalaryWeight = Integer.parseInt(yearlySalaryWeightET.getText().toString());
            comparisonSetting.yearlyBonusWeight = Integer.parseInt(yearlyBonusWeightET.getText().toString());
            comparisonSetting.trainingDevFundWeight = Integer.parseInt(trainingDevFundWeightET.getText().toString());
            comparisonSetting.leaveTimeWeight = Integer.parseInt(leaveTimeWeightET.getText().toString());
            comparisonSetting.teleworkDaysPerWeekWeight = Integer.parseInt(daysPerWeekWeightET.getText().toString());

            boolean result = false;
            if (comparisonSetting.id == 0) {
                result = settingDataModel.Add(comparisonSetting);
                System.out.println("If I reach this line during debug, I need to implement add method in settingDataModel");
            } else {
                result = settingDataModel.Update(comparisonSetting);
            }
            System.out.println("I am in middle of editCurrentJobRecord");

            finish();
        }
        System.out.println("I am in end of editCurrentJobRecord");

    }

    public void returnToMainMenu(View view) {
        finish();
    }

}
