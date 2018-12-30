package volunteerapp.cyh.volunteer.Main.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import volunteerapp.cyh.volunteer.Main.FragView_Area.View_Gu;
import volunteerapp.cyh.volunteer.Main.FragView_Area.View_Si;

public class AreaViewPagerAdapter extends FragmentStatePagerAdapter {
    public AreaViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return View_Si.getInstance();
        } else{
            return View_Gu.getInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }






}
