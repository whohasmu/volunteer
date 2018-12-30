package volunteerapp.cyh.volunteer.Main.Event;

public class SetGuData {
    private Integer id;
    private String Name;


    public SetGuData(Integer id, String name) {
        this.id = id;
        Name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
