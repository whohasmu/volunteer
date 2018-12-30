package volunteerapp.cyh.volunteer.Main;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import volunteerapp.cyh.volunteer.Tools.Bus.BusProvider;
import volunteerapp.cyh.volunteer.Main.Event.FinishActivity;
import volunteerapp.cyh.volunteer.Main.Event.FinishResultActivity;
import volunteerapp.cyh.volunteer.Main.Event.MoveViewPager;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Main.Adapter.AreaViewPagerAdapter;

public class AreaListActivity extends FragmentActivity{





    AreaViewPagerAdapter areaViewPagerAdapter;
    ViewPager Viewpager_AreaList;

    Bus bus = BusProvider.getInstance().getBus();
    CutstomViewPager cutstomViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_list);





        bus.register(this);




        /*Viewpager_AreaList = findViewById(R.id.Viewpager_AreaList);*/
        areaViewPagerAdapter = new AreaViewPagerAdapter(getSupportFragmentManager());
        cutstomViewPager = (CutstomViewPager)findViewById(R.id.Viewpager_AreaList);
        cutstomViewPager.setAdapter(areaViewPagerAdapter);


        cutstomViewPager.setPagingEnabled(false);












    }

    @Subscribe
    public void MoveViewPager(MoveViewPager event) {

        cutstomViewPager.setCurrentItem(event.getPage());


    }

    @Subscribe
    public void FinishResultActivity(FinishResultActivity event) {
        Intent intent = getIntent();
        setResult(RESULT_OK,intent);
        intent.putExtra("Si_Id",event.getSi_Id());
        intent.putExtra("Gu_Id",event.getGu_Id());
        intent.putExtra("Si_Name",event.getSi_Name());
        intent.putExtra("Gu_Name",event.getGu_Name());

        finish();

    }

    @Subscribe
    public void FinishActivity(FinishActivity event) {
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }

    @Subscribe
    public void Fasdfctivity(FinishActivity event) {
        finish();
    }

}
