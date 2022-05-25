package edu.xda.testbtl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import edu.xda.testbtl.R;

public class SizeChartActivity extends AppCompatActivity {
    Toolbar mToolbarsizechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_chart);
        mToolbarsizechart = (Toolbar) findViewById(R.id.toolbarsizechart);
        setSupportActionBar(mToolbarsizechart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarsizechart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}