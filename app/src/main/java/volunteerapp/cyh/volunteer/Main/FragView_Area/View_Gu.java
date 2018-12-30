package volunteerapp.cyh.volunteer.Main.FragView_Area;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import volunteerapp.cyh.volunteer.Tools.Bus.BusProvider;
import volunteerapp.cyh.volunteer.Main.Event.FinishResultActivity;
import volunteerapp.cyh.volunteer.Main.Event.MoveViewPager;
import volunteerapp.cyh.volunteer.Main.Event.SetGuData;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Main.Adapter.AreaListAdapter;
import volunteerapp.cyh.volunteer.Main.Object.Area;
import volunteerapp.cyh.volunteer.Tools.Passing.GetData;


public class View_Gu extends Fragment{

    private static View_Gu curr = null;

    public static View_Gu getInstance() {
        if (curr == null) {
            curr = new View_Gu();
        }
        return curr;
    }

    Bus bus = BusProvider.getInstance().getBus();
    ListView List_Item;
    AreaListAdapter areaListAdapter;
    GetData getData = new GetData();
    ArrayList<Area> AreaList_Gu = new ArrayList<>();
    Integer Si_Id;
    String Si_Name;


    TextView name;
    Button Button_Back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.frag_area_gu,container,false);


        name = view.findViewById(R.id.name);
        Button_Back = view.findViewById(R.id.Button_Back);

        bus.register(this);

        List_Item = view.findViewById(R.id.List_Item);
        areaListAdapter = new AreaListAdapter(AreaList_Gu);
        List_Item.setAdapter(areaListAdapter);




        List_Item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FinishResultActivity finishActivity = new FinishResultActivity(Si_Id, AreaList_Gu.get(position).getId(),Si_Name, AreaList_Gu.get(position).getName());
                bus.post(finishActivity);

            }
        });

        name.setText("구·군 목록");
        Button_Back.setBackgroundResource(R.drawable.back_white);
        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveViewPager MoveViewPager = new MoveViewPager(0);
                bus.post(MoveViewPager);
            }
        });

        return view;
    }

    @Subscribe
    public void setGuData(SetGuData event) {

        AreaList_Gu.clear();
        AreaList_Gu.addAll(getData.getDataList_GuList(event.getName()));
        areaListAdapter.notifyDataSetChanged();

        Si_Id = event.getId();
        Si_Name = event.getName();



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }


}