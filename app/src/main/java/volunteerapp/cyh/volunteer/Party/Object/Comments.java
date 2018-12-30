package volunteerapp.cyh.volunteer.Party.Object;

public class Comments {

    private String id;
    private String party_id;
    private String writer;
    private String content;
    private String write_date;


    public Comments(String id, String party_id, String writer, String content, String write_date) {
        this.id = id;
        this.party_id = party_id;
        this.writer = writer;
        this.content = content;
        this.write_date = write_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParty_id() {
        return party_id;
    }

    public void setParty_id(String party_id) {
        this.party_id = party_id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWrite_date() {
        return write_date;
    }

    public void setWrite_date(String write_date) {
        this.write_date = write_date;
    }
}
