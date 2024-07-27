package edu.gatech.seclass.jobcompare6300;

import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.dataAccessLayer.JobDataModel;
import edu.gatech.seclass.jobcompare6300.model.JobRecord;

public class CurrentJobActivity extends AppCompatActivity {
    public EditText titleET;
    public EditText companyET;
    public EditText locationET;
    public EditText costOfLivingET;
    public EditText yearlySalarETy;
    public EditText yearlyBonusET;
    public EditText fundET;
    public EditText leaveTimeET;
    public EditText daysPerWeekET;
    JobDataModel jobDataModel;
    JobRecord currentJobRecord = new JobRecord();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_job);

        //Take input and save it to a new job object?
        titleET = (EditText) findViewById(R.id.inputTitle);
        companyET = (EditText) findViewById(R.id.inputCompany);
        locationET = (EditText) findViewById(R.id.inputLocation);
        costOfLivingET = (EditText) findViewById(R.id.inputCostOfLiving);
        yearlySalarETy = (EditText) findViewById(R.id.inputYearlySalary);
        yearlyBonusET = (EditText) findViewById(R.id.inputYearlyBonus);
        fundET = (EditText) findViewById(R.id.inputFund);
        leaveTimeET = (EditText) findViewById(R.id.inputLeaveTime);
        daysPerWeekET = (EditText) findViewById(R.id.inputDaysPerWeek);

        jobDataModel = new JobDataModel(this);
        currentJobRecord = jobDataModel.GetCurrentJobRecord();
        viewCurrentJobRecord();
    }

    private void viewCurrentJobRecord(){
        titleET.setText(currentJobRecord.title);
        companyET.setText(currentJobRecord.company);
        locationET.setText(currentJobRecord.location);
        costOfLivingET.setText(String.valueOf(currentJobRecord.costOfLivingIndex));
        yearlySalarETy.setText(String.valueOf(currentJobRecord.yearlySalary));
        yearlyBonusET.setText(String.valueOf(currentJobRecord.yearlyBonus));
        leaveTimeET.setText(String.valueOf(currentJobRecord.leaveTime));
        daysPerWeekET.setText(String.valueOf(currentJobRecord.teleWorkDaysPerWeek));
        fundET.setText(String.valueOf(currentJobRecord.trainingDevFund));
    }

    public void editCurrentJobRecord(View view){
        System.out.println("I am in editCurrentJobRecord");
        boolean titleinit = true;
        boolean companyinit = true;
        boolean locationinit = true;
        boolean costOfLivinginit = true;
        boolean yearlySalaryinit = true;
        boolean yearlyBonusinit = true;
        boolean leaveTimeinit = true;
        boolean daysPerWeekinit = true;
        boolean fundinit = true;

        if(titleET.getText().toString().equals("")){
            titleinit = false;
            titleET.setError("Required Value");
        }
        if(companyET.getText().toString().equals("")){
            companyinit = false;
            companyET.setError("Required Value");
        }
        if(locationET.getText().toString().equals("")){
            locationinit = false;
            locationET.setError("Required Value");
        }
        if(costOfLivingET.getText().toString().equals("")){
            costOfLivinginit = false;
            costOfLivingET.setError("Required Value");
        }
        if(yearlySalarETy.getText().toString().equals("")){
            yearlySalaryinit = false;
            yearlyBonusET.setError("Required Value");
        }
        if(yearlyBonusET.getText().toString().equals("")){
            yearlyBonusinit = false;
            yearlyBonusET.setError("Required Value");
        }
        if(leaveTimeET.getText().toString().equals("")){
            leaveTimeinit = false;
            leaveTimeET.setError("Required Value");
        }
        if(daysPerWeekET.getText().toString().equals("")){
            daysPerWeekinit = false;
            daysPerWeekET.setError("Required Value");
        }
        if(fundET.getText().toString().equals("")){
            fundinit = false;
            fundET.setError("Required Value");
        }

        if(titleinit && companyinit && locationinit && yearlySalaryinit && yearlyBonusinit  && leaveTimeinit  && daysPerWeekinit  && fundinit && costOfLivinginit) {
            currentJobRecord.title = titleET.getText().toString();
            currentJobRecord.company = companyET.getText().toString();
            currentJobRecord.location = locationET.getText().toString();
            currentJobRecord.costOfLivingIndex = Double.parseDouble(costOfLivingET.getText().toString());
            currentJobRecord.yearlySalary = Double.parseDouble(yearlySalarETy.getText().toString());
            currentJobRecord.yearlyBonus = Double.parseDouble(yearlyBonusET.getText().toString());
            currentJobRecord.leaveTime = Integer.parseInt(leaveTimeET.getText().toString());
            currentJobRecord.trainingDevFund = Double.parseDouble(fundET.getText().toString());
            currentJobRecord.teleWorkDaysPerWeek = Integer.parseInt(daysPerWeekET.getText().toString());
            currentJobRecord.isCurrentJob = true;
            boolean result = false;
            if (currentJobRecord.id == 0) {
                result = jobDataModel.Add(currentJobRecord);
            } else {
                result = jobDataModel.Update(currentJobRecord);
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
