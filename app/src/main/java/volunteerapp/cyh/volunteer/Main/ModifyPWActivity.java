package volunteerapp.cyh.volunteer.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import volunteerapp.cyh.volunteer.Login.JoinActivity;
import volunteerapp.cyh.volunteer.Login.Object.Member;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Tools.Login;
import volunteerapp.cyh.volunteer.Tools.Retrofit.RetrofitService;

public class ModifyPWActivity extends AppCompatActivity {
    TextView name;
    TextView Button_Back;


    EditText Edit_Password1;
    EditText Edit_Password2;
    Button Button_Modify_Password;

    Member temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pw);

        name = findViewById(R.id.name);
        name.setText("비밀번호 변경");
        Button_Back = findViewById(R.id.Button_Back);
        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Edit_Password1 = findViewById(R.id.Edit_Password1);
        Edit_Password2 = findViewById(R.id.Edit_Password2);

        temp = Login.getInstance().getMember();

        Button_Modify_Password = findViewById(R.id.Button_Modify_Password);
        Button_Modify_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Edit_Password1.getText().toString().length() == 0) {
                    Toast.makeText(ModifyPWActivity.this, "비밀번호를입력하세요", Toast.LENGTH_SHORT).show();
                    Edit_Password1.requestFocus();
                    return;
                }else if (Edit_Password2.getText().toString().length() == 0) {
                    Toast.makeText(ModifyPWActivity.this, "비밀번호확인을입력하세요", Toast.LENGTH_SHORT).show();
                    Edit_Password2.requestFocus();
                    return;
                }else if(Edit_Password1.getText().toString().length() <6 ) {
                    Toast toast =Toast.makeText(ModifyPWActivity.this, "6자리이상입력해주세요", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();

                    return ;
                }else if (!Edit_Password1.getText().toString().equals(Edit_Password2.getText().toString())) {
                    Toast.makeText(ModifyPWActivity.this, "비밀번호가 일치하지않습니다.", Toast.LENGTH_SHORT).show();
                    Toast toast =Toast.makeText(ModifyPWActivity.this, "다시입력해주세요", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();

                    Edit_Password1.requestFocus();
                    return;
                }else if(checkInput()){
                    temp.setMem_pw(Edit_Password1.getText().toString());
                    Call<Void> observ = RetrofitService.getInstance().getRetrofitRequest().ModifyInfo(temp);
                        observ.enqueue(new Callback<Void>() {

                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {



                                Toast.makeText(ModifyPWActivity.this,"비밀번호 수정완료!",Toast.LENGTH_SHORT).show();
                                Login.getInstance().setMember(temp);
                                finish();



                            }else{

                                Toast.makeText(ModifyPWActivity.this,"비밀번호 수정실패!(통신에러)",Toast.LENGTH_SHORT).show();


                            }


                        }


                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d("로그", "수정 실패" + t);

                        }

                    });


                }
            }
        });

    }

    public boolean checkInput() {

        String pattern = ".*[a-zA-Z.*].*";
        String input = Edit_Password2.getText().toString();

        boolean i = Pattern.matches(pattern, input);
        if (i != true) {
            Toast.makeText(ModifyPWActivity.this, "문자를입력해주세요", Toast.LENGTH_SHORT).show();
            Log.d("jhh", "문자!");
            return false;
        }

        String input1 = Edit_Password2.getText().toString();

        boolean i1 = hasSpecialCharacter(input1);
        if (i1 != true) {
            Toast.makeText(ModifyPWActivity.this, "특수문자를입력해주세요", Toast.LENGTH_SHORT).show();
            Log.d("jhh", "특수문자!");
            return false;

        }

        String pattern2 = ".*[0-9.*].*";
        String input2 = Edit_Password2.getText().toString();


        boolean i2 = Pattern.matches(pattern2, input2);
        if (i2 != true) {
            Toast.makeText(ModifyPWActivity.this, "숫자를입력해주세요", Toast.LENGTH_SHORT).show();
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
