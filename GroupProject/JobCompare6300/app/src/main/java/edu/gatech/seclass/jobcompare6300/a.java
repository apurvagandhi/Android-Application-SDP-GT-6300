package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.dataAccessLayer.JobDataModel;
import edu.gatech.seclass.jobcompare6300.dataAccessLayer.SettingDataModel;
import edu.gatech.seclass.jobcompare6300.model.JobRecord;
import edu.gatech.seclass.jobcompare6300.model.ComparisonSetting;
public class a extends AppCompatActivity {
    private ListView jobOffersView;
    ArrayList<JobRecord> joblist = new ArrayList<>();
    JobDataModel dataModel;


    int selectedPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare);
        jobOffersView = (ListView) findViewById(R.id.jobOfferslist);
        jobOffersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPosition = i;
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dataModel = new JobDataModel(this);
        refreshJobList();
    }

    private void refreshJobList(){
        List<String> listItems = new ArrayList<String>();
        //joblist populated here
        ArrayList<JobRecord> tempJobList = dataModel.GetAll();
        SettingDataModel setting = new SettingDataModel(this);
        ComparisonSetting comparisonSetting = setting.Get();


        int sum_weight = comparisonSetting.yearlySalaryWeight + comparisonSetting.yearlyBonusWeight +
                comparisonSetting.trainingDevFundWeight + comparisonSetting.leaveTimeWeight +
                comparisonSetting.teleworkDaysPerWeekWeight;


        joblist = new ArrayList<>();
        for (int i = 0; i<tempJobList.size(); i++) {
            double AYS = tempJobList.get(i).yearlySalary *100 / tempJobList.get(i).costOfLivingIndex;
            double AYB = tempJobList.get(i).yearlyBonus *100 / tempJobList.get(i).costOfLivingIndex;
            double TDF = tempJobList.get(i).trainingDevFund;
            double LT = tempJobList.get(i).leaveTime;
            double RWT = tempJobList.get(i).teleWorkDaysPerWeek;
            double AYS_weight  = comparisonSetting.yearlySalaryWeight/sum_weight;
            double AYB_weight  = comparisonSetting.yearlyBonusWeight/sum_weight;
            double TDF_weight  = comparisonSetting.trainingDevFundWeight/sum_weight;
            double LT_weight  = comparisonSetting.leaveTimeWeight/sum_weight;
            double RWT_weight  = comparisonSetting.teleworkDaysPerWeekWeight/sum_weight;
            tempJobList.get(i).score = AYS_weight * AYS + AYB_weight * AYB + TDF_weight * TDF + LT_weight * (LT * AYS / 260) - RWT_weight * ((260 - 52 * RWT) * (AYS / 260) / 8);

            joblist.add(tempJobList.get(i));
            listItems.add(tempJobList.get(i).title + " at " + tempJobList.get(i).company+ tempJobList.get(i).score);

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.compare, R.id.jobTextView, listItems);
        selectedPosition = -1;
        jobOffersView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_offer, menu);
        return true;
    }



    public void returnToMainMenu(MenuItem item) {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        refreshJobList();
    }
}
