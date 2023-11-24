import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private ClubController controller;
    private Scanner scanner;

    public UserInterface(ClubController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║   𝓦𝓮𝓵𝓬𝓸𝓶𝓮 𝓽𝓸 𝓢𝓿ø𝓶𝓶𝓮𝓴𝓵𝓾𝓫𝓫𝓮𝓷 𝓓𝓮𝓵𝓯𝓲𝓷𝓮𝓷!   ║");
        System.out.println("╚═══════════════════════════════════════════╝");


        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create Member");
            System.out.println("2. View Members");
            System.out.println("3. Calculate Kontingent for a Member");
            System.out.println("4. Edit Member");
            System.out.println("5. Delete Member");
            System.out.println("6. Exit");


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
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        return choice;
    }


    private void createMember() {
        // Implement member creation logic based on your requirements
        // For simplicity, you can take input for member attributes

        System.out.print("Enter member name: ");
        String name = scanner.next();

        System.out.print("Enter member age: ");
        int age = scanner.nextInt();

        // Assuming you have a method to get the ActivityType from user input
        ActivityType activityType = getActivityTypeFromUserInput();

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

        // Create a Member object and add it to the controller
        Member newMember = new Member(name, age, activityType, cprNumber, phoneNumber, email, address, postalCode, emergencyNumber);
        //                            Name,Age,Activity Type,CPR Number,Phone Number,Email,Address,Postal Code,Emergency Number

        controller.saveMember(newMember);

        System.out.println("Member created successfully!");
    }



    private void viewMembers() {
        System.out.println("\nMembers:");

        List<Member> members = controller.getMembers();

        if (members != null && !members.isEmpty()) {
            for (Member member : members) {
                formatMemberDetails(member);
            }
        } else {
            System.out.println("No members found.");
        }
    }

    private void formatMemberDetails(Member member) {
        // Implement the formatting logic for displaying member details
        // You can customize this method based on your display requirements
        if (member != null) {
            System.out.println("Member Details:");
            System.out.println("Name: " + member.getName());
            System.out.println("Age: " + member.getAge());
            System.out.println("Activity Type: " + member.getActivityType());
            System.out.println("CPR Number: " + member.getCprNumber());
            System.out.println("Phone Number: " + member.getPhoneNumber());
            System.out.println("Email: " + member.getEmail());
            System.out.println("Address: " + member.getAddress());
            System.out.println("Postal Code: " + member.getPostalCode());
            System.out.println("Emergency Number: " + member.getEmergencyNumber());
            System.out.println("---------------------");
        } else {
            System.out.println("Invalid member data: ");
        }
    }


    private void calculateKontingent() {
        System.out.print("Enter member name to calculate kontingent: ");
        String memberName = scanner.next();
        int kontingent = controller.calculateKontingent(memberName);

        System.out.println("Kontingent for " + memberName + ": " + kontingent + " kr");
    }

    private void editMember() {
        System.out.print("Enter member name to edit: ");
        String memberName = scanner.nextLine();

        Member memberToEdit = controller.findMemberByName(memberName);

        if (memberToEdit != null) {
            System.out.println("Editing member: " + memberToEdit.getName());

            // Implement logic to update member attributes
            System.out.print("Enter new name (press Enter to keep current name): ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                memberToEdit.setName(newName);
            }

            System.out.print("Enter new age (press Enter to keep current age): ");
            String ageInput = scanner.nextLine().trim();
            if (!ageInput.isEmpty()) {
                try {
                    int newAge = Integer.parseInt(ageInput);
                    memberToEdit.setAge(newAge);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid age format. Age not updated.");
                }
            }

            System.out.print(" ");
            ActivityType newActivityType = getActivityTypeFromUserInput();
            memberToEdit.setActivityType(newActivityType);

            System.out.print("Enter new CPR number (press Enter to keep current CPR number): ");
            String newCprNumber = scanner.nextLine().trim();
            if (!newCprNumber.isEmpty()) {
                memberToEdit.setCprNumber(newCprNumber);
            }

            System.out.print("Enter new phone number (press Enter to keep current phone number): ");
            String newPhoneNumber = scanner.nextLine().trim();
            if (!newPhoneNumber.isEmpty()) {
                memberToEdit.setPhoneNumber(newPhoneNumber);
            }

            System.out.print("Enter new email (press Enter to keep current email): ");
            String newEmail = scanner.nextLine().trim();
            if (!newEmail.isEmpty()) {
                memberToEdit.setEmail(newEmail);
            }

            System.out.print("Enter new address (press Enter to keep current address): ");
            String newAddress = scanner.nextLine().trim();
            if (!newAddress.isEmpty()) {
                memberToEdit.setAddress(newAddress);
            }

            System.out.print("Enter new emergency number (press Enter to keep current emergency number): ");
            String newEmergencyNumber = scanner.nextLine().trim();
            if (!newEmergencyNumber.isEmpty()) {
                memberToEdit.setEmergencyNumber(newEmergencyNumber);
            }

            System.out.print("Enter new postal code (press Enter to keep current postal code): ");
            String newPostalCode = scanner.nextLine().trim();
            if (!newPostalCode.isEmpty()) {
                memberToEdit.setPostalCode(newPostalCode);
            }

            // Save the updated member
            controller.editMember(memberName, memberToEdit);

            System.out.println("Member updated successfully!");
        } else {
            System.out.println("Member not found.");
        }
    }



    private void deleteMember() {
        System.out.print("Enter member name to delete: ");
        String memberName = scanner.nextLine();

        controller.deleteMember(memberName);
    }

    private void updateMemberAttributes(Member member) {
        // For simplicity, take input for the attributes you want to edit
        System.out.print("Enter new age: ");
        int newAge = scanner.nextInt();
        member.setAge(newAge);

        System.out.print("Enter new activity type (1 for ACTIVE_JUNIOR, 2 for ACTIVE_SENIOR, 3 for PASSIVE): ");
        ActivityType newActivityType = getActivityTypeFromUserInput();
        member.setActivityType(newActivityType);


        // Add similar logic for other attributes you want to edit
    }


    private ActivityType getActivityTypeFromUserInput() {
        while (true) {
            System.out.print("Enter activity type (1 for ACTIVE_JUNIOR, 2 for ACTIVE_SENIOR, 3 for PASSIVE): ");
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
