import java.nio.file.Path;
import java.nio.file.Paths;

public class MainApplication {
    public static void main(String[] args) {
        Path filePath = Paths.get("C:\\Users\\Aku-1\\IdeaProjects\\Oevelser\\DelfinAsger\\members.csv"); // Replace with your actual file path
        FileHandler fileHandler = new FileHandler(filePath);
        ClubController controller = new ClubController(fileHandler);
        UserInterface userInterface = new UserInterface(controller);
        userInterface.run();
    }
}
