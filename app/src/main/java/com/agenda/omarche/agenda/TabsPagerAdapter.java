package com.agenda.omarche.agenda;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

/**
 * Created by Omar Che on 21/10/2015.
 */
public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0: return new CrearContactoFragment();
            case 1: return new ListaContactosFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
