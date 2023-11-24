public enum ActivityType {
    JUNIOR_SPRINT("Junior Sprint", "Sprint Coach", "Freestyle"),
    JUNIOR_MIDDLE_DISTANCE("Junior Middle Distance", "Middle Distance Coach", "Backstroke"),
    JUNIOR_LONG_DISTANCE("Junior Long Distance", "Long Distance Coach", "Breaststroke"),
    SENIOR_SPRINT("Senior Sprint", "Sprint Coach", "Freestyle"),
    SENIOR_MIDDLE_DISTANCE("Senior Middle Distance", "Middle Distance Coach", "Backstroke"),
    SENIOR_LONG_DISTANCE("Senior Long Distance", "Long Distance Coach", "Breaststroke"),
    PASSIVE("Passive", "General Coach", "");

    private final String displayValue;
    private final String coachName;
    private final String swimStyle;

    ActivityType(String displayValue, String coachName, String swimStyle) {
        this.displayValue = displayValue;
        this.coachName = coachName;
        this.swimStyle = swimStyle;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public String getCoachName() {
        return coachName;
    }

    public String getSwimStyle() {
        return swimStyle;
    }
}
