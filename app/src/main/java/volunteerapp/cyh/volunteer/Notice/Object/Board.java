package volunteerapp.cyh.volunteer.Notice.Object;

public class Board {
    private String id;
    private String title;
    private String type;
    private String write_date;
    private String writer;
    private Integer views;
    private String content;

    public Board(String id, String title, String type, String write_date, String writer, Integer views, String content) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.write_date = write_date;
        this.writer = writer;
        this.views = views;
        this.content = content;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWrite_date() {
        return write_date;
    }

    public void setWrite_date(String write_date) {
        this.write_date = write_date;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
