package volunteerapp.cyh.volunteer.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Tools.Retrofit.RetrofitService;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 * <p>
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class IdSearchActivity extends Activity {
    TextView name;
    Button Button_Back;
    EditText edt_phone;
    EditText edt_email;
    Button btn_phone;
    Button btn_email;
    TextView txt_view_id;
    Button btn_search_id;
    Button btn_loginpage;
    Button  btn_pw_page;
    LinearLayout liner_id_search;
    LinearLayout liner_id_view;
    String id_result;

    int temp = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_search);

        name = findViewById(R.id.name);
        name.setText("아이디 찾기");
        Button_Back = findViewById(R.id.Button_Back);


        btn_search_id = findViewById(R.id.btn_search_id);
        edt_phone = findViewById(R.id.edt_phone);
        edt_email = findViewById(R.id.edt_email);
        btn_phone = findViewById(R.id.btn_phone);
        btn_email = findViewById(R.id.btn_email);
        txt_view_id = findViewById(R.id.txt_view_id);
        btn_loginpage = findViewById(R.id.btn_loginpage);
        btn_pw_page = findViewById(R.id.btn_pw_page);
        liner_id_view = findViewById(R.id.liner_id_view);
        liner_id_search = findViewById(R.id.liner_id_search);

        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });


        btn_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (edt_email.getVisibility() == View.INVISIBLE) {
                    edt_email.setVisibility(View.VISIBLE);
                } else {
                    edt_email.setVisibility(View.GONE);
                    edt_phone.setVisibility(View.VISIBLE);

                }
                liner_id_view.setVisibility(View.GONE);
                temp=1;
                //phone visible email gone
            }

        });

        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_phone.getVisibility()==View.VISIBLE);{
                    edt_phone.setVisibility(View.GONE);
                }
                edt_email.setVisibility(View.VISIBLE);
                liner_id_view.setVisibility(View.GONE);
                temp=2;
            }
        });


        btn_search_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp==1) {
                    Log.d("jjh","전화번호");
                    final Call<JsonObject> observ = RetrofitService.getInstance().getRetrofitRequest().
                            do_search_id_phone(edt_phone.getText().toString());

                    observ.enqueue(new Callback<JsonObject>() {//비동기식 enqueue 동기식 execute
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if (response.isSuccessful()) {//  Log.d("jjh","전송성공");


//




                                JsonParser jsonParser = new JsonParser();
                                JsonObject jsonObject = (JsonObject) response.body();
                                jsonObject = (JsonObject) jsonParser.parse(jsonObject.toString());


                                txt_view_id.setText("");
                                id_result = jsonObject.get("result").toString();
                                Log.d("cyh",id_result);




                                String[] res=id_result.split("\\\\\"");



//                                for (int i = 0; i < res.length; i++) {
//                                    Log.d("jjh",i+"번 "+res[i]);
//                                }



                                String tmp ="";
                                boolean check = false;
                                for(int i = 0 ; i <res.length ; i++){


                                    Log.d("jjh",i+"번 "+res[i]);

                                    if (i % 2 == 0 && i != res.length-1) {
                                        tmp += "아이디:";
                                        check = true;
                                    }

                                    if (i % 2 == 1) {
                                        tmp += res[i];
                                        tmp += "\r\n";
                                    }
                                }

                                if (!check) {
                                    tmp = "아이디 없음";
                                }


                                txt_view_id.setText(tmp);
                                liner_id_view.setVisibility(View.VISIBLE);

                            }
                        }
                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Log.d("jjh", "실패" + t);
                        }
                    });
                }else if (temp ==2){
                    Log.d("jjh","이메일");
                    final Call<JsonObject> observ = RetrofitService.getInstance().getRetrofitRequest().
                            do_search_id_email(edt_email.getText().toString());

                    observ.enqueue(new Callback<JsonObject>() {//비동기식 enqueue 동기식 execute
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if (response.isSuccessful()) {


                                JsonParser jsonParser = new JsonParser();
                                JsonObject jsonObject = (JsonObject) response.body();
                                jsonObject = (JsonObject) jsonParser.parse(jsonObject.toString());



                                txt_view_id.setText("");
                                id_result = jsonObject.get("result").toString();

                                String[] res=id_result.split("\\\\\"");

                                for (int i = 0; i < res.length; i++) {
                                    Log.d("jjh",i+"번 "+res[i]);
                                }




                                String tmp ="";
                                boolean check=false;
                                for(int i = 0 ; i <res.length ; i++){



                                        if (i % 2 == 0 && i != res.length-1) {

                                           tmp += "아이디:";
                                           check=true;
                                        }
                                        if (i % 2 == 1) {
                                            tmp += res[i];
                                            tmp += "\r\n";
                                        }
                                 }
                                if(!check){
                                    tmp = "아이디없음";
                                }


                                txt_view_id.setText(tmp);
                                liner_id_view.setVisibility(View.VISIBLE);


                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Log.d("jjh", "실패" + t);
                        }
                    });



                }
            }
        });



        btn_loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //로그인페이지로 전환.
            }
        });


        btn_pw_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdSearchActivity.this,PwSearchActivity.class);
                startActivity(intent);
                finish();  //비빌번호찾기 페이지로 전환.
            }
        });

    }
}