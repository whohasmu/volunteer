package volunteerapp.cyh.volunteer.Party;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import volunteerapp.cyh.volunteer.Party.Object.Comments;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Tools.Login;
import volunteerapp.cyh.volunteer.Tools.Retrofit.RetrofitService;

public class PartyInfoActivity extends AppCompatActivity {


    TextView name;
    Button Button_Back;

    TextView Text_Title;
    TextView Text_Date;
    TextView Text_Writer;
    TextView Text_Hit;
    TextView Text_Content;


    EditText Edit_comment;
    Button  Button_AddComment;

    LinearLayout Layout_comment;

    ArrayList<Comments> comments = new ArrayList<>();

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_info);

        name = findViewById(R.id.name);
        name.setText("");
        Button_Back =findViewById(R.id.Button_Back);
        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Text_Title = findViewById(R.id.Text_Title);
        Text_Date = findViewById(R.id.Text_Date);
        Text_Writer = findViewById(R.id.Text_Writer);
        Text_Hit = findViewById(R.id.Text_Hit);
        Text_Content = findViewById(R.id.Text_Content);

        Layout_comment = findViewById(R.id.Layout_comment);
        Edit_comment = findViewById(R.id.Edit_comment);
        Button_AddComment = findViewById(R.id.Button_AddComment);

        intent = getIntent();
        intent.getStringExtra("Id");
        intent.getStringExtra("Title");
        intent.getStringExtra("Writer");
        intent.getStringExtra("Write_Date");
        intent.getStringExtra("Content");
        intent.getStringExtra("Views");

        Text_Title.setText(intent.getStringExtra("Title"));
        Text_Date.setText(intent.getStringExtra("Write_Date"));
        Text_Writer.setText(intent.getStringExtra("Writer"));
        Text_Hit.setText(intent.getStringExtra("Views"));
        Text_Content.setText(intent.getStringExtra("Content"));

        /*for(int i=0;i<10;i++){
            Comment comment = new Comment("1",intent.getStringExtra("Id"),"홍길동","댓글이용","1111/1111/1111");
            comments.add(comment);
        }

        for (int i = 0; i < comments.size(); i++) {

            *//*LayoutInflater inflater = (LayoutInflater) getSystemService (Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout route_info_tab = (LinearLayout) inflater.inflate(R.layout.list_comment, null);*//*

            View route_info_tab = LayoutInflater.from(PartyInfoActivity.this).inflate(R.layout.list_comment, null);
            Layout_comment.addView(route_info_tab);

            TextView Writer = route_info_tab.findViewById(R.id.Text_Writer);
            TextView Content = route_info_tab.findViewById(R.id.Text_Content);
            TextView Date = route_info_tab.findViewById(R.id.Text_Date);

            Writer.setText(comments.get(i).getWriter());
            Content.setText(comments.get(i).getContent());
            Date.setText(comments.get(i).getDate());


        }*/


        getDataList_Comment();

        Button_AddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("로그",intent.getStringExtra("Id"));



                Call<Void> observ = RetrofitService.getInstance().getRetrofitRequest().AddPartyComment(intent.getStringExtra("Id"),
                        Login.getInstance().getMember().getMem_name(),Edit_comment.getText().toString());
                observ.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if(response.isSuccessful()){

                            Log.d("로그","AddPartyComment 성공 : " + response.body());
                            View route_info_tab = LayoutInflater.from(PartyInfoActivity.this).inflate(R.layout.list_comment, null);
                            Layout_comment.addView(route_info_tab);

                            TextView Writer = route_info_tab.findViewById(R.id.Text_Writer);
                            TextView Content = route_info_tab.findViewById(R.id.Text_Content);
                            TextView Date = route_info_tab.findViewById(R.id.Text_Date);


                            Writer.setText(Login.getInstance().getMember().getMem_name());
                            Content.setText(Edit_comment.getText().toString());
                            String write_date=new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
                            Date.setText(write_date);

                            Edit_comment.setText("");


                        }else{
                            Log.d("로그","(AddPartyComment)음..."+response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("로그","AddPartyComment 실패! : " + t);

                    }
                });

            }
        });

        Call<Void> observ = RetrofitService.getInstance().getRetrofitRequest().AddPartyViews(intent.getStringExtra("Id"));
        observ.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.isSuccessful()){

                    Log.d("로그","PartyViews++ 성공 : ");

                }else{
                    Log.d("로그","(PartyViews++)음..."+response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("로그","PartyViews++ 실패! : " + t);
            }
        });



        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }//onCreate

    public void getDataList_Comment(){


        Log.d("로그", "Comment 갱신");

        Call<ArrayList<Comments>> observ = RetrofitService.getInstance().getRetrofitRequest().getPartyCommentList(intent.getStringExtra("Id"));
        observ.enqueue(new Callback<ArrayList<Comments>>() {
            @Override
            public void onResponse(Call<ArrayList<Comments>> call, Response<ArrayList<Comments>> response) {

                if(response.isSuccessful()){
                    comments = response.body();

                    Log.d("로그","comment목록 갱신 성공!" + comments.size());

                    if(comments.size()!=0) {
                        for (int i = 0; i < comments.size(); i++) {

                            LayoutInflater inflater = (LayoutInflater) getSystemService (Context.LAYOUT_INFLATER_SERVICE);
                            LinearLayout route_info_tab = (LinearLayout) inflater.inflate(R.layout.list_comment, null);

                            Layout_comment.addView(route_info_tab);

                            TextView Writer = route_info_tab.findViewById(R.id.Text_Writer);
                            TextView Content = route_info_tab.findViewById(R.id.Text_Content);
                            TextView Date = route_info_tab.findViewById(R.id.Text_Date);

                            Writer.setText(comments.get(i).getWriter());
                            Content.setText(comments.get(i).getContent());
                            Date.setText(comments.get(i).getWrite_date());
                        }
                    }



                }else{
                    Log.d("로그","(comment목록)음...");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Comments>> call, Throwable t) {
                Log.d("로그","comment목록 받아오기 실패! : " + t);
            }
        });


    }



}
