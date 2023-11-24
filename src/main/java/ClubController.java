import java.util.List;

public class ClubController {
    private Kasserer kasserer;
    private FileHandler fileHandler;

    public ClubController(FileHandler fileHandler) {
        this.kasserer = new Kasserer();
        this.fileHandler = fileHandler;
    }

    public int calculateKontingent(String memberName) {
        Member member = findMemberByName(memberName);

        if (member != null) {
            // No need to save the member here; it's already saved when edited
            return (int) kasserer.calculateKontingent(member);
        } else {
            System.out.println("Member not found.");
            return 0; // Or handle the situation accordingly
        }
    }


    Member findMemberByName(String memberName) {
        List<Member> members = fileHandler.readMembers();

        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(memberName)) {
                return member;
            }
        }

        return null; // Member not found
    }

    public void saveMember(Member member) {
        List<Member> members = fileHandler.readMembers();
        members.add(member);
        fileHandler.saveMembers(members);
    }

    public void editMember(String memberName, Member updatedMember) {
        List<Member> members = fileHandler.readMembers();

        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getName().equalsIgnoreCase(memberName)) {
                members.set(i, updatedMember);
                fileHandler.saveMembers(members); // Save updated members to file
                return;
            }
        }
        System.out.println("Member not found: " + memberName);
    }

    public List<Member> getMembers() {
        return fileHandler.readMembers();
    }

    public void saveMembers(List<Member> members) {
        fileHandler.saveMembers(members);
    }

    public void deleteMember(String memberName) {
        List<Member> members = fileHandler.readMembers();
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
        }
    }
}