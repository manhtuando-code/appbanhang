package edu.xda.testbtl.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;

import edu.xda.testbtl.R;
import edu.xda.testbtl.adapter.ViewPager2Adapter;
import edu.xda.testbtl.model.gio_hang;
import edu.xda.testbtl.more.CheckConnection;
import edu.xda.testbtl.more.CustomViewPager;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView mBottomNavigationView;
    CustomViewPager mViewPager;
    public static ArrayList<gio_hang> arrayListGio_hang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            setupviewpager();
        } else  {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
        }


    }

    private void setupviewpager() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.page_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.page_search:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.page_shopping_cart:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.page_account:
                        mViewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    private void anhxa() {
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setPagingEnabled(false);
        ViewPager2Adapter adapter = new ViewPager2Adapter(getSupportFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(adapter);

        if (arrayListGio_hang != null){

        }else {
            arrayListGio_hang = new ArrayList<>();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.page_home:
                break;
            case R.id.page_search:

                break;
            case R.id.page_shopping_cart:

                break;
            case R.id.page_account:

                break;
        }
        return true;
    }
}