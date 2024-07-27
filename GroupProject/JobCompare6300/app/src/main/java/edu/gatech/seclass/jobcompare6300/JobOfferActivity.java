package edu.gatech.seclass.jobcompare6300;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.dataAccessLayer.JobDataModel;
import edu.gatech.seclass.jobcompare6300.model.JobRecord;

public class JobOfferActivity extends AppCompatActivity {
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
    JobRecord jobOfferRecord = new JobRecord();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_offers);

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
        jobOfferRecord = new JobRecord();

        Bundle b = getIntent().getExtras();
        if(b!= null){
            int id = b.getInt("id");
            if(id > 0){
                jobOfferRecord = jobDataModel.Get(id);
            }
        }
        viewCurrentJobRecord();
    }

    private void viewCurrentJobRecord(){
        //view
        titleET.setText(jobOfferRecord.title);
        companyET.setText(jobOfferRecord.company);
        locationET.setText(jobOfferRecord.location);
        costOfLivingET.setText(String.valueOf(jobOfferRecord.costOfLivingIndex));
        yearlySalarETy.setText(String.valueOf(jobOfferRecord.yearlySalary));
        yearlyBonusET.setText(String.valueOf(jobOfferRecord.yearlyBonus));
        leaveTimeET.setText(String.valueOf(jobOfferRecord.leaveTime));
        daysPerWeekET.setText(String.valueOf(jobOfferRecord.teleWorkDaysPerWeek));
        fundET.setText(String.valueOf(jobOfferRecord.trainingDevFund));
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
            jobOfferRecord.title = titleET.getText().toString();
            jobOfferRecord.company = companyET.getText().toString();
            jobOfferRecord.location = locationET.getText().toString();
            jobOfferRecord.costOfLivingIndex = Double.parseDouble(costOfLivingET.getText().toString());
            jobOfferRecord.yearlySalary = Double.parseDouble(yearlySalarETy.getText().toString());
            jobOfferRecord.yearlyBonus = Double.parseDouble(yearlyBonusET.getText().toString());
            jobOfferRecord.leaveTime = Integer.parseInt(leaveTimeET.getText().toString());
            jobOfferRecord.trainingDevFund = Double.parseDouble(fundET.getText().toString());
            jobOfferRecord.teleWorkDaysPerWeek = Integer.parseInt(daysPerWeekET.getText().toString());
//            jobOfferRecord.isCurrentJob = true;
            boolean result = false;
            if (jobOfferRecord.id == 0) {
                result = jobDataModel.Add(jobOfferRecord);
            } else {
                result = jobDataModel.Update(jobOfferRecord);
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
