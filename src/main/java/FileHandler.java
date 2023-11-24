import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private Path filePath;

    public FileHandler(Path filePath) {
         this.filePath = filePath;
    }

    public List<Member> readMembers() {
        List<Member> members = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                Member member = parseMember(line);
                if (member != null) {
                    members.add(member);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading members from file: " + e.getMessage());
        }

        return members;
    }

    public void saveMembers(List<Member> members) {
        try {
            List<String> lines = new ArrayList<>();

            for (Member member : members) {
                if (member != null) {
                    String memberDetails = String.format(
                            "%s,%d,%s,%s,%s,%s,%s,%s,%s",
                            member.getName(),
                            member.getAge(),
                            member.getActivityType(),
                            member.getCprNumber(),
                            member.getPhoneNumber(),
                            member.getEmail(),
                            member.getAddress(),
                            member.getPostalCode(),
                            member.getEmergencyNumber()
                    );

                    lines.add(memberDetails);
                } else {
                    System.out.println("Error: Null member encountered.");
                }
            }

            Files.write(filePath, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            System.out.println("Error saving members to file: " + ex.getMessage());
        }
    }

    private Member parseMember(String line) {
        String[] fields = line.split(",");

        if (fields.length == 0) {
            return null;
        }

        try {
            String name = fields[0];
            int age = Integer.parseInt(fields[1]);
            ActivityType activityType = ActivityType.valueOf(fields[2]);
            String cprNumber = fields[3];
            String phoneNumber = fields[4];
            String email = fields[5];
            String address = fields[6];
            String postalCode = fields[7];
            String emergencyNumber = fields[8];

            return new Member(name, age, activityType, cprNumber, phoneNumber, email, address, postalCode, emergencyNumber);
        } catch (Exception e) {
            System.out.println("Invalid member data at line: " + line);
            return null;
        }
    }
}
