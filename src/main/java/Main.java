import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path filePath = Paths.get("members.csv"); // Replace with your actual file path
        FileHandler fileHandler = new FileHandler(filePath); // Provide the file path to the constructor
        Controller controller = new Controller(fileHandler);
        UserInterface userInterface = new UserInterface(controller);
        CoachRepository coachRepository = new CoachRepository();

        // Set the controller for the user interface
        userInterface.setController(controller);

        // Run the user interface
        try {
            userInterface.run();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
