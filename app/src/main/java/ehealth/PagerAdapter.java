package ehealth;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ehealth.sensor_fragments.FragmentAirflow;
import ehealth.sensor_fragments.FragmentECG;
import ehealth.sensor_fragments.FragmentEMG;
import ehealth.sensor_fragments.FragmentGSR;
import ehealth.sensor_fragments.FragmentTemperature;

/**
 * Created by beslimir on 16.01.18..
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragmentTemperature tab1 = new FragmentTemperature();
                return tab1;
            case 1:
                FragmentECG tab3 = new FragmentECG();
                return tab3;
            case 2:
                FragmentEMG tab4 = new FragmentEMG();
                return tab4;
            case 3:
                FragmentAirflow tab5 = new FragmentAirflow();
                return tab5;
            case 4:
                FragmentGSR tab6 = new FragmentGSR();
                return tab6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
