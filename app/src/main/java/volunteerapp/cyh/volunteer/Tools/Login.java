package volunteerapp.cyh.volunteer.Tools;

import volunteerapp.cyh.volunteer.Login.Object.Member;

public class Login {


    public static Login curr = null;
    public static Login getInstance() {
        if (curr == null) {
            curr = new Login();
        }

        return curr;
    }

    private Member member;



    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
