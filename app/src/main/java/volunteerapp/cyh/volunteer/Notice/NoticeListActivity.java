package volunteerapp.cyh.volunteer.Notice;

import android.content.Intent;
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
import volunteerapp.cyh.volunteer.Notice.Object.Board;
import volunteerapp.cyh.volunteer.Notice.Adapter.NoticeListAdapter;
import volunteerapp.cyh.volunteer.QnA.Object.QnA;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Tools.Passing.GetData;
import volunteerapp.cyh.volunteer.Tools.Retrofit.RetrofitService;

public class NoticeListActivity extends AppCompatActivity {



    NoticeListAdapter noticeListAdapter;
    ArrayList<Board> notices = new ArrayList<>();
    ListView List_Notice;
    TextView title;
    Button Button_Back;

    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);

        Button_Back = findViewById(R.id.Button_Back);

        swipeRefreshLayout = findViewById(R.id.SwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getDataList_Notice();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        /*ActionBar actionBar = getActionBar();
        actionBar.setTitle("공지사항");*/
        title = findViewById(R.id.name);
        title.setText("공지사항");

        List_Notice = findViewById(R.id.List_Notice);

        noticeListAdapter = new NoticeListAdapter(notices);
        List_Notice.setAdapter(noticeListAdapter);

        getDataList_Notice();


        List_Notice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(NoticeListActivity.this, NoticeInfoActivity.class);
                intent.putExtra("Notice_Id",notices.get(position).getId());
                intent.putExtra("Notice_Title",notices.get(position).getTitle());
                intent.putExtra("Notice_Write_Date",notices.get(position).getWrite_date());
                intent.putExtra("Notice_Content",notices.get(position).getContent());
                startActivity(intent);
            }
        });


        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getDataList_Notice(){

        notices.clear();

        Call<ArrayList<Board>> observ = RetrofitService.getInstance().getRetrofitRequest().getNoticeList();
        observ.enqueue(new Callback<ArrayList<Board>>() {
            @Override
            public void onResponse(Call<ArrayList<Board>> call, Response<ArrayList<Board>> response) {

                if(response.isSuccessful()){
                    notices = response.body();

                    Log.d("로그","공지목록 성공!" + notices.size());

                    noticeListAdapter = new NoticeListAdapter(notices);
                    List_Notice.setAdapter(noticeListAdapter);
                    noticeListAdapter.notifyDataSetChanged();
                    for(int i=0;i<notices.size();i++){
                        Log.d("로그",notices.get(i).getTitle() + notices.get(i).getContent());
                    }


                }else{
                    Log.d("로그","(공지)음...");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Board>> call, Throwable t) {
                Log.d("로그","공지목록 받아오기 실패! : " + t);
            }
        });


    }
}
