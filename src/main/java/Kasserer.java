public class Kasserer {
    // Constants for the kontingent calculation
    private static final double YOUTH_MEMBER_RATE = 1000;
    private static final double SENIOR_MEMBER_RATE = 1600;
    private static final double SENIOR_DISCOUNT_RATE = 0.75; // 25% discount for seniors

    public double calculateKontingent(Member member) {
        ActivityType activityType = member.getActivityType();
        int age = member.getAge();

        if (activityType == ActivityType.ACTIVE_JUNIOR) {
            return YOUTH_MEMBER_RATE;
        } else if (activityType == ActivityType.ACTIVE_SENIOR) {
            double baseRate = SENIOR_MEMBER_RATE;

            // Apply discount for seniors
            if (age > 60) {
                return baseRate * SENIOR_DISCOUNT_RATE;
            } else {
                return baseRate;
            }
        } else {
            // Assuming PASSIVE members have a fixed rate of 500
            return 500;
        }
    }

    // Other methods as needed
}
