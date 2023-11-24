import java.util.HashMap;
import java.util.Map;

public class Swimmer extends Member {
    private boolean isProfessional;
    private Map<String, Double> bestTimes;

    public Swimmer(String name, int age, ActivityType activityType, String cprNumber, String phoneNumber,
                   String email, String address, String emergencyNumber, String postalCode, Coach coach, boolean isProfessional, String swimStyle) {
        super(name, age, activityType, cprNumber, phoneNumber, email, address, emergencyNumber, postalCode, coach);
        this.isProfessional = false;
        this.bestTimes = new HashMap<>();
    }

    public boolean isProfessional() {
        return isProfessional;
    }

    public void setProfessional(boolean professional) {
        isProfessional = professional;
    }

    public Map<String, Double> getBestTimes() {
        return bestTimes;
    }

    public void setBestTimes(Map<String, Double> bestTimes) {
        this.bestTimes = bestTimes;
    }
}
