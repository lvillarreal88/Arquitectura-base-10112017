package com.cosmo.arquitecturamvpbase.views.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.views.fragments.ProductFragment;
import com.cosmo.arquitecturamvpbase.views.fragments.ProfileFragment;

/**
 * Created by leidyzulu on 14/10/17.
 */

public class DashsBoardTabsAdapter extends FragmentStatePagerAdapter {



    public DashsBoardTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ProductFragment();
            case 1:
                return new ProductFragment();
            case 2:
                return  new ProfileFragment();
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return Constants.TITLE_PRODUCT;
            case 1:
                return Constants.TITLE_CONTACT;
            case 2:
                return Constants.TITLE_PROFILE;
            default:
                return Constants.EMPTY;
        }
    }
}
