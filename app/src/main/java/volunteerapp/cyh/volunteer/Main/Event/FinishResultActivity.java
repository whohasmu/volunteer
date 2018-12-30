package volunteerapp.cyh.volunteer.Main.Event;

public class FinishResultActivity {
    private Integer Si_Id;
    private Integer Gu_Id;
    private String Si_Name;
    private String Gu_Name;

    public FinishResultActivity(Integer si_Id, Integer gu_Id) {
        Si_Id = si_Id;
        Gu_Id = gu_Id;
    }

    public FinishResultActivity(Integer si_Id, Integer gu_Id, String si_Name, String gu_Name) {
        Si_Id = si_Id;
        Gu_Id = gu_Id;
        Si_Name = si_Name;
        Gu_Name = gu_Name;
    }

    public Integer getSi_Id() {
        return Si_Id;
    }

    public void setSi_Id(Integer si_Id) {
        Si_Id = si_Id;
    }

    public Integer getGu_Id() {
        return Gu_Id;
    }

    public void setGu_Id(Integer gu_Id) {
        Gu_Id = gu_Id;
    }

    public String getSi_Name() {
        return Si_Name;
    }

    public void setSi_Name(String si_Name) {
        Si_Name = si_Name;
    }

    public String getGu_Name() {
        return Gu_Name;
    }

    public void setGu_Name(String gu_Name) {
        Gu_Name = gu_Name;
    }
}
