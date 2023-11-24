import java.util.Map;

public class CompetitionSwimmer extends Swimmer {
    private final String swimStyle;
    private Map<String, CompetitionResult> competitionResults;

    public CompetitionSwimmer(String name, int age, ActivityType activityType, String cprNumber, String phoneNumber, String email, String address, String emergencyNumber, String postalCode, Coach coach, boolean isProfessional, String swimStyle) {
        super(name, age, activityType, cprNumber, phoneNumber, email, address, emergencyNumber, postalCode, coach, isProfessional, swimStyle);
        this.setProfessional(isProfessional);
        this.swimStyle = swimStyle;
    }


    public Map<String, CompetitionResult> getCompetitionResults() {
        return competitionResults;
    }

    public Object getSwimStyle() {
        return swimStyle;
    }
}
