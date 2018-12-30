package volunteerapp.cyh.volunteer.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import volunteerapp.cyh.volunteer.Main.Adapter.FavoriteListAdapter;
import volunteerapp.cyh.volunteer.Main.Object.ItemInfo;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Tools.Passing.GetData;
import volunteerapp.cyh.volunteer.Tools.Retrofit.RetrofitService;

public class FavoriteListActivity extends AppCompatActivity {


    FavoriteListAdapter favoriteListAdapter;
    ArrayList<ItemInfo> items = new ArrayList<>();
    ArrayList<String> items_No = new ArrayList<>();

    GetData getData = new GetData();

    TextView name;
    Button Button_Back;
    GridView List_Favorite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);


        name = findViewById(R.id.name);
        Button_Back = findViewById(R.id.Button_Back);

        List_Favorite = findViewById(R.id.List_Favorite);
        name.setText("즐겨찾기");
        favoriteListAdapter = new FavoriteListAdapter(items);
        List_Favorite.setAdapter(favoriteListAdapter);


        List_Favorite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FavoriteListActivity.this, InfoActivity.class);
                intent.putExtra("ProgrmRegistNo",items.get(position).getProgrmRegistNo());
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

    @Override
    protected void onResume() {
        super.onResume();


        Call<ArrayList<String>> observ = RetrofitService.getInstance().getRetrofitRequest().getFavoriteList("tempLogin_Id");
        observ.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {

                if(response.isSuccessful()){

                    items_No.clear();
                    items.clear();
                    items_No=response.body();
                    Log.d("로그","즐겨찾기 목록 받아오기 성공! : " + items_No.size());

                        for (int i = 0; i < items_No.size(); i++) {
                            if(getData.getDataInfo(items_No.get(i)).getProgrmRegistNo()!=null)
                            items.add(getData.getDataInfo(items_No.get(i)));
                        }

                        favoriteListAdapter.notifyDataSetChanged();


                }else{
                    Log.d("로그","음..."+response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.d("로그","받아오기 실패! : " + t);
            }
        });
    }
}
