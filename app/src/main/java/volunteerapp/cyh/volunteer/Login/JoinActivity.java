package volunteerapp.cyh.volunteer.Login;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import volunteerapp.cyh.volunteer.Login.Object.Member;
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
public class JoinActivity extends Activity {
    TextView name;
    Button Button_Back;

    EditText edt_join_id;
    EditText edt_join_pw;
    EditText edt_join_pw2;
    EditText edt_join_name;
    EditText edt_join_phoneNum;
    EditText edt_join_email;
    Button btn_join;
    ImageView setImage;
    RadioGroup radio_group;
    RadioButton check_male;
    RadioButton check_female;
    String join_res;
    String gender ; //gender =0 남자 gender =1 여자
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);


        name = findViewById(R.id.name);
        name.setText("회원가입");
        Button_Back = findViewById(R.id.Button_Back);


        btn_join = findViewById(R.id.btn_join);
        edt_join_id = findViewById(R.id.edt_join_id);
        edt_join_pw = findViewById(R.id.edt_join_pw);
        edt_join_pw2 = findViewById(R.id.edt_join_pw2);
        edt_join_name = findViewById(R.id.edt_join_name);
        edt_join_phoneNum = findViewById(R.id.edt_join_phoneNum);
        edt_join_email = findViewById(R.id.edt_join_email);
        setImage = findViewById(R.id.setImage);
        radio_group = findViewById(R.id.radio_group);
        check_male = findViewById(R.id.check_male);
        check_female = findViewById(R.id.check_female);
        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {



                if (checkedId == R.id.check_male) {
                    Log.d("jjh", "check_male");
                    gender = "0";
                } else if (checkedId == R.id.check_female) {
                    Log.d("jjh", "check_female");
                    gender = "1";
                }



            }
        });
        btn_join.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (edt_join_id.getText().toString().length() == 0) {
                    Toast.makeText(JoinActivity.this, "아이디를입력하세요", Toast.LENGTH_SHORT).show();
                    edt_join_id.requestFocus();
                    return;
                }
                if (edt_join_pw.getText().toString().length() == 0) {
                    Toast.makeText(JoinActivity.this, "비밀번호를입력하세요", Toast.LENGTH_SHORT).show();
                    edt_join_pw.requestFocus();
                    return;
                }
                if (edt_join_pw2.getText().toString().length() == 0) {
                    Toast.makeText(JoinActivity.this, "비밀번호확인을입력하세요", Toast.LENGTH_SHORT).show();
                    edt_join_pw2.requestFocus();
                    return;
                }
                if (edt_join_name.getText().toString().length() == 0) {
                    Toast.makeText(JoinActivity.this, "이름을입력하세요", Toast.LENGTH_SHORT).show();
                    edt_join_name.requestFocus();
                    return;
                }
                if (edt_join_phoneNum.getText().toString().length() == 0) {
                    Toast.makeText(JoinActivity.this, "전화번호를입력하세요", Toast.LENGTH_SHORT).show();
                    edt_join_phoneNum.requestFocus();
                    return;
                }
                if (edt_join_email.getText().toString().length() == 0) {
                    Toast.makeText(JoinActivity.this, "이메일을입력하세요", Toast.LENGTH_SHORT).show();
                    edt_join_email.requestFocus();
                    return;
                }
                if (!edt_join_pw.getText().toString().equals(edt_join_pw2.getText().toString())) {
                    Toast.makeText(JoinActivity.this, "비밀번호가 일치하지않습니다.", Toast.LENGTH_SHORT).show();
                    Toast toast =Toast.makeText(JoinActivity.this, "다시입력해주세요", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    edt_join_pw.setText("");
                    edt_join_pw2.setText("");
                    edt_join_pw.requestFocus();
                    return;
                }
                if(edt_join_pw.getText().toString().length() <6 ) {
                    Toast toast =Toast.makeText(JoinActivity.this, "6자리이상입력해주세요", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    Log.d("jjh", "6자리이상입력하세요");
                    return ;
                }
                if(check_male.isChecked()==false&&check_female.isChecked()==false){
                    Toast.makeText(JoinActivity.this, "선택해주세요",Toast.LENGTH_SHORT).show();
                }

                if (checkInput()) {

            Member member = new Member(0l, edt_join_id.getText().toString(), edt_join_pw.getText().toString(),edt_join_name.getText().toString(),
                    "0",edt_join_email.getText().toString(),edt_join_phoneNum.getText().toString(),gender,"0","0");
            Call<JsonObject> observ = RetrofitService.getInstance().getRetrofitRequest().do_join(member);
            observ.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {//  Log.d("jjh","전송성공");

                        JsonParser jsonParser = new JsonParser();
                        JsonObject jsonObject = (JsonObject) response.body();
                        jsonObject = (JsonObject) jsonParser.parse(jsonObject.toString());

                        join_res=jsonObject.get("result").toString();
                            Log.d("jjh",join_res);
                            if(join_res.equals("\"1\"")){
                                Log.d("jjh","가입완료");
                                Toast.makeText(JoinActivity.this,"회원가입완료!",Toast.LENGTH_SHORT).show();

                            finish();
                            }else{
                                Log.d("jjh","가입실패");
                                Toast.makeText(JoinActivity.this,"회원가입실패!",Toast.LENGTH_SHORT).show();
                                edt_join_id.setText("");
                        }
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





        edt_join_pw2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {


                if(edt_join_pw2.length() ==0){
                    setImage.setVisibility(View.GONE);
                }else{
                    setImage.setVisibility(View.VISIBLE);
                }


            }

        });

        edt_join_pw2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (checkInput()) {

                        if(edt_join_pw.getText().toString().equals(edt_join_pw2.getText().toString())){
                            setImage.setImageResource(R.drawable.check);
//                            btn_join.setBackgroundColor(Color.rgb(255,145,164));
                            btn_join.setBackgroundResource(R.drawable.effect_button);

                        }else {
                            setImage.setImageResource(R.drawable.x);
//                            btn_join.setBackgroundColor(btn_join.getResources().getColor(R.color.colorPrimaryDark));
                            btn_join.setBackgroundResource(R.drawable.id_search);
                            /*btn_join.setBackgroundColor(Color.rgb(146,219,254));*//*mContext*/



                        }

                    }
                }
            }
        });

    }

    public boolean checkInput() {

        String pattern = ".*[a-zA-Z.*].*";
        String input = edt_join_pw2.getText().toString();

        boolean i = Pattern.matches(pattern, input);
        if (i != true) {
            Toast.makeText(JoinActivity.this, "문자를입력해주세요", Toast.LENGTH_SHORT).show();
            Log.d("jhh", "문자!");
            return false;
        }

        String input1 = edt_join_pw2.getText().toString();

        boolean i1 = hasSpecialCharacter(input1);
        if (i1 != true) {
            Toast.makeText(JoinActivity.this, "특수문자를입력해주세요", Toast.LENGTH_SHORT).show();
            Log.d("jhh", "특수문자!");
            return false;

        }

        String pattern2 = ".*[0-9.*].*";
        String input2 = edt_join_pw2.getText().toString();


        boolean i2 = Pattern.matches(pattern2, input2);
        if (i2 != true) {
            Toast.makeText(JoinActivity.this, "숫자를입력해주세요", Toast.LENGTH_SHORT).show();
            Log.d("jhh", "숫자!");
            return false;
        }

        return true;
    }
    public static boolean hasSpecialCharacter(String string) {
        if (TextUtils.isEmpty(string)) {
            return false;
        }

        for (int i = 0; i < string.length(); i++) {
            if (!Character.isLetterOrDigit(string.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}


//                boolean i1 = false, ii = false;
//                i1 = hasSpecialCharacter(input);
//                ii = Pattern.matches(pattern1, input);
//                if(i1!=true)
//                {
//                    Toast.makeText(JoinActivity.this, "특수문자", Toast.LENGTH_SHORT).show();
//                    Log.d("jjh","특수문자없음");
//                }
//                if(!ii){
//                    Log.d("jjh","특수문자없음2");
//                }

//                    String pattern1 = ".*[`^].*";