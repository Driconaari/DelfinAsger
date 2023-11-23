import java.util.List;

public class ClubController {
    private Kasserer kasserer;
    private FileHandler fileHandler;

    public ClubController(FileHandler fileHandler) {
        this.kasserer = new Kasserer();
        this.fileHandler = fileHandler;
    }

    public int calculateKontingent(Member member) {
        fileHandler.saveMember(member); // Save member to file
        return kasserer.calculateKontingent(member);
    }

    public List<String> getMembers() {
        return fileHandler.readMembers();
    }

    // Other controller methods
}
