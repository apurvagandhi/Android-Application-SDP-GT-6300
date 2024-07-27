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
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.dataAccessLayer.JobDataModel;
import edu.gatech.seclass.jobcompare6300.model.JobRecord;

public class JobOfferListActivity extends AppCompatActivity {
    private ListView jobOffersView;
    ArrayList<JobRecord> joblist = new ArrayList<>();
    JobDataModel dataModel;
    int selectedPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_offers_list);
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
        joblist = new ArrayList<>();
        for (int i = 0; i<tempJobList.size(); i++) {
            if(tempJobList.get(i).isCurrentJob == false){
                joblist.add(tempJobList.get(i));
                listItems.add(tempJobList.get(i).title + " at " + tempJobList.get(i).company);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.job_offers_list, R.id.jobTextView, listItems);
        selectedPosition = -1;
        jobOffersView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_offer, menu);
        return true;
    }

    public void addJob(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), JobOfferActivity.class);
        startActivityForResult(i, 1);
    }

    public void editJob(MenuItem item) {
        if(selectedPosition >= 0) {
            JobRecord jobToEdit = joblist.get(selectedPosition);
            Intent i = new Intent(getApplicationContext(), JobOfferActivity.class);
            //pass in id
            Bundle b = new Bundle();
            b.putInt("id", jobToEdit.id);
            i.putExtras(b);
            //open activity
            startActivityForResult(i, 1);
        }
    }

    public void deleteJob(MenuItem item) {
        if(selectedPosition >= 0){
            JobRecord jobToDelete = joblist.get(selectedPosition);
            boolean result = dataModel.Delete(jobToDelete.id);

            if(result){
                refreshJobList();
            }
            else{
            }
        }
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
