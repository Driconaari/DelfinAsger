public class CompetitionResult {
    private String competitionName;
    private int placement;
    private double time; // in seconds

    public CompetitionResult(String competitionName, int placement, double time) {
        this.competitionName = competitionName;
        this.placement = placement;
        this.time = time;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public int getPlacement() {
        return placement;
    }

    public double getTime() {
        return time;
    }

    // Other methods as needed
}
