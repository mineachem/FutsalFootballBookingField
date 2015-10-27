package com.hammersmith.fustalfootballbookingfield;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hammersmith.fustalfootballbookingfield.widgets.OnBackPressListener;
import com.hammersmith.fustalfootballbookingfield.widgets.SlidingTabLayoutText;


public class MainActivity extends RootFragmgmet {

    // Declaring Your View and Variables
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayoutText tabs;
    CharSequence Titles[]={"List","Map","League"};
    int Numboftabs =3;
    public MainActivity(){


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,container,false);

        pager = (ViewPager)view.findViewById(R.id.pager);
        tabs = (SlidingTabLayoutText)view.findViewById(R.id.tabs);
        tabs.setCustomTabView(R.layout.custom_tab_text, R.id.textCustomTab);
        tabs.setDistributeEvenly(true);
        tabs.setTabsBackgroundColor(getResources().getColor(R.color.selector));
        tabs.setCustomTabColorizer(new SlidingTabLayoutText.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabColorTest);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //adapter =  new ViewPagerAdapterSubLeague(getActivity().getSupportFragmentManager(),Titles,NumbOfTabs);
        adapter = new ViewPagerAdapter(getChildFragmentManager(),Titles,Numboftabs);
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
    }



    public boolean onBackPress() {
        OnBackPressListener currentFragment = (OnBackPressListener)adapter.getRegisterFragment(pager.getCurrentItem());
        if(currentFragment != null){
            return currentFragment.onBackPress();
        }
        return false;
    }
}