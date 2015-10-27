package com.hammersmith.fustalfootballbookingfield;

import android.support.v4.app.Fragment;

import com.hammersmith.fustalfootballbookingfield.widgets.BackPressImplement;
import com.hammersmith.fustalfootballbookingfield.widgets.OnBackPressListener;

/**
 * Created by USER on 9/28/2015.
 */
public class RootFragmgmet extends Fragment implements OnBackPressListener {
    @Override
    public boolean onBackPress() {
        return new BackPressImplement(this).onBackPress();
    }
}
