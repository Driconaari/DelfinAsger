import java.util.Objects;


public class Member {
    private Coach coach;
    private String name;
    private int age;
    private ActivityType activityType;
    private String cprNumber;
    private String phoneNumber;
    private String email;
    private String address;
    private String emergencyNumber;
    private String postalCode;
    private boolean isProfessional;
    private String swimStyle;

    public Member(String name, int age, ActivityType activityType, String cprNumber, String phoneNumber,
                  String email, String address, String emergencyNumber, String postalCode, Coach coach) {
        this.name = name;
        this.age = age;
        this.activityType = activityType;
        this.cprNumber = cprNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.emergencyNumber = emergencyNumber;
        this.postalCode = postalCode;
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

    public String getCprNumber() {
        return cprNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    // Other getter and setter methods...

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return age == member.age &&
                activityType == member.activityType &&  // Use == for enum comparison
                Objects.equals(name, member.name) &&
                Objects.equals(cprNumber, member.cprNumber) &&
                Objects.equals(phoneNumber, member.phoneNumber) &&
                Objects.equals(email, member.email) &&
                Objects.equals(address, member.address) &&
                Objects.equals(emergencyNumber, member.emergencyNumber) &&
                Objects.equals(postalCode, member.postalCode);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, age, activityType, cprNumber, phoneNumber, email, address, emergencyNumber, postalCode);
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", activityType=" + activityType +
                ", cprNumber='" + cprNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", emergencyNumber='" + emergencyNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", coach=" + coach +
                '}';
    }


    public void setName(String newName) {
    }

    public void setAge(int newAge) {
    }

    public void setActivityType(ActivityType newActivityType) {
    }

    public void setCprNumber(String newCprNumber) {
    }

    public void setPhoneNumber(String newPhoneNumber) {
    }

    public void setEmail(String newEmail) {
    }

    public void setAddress(String newAddress) {
    }

    public void setEmergencyNumber(String newEmergencyNumber) {
    }

    public void setPostalCode(String newPostalCode) {
    }


    public void setProfessional(boolean isProfessional) {
        this.isProfessional = isProfessional;
    }

    public void setSwimStyle(String swimStyle) {
        this.swimStyle = swimStyle;
    }

    public String toCSVString() {
        // Adjust this based on your actual member properties
        return String.format("%s,%d,%s,%s,%s,%s,%s,%s,%s,%s,%b,%s",
                name, age, activityType, cprNumber, phoneNumber, email, address, emergencyNumber,
                postalCode, coach != null ? coach.getName() : "No Coach", isProfessional, swimStyle);
    }
}
