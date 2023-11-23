import java.util.Scanner;

public class UserInterface {
    private ClubController controller;
    private Scanner scanner;

    public UserInterface(ClubController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to Svømmeklubben Delfinen!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Calculate Kontingent for a member");
            System.out.println("2. View Members");
            System.out.println("3. Exit");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    calculateKontingent();
                    break;
                case 2:
                    viewMembers();
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
    }

    private void calculateKontingent() {
        System.out.print("Enter member name: ");
        String name = scanner.next();
        System.out.print("Enter member age: ");
        int age = scanner.nextInt();
        System.out.print("Enter activity type (1 for ACTIVE_JUNIOR, 2 for ACTIVE_SENIOR, 3 for PASSIVE): ");
        ActivityType activityType = getActivityTypeFromUserInput();

        Member member = new Member(name, age, activityType);
        double kontingent = controller.calculateKontingent(member);

        System.out.println("Kontingent for " + member.getName() + ": " + kontingent + " kr");
    }

    private void viewMembers() {
        System.out.println("\nMembers:");

        List<String> members = controller.getMembers();

        if (members != null && !members.isEmpty()) {
            for (String member : members) {
                System.out.println(member);
            }
        } else {
            System.out.println("No members found.");
        }
    }

    private ActivityType getActivityTypeFromUserInput() {
        while (true) {
            System.out.print("Enter activity type: ");
            int input = getUserChoice();

            switch (input) {
                case 1:
                    return ActivityType.ACTIVE_JUNIOR;
                case 2:
                    return ActivityType.ACTIVE_SENIOR;
                case 3:
                    return ActivityType.PASSIVE;
                default:
                    System.out.println("Invalid activity type. Please try again.");
            }
        }
    }
}
