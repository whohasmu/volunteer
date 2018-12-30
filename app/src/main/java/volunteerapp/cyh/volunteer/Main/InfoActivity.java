package volunteerapp.cyh.volunteer.Main;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import volunteerapp.cyh.volunteer.Main.Adapter.FavoriteListAdapter;
import volunteerapp.cyh.volunteer.R;
import volunteerapp.cyh.volunteer.Main.Object.ItemInfo;
import volunteerapp.cyh.volunteer.Tools.Passing.GetData;
import volunteerapp.cyh.volunteer.Tools.Retrofit.RetrofitService;

public class InfoActivity extends AppCompatActivity {

    Button Button_Back;
    TextView name;
    Button Button_Favorite;


    TextView progrmSj; //봉사 제목
    TextView progrmSttusSe; //모집 상태
    TextView progrmBgnde; //시작일자
    TextView progrmEndde; //종료일자
    TextView actBeginTm; //시작시간
    TextView actEndTm; //종료시간
    TextView noticeBgnde;//모집 시작일
    TextView noticeEndde;//모집 종료일
    TextView rcritNmpr; //모집 인원
    TextView appTotal; //신청인원수
    TextView srvcClCode; //봉사 분야
    ImageView adultPosblAt; //성인 가능여부
    ImageView yngbgsPosblAt; //청소년 가능여부
    ImageView familyPosblAt; //가족 가능여부
    ImageView pbsvntPosblAt; //공무원가능여부
    ImageView grpPosblAt; //단체가능여부
    TextView nanmmbyNm; //모집기관
    TextView actPlace; //봉사장소

    TextView progrmCn; //내용

    TextView nanmmbyNmAdmn; //담당자명
    TextView telno; //전화번호
    TextView fxnum; //FAX번호
    TextView postAdres; //담당자 주소
    TextView email; //이메일


    GetData getData;

    int favorite=1; //1:노즐찾,  2:즐찾



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        /*getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_info);*/
        getData = new GetData();


        Button_Back = findViewById(R.id.Button_Back);
        name = findViewById(R.id.name);
        Button_Favorite = findViewById(R.id.Button_Favorite);


        progrmSj = findViewById(R.id.progrmSj);
        progrmSttusSe = findViewById(R.id.progrmSttusSe); //모집 상태
        progrmBgnde = findViewById(R.id.progrmBgnde); //시작일자
        progrmEndde = findViewById(R.id.progrmEndde); //종료일자
        actBeginTm = findViewById(R.id.actBeginTm); //시작시간
        actEndTm = findViewById(R.id.actEndTm); //종료시간
        noticeBgnde = findViewById(R.id.noticeBgnde);//모집 시작일
        noticeEndde = findViewById(R.id.noticeEndde);//모집 종료일
        rcritNmpr = findViewById(R.id.rcritNmpr); //모집 인원
        appTotal = findViewById(R.id.appTotal); //신청인원수
        srvcClCode = findViewById(R.id.srvcClCode); //봉사 분야
        adultPosblAt = findViewById(R.id.adultPosblAt); //성인 가능여부
        yngbgsPosblAt = findViewById(R.id.yngbgsPosblAt); //청소년 가능여부
        familyPosblAt = findViewById(R.id.familyPosblAt); //가족 가능여부
        pbsvntPosblAt = findViewById(R.id.pbsvntPosblAt); //공무원가능여부
        grpPosblAt = findViewById(R.id.grpPosblAt); //단체가능여부
        nanmmbyNm = findViewById(R.id.nanmmbyNm); //등록기관
        actPlace = findViewById(R.id.actPlace); //봉사장소

        progrmCn = findViewById(R.id.progrmCn); //내용

        nanmmbyNmAdmn = findViewById(R.id.nanmmbyNmAdmn); //담당자명
        telno = findViewById(R.id.atelno); //전화번호
        fxnum = findViewById(R.id.fxnum); //FAX번호
        postAdres = findViewById(R.id.postAdres); //담당자 주소
        email = findViewById(R.id.email); //이메일


        Intent intent = getIntent();
        String ProgrmRegistNo = intent.getStringExtra("ProgrmRegistNo");

        final ItemInfo itemInfo = getData.getDataInfo(ProgrmRegistNo);


        progrmSj.setText(itemInfo.getProgrmSj());
        progrmSttusSe.setText(itemInfo.getProgrmSttusSe());
        progrmBgnde.setText(itemInfo.getProgrmBgnde());
        progrmEndde.setText(itemInfo.getProgrmEndde());
        actBeginTm.setText(itemInfo.getActBeginTm());
        actEndTm.setText(itemInfo.getActEndTm());
        noticeBgnde.setText(itemInfo.getNoticeBgnde());
        noticeEndde.setText(itemInfo.getNoticeEndde());
        rcritNmpr.setText(itemInfo.getRcritNmpr());
        appTotal.setText(itemInfo.getAppTotal());
        srvcClCode.setText(itemInfo.getSrvcClCode());

