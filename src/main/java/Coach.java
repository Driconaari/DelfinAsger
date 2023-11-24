import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Coach {

    private String name;
    private List<Swimmer> swimmers;  // Initialize the list
    private CoachAssignment coachAssignment;
    private Map<String, Coach> coaches;


    public Coach(String name, CoachAssignment coachAssignment, Map<String, Coach> coaches) {
        this.name = name;
        this.swimmers = new ArrayList<>();  // Initialize the list
        this.coachAssignment = coachAssignment;
        this.coaches = coaches;
    }

    public Coach(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Swimmer> getSwimmers() {
        return swimmers;
    }

    public void addSwimmer(Swimmer swimmer) {
        if (swimmers == null) {
            swimmers = new ArrayList<>();
        }
        swimmers.add(swimmer);
    }


    private void assignSwimmerToCoach(Swimmer swimmer) {
        ActivityType activityType = swimmer.getActivityType();
        String assignedCoachName = coachAssignment.getCoachName(activityType);
        Coach assignedCoach = getCoachByName(assignedCoachName);

        if (assignedCoach != null) {
            swimmer.setCoach(assignedCoach);
        } else {
            System.out.println("Assigned coach not found for swimmer: " + swimmer.getName());
        }
    }

    public void getCoachDetails() {
        System.out.println("Coach: " + name);
        System.out.println("Assigned Swimmers:");
        for (Swimmer swimmer : swimmers) {
            System.out.println("  - " + swimmer.getName() + " (" + swimmer.getActivityType() + ")");
        }
        System.out.println("---------------------");
    }

    public static Coach getCoachByName(String coachName) {
        Map<String, Coach> coaches = getCoaches();
        if (coaches.containsKey(coachName)) {
            return coaches.get(coachName);
        } else {
            System.out.println("Coach not found with name: " + coachName);
            return null;
        }
    }

    private static Map<String, Coach> getCoaches() {
        // Replace this with your actual logic to get coaches
        Map<String, Coach> coachesMap = new HashMap<>();

        // Example coaches
        Coach sprintCoach = new Coach("Sprint Coach");
        Coach middleDistanceCoach = new Coach("Middle Distance Coach");
        Coach longDistanceCoach = new Coach("Long Distance Coach");

        // Add coaches to the map
        coachesMap.put("Sprint Coach", sprintCoach);
        coachesMap.put("Middle Distance Coach", middleDistanceCoach);
        coachesMap.put("Long Distance Coach", longDistanceCoach);

        return coachesMap;
    }


    public static Coach getCoachByName(String coachName, Map<String, Coach> coaches) {
        if (coaches.containsKey(coachName)) {
            return coaches.get(coachName);
        } else {
            System.out.println("Coach not found with name: " + coachName);
            return null;
        }
    }

    public static void writeCoachesToCSV(Map<String, Coach> coaches, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Coach coach : coaches.values()) {
                writer.println(coach.toCSVString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void readCoachesFromCSV(Map<String, Coach> coaches, String filePath, CoachAssignment coachAssignment) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Coach coach = Coach.fromCSVString(line, coachAssignment);
                coaches.put(coach.getName(), coach);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add a method to convert Coach to CSV string
    public String toCSVString() {
        // Customize this based on your Coach class structure
        return name + "," + coachAssignment;  // Adjust accordingly
    }

    // Add a method to create Coach from CSV string
    public static Coach fromCSVString(String csvString, CoachAssignment coachAssignment) {
        String[] parts = csvString.split(",");
        String name = parts[0];  // Adjust accordingly
        return new Coach(name, coachAssignment, null);  // Adjust accordingly
    }

    public Object getCoachAssignment() {
        return coachAssignment;
    }
}


