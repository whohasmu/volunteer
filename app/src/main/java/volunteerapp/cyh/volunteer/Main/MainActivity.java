package volunteerapp.cyh.volunteer.Main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.Snackbar;

import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;


import java.util.ArrayList;


import volunteerapp.cyh.volunteer.Login.LoginActivity;
import volunteerapp.cyh.volunteer.Login.Object.Member;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Main.Adapter.ItemListAdapter;
import volunteerapp.cyh.volunteer.Main.Object.Item;

import volunteerapp.cyh.volunteer.Tools.Login;
import volunteerapp.cyh.volunteer.Tools.Passing.GetData;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static int ListStatue;
    static int ALL = 1;
    static int AREA = 2;
    static int DATE = 3;
    static int INTEGRATE=4;
    static int SEARCH=5;
    static int FAVORITE=6;

    GridView List_Item;
    ArrayList<Item> items = new ArrayList<>();

    ItemListAdapter itemListAdapter;


    View View_SearchBar_ForAnim;

    View View_SearchBar;
    EditText Edit_Searchinput;
    Button Button_SearchCancel;
    GetData getData;

    boolean lastitemVisibleFlag = false;
    ProgressDialog asyncDialog=null;

    LoadData_All loadData_All;
    LoadData_Area loadData_Area;
    LoadData_Date loadData_Date;
    LoadData_Integrate loadData_Integrate;
    Integer pageNo = 0;

    int SearchStatus = 0;


    Integer Si_Id,Gu_Id;
    String Start_Date,End_Date;
    String Search_Query;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        /*toolbar.setTitle("전체목록");*/
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("전체목록");


        View_SearchBar_ForAnim = findViewById(R.id.View_SearchBar_ForAnim);

        View_SearchBar = findViewById(R.id.View_SearchBar);
        Edit_Searchinput = findViewById(R.id.Edit_Searchinput);
        Button_SearchCancel = findViewById(R.id.Button_SearchCancel);

        List_Item = findViewById(R.id.List_Item);
        itemListAdapter = new ItemListAdapter(items);
        List_Item.setAdapter(itemListAdapter);


        getData = new GetData();




        ListStatue = ALL;
        loadData_All = new LoadData_All();
        loadData_All.execute();



        /*
        Call<ArrayList<ItemInfo>> observ = RetrofitService.getInstance().getRetrofitRequest().getItemList();
        observ.enqueue(new Callback<ArrayList<ItemInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemInfo>> call, Response<ArrayList<ItemInfo>> response) {

                if(response.isSuccessful()){
                    items = response.body();
                    searchItems.addAll(items);
                    Log.d("로그","받아오기 성공!" + items.size());
                    asyncDialog.dismiss();
                    itemListAdapter = new ItemListAdapter(items);
                    List_Item.setAdapter(itemListAdapter);


                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemInfo>> call, Throwable t) {
                Log.d("로그","받아오기 실패!");
                *//*asyncDialog.dismiss();*//*
                asyncDialog.dismiss();
            }

        });*/




        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "빼꼼...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                itemListAdapter.notifyDataSetChanged();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();






        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {

                /*itemListAdapter = new ItemListAdapter(search(Edit_Searchinput.getText().toString()));
                List_Item.setAdapter(itemListAdapter);*/
                items.clear();
                items.addAll(search(Edit_Searchinput.getText().toString()));
                itemListAdapter.notifyDataSetChanged();
                Log.d("로그",Edit_Searchinput.getText().toString());
            }
        };

        Edit_Searchinput.addTextChangedListener(textWatcher);



        Button_SearchCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit_Searchinput.setText("");
                /*View_SearchBar.setVisibility(View.GONE);
                SearchStatus=0;*/
            }
        });

        List_Item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent  = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("ProgrmRegistNo",items.get(position).getProgrmRegistNo());
                Log.d("로그","No : "+ items.get(position).getProgrmRegistNo());
                startActivity(intent);
            }
        });


        /*
        * 리스트 바닥에 닿았을때 처리부분
        *
        * */
        List_Item.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //현재 화면에 보이는 첫번째 리스트 아이템의 번호(firstVisibleItem) + 현재 화면에 보이는 리스트 아이템의 갯수(visibleItemCount)가 리스트 전체의 갯수(totalItemCount) -1 보다 크거나 같을때
                lastitemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);

            }
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //OnScrollListener.SCROLL_STATE_IDLE은 스크롤이 이동하다가 멈추었을때 발생되는 스크롤 상태입니다.
                //즉 스크롤이 바닦에 닿아 멈춘 상태에 처리를 하겠다는 뜻
                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastitemVisibleFlag) {
                    Log.d("로그", "바닥에 닿음");
                    if(ListStatue==ALL) {
                        loadData_All = new LoadData_All();
                        loadData_All.execute();
                    }else if(ListStatue ==AREA){
                        loadData_Area = new LoadData_Area();
                        loadData_Area.execute(Si_Id,Gu_Id);

                    }else if(ListStatue == DATE){
                        loadData_Date = new LoadData_Date();
                        loadData_Date.execute(Start_Date,End_Date);
                    }else if(ListStatue == INTEGRATE){
                        loadData_Integrate = new LoadData_Integrate();
                        loadData_Integrate.execute(Start_Date,End_Date,Si_Id.toString(),Gu_Id.toString(),Search_Query);
                    }else if(ListStatue==SEARCH){
                        pageNo++;
                        items.addAll(getData.getDataList_BySearch(Search_Query,pageNo.toString() ) );
                        itemListAdapter.notifyDataSetChanged();
                        if(getData.getDataList_BySearch(Search_Query,pageNo.toString()).size()==0){
                            Snackbar.make(getWindow().getDecorView().getRootView(), "목록의 끝입니다.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }

                }
            }

        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        /*
        탑바 클릭 리스너
        */
        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }else */
        if(id == R.id.action_search){
            if(SearchStatus==0) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_search);
                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                        View_SearchBar_ForAnim.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        View_SearchBar_ForAnim.setVisibility(View.GONE);
                        View_SearchBar.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }

                });
                View_SearchBar_ForAnim.startAnimation(anim);
                SearchStatus=1;
            }else{

                View_SearchBar.setVisibility(View.GONE);
                SearchStatus=0;
            }

            return true;
        }else if(id == R.id.action_home){
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        /*
        사이드메뉴 클릭 리스너
                */
        if (id == R.id.nav_AllList) {
            Log.d("로그","전체목록");
            toolbar.setSubtitle("전체목록");

            items.clear();

            pageNo=0;
            ListStatue=ALL;
            loadData_All = new LoadData_All();
            loadData_All.execute();
            itemListAdapter.notifyDataSetChanged();

        } else if (id == R.id.nav_AreaList) {
            Log.d("로그","지역별 목록");

            Intent intent = new Intent(MainActivity.this, AreaListActivity.class);
            startActivityForResult(intent,AREA);

        } else if (id == R.id.nav_DateList) {
            Log.d("로그","기간별 목록");

            Intent intent = new Intent(MainActivity.this,DateListActivity.class);
            startActivityForResult(intent,DATE);



        } else if (id == R.id.nav_IntegrateList) {
            Log.d("로그","통합검색");

            Intent intent = new Intent(MainActivity.this,IntegrateActivity.class);
            startActivityForResult(intent,INTEGRATE);
        } else if (id == R.id.nav_favorite) {
            Log.d("로그","즐겨찾기");
            Intent intent = new Intent(MainActivity.this,FavoriteListActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_MyInfo) {
            Log.d("로그","내정보 보기");
            Intent intent = new Intent(MainActivity.this,MyInfoActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_LogOut) {
            Log.d("로그","로그아웃.");
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();

            editor.remove("AutoLogin_Id");
            editor.remove("AutoLogin_Pw");
            editor.putString("isAuto","N");
            editor.commit();
            Member member = new Member();
            Login.getInstance().setMember(member);
            startActivity(intent);

            ActivityCompat.finishAffinity(this);
            /*finish();*/



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public ArrayList<Item> search(String charText) {
        ArrayList<Item> searchItems = new ArrayList<>();


        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            pageNo=1;
            searchItems.addAll(getData.getDataList(pageNo));
            ListStatue=ALL;
        }
        // 문자 입력을 할때..
        else
        {
            pageNo=1;
            searchItems.addAll(getData.getDataList_BySearch(charText,pageNo.toString()) );
            Search_Query=charText;
            ListStatue=SEARCH;
        }

        return searchItems;
    }






    private class LoadData_All extends AsyncTask<Void, Void, Void> {

        private boolean run=true;


        @Override
        protected void onPreExecute() {
            asyncDialog = new ProgressDialog(
                    MainActivity.this);
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("로딩중입니다..");
            Log.d("로그","async 로딩시작");
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            Log.d("로그","async 로딩중..");
            pageNo++;
            items.addAll(getData.getDataList(pageNo));
            Log.d("로그",pageNo + "페이지 로딩");
            if(getData.getDataList(pageNo).size()==0){
                Snackbar.make(getWindow().getDecorView().getRootView(), "목록의 끝입니다.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Log.d("로그","async 로딩 끝!");

            itemListAdapter.notifyDataSetChanged();
            asyncDialog.dismiss();


        }


    }

    private class LoadData_Area extends AsyncTask<Integer, Void, Void> {



        @Override
        protected void onPreExecute() {
            asyncDialog = new ProgressDialog(
                    MainActivity.this);
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("로딩중입니다..");
            Log.d("로그","async 로딩시작");
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... arg) {
            Log.d("로그","async 로딩중.." + arg[0] + " , " + arg[1]);

            pageNo++;
            items.addAll(getData.getDataList_ByArea(arg[0], arg[1],pageNo));
            Log.d("로그",pageNo + "페이지 로딩");
            if(getData.getDataList_ByArea(arg[0], arg[1],pageNo).size()==0){
                Snackbar.make(getWindow().getDecorView().getRootView(), "목록의 끝입니다.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }



            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d("로그","async 로딩 끝!");
            asyncDialog.dismiss();


            itemListAdapter.notifyDataSetChanged();



            super.onPostExecute(result);
        }

    }

    private class LoadData_Date extends AsyncTask<String, Void, Void> {



        @Override
        protected void onPreExecute() {
            asyncDialog = new ProgressDialog(
                    MainActivity.this);
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("로딩중입니다..");
            Log.d("로그","async 로딩시작");
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... arg) {
            Log.d("로그","async 로딩중.." + arg[0] + " , " + arg[1]);

            pageNo++;
            items.addAll(getData.getDataList_ByDate(arg[0], arg[1],pageNo));
            Log.d("로그",pageNo + "페이지 로딩");
            if(getData.getDataList_ByDate(arg[0], arg[1],pageNo).size()==0){
                Snackbar.make(getWindow().getDecorView().getRootView(), "목록의 끝입니다.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }



            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d("로그","async 로딩 끝!");
            asyncDialog.dismiss();


            itemListAdapter.notifyDataSetChanged();



            super.onPostExecute(result);
        }

    }

    private class LoadData_Integrate extends AsyncTask<String, Void, Void> {



        @Override
        protected void onPreExecute() {
            asyncDialog = new ProgressDialog(MainActivity.this);
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("로딩중입니다..");
            asyncDialog.show();
            Log.d("로그","async 로딩시작");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... arg) {
            Log.d("로그","async 로딩중.." + arg[0] + " , " + arg[1] + " , " + arg[2] + " , " + arg[3]);

            pageNo++;
            if(arg[2].equals("0")) arg[2]="";
            if(arg[3].equals("0")) arg[3]="";
            items.addAll(getData.getDataList_Integrate(arg[0], arg[1], arg[2], arg[3],arg[4],pageNo));
            Log.d("로그",pageNo + "페이지 로딩");


            if(getData.getDataList_Integrate(arg[0], arg[1], arg[2], arg[3],arg[4],pageNo).size()==0){
                Snackbar.make(getWindow().getDecorView().getRootView(), "목록의 끝입니다.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }



            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.d("로그","async 로딩 끝!");
            asyncDialog.dismiss();


            itemListAdapter.notifyDataSetChanged();



            super.onPostExecute(result);
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==AREA){
                Log.d("로그","지역별목록으로 갱신");
                /*toolbar.setTitle("지역별 목록");
                setSupportActionBar(toolbar);*/
                toolbar.setSubtitle("지역별 목록");
                items.clear();
                pageNo=0;
                ListStatue=AREA;
                Si_Id = data.getIntExtra("Si_Id",0);
                Gu_Id = data.getIntExtra("Gu_Id",0);

                loadData_Area = new LoadData_Area();
                loadData_Area.execute(Si_Id,Gu_Id);



            }else if(requestCode==DATE){
                Log.d("로그","날짜별목록으로 갱신, " + data.getStringExtra("Start_Date") + "~" + data.getStringExtra("End_Date"));
                /*toolbar.setTitle("날짜별 목록");
                setSupportActionBar(toolbar);*/
                toolbar.setSubtitle("기간별 목록");
                items.clear();
                pageNo=0;
                ListStatue = DATE;
                Start_Date = data.getStringExtra("Start_Date");
                End_Date = data.getStringExtra("End_Date");
                loadData_Date = new LoadData_Date();
                loadData_Date.execute(Start_Date,End_Date);


            }else if(requestCode==INTEGRATE){
                Log.d("로그","통합검색목록으로 갱신");
                /*toolbar.setTitle("통합검색목록");
                setSupportActionBar(toolbar);*/
                toolbar.setSubtitle("통합검색 목록");
                Start_Date = data.getStringExtra("Start_Date");
                End_Date = data.getStringExtra("End_Date");
                Si_Id = data.getIntExtra("Si_Id",0);
                Gu_Id = data.getIntExtra("Gu_Id",0);
                Search_Query = data.getStringExtra("Search_Query");

                items.clear();
                pageNo=0;
                ListStatue = INTEGRATE;
                loadData_Integrate = new LoadData_Integrate();
                loadData_Integrate.execute(Start_Date,End_Date,Si_Id.toString(),Gu_Id.toString(),Search_Query);


            }
        }
    }





}


