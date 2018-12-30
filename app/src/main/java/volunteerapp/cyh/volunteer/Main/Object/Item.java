package volunteerapp.cyh.volunteer.Main.Object;

public class Item {

    private String gugunCd;
    private String NanmmbyNm;
    private String progrmBgnde;
    private String progrmEndde;
    private String progrmRegistNo;
    private String progrmSj;
    private String progrmSttusSe;
    private String sidoCd;


    public Item(String gugunCd, String nanmmbyNm, String progrmBgnde, String progrmEndde, String progrmRegistNo, String progrmSj, String progrmSttusSe, String sidoCd) {
        this.gugunCd = gugunCd;
        this.NanmmbyNm = nanmmbyNm;
        this.progrmBgnde = progrmBgnde;
        this.progrmEndde = progrmEndde;
        this.progrmRegistNo = progrmRegistNo;
        this.progrmSj = progrmSj;
        this.progrmSttusSe = progrmSttusSe;
        this.sidoCd = sidoCd;
    }

    public String getGugunCd() {
        return gugunCd;
    }

    public void setGugunCd(String gugunCd) {
        this.gugunCd = gugunCd;
    }

    public String getNanmmbyNm() {
        return NanmmbyNm;
    }

    public void setNanmmbyNm(String nanmmbyNm) {
        NanmmbyNm = nanmmbyNm;
    }

    public String getProgrmBgnde() {
        return progrmBgnde;
    }

    public void setProgrmBgnde(String progrmBgnde) {
        this.progrmBgnde = progrmBgnde;
    }

    public String getProgrmEndde() {
        return progrmEndde;
    }

    public void setProgrmEndde(String progrmEndde) {
        this.progrmEndde = progrmEndde;
    }

    public String getProgrmRegistNo() {
        return progrmRegistNo;
    }

    public void setProgrmRegistNo(String progrmRegistNo) {
        this.progrmRegistNo = progrmRegistNo;
    }

    public String getProgrmSj() {
        return progrmSj;
    }

    public void setProgrmSj(String progrmSj) {
        this.progrmSj = progrmSj;
    }

    public String getProgrmSttusSe() {
        return progrmSttusSe;
    }

    public void setProgrmSttusSe(String progrmSttusSe) {
        this.progrmSttusSe = progrmSttusSe;
    }

    public String getSidoCd() {
        return sidoCd;
    }

    public void setSidoCd(String sidoCd) {
        this.sidoCd = sidoCd;
    }
}
