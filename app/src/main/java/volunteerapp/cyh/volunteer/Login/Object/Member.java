package volunteerapp.cyh.volunteer.Login.Object;

public class Member {

    private long id;
    private String mem_id;
    private String mem_pw;
    private String mem_name;
    private String mem_birth;
    private String mem_email;
    private String mem_phone;
    private String mem_gender;
    private String mem_grade;
    private String mem_joindate;

    public Member() {
    }


    public Member(long id, String mem_id, String mem_pw, String mem_name, String mem_birth, String mem_email, String mem_phone, String mem_gender, String mem_grade, String mem_joindate) {
        this.id = id;
        this.mem_id = mem_id;
        this.mem_pw = mem_pw;
        this.mem_name = mem_name;
        this.mem_birth = mem_birth;
        this.mem_email = mem_email;
        this.mem_phone = mem_phone;
        this.mem_gender = mem_gender;
        this.mem_grade = mem_grade;
        this.mem_joindate = mem_joindate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getMem_pw() {
        return mem_pw;
    }

    public void setMem_pw(String mem_pw) {
        this.mem_pw = mem_pw;
    }

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public String getMem_birth() {
        return mem_birth;
    }

    public void setMem_birth(String mem_birth) {
        this.mem_birth = mem_birth;
    }

    public String getMem_email() {
        return mem_email;
    }

    public void setMem_email(String mem_email) {
        this.mem_email = mem_email;
    }

    public String getMem_phone() {
        return mem_phone;
    }

    public void setMem_phone(String mem_phone) {
        this.mem_phone = mem_phone;
    }

    public String getMem_gender() {
        return mem_gender;
    }

    public void setMem_gender(String mem_gender) {
        this.mem_gender = mem_gender;
    }

    public String getMem_grade() {
        return mem_grade;
    }

    public void setMem_grade(String mem_grade) {
        this.mem_grade = mem_grade;
    }

    public String getMem_joindate() {
        return mem_joindate;
    }

    public void setMem_joindate(String mem_joindate) {
        this.mem_joindate = mem_joindate;
    }
}
