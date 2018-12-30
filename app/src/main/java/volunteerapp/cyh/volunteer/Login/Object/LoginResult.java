package volunteerapp.cyh.volunteer.Login.Object;

public class LoginResult {
    private String LoginResult;
    private Member Login_Member;


    public LoginResult(String loginResult, Member login_Member) {
        LoginResult = loginResult;
        Login_Member = login_Member;
    }

    public String getLoginResult() {
        return LoginResult;
    }

    public void setLoginResult(String loginResult) {
        LoginResult = loginResult;
    }

    public Member getLogin_Member() {
        return Login_Member;
    }

    public void setLogin_Member(Member login_Member) {
        Login_Member = login_Member;
    }


}
