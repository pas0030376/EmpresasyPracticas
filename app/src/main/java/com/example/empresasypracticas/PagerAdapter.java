package com.example.empresasypracticas;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Vicky on 15/03/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;
    private String[] tabTitles = new String[]{"Todos","Practicas en curso", "Practicas terminadas"};

    public PagerAdapter(android.support.v4.app.FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                ListAlumnos1 tab1 = new ListAlumnos1();
                return tab1;
            case 1:
                ListAlumnos2 tab2 = new ListAlumnos2();
                return tab2;
            case 2:
                ListAlumnos3 tab3 = new ListAlumnos3();
                return tab3;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
    

}
