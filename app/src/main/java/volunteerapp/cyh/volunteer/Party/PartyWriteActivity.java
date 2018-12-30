package volunteerapp.cyh.volunteer.Party;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Tools.Login;
import volunteerapp.cyh.volunteer.Tools.Retrofit.RetrofitService;

public class PartyWriteActivity extends AppCompatActivity {


    TextView name;
    Button Button_Back;

    EditText Edit_Title;
    EditText Edit_Content;
    FloatingActionButton Button_Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_write);

        name = findViewById(R.id.name);
        Button_Back = findViewById(R.id.Button_Back);
        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        name.setText("글작성");

        Edit_Title = findViewById(R.id.Edit_Title);
        Edit_Content = findViewById(R.id.Edit_Content);
        Button_Add = findViewById(R.id.Button_Add);




        Button_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Void> observ = RetrofitService.getInstance().getRetrofitRequest().AddParty(Edit_Title.getText().toString(),
                        Login.getInstance().getMember().getMem_name(),Edit_Content.getText().toString());
                observ.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if(response.isSuccessful()){

                            Log.d("로그","Group등록 성공 : " + response.body());

                            Intent intent = getIntent();
                            setResult(RESULT_OK,intent);
                            finish();

                        }else{
                            Log.d("로그","(Group등록)음..."+response.message());
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("로그","Group등록 실패! : " + t);
                    }
                });

            }
        });
    }
}
