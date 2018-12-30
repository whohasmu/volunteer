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

import java.util.ArrayList;

import volunteerapp.cyh.volunteer.Tools.Bus.BusProvider;
import volunteerapp.cyh.volunteer.Main.Event.FinishActivity;
import volunteerapp.cyh.volunteer.Main.Event.MoveViewPager;
import volunteerapp.cyh.volunteer.Main.Event.SetGuData;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Main.Adapter.AreaListAdapter;
import volunteerapp.cyh.volunteer.Main.Object.Area;

public class View_Si extends Fragment{

    private static View_Si curr = null;

    public static View_Si getInstance() {
        if (curr == null) {
            curr = new View_Si();
        }
        return curr;
    }
    Bus bus = BusProvider.getInstance().getBus();
    ListView List_Item;
    AreaListAdapter areaListAdapter;

    ArrayList<Area> Area_Si = new ArrayList<>();
    Area inputArea;

    TextView name;
    Button Button_Back;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.frag_area_city,container,false);

        name = view.findViewById(R.id.name);
        Button_Back = view.findViewById(R.id.Button_Back);

        bus.register(this);

        Area_Si.clear();
        inputArea_Si();
        List_Item = view.findViewById(R.id.List_Item);

        areaListAdapter = new AreaListAdapter(Area_Si);
        List_Item.setAdapter(areaListAdapter);

        List_Item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SetGuData setGuData = new SetGuData(Area_Si.get(position).getId(),Area_Si.get(position).getName());
                bus.post(setGuData);
                MoveViewPager MoveViewPager = new MoveViewPager(1);
                bus.post(MoveViewPager);

            }
        });

        name.setText("시·도 목록");
        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinishActivity finishActivity = new FinishActivity();
                bus.post(finishActivity);
            }
        });
        return view;
    }

    public void inputArea_Si(){
        inputArea = new Area(6110000,"서울특별시");
        Area_Si.add(inputArea);
        inputArea = new Area(6260000,"부산광역시");
        Area_Si.add(inputArea);
        inputArea = new Area(6270000,"대구광역시");
        Area_Si.add(inputArea);
        inputArea = new Area(6280000,"인천광역시");
        Area_Si.add(inputArea);
        inputArea = new Area(6290000,"광주광역시");
        Area_Si.add(inputArea);
        inputArea = new Area(6300000,"대전광역시");
        Area_Si.add(inputArea);
        inputArea = new Area(6310000,"울산광역시");
        Area_Si.add(inputArea);
        inputArea = new Area(6420000,"강원도");
        Area_Si.add(inputArea);
        inputArea = new Area(6410000,"경기도");
        Area_Si.add(inputArea);
        inputArea = new Area(6480000,"경상남도");
        Area_Si.add(inputArea);
        inputArea = new Area(6470000,"경상북도");
        Area_Si.add(inputArea);
        inputArea = new Area(6460000,"전라남도");
        Area_Si.add(inputArea);
        inputArea = new Area(6450000,"전라북도");
        Area_Si.add(inputArea);
        inputArea = new Area(6500000,"제주특별자치도");
        Area_Si.add(inputArea);
        inputArea = new Area(6440000,"충청남도");
        Area_Si.add(inputArea);
        inputArea = new Area(6430000,"충청북도");
        Area_Si.add(inputArea);






    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}
