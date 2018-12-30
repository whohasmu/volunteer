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
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import volunteerapp.cyh.volunteer.R;

public class IntegrateActivity extends AppCompatActivity {

    Button Button_Area;
    Button Button_StartDate;
    Button Button_EndDate;
    EditText Edit_Searchinput;
    Button Button_Search;


    Calendar c = Calendar.getInstance();
    int cyear = c.get(Calendar.YEAR);
    int cmonth = c.get(Calendar.MONTH);
    int cday = c.get(Calendar.DAY_OF_MONTH);
    Calendar minDate = Calendar.getInstance();

    Integer Si_Id,Gu_Id;
    String Start_Date;
    String End_Date;

    Button Button_Back;
    TextView name;

    Boolean Start_Date_Check = false, End_Date_Check = false ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrate);

        Button_Area = findViewById(R.id.Button_Area);
        Button_StartDate = findViewById(R.id.Button_StartDate);
        Button_EndDate = findViewById(R.id.Button_EndDate);
        Edit_Searchinput = findViewById(R.id.Edit_Searchinput);
        Button_Search = findViewById(R.id.Button_Search);

        name = findViewById(R.id.name);
        Button_Back = findViewById(R.id.Button_Back);

        name.setText("통합검색");

        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button_Area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntegrateActivity.this, AreaListActivity.class);
                startActivityForResult(intent,0);


            }
        });

        Button_StartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int inputyear, int inputmonth, int inputday) {
                        Log.d("로그","startDate : " + inputyear + "," + (inputmonth+1) + "," + inputday);
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

                DatePickerDialog dialog = new DatePickerDialog(IntegrateActivity.this,dateSetListener,
                        cyear,cmonth,cday);
                dialog.openOptionsMenu();
                dialog.show();
            }
        });

        Button_EndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Start_Date_Check == true) {
                    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int inputyear, int inputmonth, int inputday) {
                            Log.d("로그", "endDate : " + inputyear + 1 + "," + (inputmonth + 1) + "," + inputday);
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

                    DatePickerDialog dialog = new DatePickerDialog(IntegrateActivity.this, dateSetListener,
                            cyear, cmonth, cday);
                    dialog.getDatePicker().setMinDate(minDate.getTime().getTime());
                    dialog.openOptionsMenu();
                    dialog.show();

                }else {
                    Snackbar.make(getWindow().getDecorView().getRootView(), "시작날짜를 먼저 입력해주세요.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });


        Button_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                setResult(RESULT_OK,intent);
                intent.putExtra("Start_Date",Start_Date);
                intent.putExtra("End_Date",End_Date);
                intent.putExtra("Si_Id",Si_Id);
                intent.putExtra("Gu_Id",Gu_Id);
                intent.putExtra("Search_query",Edit_Searchinput.getText().toString());

                finish();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==0){
                Log.d("로그",data.getStringExtra("Si_Name")+ " " + data.getStringExtra("Gu_Name"));
                Si_Id = data.getIntExtra("Si_Id",0);
                Gu_Id = data.getIntExtra("Gu_Id",0);
                Button_Area.setText(data.getStringExtra("Si_Name") + " " +  data.getStringExtra("Gu_Name"));

                ColorStateList stateList = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                Button_Area.setBackgroundTintList(stateList);






            }
        }
    }
}
