import java.util.HashMap;
import java.util.Map;

public class CoachRepository {
    private Map<String, Coach> coaches;

    public CoachRepository() {
        this.coaches = new HashMap<>();
    }

    public void addCoach(Coach coach) {
        coaches.put(coach.getName(), coach);
    }

    public Coach getCoachByName(String coachName) {
        return coaches.get(coachName);
    }
}
