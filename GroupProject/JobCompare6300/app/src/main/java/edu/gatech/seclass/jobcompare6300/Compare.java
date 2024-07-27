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
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.dataAccessLayer.JobDataModel;
import edu.gatech.seclass.jobcompare6300.dataAccessLayer.SettingDataModel;
import edu.gatech.seclass.jobcompare6300.model.JobRecord;
import edu.gatech.seclass.jobcompare6300.model.ComparisonSetting;
public class Compare extends AppCompatActivity {
    private ListView jobOffersView;
    ArrayList<JobRecord> joblist = new ArrayList<>();
    JobDataModel dataModel;


    int[] selectedPositions = new int[]{-1, -1};
    private int firstjob = -1;
    private int secondjob = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare);
        jobOffersView = (ListView) findViewById(R.id.jobScorelist);
        jobOffersView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                if (firstjob == -1) {
                    firstjob = i;
                    Toast.makeText(Compare.this, "First job is selected", Toast.LENGTH_SHORT).show();
                } else if (secondjob == -1 && i != firstjob) {
                    secondjob = i;
                    Toast.makeText(Compare.this, "Second job is selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Compare.this, "There are already two selections", Toast.LENGTH_LONG).show();
                }
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
        dataModel.UpdateAllScores();
        ArrayList<JobRecord> tempJobList = dataModel.GetAll();
        SettingDataModel setting = new SettingDataModel(this);
        ComparisonSetting comparisonSetting = setting.Get();


        int sum_weight = comparisonSetting.yearlySalaryWeight + comparisonSetting.yearlyBonusWeight +
                comparisonSetting.trainingDevFundWeight + comparisonSetting.leaveTimeWeight +
                comparisonSetting.teleworkDaysPerWeekWeight;


        joblist = new ArrayList<>();
        for (int i = 0; i<tempJobList.size(); i++) {

            joblist.add(tempJobList.get(i));
            listItems.add(tempJobList.get(i).title + " Company: " + tempJobList.get(i).company +" Score:  "+tempJobList.get(i).score);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.compare, R.id.jobTextView, listItems);
        selectedPositions[0] = -1;
        selectedPositions[1] = -1;
        jobOffersView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_compare, menu);
        return true;
    }

    public void CompareTwoJob(MenuItem item) {
        if (firstjob >= 0 & secondjob >= 0) {
            JobRecord job1 = joblist.get(firstjob);
            JobRecord job2 = joblist.get(secondjob);

            Intent i = new Intent(getApplicationContext(), Compareresult.class);
            //pass in id
            Bundle b = new Bundle();
            b.putInt("id1", job1.id);
            b.putInt("id2", job2.id);

            i.putExtras(b);
            //open activity
            startActivityForResult(i, 1);



        }
    }



    public void returnToMainMenu(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        refreshJobList();
    }
}
