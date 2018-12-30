package volunteerapp.cyh.volunteer.Main;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

import volunteerapp.cyh.volunteer.R;


public class DateListActivity extends AppCompatActivity {

    Button Button_StartDate;
    Button Button_EndDate;
    Button Button_DateSearch;

    Button Button_Back;
    TextView name;

    Calendar c = Calendar.getInstance();
    int cyear = c.get(Calendar.YEAR);
    int cmonth = c.get(Calendar.MONTH);
    int cday = c.get(Calendar.DAY_OF_MONTH);

    Calendar minDate = Calendar.getInstance();

    String Start_Date;
    String End_Date;

    Boolean Start_Date_Check = false, End_Date_Check = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_list);

        Button_StartDate = findViewById(R.id.Button_StartDate);
        Button_EndDate = findViewById(R.id.Button_EndDate);
        Button_DateSearch = findViewById(R.id.Button_DateSearch);

        name = findViewById(R.id.name);
        Button_Back = findViewById(R.id.Button_Back);

        name.setText("기간별 목록 검색");

        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        Button_StartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int inputyear, int inputmonth, int inputday) {
                        Log.d("로그","startDate : " + inputyear + "," + inputmonth + "," + inputday);
                        minDate.set(inputyear,inputmonth,inputday);

                        Integer y = inputyear,m = inputmonth+1,d = inputday;
                        String Str_y , Str_m , Str_d;


                        Str_y = y.toString();
                        if(m<10){
                            Str_m = "0"+m;
                        }else{
                            Str_m = m.toString();
                        }

                        if(d<10){
                            Str_d = "0"+d;
                        }else{
                            Str_d = d.toString();
                        }

                        Start_Date = Str_y+Str_m+Str_d;

                        Button_StartDate.setText(Start_Date);
                        ColorStateList stateList = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                        Button_StartDate.setBackgroundTintList(stateList);
                        Start_Date_Check = true;

                    }
                };

                DatePickerDialog dialog = new DatePickerDialog(DateListActivity.this,dateSetListener,
                        cyear,cmonth,cday);
                dialog.openOptionsMenu();

                dialog.show();
            }
        });

        Button_EndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Start_Date_Check==true) {
                    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int inputyear, int inputmonth, int inputday) {
                            Log.d("로그", "EndDate : " + inputyear + "," + inputmonth + "," + inputday);
                            Integer y = inputyear, m = inputmonth + 1, d = inputday;
                            String Str_y, Str_m, Str_d;


                            Str_y = y.toString();
                            if (m < 10) {
                                Str_m = "0" + m;
                            } else {
                                Str_m = m.toString();
                            }

                            if (d < 10) {
                                Str_d = "0" + d;
                            } else {
                                Str_d = d.toString();
                            }

                            End_Date = Str_y + Str_m + Str_d;

                            Button_EndDate.setText(End_Date);

                            ColorStateList stateList = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                            Button_EndDate.setBackgroundTintList(stateList);
                            End_Date_Check = true;
                        }
                    };
                    DatePickerDialog dialog = new DatePickerDialog(DateListActivity.this,dateSetListener,
                            cyear,cmonth,cday);
                    dialog.getDatePicker().setMinDate(minDate.getTime().getTime());
                    dialog.openOptionsMenu();

                    dialog.show();
                }else{
                    Snackbar.make(getWindow().getDecorView().getRootView(), "시작날짜를 먼저 입력해주세요.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }



            }

        });

        Button_DateSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Start_Date_Check==true && End_Date_Check == true) {
                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);
                    intent.putExtra("Start_Date", Start_Date);
                    intent.putExtra("End_Date", End_Date);
                    finish();
                }else if(Start_Date_Check==false){
                    Snackbar.make(getWindow().getDecorView().getRootView(), "시작날짜를 입력해주세요.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else if(End_Date_Check==false){
                    Snackbar.make(getWindow().getDecorView().getRootView(), "종료날짜를 입력해주세요.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });

    }
}
