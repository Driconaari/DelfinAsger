import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class UserInterface {
    private Controller controller;
    private Scanner scanner;
    private CoachAssignment coachAssignment;

    private Map<String, Coach> coaches = new HashMap<>();

    private String coachName;

    public UserInterface(Controller controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
        this.coachAssignment = getOrCreateCoachAssignment();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void run() throws IOException {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║   𝓦𝓮𝓵𝓬𝓸𝓶𝓮 𝓽𝓸 𝓢𝓿ø𝓮𝓶𝓶𝓮𝓴𝓵𝓾𝓫𝓫𝓮𝓷 𝓓𝓮𝓵𝓯𝓲𝓷𝓮𝓷!  ║");
        System.out.println("╚═══════════════════════════════════════════╝");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create Member");
            System.out.println("2. View Members");
            System.out.println("3. Calculate Kontingent for a Member");
            System.out.println("4. Edit Member");
            System.out.println("5. Delete Member");
            System.out.println("6. Exit");
            System.out.println("7. View Coaches");
            System.out.println("8. View Competitive Swimmers' Best Times");
            System.out.println("9. Add Coach"); // Add an option to add a coach
            System.out.println("10. MarkSwimmerAsProfessional"); // Add an option to add a coach
            System.out.println("11. RecordBestTime"); // Add an option to add a coach
            System.out.println("12. ViewBestTimesForProfessionalSwimmers"); // Add an option to add a coach

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    createMember();
                    break;
                case 2:
                    viewMembers();
                    break;
                case 3:
                    calculateKontingent();
                    break;
                case 4:
                    editMember();
                    break;
                case 5:
                    deleteMember();
                    break;
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                case 7:
                    viewCoaches();
                    break;
                case 8:
                    viewCompetitiveSwimmersBestTimes();
                    break;
                case 9:
                    addCoach();
                    break;
                // Inside the switch statement in the run() method
                case 10:
                    markSwimmerAsProfessional();
                    break;
                case 11:
                    recordBestTime();
                    break;
                case 12:
                    viewBestTimesForProfessionalSwimmers();
                    break;
                case 13:
                    saveCoachesToCSV();
                    break;
                case 14:
                    loadCoachesFromCSV();
                    break;


                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }

    private void loadCoachesFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("coaches.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String coachName = parts[0].trim();
                    String coachAssignment = parts[1].trim(); // Assuming the second part is coach assignment info
                    // Create Coach object and add it to the map
                    coaches.put(coachName, new Coach(coachName, new CoachAssignment(coachAssignment), coaches));
                }
            }
            System.out.println("Coaches loaded successfully!");
        } catch (IOException e) {
            System.out.println("Error loading coaches from CSV: " + e.getMessage());
        }
    }

    private void saveCoachesToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("coaches.csv"))) {
            for (Map.Entry<String, Coach> entry : coaches.entrySet()) {
                String coachName = entry.getKey();
                Coach coach = entry.getValue();
                String coachAssignmentInfo = coach.getCoachAssignment().toString(); // Adjust based on your CoachAssignment class
                // Write coach information to CSV
                writer.write(coachName + "," + coachAssignmentInfo);
                writer.newLine();
            }
            System.out.println("Coaches saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving coaches to CSV: " + e.getMessage());
        }
    }

    private void viewBestTimesForProfessionalSwimmers() {
        Map<String, Double> bestTimes = controller.getBestTimesForProfessionalSwimmers();
        if (!bestTimes.isEmpty()) {
            System.out.println("\nBest Times for Professional Swimmers:");
            for (Map.Entry<String, Double> entry : bestTimes.entrySet()) {
                System.out.println("Swimmer: " + entry.getKey() + ", Discipline: " + entry.getValue() + " seconds");
                System.out.println("---------------------");
            }
        } else {
            System.out.println("No best times found for professional swimmers.");
        }
    }

    private void recordBestTime() throws IOException {
        System.out.print("Enter swimmer name to record best time: ");
        String swimmerName = scanner.nextLine();

        System.out.print("Enter discipline: ");
        String discipline = scanner.nextLine();

        System.out.print("Enter best time (in seconds): ");
        double time = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        controller.recordBestTime(swimmerName, discipline, time);
        System.out.println("Best time recorded successfully!");
    }

    private void markSwimmerAsProfessional() throws IOException {
        System.out.print("Enter swimmer name to mark as professional: ");
        String swimmerName = scanner.nextLine();

        Member member = controller.findMemberByName(swimmerName);
        if (member instanceof Swimmer) {
            Swimmer swimmer = (Swimmer) member;
            swimmer.setProfessional(true);
            System.out.println("Swimmer marked as professional successfully!");
        } else {
            System.out.println("Swimmer not found.");
        }
    }


    public void addCoach() {
        System.out.print("Enter coach name: ");
        String coachName = scanner.nextLine();

        if (!coaches.containsKey(coachName)) {
            Coach newCoach = new Coach(coachName, coachAssignment, coaches);
            coaches.put(coachName, newCoach);
            System.out.println("Coach added successfully!");
        } else {
            System.out.println("Coach with the same name already exists.");
        }
    }


    private CoachAssignment getOrCreateCoachAssignment() {
        CoachAssignment existingAssignment = getCoachAssignment();

        if (existingAssignment != null) {
            return existingAssignment;
        } else {
            // If the CoachAssignment doesn't exist, create a new one with the coaches map
            CoachAssignment newAssignment = new CoachAssignment(coaches.toString());
            // Assuming you have a method to set or store the new CoachAssignment in your controller
            setCoachAssignment(newAssignment);
            return newAssignment;
        }
    }

    public CoachAssignment getCoachAssignment() {
        return coachAssignment;
    }

    public void setCoachAssignment(CoachAssignment coachAssignment) {
        this.coachAssignment = coachAssignment;
    }

    public void viewCoaches() {
        System.out.println("\nCoaches:");

        Map<String, Coach> coachMap = controller.getCoaches();
        int index = 1;

        for (Map.Entry<String, Coach> entry : coachMap.entrySet()) {
            System.out.println(index + ". " + entry.getKey());
            index++;
        }
    }


    private void viewCompetitiveSwimmersBestTimes() throws IOException {
        System.out.println("\nCompetitive Swimmers' Best Times:");

        // Assuming you have a method in the controller to get the best times
        Map<String, Double> bestTimes = controller.getCompetitiveSwimmersBestTimes();

        if (bestTimes != null && !bestTimes.isEmpty()) {
            for (Map.Entry<String, Double> entry : bestTimes.entrySet()) {
                System.out.println("Swimmer: " + entry.getKey() + ", Best Time: " + entry.getValue() + " seconds");
                // You can add more details about the swimmer as needed
                System.out.println("---------------------");
            }
        } else {
            System.out.println("No best times found for competitive swimmers.");
        }
    }

    private int getUserChoice() {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // consume the invalid input
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        return choice;

    }


    private void createMember() throws IOException {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();

        System.out.print("Enter member age: ");
        int age = scanner.nextInt();

        // Assuming you have a method to get the ActivityType from user input
        ActivityType activityType = getActivityTypeFromUserInput();

        // Add these lines to get the swimStyle based on the activityType
        String swimStyle = activityType.getSwimStyle();

        System.out.print("Enter member CPR number: ");
        String cprNumber = scanner.next();

        System.out.print("Enter member phone number: ");
        String phoneNumber = scanner.next();

        System.out.print("Enter member email: ");
        String email = scanner.next();

        System.out.print("Enter member address: ");
        String address = scanner.next();

        System.out.print("Enter postal code: ");
        String postalCode = scanner.next();

        System.out.print("Enter member emergency number: ");
        String emergencyNumber = scanner.next();

        // Assume you have a method to get coach information from the user
        Coach coach = getCoachFromUserInput(activityType);

        System.out.print("Is the swimmer professional? (true/false): ");
        boolean isProfessional = scanner.nextBoolean();

        // Create a Swimmer object and add it to the controller
        Swimmer newMember = new Swimmer(name, age, activityType, cprNumber, phoneNumber, email, address, emergencyNumber, postalCode, coach, isProfessional, swimStyle);

        // Save the new member and associate them with the appropriate coach based on the activity type
        controller.saveMember(newMember);
        assignMemberToCoach(newMember);

        System.out.println("Swimmer created successfully!");
    }


    private void assignMemberToCoach(Member member) {
        switch (member.getActivityType()) {
            case SENIOR_SPRINT:
                assignCoach(member, "Sprint Coach"); // Update to match the coach's actual name
                break;
            case SENIOR_MIDDLE_DISTANCE:
                assignCoach(member, "Middle Distance Coach"); // Update to match the coach's actual name
                break;
            case SENIOR_LONG_DISTANCE:
                assignCoach(member, "Long Distance Coach"); // Update to match the coach's actual name
                break;
            default:
                System.out.println("Unsupported activity type. Member not assigned to a coach.");
        }
    }


    private void assignCoach(Member member, String coachName) {
        Coach coach = Coach.getCoachByName(coachName, coaches);
        coach.addSwimmer((Swimmer) member);
        member.setCoach(coach);
    }

    private Coach getCoachFromUserInput(ActivityType activityType) {
        System.out.println("Choose a coach for the category:");
        switch (activityType) {
            case JUNIOR_SPRINT:
            case SENIOR_SPRINT:
                System.out.println("1. Sprint Coach");
                break;
            case JUNIOR_MIDDLE_DISTANCE:
            case SENIOR_MIDDLE_DISTANCE:
                System.out.println("2. Middle Distance Coach");
                break;
            case JUNIOR_LONG_DISTANCE:
            case SENIOR_LONG_DISTANCE:
                System.out.println("3. Long Distance Coach");
                break;
            default:
                System.out.println("4. General Coach");
        }

        int choice = getUserChoice();
        switch (choice) {
            case 1:
                return getOrCreateCoach("Sprint Coach");
            case 2:
                return getOrCreateCoach("Middle Distance Coach");
            case 3:
                return getOrCreateCoach("Long Distance Coach");
            default:
                return getOrCreateCoach("General Coach");
        }
    }

    private Coach getOrCreateCoach(String coachName) {
        if (!coaches.containsKey(coachName)) {
            // Assuming coachAssignment is an instance variable or otherwise accessible
            Coach coach = new Coach(coachName, coachAssignment, coaches);
            coaches.put(coachName, coach);
        }
        return coaches.get(coachName);
    }


    public void viewMembers() throws IOException {
        System.out.println("\nMembers:");

        List<Member> members = controller.getMembersFromCSV();

        if (members != null && !members.isEmpty()) {
            for (Member member : members) {
                System.out.println("Name: " + member.getName());
                System.out.println("Age: " + member.getAge());
                System.out.println("Activity Type: " + member.getActivityType());

                // Check if the member has a coach
                if (member instanceof Swimmer) {
                    Swimmer swimmer = (Swimmer) member;
                    Coach coach = swimmer.getCoach();
                    System.out.println("Coach: " + (coach != null ? coach.getName() : "Not assigned"));
                    System.out.println("Is Professional: " + (swimmer.isProfessional() ? "Yes" : "No"));
                    System.out.println("Swim Style: " + ((CompetitionSwimmer) swimmer).getSwimStyle());
                }

                System.out.println("CPR Number: " + member.getCprNumber());
                System.out.println("Phone Number: " + member.getPhoneNumber());
                System.out.println("Email: " + member.getEmail());
                System.out.println("Address: " + member.getAddress());
                System.out.println("Postal Code: " + member.getPostalCode());
                System.out.println("Emergency Number: " + member.getEmergencyNumber());

                System.out.println("---------------------");
            }

            // Ask if the user wants to assign a coach to a swimmer
            System.out.println("\nDo you want to assign a coach to a swimmer?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter the name of the swimmer to assign a coach: ");
                String swimmerName = scanner.next();
                System.out.print("Enter the name of the coach to assign: ");
                String coachName = scanner.next();

                // Add logic to assign the coach to the swimmer
                assignCoachToSwimmer(swimmerName, coachName);
            }
        } else {
            System.out.println("No members found.");
        }
    }
    


    private void calculateKontingent() throws IOException {
        System.out.print("Enter member name to calculate kontingent: ");
        String memberName = scanner.next();
        int kontingent = controller.calculateKontingent(memberName);

        System.out.println("Kontingent for " + memberName + ": " + kontingent + " kr");
    }

    private Swimmer gatherMemberInfo() throws IOException {
        System.out.print("Enter member name: ");
        String name = scanner.nextLine();

        System.out.print("Enter member age: ");
        int age = scanner.nextInt();

        // Assuming you have a method to get the ActivityType from user input
        ActivityType activityType = getActivityTypeFromUserInput();

        // Add these lines to get the swimStyle based on the activityType
        String swimStyle = activityType.getSwimStyle();

        System.out.print("Enter member CPR number: ");
        String cprNumber = scanner.next();

        System.out.print("Enter member phone number: ");
        String phoneNumber = scanner.next();

        System.out.print("Enter member email: ");
        String email = scanner.next();

        System.out.print("Enter member address: ");
        String address = scanner.next();

        System.out.print("Enter postal code: ");
        String postalCode = scanner.next();

        System.out.print("Enter member emergency number: ");
        String emergencyNumber = scanner.next();

        // Assume you have a method to get coach information from the user
        Coach coach = getCoachFromUserInput(activityType);

        System.out.print("Is the swimmer professional? (true/false): ");
        boolean isProfessional = scanner.nextBoolean();

        // Create a Swimmer object and return it
        return new Swimmer(name, age, activityType, cprNumber, phoneNumber, email, address, emergencyNumber, postalCode, coach, isProfessional, swimStyle);
    }

    // Method for creating a new member
    /*private void createMember() throws IOException {
        Swimmer newMember = gatherMemberInfo();

        // Save the new member and associate them with the appropriate coach based on the activity type
        controller.saveMember(newMember);
        assignMemberToCoach(newMember);

        System.out.println("Swimmer created successfully!");
    }

     */

    // Method for editing an existing member
    private void editMember() throws IOException {
        System.out.print("Enter member name to edit: ");
        String memberName = scanner.nextLine();

        Member memberToEdit = controller.findMemberByName(memberName);

        if (memberToEdit != null) {
            System.out.println("Editing member: " + memberToEdit.getName());

            // Implement logic to update member attributes
            System.out.print("Enter new name (press Enter to keep the current name): ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                memberToEdit.setName(newName);
            }

            System.out.print("Enter new age (press Enter to keep the current age): ");
            String ageInput = scanner.nextLine().trim();
            if (!ageInput.isEmpty()) {
                try {
                    int newAge = Integer.parseInt(ageInput);
                    memberToEdit.setAge(newAge);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid age format. Age not updated.");
                }
            }

            ActivityType newActivityType = getActivityTypeFromUserInput();
            memberToEdit.setActivityType(newActivityType);

            System.out.print("Enter new CPR number (press Enter to keep the current CPR number): ");
            String newCprNumber = scanner.nextLine().trim();
            if (!newCprNumber.isEmpty()) {
                memberToEdit.setCprNumber(newCprNumber);
            }

            System.out.print("Enter new phone number (press Enter to keep the current phone number): ");
            String newPhoneNumber = scanner.nextLine().trim();
            if (!newPhoneNumber.isEmpty()) {
                memberToEdit.setPhoneNumber(newPhoneNumber);
            }

            System.out.print("Enter new email (press Enter to keep the current email): ");
            String newEmail = scanner.nextLine().trim();
            if (!newEmail.isEmpty()) {
                memberToEdit.setEmail(newEmail);
            }

            System.out.print("Enter new address (press Enter to keep the current address): ");
            String newAddress = scanner.nextLine().trim();
            if (!newAddress.isEmpty()) {
                memberToEdit.setAddress(newAddress);
            }

            System.out.print("Enter new emergency number (press Enter to keep the current emergency number): ");
            String newEmergencyNumber = scanner.nextLine().trim();
            if (!newEmergencyNumber.isEmpty()) {
                memberToEdit.setEmergencyNumber(newEmergencyNumber);
            }

            System.out.print("Enter new postal code (press Enter to keep the current postal code): ");
            String newPostalCode = scanner.nextLine().trim();
            if (!newPostalCode.isEmpty()) {
                memberToEdit.setPostalCode(newPostalCode);
            }

            // Assume you have a method to get coach information from the user
            Coach coach = getCoachFromUserInput(newActivityType);
            memberToEdit.setCoach(coach);

            System.out.print("Is the swimmer professional? (true/false): ");
            String isProfessionalInput = scanner.nextLine().trim();
            if (!isProfessionalInput.isEmpty()) {
                boolean isProfessional = Boolean.parseBoolean(isProfessionalInput);
                memberToEdit.setProfessional(isProfessional);
            }

            // Add these lines to get the swimStyle based on the activityType
            String swimStyle = newActivityType.getSwimStyle();
            if (memberToEdit instanceof CompetitionSwimmer) {
                ((CompetitionSwimmer) memberToEdit).setSwimStyle(swimStyle);
            }

            System.out.print("Do you want to change the coach? (yes/no): ");
            String changeCoachOption = scanner.nextLine().trim().toLowerCase();



            // Save the updated member
            controller.editMember(memberName, memberToEdit);

            System.out.println("Member updated successfully!");
        } else {
            System.out.println("Member not found.");
        }
    }





    private void deleteMember() throws IOException {
        System.out.print("Enter member name to delete: ");
        String memberName = scanner.nextLine();

        controller.deleteMember(memberName);
    }

    // Add a method to get a coach by number
    private Coach getCoachByNumber(int number) {
        Map<String, Coach> coachMap = controller.getCoaches();

        if (number >= 1 && number <= coachMap.size()) {
            return coachMap.values().toArray(new Coach[0])[number - 1];
        } else {
            return null;
        }
    }

    private void assignCoachToSwimmer (String swimmerName, String coachName){
        this.coachName = coachName;
        Member swimmer = controller.getMemberByName(swimmerName);

        if (swimmer != null && swimmer instanceof Swimmer) {
            Swimmer swimmerToAssign = (Swimmer) swimmer;

            // Display available coaches
            List<Coach> coaches = controller.getCoachesList();
            System.out.println("Available Coaches:");
            for (int i = 0; i < coaches.size(); i++) {
                System.out.println((i + 1) + ". " + coaches.get(i).getName());
            }

            // Ask the user to choose a coach
            System.out.print("Enter the number of the coach to assign (0 to cancel): ");
            try {
                int coachNumber = Integer.parseInt(scanner.next());

                if (coachNumber == 0) {
                    System.out.println("Assignment canceled.");
                } else if (coachNumber > 0 && coachNumber <= coaches.size()) {
                    Coach coach = coaches.get(coachNumber - 1);

                    // Assign the coach to the swimmer
                    controller.assignCoach(swimmerToAssign, coach);
                    System.out.println("Coach assigned successfully!");

                    // Update the CSV file
                    controller.updateCSVFile();

                } else {
                    System.out.println("Invalid coach number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for coach number. Please enter a valid integer.");
            }
        } else {
            System.out.println("Swimmer not found.");
        }
    }

    private ActivityType getActivityTypeFromUserInput() {
        while (true) {
            System.out.print("Enter activity type (1 for Junior Sprint, 2 for Junior Middle Distance, 3 for Junior Long Distance, 4 for Senior Sprint, 5 for Senior Middle Distance, 6 for Senior Long Distance, 7 for Passive): ");
            int input = getUserChoice();

            switch (input) {
                case 1:
                    return ActivityType.JUNIOR_SPRINT;
                case 2:
                    return ActivityType.JUNIOR_MIDDLE_DISTANCE;
                case 3:
                    return ActivityType.JUNIOR_LONG_DISTANCE;
                case 4:
                    return ActivityType.SENIOR_SPRINT;
                case 5:
                    return ActivityType.SENIOR_MIDDLE_DISTANCE;
                case 6:
                    return ActivityType.SENIOR_LONG_DISTANCE;
                case 7:
                    return ActivityType.PASSIVE;
                default:
                    System.out.println("Invalid activity type. Please try again.");
                    // Handle invalid choice...
            }
        }
    }
}
