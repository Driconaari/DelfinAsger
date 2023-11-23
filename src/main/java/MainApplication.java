public class MainApplication {
    public static void main(String[] args) {
        ClubController controller = new ClubController(new FileHandler());
        UserInterface userInterface = new UserInterface(controller);
        userInterface.run();
    }
}
