import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileHandler {
    private static final String FILE_PATH = "members.csv";

    public void saveMember(Member member) {
        try {
            String data = member.getName() + "," + member.getAge() + "," + member.getActivityType() + "\n";
            Files.write(Path.of(FILE_PATH), data.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    public List<String> readMembers() {
        try {
            return Files.readAllLines(Path.of(FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null;
        }
    }
}