        if(itemInfo.getAdultPosblAt().equals("Y")) adultPosblAt.setBackgroundResource(R.drawable.possible);
        else if(itemInfo.getGrpPosblAt().equals("N")) adultPosblAt.setBackgroundResource(R.drawable.impossible);

        if(itemInfo.getYngbgsPosblAt().equals("Y")) yngbgsPosblAt.setBackgroundResource(R.drawable.possible);
        else if(itemInfo.getGrpPosblAt().equals("N")) yngbgsPosblAt.setBackgroundResource(R.drawable.impossible);

        if(itemInfo.getFamilyPosblAt().equals("Y")) familyPosblAt.setBackgroundResource(R.drawable.possible);
        else if(itemInfo.getGrpPosblAt().equals("N")) familyPosblAt.setBackgroundResource(R.drawable.impossible);

        if(itemInfo.getPbsvntPosblAt().equals("Y")) pbsvntPosblAt.setBackgroundResource(R.drawable.possible);
        else if(itemInfo.getGrpPosblAt().equals("N")) pbsvntPosblAt.setBackgroundResource(R.drawable.impossible);

        if(itemInfo.getGrpPosblAt().equals("Y")) grpPosblAt.setBackgroundResource(R.drawable.possible);
        else if(itemInfo.getGrpPosblAt().equals("N")) grpPosblAt.setBackgroundResource(R.drawable.impossible);





        nanmmbyNm.setText(itemInfo.getNanmmbyNm());

        SpannableString content = new SpannableString(itemInfo.getActPlace());
        if(content!=null) {
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            actPlace.setText(content);
        }


        progrmCn.setText(itemInfo.getProgrmCn());

        nanmmbyNmAdmn.setText(itemInfo.getNanmmbyNmAdmn());

            content = new SpannableString(itemInfo.getTelno());
        if(content!=null) {
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            telno.setText(content);
        }
        fxnum.setText(itemInfo.getFxnum());
        postAdres.setText(itemInfo.getPostAdres());
        email.setText(itemInfo.getEmail());


        actPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + actPlace.getText()));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");

                startActivity(intent);
            }
        });

        postAdres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + postAdres.getText()));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");

                startActivity(intent);
            }
        });

        telno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(InfoActivity.this);
                alertDialog.setTitle("경고");
                alertDialog.setMessage("전화연결을 하시겠습니까?");
                alertDialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {




                        PermissionListener permissionListener = new PermissionListener() {
                            @Override
                            public void onPermissionGranted() { //권한이 허용 됬을 때
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telno.getText()));
                                startActivity(intent);
                            }

                            @Override
                            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                            }
                        };
                        TedPermission.with(InfoActivity.this)
                                .setPermissionListener(permissionListener)
                                .setDeniedMessage("기능사용을 위해서 권한설정이 필요합니다.\n\n[설정] > [권한]에서 권한을 부여할수있습니다.")
                                .setPermissions(Manifest.permission.CALL_PHONE)
                                .check();

                    }
                });
                alertDialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.show();

            }
        });

        Call<String> observ = RetrofitService.getInstance().getRetrofitRequest().checkFavorite("tempLogin_Id",itemInfo.getProgrmRegistNo());
        observ.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.isSuccessful()){
                    favorite = Integer.parseInt(response.body());
                    Log.d("로그","즐겨찾기 체크 성공 : " + response.body());
                    if(favorite==0) {
                        Button_Favorite.setBackgroundResource(R.drawable.unfavorite);
                    }else{
                        Button_Favorite.setBackgroundResource(R.drawable.favorite);
                    }

                }else{
                    Log.d("로그","(즐찾)음..."+response.message());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("로그","즐겨찾기 체크 실패! : " + t);
            }
        });


        Button_Favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Animation likeAnim = AnimationUtils.loadAnimation(InfoActivity.this,R.anim.anim_like);
                if(favorite==0){

                    Snackbar.make(getWindow().getDecorView().getRootView(), "즐겨찾기에 추가되었습니다.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Button_Favorite.setBackgroundResource(R.drawable.favorite);
                    favorite=1;
                    v.startAnimation(likeAnim);

                    Call<Void> observ  = RetrofitService.getInstance().getRetrofitRequest().AddFavorite("tempLogin_Id",itemInfo.getProgrmRegistNo());

                    observ.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()){
                                Log.d("로그","즐찾 성공!");
                            }else{
                                Log.d("로그","(즐찾)음...");
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d("로그","즐찾 실패!");
                        }
                    });



                }else{
                    Snackbar.make(getWindow().getDecorView().getRootView(), "즐겨찾기에서 삭제되었습니다.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Button_Favorite.setBackgroundResource(R.drawable.unfavorite);
                    favorite=0;

                    Call<String> observ  = RetrofitService.getInstance().getRetrofitRequest().RemoveFavorite("tempLogin_Id",itemInfo.getProgrmRegistNo());

                    observ.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.isSuccessful()){
                                Log.d("로그","즐찾 삭제 성공!");
                            }else{
                                Log.d("로그","(즐찾삭제)음...");
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("로그","즐찾 삭제 실패!");
                        }
                    });

                }
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
