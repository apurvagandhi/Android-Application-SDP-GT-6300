package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.gatech.seclass.jobcompare6300.dataAccessLayer.JobDataModel;

public class MainActivity extends AppCompatActivity {
    private Button viewCurrentJobBtn;
    private Button viewJobOffersBtn;
    private Button listJobOfferBtn;
    private Button comparisonSettingBtn;
    private Button compareJobsBtn;
    public  JobDataModel jobDataModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        viewCurrentJobBtn = (Button) findViewById(R.id.viewCurrentJobButton);
        viewJobOffersBtn = (Button) findViewById(R.id.viewJobOfferButton);
        listJobOfferBtn = (Button) findViewById(R.id.listJobOfferButton);
        comparisonSettingBtn = (Button) findViewById(R.id.comparisonSettingButton);
        compareJobsBtn = (Button) findViewById(R.id.compareJobButton);
        jobDataModel = new JobDataModel(this);
        if(jobDataModel.GetAll().size() <= 1) {
            compareJobsBtn.setText("Must add jobs to compare");
        }  else {
            compareJobsBtn.setText("Compare Job Offers");
        }
        setBtnViewCurrentJob();
        setBtnViewJobOffers();
        setBtnListJobOffers();
        setBtnComparisionSetting();
        setBtncompareJobs();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    void setBtnViewCurrentJob() {
        viewCurrentJobBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CurrentJobActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    void setBtnViewJobOffers() {
        viewJobOffersBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), JobOfferActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }
    private void setBtnListJobOffers() {
        listJobOfferBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), JobOfferListActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    private void setBtnComparisionSetting() {
        comparisonSettingBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Clicked on Comparision Setting Button");
                Intent i = new Intent(getApplicationContext(), ComparisonSettingActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    private void setBtncompareJobs() {
        compareJobsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Compare.class);
                startActivityForResult(i, 1);
            }
        });
    }
}