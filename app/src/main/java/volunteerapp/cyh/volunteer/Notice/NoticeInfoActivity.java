package volunteerapp.cyh.volunteer.Notice;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;

import android.util.Log;

import android.view.View;

import android.widget.Button;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Tools.Retrofit.RetrofitService;

public class NoticeInfoActivity extends AppCompatActivity {



    TextView name;
    TextView Text_Notice_Title;
    TextView Text_Notice_Content;
    TextView Text_Notice_Write_Date;
    Button Button_Back;

    LinearLayout Layout_Img;
    ArrayList<String> imgUrls = new ArrayList<>();
    String Id;

    String ImgUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_info);

        name = findViewById(R.id.name);
        Text_Notice_Title = findViewById(R.id.Text_Notice_Title);
        Text_Notice_Content = findViewById(R.id.Text_Notice_Content);
        Text_Notice_Write_Date = findViewById(R.id.Text_Notice_Write_Date);
        Layout_Img = findViewById(R.id.Layout_Img);

        Button_Back = findViewById(R.id.Button_Back);
        Button_Back.setBackgroundResource(R.drawable.back_white);


        Intent intent = getIntent();
        name.setText("");

        Id = intent.getStringExtra("Notice_Id");
        Text_Notice_Title.setText(intent.getStringExtra("Notice_Title"));
        Text_Notice_Write_Date.setText(intent.getStringExtra("Notice_Write_Date"));

        String Content = intent.getStringExtra("Notice_Content");





        getImgs(Content);

        for(int i=0;i<imgUrls.size();i++) {
            Log.d("로그", "이미지링크 : " + imgUrls.get(i));
            ImageView imageView = new ImageView(this);
            Layout_Img.addView(imageView);

            ImgUrl = imgUrls.get(i);

            Picasso.get().load(imgUrls.get(i)).into(imageView);
            Layout_Img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NoticeInfoActivity.this, ImgFullActivity.class);
                    intent.putExtra("ImgUrl",ImgUrl);
                    startActivity(intent);
                }
            });


        }
        Text_Notice_Content.setText(deleteTag(Content));


        Call<Void> observ = RetrofitService.getInstance().getRetrofitRequest().AddNoticeViews(Id);
        observ.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if(response.isSuccessful()){

                    Log.d("로그","NoticeViews++ 성공 : " + response.body());



                }else{
                    Log.d("로그","(NoticeViews++)음..."+response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("로그","NoticeViews++ 실패! : " + t);
            }
        });



        Button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });







    }






    public String deleteTag(String t){
        t = t.replaceAll("</p>","\n");
        t = t.replaceAll("&nbsp;"," ");
        t = t.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        return t;


    }

    public void getImgs(String _html)
    {

        Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); //img 태그 src 추출 정규표현식
        Matcher matcher = pattern.matcher(_html);

        while(matcher.find()){

            imgUrls.add(matcher.group(1));
        }


            /*Thread mThread = new Thread() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(ImgUrl);


                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true); // 서버로 부터 응답 수신
                        conn.connect();

                        InputStream is = conn.getInputStream(); // InputStream 값 가져오기
                        bitmap = BitmapFactory.decodeStream(is); // Bitmap으로 변환

                    } catch (MalformedURLException e) {
                        e.printStackTrace();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            mThread.start(); // Thread 실행

            try {
                // 메인 Thread는 별도의 작업 Thread가 작업을 완료할 때까지 대기해야한다
                // join()를 호출하여 별도의 작업 Thread가 종료될 때까지 메인 Thread가 기다리게 한다
                mThread.join();

                // 작업 Thread에서 이미지를 불러오는 작업을 완료한 뒤
                // UI 작업을 할 수 있는 메인 Thread에서 ImageView에 이미지를 지정한다
                imageView.setImageBitmap(bitmap);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
    }



}









