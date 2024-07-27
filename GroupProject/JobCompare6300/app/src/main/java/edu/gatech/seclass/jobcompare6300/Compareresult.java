package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.dataAccessLayer.JobDataModel;
import edu.gatech.seclass.jobcompare6300.model.JobRecord;

public class Compareresult extends AppCompatActivity {
    public TextView titleET1;
    public TextView companyET1;
    public TextView locationET1;
    public TextView costOfLivingET1;
    public TextView yearlySalarETy1;
    public TextView yearlyBonusET1;
    public TextView fundET1;
    public TextView leaveTimeET1;
    public TextView daysPerWeekET1;
    public TextView titleET2;
    public TextView companyET2;
    public TextView locationET2;
    public TextView costOfLivingET2;
    public TextView yearlySalarETy2;
    public TextView yearlyBonusET2;
    public TextView fundET2;
    public TextView leaveTimeET2;
    public TextView daysPerWeekET2;
    JobDataModel jobDataModel;
    JobRecord jobOfferRecord1 = new JobRecord();
    JobRecord jobOfferRecord2 = new JobRecord();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_result);

        titleET1 = (TextView) findViewById(R.id.r2c2);
        companyET1 = (TextView) findViewById(R.id.r1c2);
        locationET1 = (TextView) findViewById(R.id.r3c2);
        costOfLivingET1 = (TextView) findViewById(R.id.r4c2);
        yearlySalarETy1 = (TextView) findViewById(R.id.r5c2);
        yearlyBonusET1 = (TextView) findViewById(R.id.r6c2);
        fundET1 = (TextView) findViewById(R.id.r7c2);
        leaveTimeET1 = (TextView) findViewById(R.id.r8c2);
        daysPerWeekET1 = (TextView) findViewById(R.id.r9c2);

        titleET2 = (TextView) findViewById(R.id.r2c3);
        companyET2 = (TextView) findViewById(R.id.r1c3);
        locationET2 = (TextView) findViewById(R.id.r3c3);
        costOfLivingET2 = (TextView) findViewById(R.id.r4c3);
        yearlySalarETy2 = (TextView) findViewById(R.id.r5c3);
        yearlyBonusET2 = (TextView) findViewById(R.id.r6c3);
        fundET2 = (TextView) findViewById(R.id.r7c3);
        leaveTimeET2 = (TextView) findViewById(R.id.r8c3);
        daysPerWeekET2 = (TextView) findViewById(R.id.r9c3);

        jobDataModel = new JobDataModel(this);
        jobOfferRecord1 = new JobRecord();
        jobOfferRecord2 = new JobRecord();
        Bundle b = getIntent().getExtras();
        if(b!= null){
            int id1 = b.getInt("id1");
            int id2 = b.getInt("id2");
            if(id1 > 0 & id2>0){
                jobOfferRecord1 = jobDataModel.Get(id1);
                jobOfferRecord2 = jobDataModel.Get(id2);
            }
        }
        viewCurrentJobRecord();
    }

    private void viewCurrentJobRecord(){
        //view
        titleET1.setText(jobOfferRecord1.title);
        companyET1.setText(jobOfferRecord1.company);
        locationET1.setText(jobOfferRecord1.location);
        costOfLivingET1.setText(String.valueOf(jobOfferRecord1.costOfLivingIndex));
        yearlySalarETy1.setText(String.valueOf(jobOfferRecord1.yearlySalary));
        yearlyBonusET1.setText(String.valueOf(jobOfferRecord1.yearlyBonus));
        leaveTimeET1.setText(String.valueOf(jobOfferRecord1.leaveTime));
        daysPerWeekET1.setText(String.valueOf(jobOfferRecord1.teleWorkDaysPerWeek));
        fundET1.setText(String.valueOf(jobOfferRecord1.trainingDevFund));

        titleET2.setText(jobOfferRecord2.title);
        companyET2.setText(jobOfferRecord2.company);
        locationET2.setText(jobOfferRecord2.location);
        costOfLivingET2.setText(String.valueOf(jobOfferRecord2.costOfLivingIndex));
        yearlySalarETy2.setText(String.valueOf(jobOfferRecord2.yearlySalary));
        yearlyBonusET2.setText(String.valueOf(jobOfferRecord2.yearlyBonus));
        leaveTimeET2.setText(String.valueOf(jobOfferRecord2.leaveTime));
        daysPerWeekET2.setText(String.valueOf(jobOfferRecord2.teleWorkDaysPerWeek));
        fundET2.setText(String.valueOf(jobOfferRecord2.trainingDevFund));
    }
    public void AnotherCompare(View view) {

        Intent i = new Intent(getApplicationContext(), Compare.class);
        startActivityForResult(i, 1);
    }



    public void returnToMainMenu(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(i, 1);
    }

}
