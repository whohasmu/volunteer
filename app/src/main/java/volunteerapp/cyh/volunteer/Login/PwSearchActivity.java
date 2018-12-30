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
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
public class PwSearchActivity extends Activity {
    Button Button_Back;
    TextView name;



    LinearLayout Layout_Id;
    EditText Edit_Id;
    Button Button_Id;

    LinearLayout Layout_Email;
    EditText Edit_Email;
    Button Button_Email;

    String res_pw_id ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw_search);

        Button_Back= findViewById(R.id.Button_Back);
        name = findViewById(R.id.name);
        name.setText("비밀번호 찾기");

        Layout_Id = findViewById(R.id.Layout_Id);
        Edit_Id = findViewById(R.id.Edit_Id);
        Button_Id = findViewById(R.id.Button_Id);

        Layout_Email = findViewById(R.id.Layout_Email);
        Edit_Email = findViewById(R.id.Edit_Email);
        Button_Email = findViewById(R.id.Button_Email);







        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button_Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<JsonObject> observ = RetrofitService.getInstance().getRetrofitRequest().do_search_id_confirm(Edit_Id.getText().toString());

                observ.enqueue(new Callback<JsonObject>() {//비동기식 enqueue 동기식 execute
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {//  Log.d("jjh","전송성공");


                            JsonParser jsonParser = new JsonParser();
                            JsonObject jsonObject = (JsonObject) response.body();
                            jsonObject = (JsonObject) jsonParser.parse(jsonObject.toString());





                            res_pw_id  = jsonObject.get("result").toString();

                            if(res_pw_id .equals("1")){

                                Layout_Id.setVisibility(View.INVISIBLE);
                                Layout_Email.setVisibility(View.VISIBLE);

                            }else {

                                Toast.makeText(PwSearchActivity.this, "아이디가 존재하지않습니다",Toast.LENGTH_SHORT).show();
                            }


                        }

                    }


                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.d("로그", "실패" + t);

                    }
                });

            }

        });


        Button_Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (Edit_Email.getText().toString().length() == 0) {
                    Toast.makeText(PwSearchActivity.this, "이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    Edit_Email.requestFocus();
                    return;
                }else{
                    Call<String> observ = RetrofitService.getInstance().getRetrofitRequest().getPasswordEmail(Edit_Email.getText().toString(),Edit_Id.getText().toString());
                    observ.enqueue(new Callback<String>() {//비동기식 enqueue 동기식 execute
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {//  Log.d("jjh","전송성공");

                                /*JsonParser jsonParser = new JsonParser();
                                JsonObject jsonObject = (JsonObject) response.body();
                                jsonObject = (JsonObject) jsonParser.parse(jsonObject.toString());*/


                                if(response.body().equals("0")) {
                                    Toast.makeText(PwSearchActivity.this, "이메일이 일치하지 않아요.",Toast.LENGTH_SHORT).show();


                                }else{
                                    Toast.makeText(PwSearchActivity.this, "이메일로 임시비밀번호가 발급되었어요.",Toast.LENGTH_SHORT).show();
                                    finish();
                                }




                            }

                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("로그", "실패" + t);

                        }
                    });
                }

            }
        });


    }
}