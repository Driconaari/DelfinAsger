public class Kasserer {
    private static final double ANNUAL_YOUTH_FEE = 1000;
    private static final double ANNUAL_ADULT_FEE = 1600;
    private static final double PASSIVE_FEE = 500;
    private static final double SENIOR_DISCOUNT_PERCENTAGE = 0.25;

    public double calculateKontingent(Member member) {
        double kontingent;

        switch (member.getActivityType()) {
            case ACTIVE_JUNIOR:
                kontingent = ANNUAL_YOUTH_FEE;
                break;
            case ACTIVE_SENIOR:
                kontingent = ANNUAL_ADULT_FEE;
                if (member.getAge() > 60) {
                    kontingent -= kontingent * SENIOR_DISCOUNT_PERCENTAGE;
                }
                break;
            case PASSIVE:
                kontingent = PASSIVE_FEE;
                break;
            default:
                throw new IllegalArgumentException("Invalid activity type");
        }

        return kontingent;
    }

    // Other methods related to kontingent handling
}
