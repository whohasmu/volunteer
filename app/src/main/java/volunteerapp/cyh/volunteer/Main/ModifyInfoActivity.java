package volunteerapp.cyh.volunteer.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import volunteerapp.cyh.volunteer.Login.JoinActivity;
import volunteerapp.cyh.volunteer.Login.Object.Member;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Tools.Login;
import volunteerapp.cyh.volunteer.Tools.Retrofit.RetrofitService;

public class ModifyInfoActivity extends AppCompatActivity {
    TextView name;
    TextView Button_Back;

    TextView Text_Id;
    EditText Edit_Name;
    EditText Edit_Birth;
    EditText Edit_Email;
    EditText Edit_Phone;
    TextView Text_Gender;
    TextView Text_Grade;
    TextView Text_JoinDate;

    Button Button_Modify_Info;

    Member temp;

    String join_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);

        name = findViewById(R.id.name);
        Button_Back = findViewById(R.id.Button_Back);
        name.setText("회원정보 수정");

        Text_Id = findViewById(R.id.Text_Id);
        Edit_Name = findViewById(R.id.Edit_Name);
        Edit_Birth = findViewById(R.id.Edit_Birth);
        Edit_Email = findViewById(R.id.Edit_Email);
        Edit_Phone = findViewById(R.id.Edit_Phone);
        Text_Gender = findViewById(R.id.Text_Gender);
        Text_Grade = findViewById(R.id.Text_Grade);
        Text_JoinDate = findViewById(R.id.Text_JoinDate);

        temp = Login.getInstance().getMember();
        Text_Id.setText(temp.getMem_id());
        Edit_Name.setText(temp.getMem_name());
        Edit_Birth.setText(temp.getMem_birth());
        Edit_Email.setText(temp.getMem_email());
        Edit_Phone.setText(temp.getMem_phone());
        if(temp.getMem_gender().equals("0"))Text_Gender.setText("남");
        else Text_Gender.setText("여");
        Text_Grade.setText(temp.getMem_grade());
        Text_JoinDate.setText(temp.getMem_joindate());

        Button_Modify_Info = findViewById(R.id.Button_Modify_Info);
        Button_Modify_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                temp.setMem_name(Edit_Name.getText().toString());
                temp.setMem_birth(Edit_Birth.getText().toString());
                temp.setMem_email(Edit_Email.getText().toString());
                temp.setMem_phone(Edit_Phone.getText().toString());
                Call<Void> observ = RetrofitService.getInstance().getRetrofitRequest().ModifyInfo(temp);
                observ.enqueue(new Callback<Void>() {

                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {//  Log.d("jjh","전송성공");



                                Toast.makeText(ModifyInfoActivity.this,"회원정보 수정완료!",Toast.LENGTH_SHORT).show();
                                Login.getInstance().setMember(temp);
                                finish();



                            }else{

                                Toast.makeText(ModifyInfoActivity.this,"회원정보 수정실패!(통신에러)",Toast.LENGTH_SHORT).show();


                            }


                    }


                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("로그", "수정 실패" + t);

                    }

                });

            }
        });

        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
