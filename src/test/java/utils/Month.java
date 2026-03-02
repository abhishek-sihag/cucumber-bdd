package utils;

public enum Month {
    JAN(1, "Jan"),
    FEB(2, "Feb"),
    MAR(3, "Mar"),
    APR(4, "Apr"),
    MAY(5, "May"),
    JUN(6, "Jun"),
    JUL(7, "Jul"),
    AUG(8, "Aug"),
    SEP(9, "Sep"),
    OCT(10, "Oct"),
    NOV(11, "Nov"),
    DEC(12, "Dec");

    private final int value;
    private final String displayName;

    Month(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public int getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Month fromInt(int month) {
        for (Month m : Month.values()) {
            if (m.getValue() == month) {
                return m;
            }
        }
        throw new IllegalArgumentException("Invalid month value: " + month);
    }
}
