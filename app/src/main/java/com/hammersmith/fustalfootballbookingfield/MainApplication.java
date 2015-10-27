package com.hammersmith.fustalfootballbookingfield;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hammersmith.fustalfootballbookingfield.widgets.OnBackPressListener;
import com.hammersmith.fustalfootballbookingfield.widgets.SlidingTabLayoutText;

/**
 * Created by USER on 10/2/2015.
 */
public class MainApplication extends RootFragmgmet {

    ViewPager pager;
    ViewPagerAdapterSubLeague adapter;
    SlidingTabLayoutText tab;
    CharSequence Titles[] = {"Booking","League","Setting"};
    int NumbOfTabs = 3;
    public MainApplication(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        pager = (ViewPager) view.findViewById(R.id.sub_pager);
        tab = (SlidingTabLayoutText) view.findViewById(R.id.sub_tabs);
        tab.setCustomTabView(R.layout.sub_custom_tab, R.id.textTabs);
        tab.setDistributeEvenly(true);
        tab.setCustomTabColorizer(new SlidingTabLayoutText.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.subTabsScrollColor);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //adapter =  new ViewPagerAdapterSubLeague(getActivity().getSupportFragmentManager(),Titles,NumbOfTabs);
        adapter = new ViewPagerAdapterSubLeague(getChildFragmentManager(),Titles,NumbOfTabs);
        pager.setAdapter(adapter);
        tab.setViewPager(pager);
    }

    @Override
    public boolean onBackPress() {
        OnBackPressListener currentFragment = (OnBackPressListener)adapter.getRegisterFragmentFixture(pager.getCurrentItem());
        if(currentFragment != null){
            return currentFragment.onBackPress();
        }
        return false;
    }

}
