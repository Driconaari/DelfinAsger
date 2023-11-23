public class Member {
    private String name;
    private int age;
    private ActivityType activityType;
    private String cprNumber;
    private String phoneNumber;
    private String email;
    private String address;
    private String emergencyNumber;
    private String postalCode;

    public Member(String name, int age, ActivityType activityType, String cprNumber, String phoneNumber, String email, String address, String emergencyNumber, String postalCode) {
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

    public String getEmergencyNumber() {
        return emergencyNumber;
    }

    public void setAge(int newAge) {
        this.age = newAge;
    }

    public void setActivityType(ActivityType newActivityType) {
        this.activityType = newActivityType;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setCprNumber(String newCprNumber) {
        this.cprNumber = newCprNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    // Setter for email
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    // Setter for address
    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    // Setter for emergency number
    public void setEmergencyNumber(String newEmergencyNumber) {
        this.emergencyNumber = newEmergencyNumber;
    }
}
