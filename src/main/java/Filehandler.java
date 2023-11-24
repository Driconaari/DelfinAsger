import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private Path filePath;

    public FileHandler(Path filePath) {
        this.filePath = filePath;
    }

    public List<Member> readMembers(Coach defaultCoach) {
        List<Member> members = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("members.csv"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 12) {
                    // Handle incomplete data line
                    System.out.println("Skipping incomplete data line: " + line);
                    continue;
                }

                try {
                    String name = data[0].trim();
                    int age = Integer.parseInt(data[1].trim());
                    ActivityType activityType = ActivityType.valueOf(data[2].trim());
                    String cprNumber = data[3].trim();
                    String phoneNumber = data[4].trim();
                    String email = data[5].trim();
                    String address = data[6].trim();
                    String emergencyNumber = data[7].trim();
                    String postalCode = data[8].trim();
                    String coachName = data[9].trim();
                    boolean isProfessional = Boolean.parseBoolean(data[10].trim());
                    String swimStyle = data[11].trim();

                    Coach coach = (coachName.equals("No Coach")) ? null : new Coach(coachName, null, null); // Replace with actual parameters

                    // Create the appropriate member type based on activity type
                    Member member;
                    if (activityType == ActivityType.PASSIVE) {
                        member = new Member(name, age, activityType, cprNumber, phoneNumber, email, address, emergencyNumber, postalCode, coach);
                    } else {
                        member = new CompetitionSwimmer(name, age, activityType, cprNumber, phoneNumber, email, address, emergencyNumber, postalCode, coach, isProfessional, swimStyle);
                    }

                    // Add the created member to the list
                    members.add(member);
                } catch (Exception e) {
                    // Log the error and continue to the next line
                    System.err.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return members;
    }


    public void saveMembers(List<Member> members) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            for (Member member : members) {
                if (member != null) {
                    String coachName = (member.getCoach() != null) ? member.getCoach().getName() : "No Coach";
                    String memberDetails = String.format(
                            "%s,%d,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",  // Adjust the format if needed
                            member.getName(),
                            member.getAge(),
                            member.getActivityType(),
                            member.getCprNumber(),
                            member.getPhoneNumber(),
                            member.getEmail(),
                            member.getAddress(),
                            member.getPostalCode(),
                            member.getEmergencyNumber(),
                            coachName,
                            (member instanceof Swimmer) ? ((Swimmer) member).isProfessional() : "false",
                            (member instanceof CompetitionSwimmer) ? ((CompetitionSwimmer) member).getSwimStyle() : ""  // Include swim style for competition swimmers
                    );

                    writer.write(memberDetails);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new IOException("Error writing members to file: " + e.getMessage(), e);
        }
    }
}