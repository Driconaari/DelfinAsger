import java.util.HashMap;
import java.util.Map;

public class CoachAssignment {

    private Map<String, Coach> coaches;


    public CoachAssignment(String csvString) {
        this.coaches = new HashMap<>();
        initializeCoachesFromCSV(csvString);
    }

    private void initializeCoachesFromCSV(String csvString) {
        // Split the CSV string into coach names
        String[] coachNames = csvString.split(",");

        // Create Coach objects and add them to the map
        for (String coachName : coachNames) {
            Coach coach = new Coach(coachName.trim(), this, coaches);
            coaches.put(coachName.trim(), coach);
        }
    }


    private Coach getOrCreateCoach(ActivityType activityType) {
        String coachName = getCoachName(activityType);

        // Use computeIfAbsent to simplify the logic
        return coaches.computeIfAbsent(coachName, name -> new Coach(name, this, coaches));
    }

    public String getCoachName(ActivityType activityType) {
        switch (activityType) {
            case JUNIOR_SPRINT:
            case SENIOR_SPRINT:
                return "Sprint Coach";
            case JUNIOR_MIDDLE_DISTANCE:
            case SENIOR_MIDDLE_DISTANCE:
                return "Middle Distance Coach";
            case JUNIOR_LONG_DISTANCE:
            case SENIOR_LONG_DISTANCE:
                return "Long Distance Coach";
            default:
                return "General Coach";
        }
    }
}
