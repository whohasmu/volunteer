package volunteerapp.cyh.volunteer.Home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;


import volunteerapp.cyh.volunteer.Party.PartyListActivity;
import volunteerapp.cyh.volunteer.Main.MainActivity;
import volunteerapp.cyh.volunteer.Notice.NoticeListActivity;
import volunteerapp.cyh.volunteer.QnA.QnAListActivity;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Main.Adapter.ViewPagerAdapter;

import android.content.Intent;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;

    static TimerTask task;
    Timer timer = new Timer();

    RelativeLayout Layout_menu1;
    RelativeLayout Layout_menu2;
    RelativeLayout Layout_menu3;
    RelativeLayout Layout_menu4;
    static boolean Do;
    static boolean Start;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = findViewById(R.id.viewpager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);


        Layout_menu1 = findViewById(R.id.Layout_menu1);
        Layout_menu2 = findViewById(R.id.Layout_menu2);
        Layout_menu3 = findViewById(R.id.Layout_menu3);
        Layout_menu4 = findViewById(R.id.Layout_menu4);



        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("로그","터치!");
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("로그","스크롤");

                if(Start==false) {
                    Start=true;
                }else{
                    Do = false;
                }

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("로그","셀렉트셀렉트");

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Do=true;
                Log.d("로그","change");

            }
        });

        

        Log.d("로그","onCreate");



        Layout_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });
        Layout_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NoticeListActivity.class);
                startActivity(intent);

            }
        });
        Layout_menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, QnAListActivity.class);
                startActivity(intent);

            }
        });

        Layout_menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PartyListActivity.class);
                startActivity(intent);

            }
        });

    }//onCreate()

    public TimerTask Viewtask(){

        TimerTask m_task  = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(Do==true) {
                            if (viewPager.getCurrentItem() >= 2) {
                                viewPager.setCurrentItem(0);
                            } else {
                                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                            }
                        }
                    }
                });
            }


        };

        return m_task;


    }public void Do_Task(){

            Log.d("로그task","task 시작");
            task = Viewtask();
            timer.schedule(task,1000,1000);


    }


    private long pressedTime;
    @Override
    public void onBackPressed() {
        if (pressedTime == 0) {
            Toast.makeText(HomeActivity.this, "한번 더 누르면 종료 됩니다.", Toast.LENGTH_SHORT).show();
            pressedTime = System.currentTimeMillis();

        } else {
            int sec = (int) (System.currentTimeMillis() - pressedTime);

            if (sec > 1500) {
                Toast.makeText(HomeActivity.this, "한번 더 누르면 종료 됩니다.", Toast.LENGTH_SHORT).show();
            } else {

                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        Log.d("로그","onResume");
        super.onResume();
        Start=false;
        Do=true;
        Do_Task();


    }

    @Override
    protected void onPause() {
        Log.d("로그","onPause");
        super.onPause();
        task.cancel();
        Do=false;

    }

    @Override
    protected void onDestroy() {
        Log.d("cyhh","onDestroy");
        /*time_task.cancel();
        start=false;*/
        super.onDestroy();
    }
}
