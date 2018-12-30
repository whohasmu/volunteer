package volunteerapp.cyh.volunteer.Main.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import volunteerapp.cyh.volunteer.Home.FragView_HomeImage.view1;
import volunteerapp.cyh.volunteer.Home.FragView_HomeImage.view2;
import volunteerapp.cyh.volunteer.Home.FragView_HomeImage.view3;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return view1.getInstance();
        } else if (position == 1){
            return view2.getInstance();
        }else {
            return view3.getInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
