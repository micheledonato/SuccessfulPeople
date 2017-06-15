package com.devmicheledonato.successfulpeople;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class StepFragment extends Fragment {

    private static final String POSITION = "position";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String BACKGROUND = "background";

    private int mPosition;
    private int mTitle;
    private int mDescription;
    private int mImage;
    private int mBackColor;

    private TextView textTitle;
    private TextView textDescription;
    private ImageView imageCenter;

    public StepFragment() {
        // Required empty public constructor
    }

    public static StepFragment newInstance(int position) {
        StepFragment fragment = new StepFragment();
        // Create a new Bundle
        Bundle args = new Bundle();
        // Put in the bundle the position (page number)
        args.putInt(POSITION, position);
        // And according to position, put in the bundle the right id resources
        switch (position) {
            case 0:
                args.putInt(TITLE, R.string.passionTitle);
                args.putInt(DESCRIPTION, R.string.passionDesc);
                args.putInt(IMAGE, R.drawable.passion);
                args.putInt(BACKGROUND, R.color.red);
                break;
            case 1:
                args.putInt(TITLE, R.string.goalsTitle);
                args.putInt(DESCRIPTION, R.string.goalsDesc);
                args.putInt(IMAGE, R.drawable.goals);
                args.putInt(BACKGROUND, R.color.green);
                break;
            case 2:
                args.putInt(TITLE, R.string.ideasTitle);
                args.putInt(DESCRIPTION, R.string.ideasDesc);
                args.putInt(IMAGE, R.drawable.ideas);
                args.putInt(BACKGROUND, R.color.orange);
                break;
            case 3:
                args.putInt(TITLE, R.string.improveTitle);
                args.putInt(DESCRIPTION, R.string.improveDesc);
                args.putInt(IMAGE, R.drawable.improve);
                args.putInt(BACKGROUND, R.color.indigo);
                break;
            case 4:
                args.putInt(TITLE, R.string.persistTitle);
                args.putInt(DESCRIPTION, R.string.persistDesc);
                args.putInt(IMAGE, R.drawable.persist);
                args.putInt(BACKGROUND, R.color.pink);
                break;
            case 5:
                args.putInt(TITLE, R.string.no_string);
                args.putInt(DESCRIPTION, R.string.developer);
                // If the language of device is Italian, put italian image
                if (Locale.getDefault().getLanguage().equals(Locale.ITALIAN.toString())) {
                    args.putInt(IMAGE, R.drawable.grazie);
                } else {
                    args.putInt(IMAGE, R.drawable.thanks);
                }
                args.putInt(BACKGROUND, R.color.blue);
                break;
        }
        // Insert the bundle in the new fragment
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the id resources from bundle
        if (getArguments() != null) {
            mPosition = getArguments().getInt(POSITION);
            mTitle = getArguments().getInt(TITLE);
            mDescription = getArguments().getInt(DESCRIPTION);
            mImage = getArguments().getInt(IMAGE);
            mBackColor = getArguments().getInt(BACKGROUND);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_step, container, false);

        // Find the ImageView of gdg logo and set its visibility to gone
        ImageView imageLogo = (ImageView) viewGroup.findViewById(R.id.imageLogo);
        imageLogo.setVisibility(View.GONE);

        // If it's the last page set ImageView visibility to visible
        if (mPosition == 5) {
            imageLogo.setVisibility(View.VISIBLE);
        }

        // Set the background color to the whole fragment
        viewGroup.findViewById(R.id.frameLayout).setBackgroundColor(ContextCompat.getColor(getContext(), mBackColor));

        // Set the image to the ImageView
        imageCenter = (ImageView) viewGroup.findViewById(R.id.imageCenter);
        imageCenter.setImageResource(mImage);

        // Set the Title text to the TextView
        textTitle = (TextView) viewGroup.findViewById(R.id.textTitle);
        textTitle.setText(mTitle);

        // Set the Description text to the TextView
        textDescription = (TextView) viewGroup.findViewById(R.id.textDescription);
        textDescription.setText(mDescription);

        return viewGroup;
    }
}