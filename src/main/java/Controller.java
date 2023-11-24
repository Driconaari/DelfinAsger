import java.io.*;
import java.util.*;

public class Controller {
    private Map<String, Coach> coaches;
    private Kasserer kasserer;
    private FileHandler fileHandler;
    private List<Member> members;
    private CoachAssignment coachAssignment;
    private CoachRepository coachRepository;
    private Controller controller;
    private boolean isProfessional;
    private String swimStyle;

    public Controller(FileHandler fileHandler) {
        // Initialize Map for coaches
        Map<String, Coach> coachesMap = new HashMap<>();

        // Initialize coaches as a List (if needed)
        this.coaches = new HashMap<>();


        // Initialize other fields
        this.kasserer = new Kasserer();
        this.fileHandler = fileHandler;  // Use the passed FileHandler
        this.members = new ArrayList<>();

        // Pass coachesMap to CoachAssignment constructor
        this.coachAssignment = new CoachAssignment(coachesMap.toString());

        this.coachRepository = new CoachRepository();
    }

    public Controller(FileHandler fileHandler, CoachRepository coachRepository) {
        this.fileHandler = fileHandler;
        this.coachRepository = coachRepository;
    }


    public Map<String, Double> getCompetitiveSwimmersBestTimes() throws IOException {
        Map<String, Double> bestTimes = new HashMap<>();

        for (Member member : members) {
            if (member instanceof CompetitionSwimmer) {
                CompetitionSwimmer swimmer = (CompetitionSwimmer) member;

                double bestTime = getBestTimeForSwimmer(swimmer);
                bestTimes.put(swimmer.getName(), bestTime);
            }
        }

        return bestTimes;
    }

    private double getBestTimeForSwimmer(CompetitionSwimmer swimmer) {
        double bestTime = Double.MAX_VALUE;

        for (CompetitionResult result : swimmer.getCompetitionResults().values()) {
            double time = result.getTime();
            if (time < bestTime) {
                bestTime = time;
            }
        }

        return bestTime;
    }


    public Map<String, Coach> getCoaches() {
        Map<String, Coach> coachMap = new HashMap<>();

        // Assuming you have three coaches in your 'coaches' list
        Coach coach1 = new Coach("Sprint Coach Carl");
        Coach coach2 = new Coach("Middle Distance Coach Mary");
        Coach coach3 = new Coach("Long Distance Coach John");

        // Add coaches to the list
        List<Coach> coaches = Arrays.asList(coach1, coach2, coach3);

        // Populate the map
        for (Coach coach : coaches) {
            coachMap.put(coach.getName(), coach);
        }

        return coachMap;
    }

    public void saveCoachesToCSV() {
        Coach.writeCoachesToCSV(coaches, "coaches.csv");
    }

    public void loadCoachesFromCSV() {
        Coach.readCoachesFromCSV(coaches, "coaches.csv", coachAssignment);
    }

    public void saveMember(Member newMember) throws IOException {
        members.add(newMember);
        fileHandler.saveMembers(members);
    }


