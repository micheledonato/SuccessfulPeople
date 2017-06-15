package com.devmicheledonato.successfulpeople;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class StepFragmentPageAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    private MainActivity mContext;
    private ViewPager mViewPager;

    private int stepNum;

    public StepFragmentPageAdapter(Activity activity, FragmentManager fm, ViewPager pager, int stepNum){
        super(fm);
        // I need the context to call MainActivity's method
        mContext = (MainActivity) activity;
        // The ViewPager into the activity_main layout
        mViewPager = pager;
        // Set a FragmentPagerAdapter to provide fragments for this ViewPager
        mViewPager.setAdapter(this);
        // Add a listener that will be invoked whenever the page changes or is scrolled
        mViewPager.addOnPageChangeListener(this);
        this.stepNum = stepNum;
    }

    @Override
    public int getCount() {
        // Return number of fragments
        return stepNum;
    }

    @Override
    public Fragment getItem(int position) {
        // Create a new instance of StepFragment by passing the page number
        // And return the object to the super class
        return new StepFragment().newInstance(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // When the page is scrolled and it's the last one, call updateFab
        if(position == stepNum) {
            mContext.updateFab(positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        // When the page is selected, update the visibility of buttons and update the dots
        mContext.updateButtons();
        mContext.selectPosition(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}