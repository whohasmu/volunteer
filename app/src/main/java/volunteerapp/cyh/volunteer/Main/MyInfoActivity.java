package volunteerapp.cyh.volunteer.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import volunteerapp.cyh.volunteer.Login.Object.Member;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Tools.Login;

public class MyInfoActivity extends AppCompatActivity {

    TextView name;
    TextView Button_Back;

    TextView Text_Id;
    TextView Text_Name;
    TextView Text_Birth;
    TextView Text_Email;
    TextView Text_Phone;
    TextView Text_Gender;
    TextView Text_Grade;
    TextView Text_JoinDate;

    Button Button_Modify_Info;
    Button Button_Modify_Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        name = findViewById(R.id.name);
        Button_Back = findViewById(R.id.Button_Back);
        name.setText("내정보 보기");


        Text_Id = findViewById(R.id.Text_Id);
        Text_Name = findViewById(R.id.Text_Name);
        Text_Birth = findViewById(R.id.Text_Birth);
        Text_Email = findViewById(R.id.Text_Email);
        Text_Phone = findViewById(R.id.Text_Phone);
        Text_Gender = findViewById(R.id.Text_Gender);
        Text_Grade = findViewById(R.id.Text_Grade);
        Text_JoinDate = findViewById(R.id.Text_JoinDate);




        Button_Modify_Info = findViewById(R.id.Button_Modify_Info);
        Button_Modify_Password = findViewById(R.id.Button_Modify_Password);

        Button_Modify_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this, ModifyInfoActivity.class);
                startActivity(intent);
            }
        });
        Button_Modify_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyInfoActivity.this, ModifyPWActivity.class);
                startActivity(intent);
            }
        });

        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Member LM = Login.getInstance().getMember();
        Text_Id.setText(LM.getMem_id());
        Text_Name.setText(LM.getMem_name());
        Text_Birth.setText(LM.getMem_birth());
        Text_Email.setText(LM.getMem_email());
        Text_Phone.setText(LM.getMem_phone());

        if(LM.getMem_gender().equals("0"))Text_Gender.setText("남");
        else Text_Gender.setText("여");
        Text_Grade.setText(LM.getMem_grade());
        Text_JoinDate.setText(LM.getMem_joindate());
    }
}
