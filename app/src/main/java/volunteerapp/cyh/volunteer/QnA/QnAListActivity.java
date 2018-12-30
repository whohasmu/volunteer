package volunteerapp.cyh.volunteer.QnA;

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
import volunteerapp.cyh.volunteer.QnA.Adapter.QnAListAdapter;
import volunteerapp.cyh.volunteer.QnA.Object.QnA;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Tools.Passing.GetData;
import volunteerapp.cyh.volunteer.Tools.Retrofit.RetrofitService;

public class QnAListActivity extends AppCompatActivity {

    TextView name;
    Button Button_Back;

    ListView List_QnA;
    QnAListAdapter qnAListAdapter;
    ArrayList<QnA> QnAs = new ArrayList<>();

    FloatingActionButton Button_WriteQnA;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_list);

        name = findViewById(R.id.name);
        Button_Back = findViewById(R.id.Button_Back);

        name.setText("Q&A");
        List_QnA = findViewById(R.id.List_QnA);

        Button_WriteQnA = findViewById(R.id.Button_WriteQnA);

        swipeRefreshLayout = findViewById(R.id.SwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getDataList_QnA();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );


        List_QnA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Call<Void> observ = RetrofitService.getInstance().getRetrofitRequest().AddQnAViews(QnAs.get(position).getId());
                observ.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if(response.isSuccessful()){
                            Log.d("로그","QnA조회수++ 성공!" + QnAs.size());

                        }else{
                            Log.d("로그","(QnA조회수++)음...");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("로그","QnA조회수++ 실패! : " + t);
                    }
                });

                Intent intent = new Intent(QnAListActivity.this, QnAInfoActivity.class);
                intent.putExtra("Id", QnAs.get(position).getId());
                intent.putExtra("Title", QnAs.get(position).getTitle());
                intent.putExtra("Writer", QnAs.get(position).getWriter());
                intent.putExtra("Date_Q", QnAs.get(position).getDate_Q());
                intent.putExtra("Views", QnAs.get(position).getHit());
                intent.putExtra("Content_Q", QnAs.get(position).getContent_Q());
                intent.putExtra("Isanswer", QnAs.get(position).getIsanswer());
                intent.putExtra("Date_A", QnAs.get(position).getDate_A());
                intent.putExtra("Content_A", QnAs.get(position).getContent_A());
                startActivity(intent);
            }
        });


        Button_WriteQnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QnAListActivity.this, QnAWriteActivity.class);
                startActivityForResult(intent, 1);
            }
        });


        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    public void getDataList_QnA(){



        Call<ArrayList<QnA>> observ = RetrofitService.getInstance().getRetrofitRequest().getQnAList();
        observ.enqueue(new Callback<ArrayList<QnA>>() {
            @Override
            public void onResponse(Call<ArrayList<QnA>> call, Response<ArrayList<QnA>> response) {

                if(response.isSuccessful()){
                    QnAs.clear();
                    QnAs.addAll(response.body());
                    qnAListAdapter = new QnAListAdapter(QnAs);
                    List_QnA.setAdapter(qnAListAdapter);
                    qnAListAdapter.notifyDataSetChanged();

                    Log.d("로그","QnA목록 성공!" + QnAs.size());


                }else{
                    Log.d("로그","(QnA)음...");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<QnA>> call, Throwable t) {
                Log.d("로그","QnA목록 받아오기 실패! : " + t);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        getDataList_QnA();
    }
}

