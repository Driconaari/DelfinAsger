public class Kasserer {
    // Constants for the kontingent calculation
    private static final double YOUTH_MEMBER_RATE = 1000;
    private static final double SENIOR_MEMBER_RATE = 1600;
    private static final double SENIOR_DISCOUNT_RATE = 0.75; // 25% discount for seniors

    public double calculateKontingent(Member member) {
        ActivityType activityType = member.getActivityType();
        int age = member.getAge();

        switch (activityType) {
            case JUNIOR_SPRINT:
                return YOUTH_MEMBER_RATE;  // Assuming the rate for Junior Sprint is the same as for youth members
            case JUNIOR_MIDDLE_DISTANCE:
                return YOUTH_MEMBER_RATE;  // Adjust the rate as needed
            case JUNIOR_LONG_DISTANCE:
                return YOUTH_MEMBER_RATE;  // Adjust the rate as needed
            case SENIOR_SPRINT:
                double baseSeniorRate = SENIOR_MEMBER_RATE;
                // Apply discount for seniors
                return (age > 60) ? baseSeniorRate * SENIOR_DISCOUNT_RATE : baseSeniorRate;
            case SENIOR_MIDDLE_DISTANCE:
                double baseMiddleDistanceRate = SENIOR_MEMBER_RATE;  // Adjust the rate as needed
                // Apply discount for seniors
                return (age > 60) ? baseMiddleDistanceRate * SENIOR_DISCOUNT_RATE : baseMiddleDistanceRate;
            case SENIOR_LONG_DISTANCE:
                double baseLongDistanceRate = SENIOR_MEMBER_RATE;  // Adjust the rate as needed
                // Apply discount for seniors
                return (age > 60) ? baseLongDistanceRate * SENIOR_DISCOUNT_RATE : baseLongDistanceRate;
            case PASSIVE:
                return 500;  // Assuming PASSIVE members have a fixed rate of 500
            default:
                System.out.println("Invalid activity type. Cannot calculate kontingent.");
                return 0;
        }
    }


    // Other methods as needed
}
