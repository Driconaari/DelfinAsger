import java.util.HashMap;
import java.util.Map;

public class CompetitionSwimmer extends Member {
    private String coach;
    private Map<String, Double> trainingResults; // Map of swim disciplines and training results
    private Map<String, CompetitionResult> competitionResults; // Map of swim disciplines and competition results


    public CompetitionSwimmer(String name, int age, ActivityType activityType, String cprNumber, String phoneNumber, String email, String address, String emergencyNumber, String postalCode) {
        super(name, age, activityType, cprNumber, phoneNumber, email, address, emergencyNumber, postalCode);
        this.coach = coach;
        this.trainingResults = new HashMap<>();
        this.competitionResults = new HashMap<>();
    }




    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public void addTrainingResult(String discipline, double result) {
        trainingResults.put(discipline, result);
    }

    public double getTrainingResult(String discipline) {
        return trainingResults.getOrDefault(discipline, 0.0);
    }

    public void addCompetitionResult(String discipline, CompetitionResult result) {
        competitionResults.put(discipline, result);
    }

    public CompetitionResult getCompetitionResult(String discipline) {
        return competitionResults.get(discipline);
    }

    // Other methods as needed
}