    public int calculateKontingent(String memberName) throws IOException {
        Coach defaultCoach = new Coach("Default Coach Bob", null, null); // replace with actual parameters
        List<Member> members = fileHandler.readMembers(defaultCoach);

        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(memberName)) {
                // Assuming 'kasserer' is an instance of some class that calculates kontingent
                // Adjust the following line based on your actual implementation
                return (int) kasserer.calculateKontingent(member);
            }
        }

        // Return some default value if the member is not found
        return 0;
    }


    public Member findMemberByName(String memberName) throws IOException {
        Coach defaultCoach = new Coach("Default Coach Bob", null, null); // replace with actual parameters
        List<Member> members = fileHandler.readMembers(defaultCoach);

        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(memberName)) {
                return member;
            }
        }

        return null; // Member not found
    }


    public void editMember(String memberName, Member updatedMember) {
        List<Member> members = readMembersFromCSV(); // Replace with your method to read members from CSV

        for (int i = 0; i < members.size(); i++) {
            Member existingMember = members.get(i);
            if (existingMember.getName().equalsIgnoreCase(memberName)) {
                members.set(i, updatedMember);
                break;
            }
        }

        saveMembersToCSV(members); // Replace with your method to save members to CSV
    }

    private void saveMembersToCSV(List<Member> members) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.csv"))) {
            for (Member member : members) {
                // Write each member to the CSV file
                // Adjust this part based on your Member class structure
                writer.write(member.toCSVString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    private List<Member> readMembersFromCSV() {
        return null;
    }

    public void deleteMember(String memberName) throws IOException {
        Coach defaultCoach = new Coach("Default Coach Bob", null, null); // replace with actual parameters
        List<Member> members = fileHandler.readMembers(defaultCoach);
        Member memberToDelete = null;

        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(memberName)) {
                memberToDelete = member;
                break;
            }
        }

        if (memberToDelete != null) {
            members.remove(memberToDelete);
            fileHandler.saveMembers(members);
            System.out.println("Member deleted successfully.");
        } else {
            System.out.println("Member not found: " + memberName);
            System.out.println("Debug info: " + members.toString()); // Add this line for debugging
        }
    }

    public List<Member> getMembersFromCSV() {
        List<Member> members = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("members.csv"))) {
            String line;
            // Assuming the first line contains headers, you can skip it
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 9) {
                    // Handle incomplete data line
                    System.out.println("Skipping incomplete data line: " + line);
                    continue;
                }

                String name = data[0].trim();
                int age = Integer.parseInt(data[1].trim());
                ActivityType activityType = ActivityType.valueOf(data[2].trim());
                String cprNumber = data[3].trim();
                String phoneNumber = data[4].trim();
                String email = data[5].trim();
                String address = data[6].trim();
                String emergencyNumber = data[7].trim();
                String postalCode = data[8].trim();

                // Assuming you have a method to get coach information from the CSV data
                Coach coach = getCoachFromCSVData(activityType, data);

                // Get the swimStyle based on the activityType (replace with your logic)
                String swimStyle = getSwimStyleBasedOnActivityType(activityType);

                // Create the appropriate member type based on activity type
                Member member;
                if (activityType == ActivityType.PASSIVE) {
                    member = new Member(name, age, activityType, cprNumber, phoneNumber, email, address, emergencyNumber, postalCode, coach);

                } else {
                    member = new CompetitionSwimmer(name, age, activityType, cprNumber, phoneNumber, email, address, emergencyNumber, postalCode, coach, false, swimStyle);
                }

                members.add(member);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return members;
    }

    private String getSwimStyleBasedOnActivityType(ActivityType activityType) {
        switch (activityType) {
            case SENIOR_SPRINT:
                return "Freestyle";
            case JUNIOR_SPRINT:
                return "Butterfly";
            case SENIOR_LONG_DISTANCE:
                return "Backstroke";
            // Add more cases as needed
            default:
                return "Unknown";
        }
    }


    private Coach getCoachFromCSVData(ActivityType activityType, String[] data) {
        // Assuming the coach information is in a specific column in the CSV data
        // Adjust the index according to your CSV format
        int coachInfoIndex = 9; // Adjust this index based on your CSV format

        // Check if the index is within bounds of the data array
        if (coachInfoIndex < data.length) {
            String coachName = data[coachInfoIndex];

            // Assuming you have a method to get coach information by name
            return getCoachByName(coachName);
        } else {
            // Handle the case where the coach information is missing or out of bounds
            // You might return null or a default coach, or throw an exception based on your requirements
            return null;
        }
    }

    // Assuming you have a method to get coach information by name
    public Coach getCoachByName(String coachName) {
        // Make sure coachRepository is not null before using it
        if (coachRepository != null) {
            return coachRepository.getCoachByName(coachName);
        } else {
            // Handle the case where coachRepository is null
            throw new IllegalStateException("coachRepository is not initialized");
        }
    }

    public void addProfessionalSwimmer(String name, int age, ActivityType activityType, String cprNumber, String phoneNumber,
                                       String email, String address, String emergencyNumber, String postalCode, Coach coach) throws IOException {
        Swimmer professionalSwimmer = new Swimmer(name, age, activityType, cprNumber, phoneNumber, email, address,
                emergencyNumber, postalCode, coach, isProfessional, swimStyle);
        professionalSwimmer.setProfessional(true);
        saveMember(professionalSwimmer);
    }

    public void recordBestTime(String swimmerName, String discipline, double time) throws IOException {
        Member member = findMemberByName(swimmerName);
        if (member instanceof Swimmer) {
            Swimmer swimmer = (Swimmer) member;
            swimmer.getBestTimes().put(discipline, Double.valueOf(time));
        }
    }


    public Map<String, Double> getBestTimesForProfessionalSwimmers() {
        Map<String, Double> bestTimes = new HashMap<>();
        for (Member member : members) {
            if (member instanceof Swimmer && ((Swimmer) member).isProfessional()) {
                Swimmer professionalSwimmer = (Swimmer) member;
                bestTimes.putAll(professionalSwimmer.getBestTimes());
            }
        }
        return bestTimes;
    }

    public Member getMemberByName(String swimmerName) {
        List<Member> members = controller.getMembersFromCSV();

        if (members != null && !members.isEmpty()) {
            for (Member member : members) {
                if (member.getName().equalsIgnoreCase(swimmerName)) {
                    return member;
                }
            }
        }

        return null; // Member not found
    }

    public void assignCoach(Swimmer swimmer, Coach coach) {
        // Check if the swimmer and coach are not null
        if (swimmer != null && coach != null) {
            // Set the coach for the swimmer
            swimmer.setCoach(coach);

            // Add the swimmer to the coach's list of swimmers
            coach.addSwimmer(swimmer);

            System.out.println("Coach assigned successfully!");
        } else {
            System.out.println("Invalid swimmer or coach provided.");
        }
    }

    public void updateCSVFile() {
        List<Member> members = getMembersList(); // Replace with your method to get the list of members

        try (FileWriter writer = new FileWriter("members.csv")) {
            for (Member member : members) {
                if (member instanceof Swimmer) {
                    Swimmer swimmer = (Swimmer) member;
                    writer.write(swimmer.toCSVString() + "\n");
                } else {
                    // Handle other types of members if needed
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    public List<Member> getMembersList() {
        return members;
    }

    public List<Coach> getCoachesList() {
        return (List<Coach>) coaches;
    }
}
