import java.util.List;

public class Coach {
    private String name;
    private List<CompetitionSwimmer> coachedSwimmers;

    public Coach(String name, List<CompetitionSwimmer> coachedSwimmers) {
        this.name = name;
        this.coachedSwimmers = coachedSwimmers;
    }

    public String getName() {
        return name;
    }

    public List<CompetitionSwimmer> getCoachedSwimmers() {
        return coachedSwimmers;
    }

    // Other methods as needed
}
