package volunteerapp.cyh.volunteer.QnA;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import volunteerapp.cyh.volunteer.QnA.Object.QnA;
import volunteerapp.cyh.volunteer.R;

public class QnAInfoActivity extends AppCompatActivity {

    TextView Text_Title;
    TextView Text_Date_Q;
    TextView Text_Writer;
    TextView Text_Views;
    TextView Text_Content_Q;

    TextView Text_Date_A;
    TextView Text_Content_A;

    Button Button_Back;
    TextView name;


    QnA thisQnA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_info);


        Button_Back = findViewById(R.id.Button_Back);
        name = findViewById(R.id.name);

        Text_Title = findViewById(R.id.Text_Title);
        Text_Date_Q = findViewById(R.id.Text_Date_Q);
        Text_Writer = findViewById(R.id.Text_Writer);
        Text_Views = findViewById(R.id.Text_Views);
        Text_Content_Q = findViewById(R.id.Text_Content_Q);
        Text_Date_A = findViewById(R.id.Text_Date_A);
        Text_Content_A = findViewById(R.id.Text_Content_A);



        Button_Back.setBackgroundResource(R.drawable.back_white);
        name.setText("");

        Intent intent = getIntent();

        thisQnA = new QnA(intent.getStringExtra("Id"), intent.getStringExtra("Title"),
                intent.getStringExtra("Writer"),intent.getStringExtra("Date_Q"),
                intent.getStringExtra("Views"),intent.getStringExtra("Content_Q"),
                intent.getStringExtra("Isanswer"),intent.getStringExtra("Date_A"),
                intent.getStringExtra("Content_A"));

        Text_Title.setText(thisQnA.getTitle());
        Text_Date_Q.setText(thisQnA.getDate_Q());
        Text_Writer.setText(thisQnA.getWriter());
        Text_Views.setText(thisQnA.getHit());
        Text_Content_Q.setText(thisQnA.getContent_Q());

        if(thisQnA.getIsanswer().equals("1")){
            Text_Date_A.setText("-");
            Text_Content_A.setText("답변 대기중입니다...");
        }else{
            Text_Date_A.setText(thisQnA.getDate_A());
            Text_Content_A.setText(thisQnA.getContent_A());
        }


        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
