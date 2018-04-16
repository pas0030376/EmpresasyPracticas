package com.example.empresasypracticas;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Vicky on 16/04/2018.
 */

public class PagerEmpresas extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;
    private String[] tabTitles = new String[]{"Informàtica i comunicacions","Comerç i màrqueting", "Hoteleria i turisme"};

    public PagerEmpresas(android.support.v4.app.FragmentManager fm, int tabCount) {
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
                FamInformatica tab1 = new FamInformatica();
                return tab1;
            case 1:
                FamComercio tab2 = new FamComercio();
                return tab2;
            case 2:
                FamHosteleria tab3 = new FamHosteleria();
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
