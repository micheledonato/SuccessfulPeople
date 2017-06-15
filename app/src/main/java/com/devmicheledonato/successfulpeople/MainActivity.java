package com.devmicheledonato.successfulpeople;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private StepFragmentPageAdapter mPageAdapter;
    private ViewPager mViewPager;

    protected View prevButton;
    protected View nextButton;
    protected View doneButton;
    protected ViewGroup linearIndicator;
    protected FloatingActionButton email_fab;

    // Page numbers (fragments)
    private int stepNum = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email_fab = (FloatingActionButton) findViewById(R.id.fab);
        if (email_fab != null) {
            email_fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // If click on button, send an email
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:" + getResources().getString(R.string.email)));
                    intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                    startActivity(intent);
                }
            });
        }

        // Find the ViewPager into the layout
        mViewPager = (ViewPager) findViewById(R.id.pager);
        // Create a PageAdapter by passing the FragmentManager, the ViewPager and the page numbers
        mPageAdapter = new StepFragmentPageAdapter(this, getSupportFragmentManager(), mViewPager, stepNum);
        mPageAdapter.notifyDataSetChanged(); // Required to update stepNum within StepFragmentPageAdapter

        defaultIndicatorInit();

        prevButton = findViewById(R.id.prevButton);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If click on button, turn back to the previous fragment
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
                updateButtons();
            }
        });
        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If click on button, go to the next fragment
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                updateButtons();
            }
        });
        doneButton = findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Exit from application
                finish();
            }
        });

        updateButtons();
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            // If it's the first fragment, call the parent's method
            super.onBackPressed();
        } else {
            // If back button is pressed, turn back to the previous fragment
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }

    // Update the visibility of buttons
    protected void updateButtons() {
        if (mViewPager.getCurrentItem() == 0) {
            prevButton.setVisibility(View.GONE);
            nextButton.setVisibility(View.VISIBLE);
            doneButton.setVisibility(View.GONE);
            email_fab.setVisibility(View.GONE);
        } else if (mViewPager.getCurrentItem() > 0 && mViewPager.getCurrentItem() < (stepNum - 1)) {
            prevButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.VISIBLE);
            doneButton.setVisibility(View.GONE);
            email_fab.setVisibility(View.GONE);
        } else if (mViewPager.getCurrentItem() == (stepNum - 1)) {
            prevButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.GONE);
            doneButton.setVisibility(View.VISIBLE);
            email_fab.setVisibility(View.VISIBLE);
        }
    }

    // Update the position of fab button during page scrolling
    protected void updateFab(int offset) {
        email_fab.offsetLeftAndRight(offset);
    }

    // Initialization of the indicators
    private void defaultIndicatorInit() {
        linearIndicator = (ViewGroup) findViewById(R.id.linearIndicator);
        // Create a number of dot as many as the fragments
        for (int i = 0; i < stepNum; i++) {
            ImageView dot = new ImageView(this);
            dot.setImageResource(R.drawable.indicator_dot_grey);
            // Set width = 0, height = WRAP_CONTENT e weight = 1
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            dot.setLayoutParams(params);
            // Add the created dot to layout
            linearIndicator.addView(dot);
        }
        // Start from first fragment, so select the zero dot fragment
        selectPosition(0);
    }

    // Select the dot according to position
    public void selectPosition(int position) {
        ImageView dot;
        for (int i = 0; i < stepNum; i++) {
            dot = (ImageView) linearIndicator.getChildAt(i);
            if (i != position) {
                // Other dots are grey
                dot.setImageResource(R.drawable.indicator_dot_grey);
            } else {
                // The selected dot are white
                dot.setImageResource(R.drawable.indicator_dot_white);
            }
        }
    }
}