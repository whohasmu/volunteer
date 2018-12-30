package volunteerapp.cyh.volunteer.Party;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import volunteerapp.cyh.volunteer.Party.Adapter.PartyListAdapter;
import volunteerapp.cyh.volunteer.Party.Object.Party;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Tools.Retrofit.RetrofitService;

public class PartyListActivity extends AppCompatActivity {

    TextView name;
    Button Button_Back;


    ArrayList<Party> parties = new ArrayList<>();

    ListView List_Group;
    PartyListAdapter groupListAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    FloatingActionButton Button_WriteGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_list);

        name = findViewById(R.id.name);
        name.setText("유저게시판");
        Button_Back = findViewById(R.id.Button_Back);

        swipeRefreshLayout = findViewById(R.id.SwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getDataList_Group();



                swipeRefreshLayout.setRefreshing(false);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        List_Group = findViewById(R.id.List_Group);
        groupListAdapter = new PartyListAdapter(parties);
        List_Group.setAdapter(groupListAdapter);

        /*getDataList_Group();*/
        /*for(int i=0;i<50;i++){
            Party party = new Party("1","안녕하세요","김두환","2012/12/12","방가루~",0);
            parties.add(party);
        }*/
        groupListAdapter.notifyDataSetChanged();


        List_Group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(PartyListActivity.this, PartyInfoActivity.class);
                intent.putExtra("Id",parties.get(position).getId());
                intent.putExtra("Title",parties.get(position).getTitle());
                intent.putExtra("Writer",parties.get(position).getWriter());
                intent.putExtra("Write_Date",parties.get(position).getWrite_date());
                intent.putExtra("Content",parties.get(position).getContent());
                intent.putExtra("Views",parties.get(position).getViews().toString());

                startActivity(intent);

            }
        });

        Button_WriteGroup = findViewById(R.id.Button_WriteGroup);
        Button_WriteGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(PartyListActivity.this, PartyWriteActivity.class);
                startActivityForResult(intent,1);
            }
        });





        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void getDataList_Group(){

        parties.clear();
        Log.d("로그", "Group목록 갱신");

        Call<ArrayList<Party>> observ = RetrofitService.getInstance().getRetrofitRequest().getPartyList();
        observ.enqueue(new Callback<ArrayList<Party>>() {
            @Override
            public void onResponse(Call<ArrayList<Party>> call, Response<ArrayList<Party>> response) {

                if(response.isSuccessful()){
                    /*parties.addAll(response.body());*/
                    parties.addAll(response.body());

                    Log.d("로그","Group목록 성공!" + parties.size());

                    groupListAdapter = new PartyListAdapter(parties);
                    List_Group.setAdapter(groupListAdapter);
                    groupListAdapter.notifyDataSetChanged();




                }else{
                    Log.d("로그","(Group목록)음...");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Party>> call, Throwable t) {
                Log.d("로그","Group목록 받아오기 실패! : " + t);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("로그","onResume");
        getDataList_Group();


    }

    @Override
    protected void onPause() {
        Log.d("로그","onPause");
        super.onPause();
    }
}
