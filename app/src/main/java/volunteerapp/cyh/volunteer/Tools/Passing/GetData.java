package volunteerapp.cyh.volunteer.Tools.Passing;

import android.os.StrictMode;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import volunteerapp.cyh.volunteer.Notice.Object.Board;
import volunteerapp.cyh.volunteer.Main.Object.Area;
import volunteerapp.cyh.volunteer.Main.Object.Item;
import volunteerapp.cyh.volunteer.Main.Object.ItemInfo;
import volunteerapp.cyh.volunteer.Tools.Retrofit.RetrofitService;

public class GetData {


    ArrayList<Item> dataList = new ArrayList<>();
    ArrayList<Board> noticeList = new ArrayList<>();



    public ArrayList<Item> getDataList(Integer pageNo) {
        StrictMode.enableDefaults();


        boolean initem = false, inGugunCd = false, inNanmmbyNm = false,
                inProgrmBgnde = false, inProgrmEndde = false, inProgrmRegistNo = false,
                inProgrmSj = false, inProgrmSttusSe = false, inSidoCd = false;


        String GugunCd = null, NanmmbyNm = null, ProgrmBgnde = null, ProgrmEndde = null,
                ProgrmRegistNo = null, ProgrmSj = null, ProgrmSttusSe = null,
                SidoCd = null;
        dataList.clear();

        try {
            URL url = new URL("http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService/getVltrSearchWordList?numOfRows=50&pageNo="+pageNo.toString()
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            Log.d("로그","파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {

                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("gugunCd")) { inGugunCd = true;}
                        if (parser.getName().equals("nanmmbyNm")) { inNanmmbyNm = true; }
                        if (parser.getName().equals("progrmBgnde")) { inProgrmBgnde = true; }

                        if (parser.getName().equals("progrmEndde")) { inProgrmEndde = true; }

                        if (parser.getName().equals("progrmRegistNo")) { inProgrmRegistNo = true; }
                        if (parser.getName().equals("progrmSj")) { inProgrmSj = true; }
                        if (parser.getName().equals("progrmSttusSe")) { inProgrmSttusSe = true; }
                        if (parser.getName().equals("sidoCd")) { inSidoCd = true; }
                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
                            Log.d("로그", "error...");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if (inGugunCd) { GugunCd = parser.getText();inGugunCd = false; }
                        if (inNanmmbyNm) { NanmmbyNm = parser.getText();inNanmmbyNm = false; }
                        if (inProgrmBgnde) { ProgrmBgnde = parser.getText();inProgrmBgnde = false; }
                        if (inProgrmEndde) { ProgrmEndde = parser.getText();inProgrmEndde = false; }
                        if (inProgrmRegistNo) { ProgrmRegistNo = parser.getText();inProgrmRegistNo = false; }
                        if (inProgrmSj) { ProgrmSj = parser.getText();inProgrmSj = false; }
                        if (inProgrmSttusSe) { ProgrmSttusSe = parser.getText();inProgrmSttusSe = false; }
                        if (inSidoCd) { SidoCd = parser.getText();inSidoCd = false; }

                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            /*Log.d("로그", "GugunCd : " + GugunCd + "\n NanmmbyNm  : " + NanmmbyNm  + "\n ProgrmBgnde  : " + ProgrmBgnde
                                    + "\n ProgrmEndde  : " + ProgrmEndde  + "\n ProgrmRegistNo  : " + ProgrmRegistNo  + "\n");*/
                            Log.d("로그","불러오는중...");
                            initem = false;
                            Item item = new Item(GugunCd, NanmmbyNm, ProgrmBgnde, ProgrmEndde, ProgrmRegistNo, ProgrmSj, ProgrmSttusSe, SidoCd);

                            dataList.add(item);
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            Log.d("로그", "에러가...났습니다..");
        }
        Log.d("로그","불러오기 완료!!");
        return dataList;
    }


    public ItemInfo getDataInfo(String ProgrmRegistNo) {
        StrictMode.enableDefaults();
        ItemInfo item = new ItemInfo();


        boolean initem = false, inactBeginTm = false, inactEndTm = false,
                inactPlace = false, inadultPosblAt = false, inappTotal = false,
                inemail = false, infamilyPosblAt = false, infxnum = false, ingrpPosblAt = false,
                ingugunCd = false, innanmmbyNm = false, innanmmbyNmAdmn = false, innoticeBgnde = false,
                innoticeEndde = false, inpbsvntPosblAt = false, inpostAdres = false,inprogrmBgnde = false,
                inprogrmCn = false, inprogrmEndde = false, inprogrmRegistNo = false,
                inprogrmSj = false, inprogrmSttusSe = false, inrcritNmpr = false, insidoCd = false,
                insrvcClCode = false, intelno = false, inyngbgsPosblAt = false;



        String actBeginTm = "", actEndTm = "", actPlace = "",
                adultPosblAt = "", appTotal = "", email = "",
                familyPosblAt = "", fxnum = "", grpPosblAt = "",
                gugunCd = "", nanmmbyNm = "", nanmmbyNmAdmn = "",
                noticeBgnde = "", noticeEndde = "", pbsvntPosblAt = "",
                postAdres = "", progrmBgnde = "",  progrmCn = "",
                progrmEndde = "", progrmRegistNo = "", progrmSj = "",
                progrmSttusSe = "", rcritNmpr = "", sidoCd = "",
                srvcClCode = "", telno = "", yngbgsPosblAt = "";


        try {
            URL url = new URL("http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService/getVltrPartcptnItem?progrmRegistNo="+ProgrmRegistNo
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            Log.d("로그","상세데이터 파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {

                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("actBeginTm")) inactBeginTm= true;
                        if (parser.getName().equals("actEndTm"))  inactEndTm = true;
                        if (parser.getName().equals("actPlace")) inactPlace = true;
                        if (parser.getName().equals("adultPosblAt")) inadultPosblAt = true;
                        if (parser.getName().equals("appTotal")) inappTotal = true;
                        if (parser.getName().equals("email")) inemail = true;
                        if (parser.getName().equals("familyPosblAt"))infamilyPosblAt = true;
                        if (parser.getName().equals("fxnum")) infxnum = true;
                        if (parser.getName().equals("grpPosblAt")) ingrpPosblAt= true;
                        if (parser.getName().equals("gugunCd")) ingugunCd = true;
                        if (parser.getName().equals("nanmmbyNm")) innanmmbyNm= true;
                        if (parser.getName().equals("nanmmbyNmAdmn")) innanmmbyNmAdmn = true;
                        if (parser.getName().equals("noticeBgnde")) innoticeBgnde= true;
                        if (parser.getName().equals("noticeEndde"))  innoticeEndde = true;
                        if (parser.getName().equals("pbsvntPosblAt")) inpbsvntPosblAt = true;
                        if (parser.getName().equals("postAdres")) inpostAdres = true;
                        if (parser.getName().equals("progrmBgnde")) inprogrmBgnde = true;
                        if (parser.getName().equals("progrmCn"))  inprogrmCn = true;
                        if (parser.getName().equals("progrmEndde")) inprogrmEndde= true;
                        if (parser.getName().equals("progrmRegistNo")) inprogrmRegistNo = true;
                        if (parser.getName().equals("progrmSj")) inprogrmSj = true;
                        if (parser.getName().equals("progrmSttusSe")) inprogrmSttusSe = true;
                        if (parser.getName().equals("rcritNmpr")) inrcritNmpr = true;
                        if (parser.getName().equals("sidoCd"))insidoCd = true;
                        if (parser.getName().equals("srvcClCode")) insrvcClCode = true;
                        if (parser.getName().equals("telno")) intelno = true;
                        if (parser.getName().equals("yngbgsPosblAt"))inyngbgsPosblAt = true;


                        break;

                    case XmlPullParser.TEXT:
                        if (inactBeginTm) {  actBeginTm  = parser.getText(); inactBeginTm = false; }
                        if (inactEndTm) {  actEndTm  = parser.getText(); inactEndTm = false; }
                        if (inactPlace) {  actPlace  = parser.getText(); inactPlace = false; }
                        if (inadultPosblAt ) {  adultPosblAt  = parser.getText(); inadultPosblAt  = false; }
                        if (inappTotal ) {  appTotal  = parser.getText(); inappTotal  = false; }
                        if (inemail ) {  email  = parser.getText(); inemail  = false; }
                        if (infamilyPosblAt ) {  familyPosblAt  = parser.getText(); infamilyPosblAt  = false; }
                        if (infxnum ) {  fxnum  = parser.getText(); infxnum  = false; }
                        if (ingrpPosblAt ) {  grpPosblAt  = parser.getText(); ingrpPosblAt  = false; }
                        if (ingugunCd ) {  gugunCd  = parser.getText(); ingugunCd  = false; }
                        if (innanmmbyNm ) {  nanmmbyNm  = parser.getText(); innanmmbyNm  = false; }
                        if (innanmmbyNmAdmn ) {  nanmmbyNmAdmn  = parser.getText(); innanmmbyNmAdmn  = false; }
                        if (innoticeBgnde ) {  noticeBgnde  = parser.getText(); innoticeBgnde  = false; }
                        if (innoticeEndde ) {  noticeEndde  = parser.getText(); innoticeEndde  = false; }
                        if (inpbsvntPosblAt ) {  pbsvntPosblAt  = parser.getText(); inpbsvntPosblAt  = false; }
                        if (inpostAdres ) {  postAdres  = parser.getText(); inpostAdres  = false; }
                        if (inprogrmBgnde) {  progrmBgnde  = parser.getText(); inprogrmBgnde = false; }
                        if (inprogrmCn ) {  progrmCn  = parser.getText(); inprogrmCn  = false; }
                        if (inprogrmEndde ) {  progrmEndde  = parser.getText(); inprogrmEndde  = false; }
                        if (inprogrmRegistNo ) {  progrmRegistNo  = parser.getText(); inprogrmRegistNo  = false; }
                        if (inprogrmSj ) {  progrmSj  = parser.getText(); inprogrmSj  = false; }
                        if (inprogrmSttusSe ) {  progrmSttusSe  = parser.getText(); inprogrmSttusSe  = false; }
                        if (inrcritNmpr ) {  rcritNmpr  = parser.getText(); inrcritNmpr  = false; }
                        if (insidoCd ) {  sidoCd  = parser.getText(); insidoCd  = false; }
                        if (insrvcClCode ) {  srvcClCode  = parser.getText(); insrvcClCode  = false; }
                        if (intelno ) {  telno  = parser.getText(); intelno  = false; }
                        if (inyngbgsPosblAt ) {  yngbgsPosblAt  = parser.getText(); inyngbgsPosblAt  = false; }




                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {

                            initem = false;
                            item = new ItemInfo(actBeginTm,actEndTm,actPlace,adultPosblAt,appTotal,email,familyPosblAt,fxnum,grpPosblAt,gugunCd,nanmmbyNm,nanmmbyNmAdmn,noticeBgnde,noticeEndde,pbsvntPosblAt,
                                    postAdres,progrmBgnde,progrmCn,progrmEndde,progrmRegistNo,progrmSj,progrmSttusSe,rcritNmpr,sidoCd,srvcClCode,telno,yngbgsPosblAt);


                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            Log.d("로그", "상세데이터 에러가...났습니다..");
        }
        Log.d("로그","상세데이터 파싱완료");
        return item;
    }


    public ArrayList<Item> getDataList_BySearch(String t,String pageNo) {
        StrictMode.enableDefaults();


        boolean initem = false, inGugunCd = false, inNanmmbyNm = false,
                inProgrmBgnde = false, inProgrmEndde = false, inProgrmRegistNo = false,
                inProgrmSj = false, inProgrmSttusSe = false, inSidoCd = false;


        String GugunCd = null, NanmmbyNm = null, ProgrmBgnde = null, ProgrmEndde = null,
                ProgrmRegistNo = null, ProgrmSj = null, ProgrmSttusSe = null,
                SidoCd = null;
        dataList.clear();

        try {
            URL url = new URL("http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService/getVltrSearchWordList?pageNo=" + pageNo + "&schCateGu=progrmSj&keyword="+t
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            Log.d("로그","검색데이터 파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {

                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("gugunCd")) { inGugunCd = true;}
                        if (parser.getName().equals("nanmmbyNm")) { inNanmmbyNm = true; }
                        if (parser.getName().equals("progrmBgnde")) { inProgrmBgnde = true; }

                        if (parser.getName().equals("progrmEndde")) { inProgrmEndde = true; }

                        if (parser.getName().equals("progrmRegistNo")) { inProgrmRegistNo = true; }
                        if (parser.getName().equals("progrmSj")) { inProgrmSj = true; }
                        if (parser.getName().equals("progrmSttusSe")) { inProgrmSttusSe = true; }
                        if (parser.getName().equals("sidoCd")) { inSidoCd = true; }
                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
                            Log.d("로그", "검색데이터파싱 error...");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if (inGugunCd) { GugunCd = parser.getText();inGugunCd = false; }
                        if (inNanmmbyNm) { NanmmbyNm = parser.getText();inNanmmbyNm = false; }
                        if (inProgrmBgnde) { ProgrmBgnde = parser.getText();inProgrmBgnde = false; }
                        if (inProgrmEndde) { ProgrmEndde = parser.getText();inProgrmEndde = false; }
                        if (inProgrmRegistNo) { ProgrmRegistNo = parser.getText();inProgrmRegistNo = false; }
                        if (inProgrmSj) { ProgrmSj = parser.getText();inProgrmSj = false; }
                        if (inProgrmSttusSe) { ProgrmSttusSe = parser.getText();inProgrmSttusSe = false; }
                        if (inSidoCd) { SidoCd = parser.getText();inSidoCd = false; }

                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            Log.d("로그", "GugunCd : " + GugunCd + "\n NanmmbyNm  : " + NanmmbyNm  + "\n ProgrmBgnde  : " + ProgrmBgnde
                                    + "\n ProgrmEndde  : " + ProgrmEndde  + "\n ProgrmRegistNo  : " + ProgrmRegistNo  + "\n");
                            initem = false;
                            Item item = new Item(GugunCd, NanmmbyNm, ProgrmBgnde, ProgrmEndde, ProgrmRegistNo, ProgrmSj, ProgrmSttusSe, SidoCd);

                            dataList.add(item);
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            Log.d("로그", "검색데이터 에러가...났습니다..");
        }
        Log.d("로그","검색데이터 파싱완료" + dataList.size());
        return dataList;
    }

    public ArrayList<Area> getDataList_GuList(String si_name) {
        StrictMode.enableDefaults();


        boolean initem = false, inGugunCd = false, inGugunNm = false;

        String GugunCd = null, GugunNm = null;

        ArrayList<Area> dataList = new ArrayList<>();

        try {
            URL url = new URL("http://openapi.1365.go.kr/openapi/service/rest/CodeInquiryService/getAreaCodeInquiryList?numOfRows=50&schSido="+si_name
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            Log.d("로그","구 리스트 파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {

                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("gugunCd")) { inGugunCd = true;}
                        if (parser.getName().equals("gugunNm")) { inGugunNm = true; }
                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
                            Log.d("로그", "검색데이터파싱 error...");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if (inGugunCd) { GugunCd = parser.getText();inGugunCd = false; }
                        if (inGugunNm) { GugunNm = parser.getText();inGugunNm = false; }


                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            Log.d("로그", "GugunCd : " + GugunCd + "\n GugunNm  : " + GugunNm + "\n");
                            initem = false;
                            Area item = new Area(Integer.parseInt(GugunCd),GugunNm);

                            dataList.add(item);
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            Log.d("로그", "구 리스트 에러가...났습니다..");
        }
        Log.d("로그","구 리스트 파싱완료" + dataList.size());

        return dataList;
    }


    public ArrayList<Item> getDataList_ByArea(Integer Si_Id, Integer Gu_Id,Integer pageNo) {
        StrictMode.enableDefaults();


        boolean initem = false, inGugunCd = false, inNanmmbyNm = false,
                inProgrmBgnde = false, inProgrmEndde = false, inProgrmRegistNo = false,
                inProgrmSj = false, inProgrmSttusSe = false, inSidoCd = false;


        String GugunCd = null, NanmmbyNm = null, ProgrmBgnde = null, ProgrmEndde = null,
                ProgrmRegistNo = null, ProgrmSj = null, ProgrmSttusSe = null,
                SidoCd = null;
        dataList.clear();

        try {
            URL url = new URL("http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService/getVltrAreaList?numOfRows=50&schSido="+Si_Id.toString()+"&schSign1="+Gu_Id.toString()+"&pageNo="+pageNo.toString()
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            Log.d("로그","지역별데이터 파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {

                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("gugunCd")) { inGugunCd = true;}
                        if (parser.getName().equals("nanmmbyNm")) { inNanmmbyNm = true; }
                        if (parser.getName().equals("progrmBgnde")) { inProgrmBgnde = true; }

                        if (parser.getName().equals("progrmEndde")) { inProgrmEndde = true; }

                        if (parser.getName().equals("progrmRegistNo")) { inProgrmRegistNo = true; }
                        if (parser.getName().equals("progrmSj")) { inProgrmSj = true; }
                        if (parser.getName().equals("progrmSttusSe")) { inProgrmSttusSe = true; }
                        if (parser.getName().equals("sidoCd")) { inSidoCd = true; }
                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
                            Log.d("로그", "지역별데이터 error...");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if (inGugunCd) { GugunCd = parser.getText();inGugunCd = false; }
                        if (inNanmmbyNm) { NanmmbyNm = parser.getText();inNanmmbyNm = false; }
                        if (inProgrmBgnde) { ProgrmBgnde = parser.getText();inProgrmBgnde = false; }
                        if (inProgrmEndde) { ProgrmEndde = parser.getText();inProgrmEndde = false; }
                        if (inProgrmRegistNo) { ProgrmRegistNo = parser.getText();inProgrmRegistNo = false; }
                        if (inProgrmSj) { ProgrmSj = parser.getText();inProgrmSj = false; }
                        if (inProgrmSttusSe) { ProgrmSttusSe = parser.getText();inProgrmSttusSe = false; }
                        if (inSidoCd) { SidoCd = parser.getText();inSidoCd = false; }

                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            /*Log.d("로그", "GugunCd : " + GugunCd + "\n NanmmbyNm  : " + NanmmbyNm  + "\n ProgrmBgnde  : " + ProgrmBgnde
                                    + "\n ProgrmEndde  : " + ProgrmEndde  + "\n ProgrmRegistNo  : " + ProgrmRegistNo  + "\n");*/
                            initem = false;
                            Item item = new Item(GugunCd, NanmmbyNm, ProgrmBgnde, ProgrmEndde, ProgrmRegistNo, ProgrmSj, ProgrmSttusSe, SidoCd);

                            dataList.add(item);
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            Log.d("로그", "지역별데이터 에러가...났습니다..");
        }
        Log.d("로그","지역별데이터 파싱완료" + dataList.size());
        return dataList;
    }


    public ArrayList<Item> getDataList_ByDate(String StartDate, String EndDate,Integer pageNo) {
        StrictMode.enableDefaults();


        boolean initem = false, inGugunCd = false, inNanmmbyNm = false,
                inProgrmBgnde = false, inProgrmEndde = false, inProgrmRegistNo = false,
                inProgrmSj = false, inProgrmSttusSe = false, inSidoCd = false;


        String GugunCd = null, NanmmbyNm = null, ProgrmBgnde = null, ProgrmEndde = null,
                ProgrmRegistNo = null, ProgrmSj = null, ProgrmSttusSe = null,
                SidoCd = null;
        dataList.clear();

        try {
            URL url = new URL("http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService/getVltrPeriodSrvcList?&numOfRows=50&progrmBgnde="+StartDate+"&progrmEndde="+EndDate+"&pageNo="+pageNo.toString()
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            Log.d("로그","기간별데이터 파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {

                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("gugunCd")) { inGugunCd = true;}
                        if (parser.getName().equals("nanmmbyNm")) { inNanmmbyNm = true; }
                        if (parser.getName().equals("progrmBgnde")) { inProgrmBgnde = true; }

                        if (parser.getName().equals("progrmEndde")) { inProgrmEndde = true; }

                        if (parser.getName().equals("progrmRegistNo")) { inProgrmRegistNo = true; }
                        if (parser.getName().equals("progrmSj")) { inProgrmSj = true; }
                        if (parser.getName().equals("progrmSttusSe")) { inProgrmSttusSe = true; }
                        if (parser.getName().equals("sidoCd")) { inSidoCd = true; }
                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
                            Log.d("로그", "기간별데이터 error...");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if (inGugunCd) { GugunCd = parser.getText();inGugunCd = false; }
                        if (inNanmmbyNm) { NanmmbyNm = parser.getText();inNanmmbyNm = false; }
                        if (inProgrmBgnde) { ProgrmBgnde = parser.getText();inProgrmBgnde = false; }
                        if (inProgrmEndde) { ProgrmEndde = parser.getText();inProgrmEndde = false; }
                        if (inProgrmRegistNo) { ProgrmRegistNo = parser.getText();inProgrmRegistNo = false; }
                        if (inProgrmSj) { ProgrmSj = parser.getText();inProgrmSj = false; }
                        if (inProgrmSttusSe) { ProgrmSttusSe = parser.getText();inProgrmSttusSe = false; }
                        if (inSidoCd) { SidoCd = parser.getText();inSidoCd = false; }

                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            /*Log.d("로그", "GugunCd : " + GugunCd + "\n NanmmbyNm  : " + NanmmbyNm  + "\n ProgrmBgnde  : " + ProgrmBgnde
                                    + "\n ProgrmEndde  : " + ProgrmEndde  + "\n ProgrmRegistNo  : " + ProgrmRegistNo  + "\n");*/
                            initem = false;
                            Item item = new Item(GugunCd, NanmmbyNm, ProgrmBgnde, ProgrmEndde, ProgrmRegistNo, ProgrmSj, ProgrmSttusSe, SidoCd);

                            dataList.add(item);
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            Log.d("로그", "기간별데이터 에러가...났습니다..");
        }
        Log.d("로그","기간별데이터 파싱완료" + dataList.size());
        return dataList;
    }

    public ArrayList<Item> getDataList_Integrate(String StartDate, String EndDate,String Si_Id, String Gu_Id, String t, Integer pageNo) {
        StrictMode.enableDefaults();


        boolean initem = false, inGugunCd = false, inNanmmbyNm = false,
                inProgrmBgnde = false, inProgrmEndde = false, inProgrmRegistNo = false,
                inProgrmSj = false, inProgrmSttusSe = false, inSidoCd = false;


        String GugunCd = null, NanmmbyNm = null, ProgrmBgnde = null, ProgrmEndde = null,
                ProgrmRegistNo = null, ProgrmSj = null, ProgrmSttusSe = null,
                SidoCd = null;
        dataList.clear();
        if(StartDate==null) StartDate="";
        if(EndDate==null) EndDate="";
        if(Si_Id==null)Si_Id="";
        if(Gu_Id==null)Gu_Id="";
        if(t==null)t="";

        try {
            URL url = new URL("http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService/getVltrSearchWordList?numOfRows=50&pageNo="+pageNo.toString()+"&schCateGu=all&keyword="+t+"&progrmBgnde="+StartDate+"&progrmEndde="+EndDate+"schSido="+Si_Id+"&schSign1="+Gu_Id
            ); //검색 URL부분
            Log.d("로그",url.toString());
            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            Log.d("로그","통합데이터 파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {

                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("gugunCd")) { inGugunCd = true;}
                        if (parser.getName().equals("nanmmbyNm")) { inNanmmbyNm = true; }
                        if (parser.getName().equals("progrmBgnde")) { inProgrmBgnde = true; }

                        if (parser.getName().equals("progrmEndde")) { inProgrmEndde = true; }

                        if (parser.getName().equals("progrmRegistNo")) { inProgrmRegistNo = true; }
                        if (parser.getName().equals("progrmSj")) { inProgrmSj = true; }
                        if (parser.getName().equals("progrmSttusSe")) { inProgrmSttusSe = true; }
                        if (parser.getName().equals("sidoCd")) { inSidoCd = true; }
                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
                            Log.d("로그", "통합데이터 error...");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if (inGugunCd) { GugunCd = parser.getText();inGugunCd = false; }
                        if (inNanmmbyNm) { NanmmbyNm = parser.getText();inNanmmbyNm = false; }
                        if (inProgrmBgnde) { ProgrmBgnde = parser.getText();inProgrmBgnde = false; }
                        if (inProgrmEndde) { ProgrmEndde = parser.getText();inProgrmEndde = false; }
                        if (inProgrmRegistNo) { ProgrmRegistNo = parser.getText();inProgrmRegistNo = false; }
                        if (inProgrmSj) { ProgrmSj = parser.getText();inProgrmSj = false; }
                        if (inProgrmSttusSe) { ProgrmSttusSe = parser.getText();inProgrmSttusSe = false; }
                        if (inSidoCd) { SidoCd = parser.getText();inSidoCd = false; }

                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            /*Log.d("로그", "GugunCd : " + GugunCd + "\n NanmmbyNm  : " + NanmmbyNm  + "\n ProgrmBgnde  : " + ProgrmBgnde
                                    + "\n ProgrmEndde  : " + ProgrmEndde  + "\n ProgrmRegistNo  : " + ProgrmRegistNo  + "\n");*/
                            initem = false;
                            Item item = new Item(GugunCd, NanmmbyNm, ProgrmBgnde, ProgrmEndde, ProgrmRegistNo, ProgrmSj, ProgrmSttusSe, SidoCd);

                            dataList.add(item);
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            Log.d("로그", "통합데이터 에러가...났습니다..");
        }
        Log.d("로그","통합데이터 파싱완료" + dataList.size());
        return dataList;
    }
    /*public ArrayList<ItemInfo> getDataList_Favorite(){

        dataList.clear();

        Call<ArrayList<ItemInfo>> observ = RetrofitService.getInstance().getRetrofitRequest().getFavoriteList("tempLogin_Id");
        observ.enqueue(new Callback<ArrayList<ItemInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemInfo>> call, Response<ArrayList<ItemInfo>> response) {

                if(response.isSuccessful()){
                    dataList = response.body();

                    Log.d("로그","받아오기 성공!" + dataList.size());


                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemInfo>> call, Throwable t) {
                Log.d("로그","받아오기 실패! : " + t);
            }
        });

        return dataList;
    }
*/

    public ArrayList<Board> getDataList_Notice(){

        noticeList.clear();

        Call<ArrayList<Board>> observ = RetrofitService.getInstance().getRetrofitRequest().getNoticeList();
        observ.enqueue(new Callback<ArrayList<Board>>() {
            @Override
            public void onResponse(Call<ArrayList<Board>> call, Response<ArrayList<Board>> response) {

                if(response.isSuccessful()){
                    noticeList = response.body();

                    Log.d("로그","받아오기 성공!" + noticeList.size());


                }
            }

            @Override
            public void onFailure(Call<ArrayList<Board>> call, Throwable t) {
                Log.d("로그","받아오기 실패! : " + t);
            }
        });

        return noticeList;
    }


}


