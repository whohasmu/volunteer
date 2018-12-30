package volunteerapp.cyh.volunteer.QnA.Object;

public class QnA {
    private String id;
    private String title;
    private String writer;
    private String date_Q;
    private String hit;
    private String content_Q;

    private String isanswer; //1:답변X , 2:답변O
    private String date_A;
    private String content_A;

    public QnA(String id, String title, String writer, String date_Q, String hit, String content_Q, String isanswer, String date_A, String content_A) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.date_Q = date_Q;
        this.hit = hit;
        this.content_Q = content_Q;
        this.isanswer = isanswer;
        this.date_A = date_A;
        this.content_A = content_A;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDate_Q() {
        return date_Q;
    }

    public void setDate_Q(String date_Q) {
        this.date_Q = date_Q;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getContent_Q() {
        return content_Q;
    }

    public void setContent_Q(String content_Q) {
        this.content_Q = content_Q;
    }

    public String getIsanswer() {
        return isanswer;
    }

    public void setIsanswer(String isanswer) {
        this.isanswer = isanswer;
    }

    public String getDate_A() {
        return date_A;
    }

    public void setDate_A(String date_A) {
        this.date_A = date_A;
    }

    public String getContent_A() {
        return content_A;
    }

    public void setContent_A(String content_A) {
        this.content_A = content_A;
    }
}
