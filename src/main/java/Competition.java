import java.util.Date;
import java.util.List;

public class Competition {
    private String name;
    private Date date;
    private List<CompetitionDiscipline> disciplines;

    public Competition(String name, Date date, List<CompetitionDiscipline> disciplines) {
        this.name = name;
        this.date = date;
        this.disciplines = disciplines;
    }

    public String getName() {
        return name;
    }

    public List<CompetitionDiscipline> getDisciplines() {
        return disciplines;
    }

    // Other methods as needed
}
