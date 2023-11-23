public class Member {
    private String name;
    private int age;
    private ActivityType activityType;

    public Member(String name, int age, ActivityType activityType) {
        this.name = name;
        this.age = age;
        this.activityType = activityType;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    // You might want to add additional methods or attributes as needed
}
