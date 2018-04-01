package com.adib.adibfakhrimuhtadi_1202154192_modul6;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adib on 1/04/2018.
 */

public class Adapter3 extends FragmentPagerAdapter {

    private final List<Fragment> mFragList = new ArrayList<>();
    private final List<String> mFragTitList = new ArrayList<>();
    public Adapter3(FragmentManager fm) {
        super(fm);
    }
    public void addFragment(Fragment fragment,  String Tittle){
    mFragList.add(fragment);
    mFragTitList.add(Tittle);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragTitList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragList.get(position);
    }

    @Override
    public int getCount() {
        return mFragList.size();
    }
}
