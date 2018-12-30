package volunteerapp.cyh.volunteer.Tools.Retrofit;



import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import volunteerapp.cyh.volunteer.Login.Object.LoginResult;
import volunteerapp.cyh.volunteer.Login.Object.Member;
import volunteerapp.cyh.volunteer.Notice.Object.Board;
import volunteerapp.cyh.volunteer.Party.Object.Comments;
import volunteerapp.cyh.volunteer.Party.Object.Party;
import volunteerapp.cyh.volunteer.QnA.Object.QnA;

/**
 * Created by Administrator on 2018-01-29.
 */

public interface RetrofitRequest {
   /* @GET("getBookInfo.do")
    Call<Book> getBookInfo();

    @GET("getBookList.do")
    Call<ArrayList<Book>> getBookList();

    @GET("cal.do")
    Call<Result> cal(@Query("num1") Integer n1, @Query("num2") Integer n2);

    @GET("getRandomList.do")
    Call<ArrayList<Integer>> getRandomList(@Query("num") Integer n);

    @FormUrlEncoded
    @POST("inputMember.do")
    Call<Void> inputMember(@Field("name") String name, @Field("age") Integer age);



    @FormUrlEncoded
    @POST("inputMemo.do")
    Call<Void> inputMemo(@Field("content") String content);
*/


    @GET("Android/getFavoriteList.do")
    Call<ArrayList<String>> getFavoriteList(@Query("Login_Id")String Login_Id);

    @FormUrlEncoded
    @POST("Android/AddFavorite.do")
    Call<Void> AddFavorite(@Field("Login_Id") String Login_Id,@Field("Content") String Content);

    @FormUrlEncoded
    @POST("Android/RemoveFavorite.do")
    Call<String> RemoveFavorite(@Field("Login_Id") String Login_Id,@Field("Content") String Content);

    @GET("Android/checkFavorite.do")
    Call<String> checkFavorite(@Query("Login_Id")String Login_Id, @Query("Content") String Content);

    @GET("Android/getNoticeList.do")
    Call<ArrayList<Board>> getNoticeList();

    @FormUrlEncoded
    @POST("Android/AddNoticeViews.do")
    Call<Void> AddNoticeViews(@Field ("Id") String  Id);

    @GET("Android/getQnAList.do")
    Call<ArrayList<QnA>> getQnAList();

    @FormUrlEncoded
    @POST("Android/AddQnAViews.do")
    Call<Void> AddQnAViews(@Field ("Id") String  Id);

    @FormUrlEncoded
    @POST("Android/AddQnA.do")
    Call<Void> AddQnA(@Field ("Title") String  Title,@Field("Login_Id") String Login_Id, @Field ("Date_Q") String Date_Q, @Field("Content_Q") String Content_Q);


    @GET("Android/getPartyList.do")
    Call<ArrayList<Party>> getPartyList();

    @FormUrlEncoded
    @POST("Android/AddPartyViews.do")
    Call<Void> AddPartyViews(@Field ("Id") String  Id);

    @FormUrlEncoded
    @POST("Android/AddParty.do")
    Call<Void> AddParty(@Field ("Title") String  Title,@Field ("Login_Id") String  Login_Id,@Field ("Content") String  Content);

    @GET("Android/getPartyCommentCount.do")
    Call<String> getPartyCommentCount(@Query("Id") String Id);

    @GET("Android/getPartyCommentList.do")
    Call<ArrayList<Comments>> getPartyCommentList(@Query("Id") String Id);

    @FormUrlEncoded
    @POST("Android/AddPartyComment.do")
    Call<Void> AddPartyComment(@Field("Party_Id") String Party_Id,@Field ("Login_Id") String  Login_Id,@Field ("Content") String  Content);


    //String login_id,=이부분에서 내가 보낼값들을 넣어주면된다.

    /*훈*/

    @FormUrlEncoded
    @POST("Android/vols_login.do")                              //로그인 (아이디 비빌번호 보내기)
    Call<LoginResult> do_login(@Field("login_id") String login_id, @Field("login_pw") String login_pw);

    @FormUrlEncoded
    @POST("Android/vols_search_id_phone.do")
    Call<JsonObject> do_search_id_phone (@Field("search_phone") String search_phone  ); //아이디찾기 ( 번호보내기)

    @FormUrlEncoded
    @POST("Android/vols_search_id_email.do")
    Call<JsonObject> do_search_id_email (@Field("search_email") String search_email  ); //아이디찾기 (이메일보내기)

    @FormUrlEncoded
    @POST("Android/vols_id_check.do")  //url 수정
    Call<JsonObject> do_search_id_confirm(@Field("search_id_confirm") String search_id_confirm  ); //비밀번호찾기 (아이디보내기)

    @FormUrlEncoded
    @POST("Android/vols_email.do")
    Call<String> getPasswordEmail(@Field("email") String email, @Field("id") String id ); //비밀번호찾기 (이메일보내기)

    @Headers("Content-Type: application/json")
    @POST("Android/vols_join.do")
    Call<JsonObject> do_join(@Body Member member ); //회원가입 정보보내기  // 객체넘기기

    @Headers("Content-Type: application/json")
    @POST("Android/vols_ModifyInfo.do")
    Call<Void> ModifyInfo(@Body Member member );



}
