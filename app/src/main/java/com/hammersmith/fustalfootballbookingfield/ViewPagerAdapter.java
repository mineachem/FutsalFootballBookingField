package com.hammersmith.fustalfootballbookingfield;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter{

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    //int iconTab[];
    //int iconSelected[];
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    SparseArray<Fragment> registerFragment = new SparseArray<Fragment>();


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence[] mTitles,int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        //this.iconTab = mIconTab;
        //this.iconSelected = mIconSelected;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            Tab_List home = new Tab_List();
            return home;

        }
        else if(position == 1)
        {
            Tab_Map setting = new Tab_Map();
            return setting;
        }
        else
        {
            Tab_League league = new Tab_League();
            return league;
        }
    }


    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }



    public Fragment getRegisterFragment(int position) {
        return registerFragment.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment)super.instantiateItem(container,position);
        registerFragment.put(position,fragment);
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        registerFragment.remove(position);
        super.destroyItem(container, position, object);
    }
    //@Override
//    public int getPageIconSelected(int position) {
//        return iconSelected[position];
//    }
}