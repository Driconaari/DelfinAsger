public enum ActivityType {
    ACTIVE_JUNIOR("Active Junior"),
    ACTIVE_SENIOR("Active Senior"),
    PASSIVE("Passive");

    private final String displayValue;

    ActivityType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
