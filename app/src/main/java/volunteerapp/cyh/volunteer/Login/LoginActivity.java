package volunteerapp.cyh.volunteer.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import volunteerapp.cyh.volunteer.Home.HomeActivity;
import volunteerapp.cyh.volunteer.Login.Object.LoginResult;
import volunteerapp.cyh.volunteer.Login.Object.Member;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Tools.Login;
import volunteerapp.cyh.volunteer.Tools.Retrofit.RetrofitService;

public class LoginActivity extends AppCompatActivity {
    Button btn_login;
    TextView txt_join;
    TextView txt_id_search;
    TextView txt_pw_search;
    EditText edt_login_id;
    EditText edt_login_pw;
    String login_result; // 1 =로그인성공 2 = 로그인실패

    CheckBox CheckBox_AutoLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_join = findViewById(R.id.txt_join);
        txt_id_search = findViewById(R.id.txt_search_id);
        txt_pw_search = findViewById(R.id.txt_search_pw);
        btn_login = findViewById(R.id.btn_login);
        edt_login_id = findViewById(R.id.edt_login_id);
        edt_login_pw = findViewById(R.id.edt_login_pw);

        CheckBox_AutoLogin = findViewById(R.id.CheckBox_AutoLogin);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (edt_login_id.getText().toString().length() == 0) {
                    Toast.makeText(LoginActivity.this, "아이디를입력하세요", Toast.LENGTH_SHORT).show();
                    edt_login_id.requestFocus();
                    return;
                }
                if (edt_login_pw.getText().toString().length() == 0) {
                    Toast.makeText(LoginActivity.this, "비밀번호를입력하세요", Toast.LENGTH_SHORT).show();
                    edt_login_pw.requestFocus();
                    return;
                }




                Call<LoginResult> observ  = RetrofitService.getInstance().getRetrofitRequest().
                        do_login(edt_login_id.getText().toString(),edt_login_pw.getText().toString());

                observ.enqueue(new Callback<LoginResult>() {//비동기식 enqueue 동기식 execute
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if(response.isSuccessful()) {


                            LoginResult get = response.body();

                            if (get.getLoginResult().equals("1")) {
                                Log.d("로그", "로그인성공");


                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                Login.getInstance().setMember(get.getLogin_Member());

                                if(CheckBox_AutoLogin.isChecked()) {
                                    Log.d("로그","자동로그인 체크");

                                    SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("isAuto","Y");
                                    editor.commit();
                                    editor.putString("AutoLogin_Id", Login.getInstance().getMember().getMem_id());
                                    editor.commit();
                                    editor.putString("AutoLogin_Pw", Login.getInstance().getMember().getMem_pw());
                                    editor.commit();





                                }else{
                                    Log.d("로그","자동로그인 체크해제");
                                    SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("isAuto","N");
                                    editor.commit();
                                }

                                Toast.makeText(LoginActivity.this, Login.getInstance().getMember().getMem_name() + "님, 로그인성공", Toast.LENGTH_SHORT).show();
                                finish();


                            } else
                                Toast.makeText(LoginActivity.this, "로그인실패", Toast.LENGTH_SHORT).show();

                        }



                    }
                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {

                    }


                });








            }
        });



        txt_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,JoinActivity.class);
                startActivity(intent);
            }
        });

        txt_id_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,IdSearchActivity.class);
                startActivity(intent);
            }
        });

        txt_pw_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,PwSearchActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);





        if(pref.getString("isAuto","").equals("Y")){


            Call<LoginResult> observ  = RetrofitService.getInstance().getRetrofitRequest().
                    do_login( pref.getString("AutoLogin_Id",""),pref.getString("AutoLogin_Pw",""));

            observ.enqueue(new Callback<LoginResult>() {//비동기식 enqueue 동기식 execute
                @Override
                public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                    if(response.isSuccessful()) {


                        LoginResult get = response.body();

                        if (get.getLoginResult().equals("1")) {
                            Log.d("로그", "로그인성공");


                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            Login.getInstance().setMember(get.getLogin_Member());



                            Toast.makeText(LoginActivity.this, Login.getInstance().getMember().getMem_name() + "님, 로그인성공", Toast.LENGTH_SHORT).show();
                            finish();


                        } else
                            Toast.makeText(LoginActivity.this, "로그인실패", Toast.LENGTH_SHORT).show();

                    }
                }
                @Override
                public void onFailure(Call<LoginResult> call, Throwable t) {

                }


            });
        }


    }
}