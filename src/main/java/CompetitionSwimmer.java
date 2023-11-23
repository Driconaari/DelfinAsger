import java.util.List;

public class CompetitionSwimmer extends Member {
    private Trainer trainer;
    private List<SwimmingResult> swimmingResults;

    public CompetitionSwimmer(String name, int age, ActivityType activityType, Trainer trainer, List<SwimmingResult> swimmingResults) {
        super(name, age, activityType);
        this.trainer = trainer;
        this.swimmingResults = swimmingResults;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public List<SwimmingResult> getSwimmingResults() {
        return swimmingResults;
    }

    // You might want to add additional methods or attributes as needed
}
