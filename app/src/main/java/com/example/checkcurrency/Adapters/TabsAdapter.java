package com.example.checkcurrency.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.checkcurrency.fragments.FragmentContact;
import com.example.checkcurrency.fragments.FragmentCurrencies;


public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public TabsAdapter(FragmentManager fm, int NoofTabs) {
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentCurrencies currencies = new FragmentCurrencies();
                return currencies;
            case 1:
                FragmentContact calculate = new FragmentContact();
                return calculate;
            default:
                return null;
        }
    }
}
