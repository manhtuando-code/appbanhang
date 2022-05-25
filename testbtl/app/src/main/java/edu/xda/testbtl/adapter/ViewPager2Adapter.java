package edu.xda.testbtl.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;

import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import edu.xda.testbtl.activity.MainActivity;
import edu.xda.testbtl.fragment.AccountFragment;
import edu.xda.testbtl.fragment.HomeFragment;
import edu.xda.testbtl.fragment.SearchFragment;
import edu.xda.testbtl.fragment.ShoppingcartFragment;

public class ViewPager2Adapter extends FragmentStatePagerAdapter {
    public ViewPager2Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        switch (position) {
//            case 0:
//                return new HomeFragment();
//            case 1:
//                return new SearchFragment();
//            case 2:
//                return new ShoppingcartFragment();
//            case 3:
//                return new AccountFragment();
//            default:
//                return new HomeFragment();
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return 4;
//    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new ShoppingcartFragment();
            case 3:
                return new AccountFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

}
