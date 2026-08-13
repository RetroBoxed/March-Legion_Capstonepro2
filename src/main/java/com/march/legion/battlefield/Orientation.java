package com.march.legion.battlefield;

public enum Orientation {
    NORTH_SOUTH('n', "South to North"),
    SOUTH_NORTH('s', "North to South"),
    EAST_WEST('e', "West to East"),
    WEST_EAST('w', "East to West");

    private final char code;
    private final String description;

    Orientation(char code, String description) {
        this.code = code;
        this.description = description;
    }

    public char getCode() { return code; }
    public String getDescription() { return description; }

    public static Orientation fromCode(char code) {
        for (Orientation o : Orientation.values()) {
            if (o.code == code) return o;
        }
        throw new IllegalArgumentException("Invalid orientation: " + code);
    }

    public boolean isHorizontal() { return this == EAST_WEST || this == WEST_EAST; }
    public boolean isVertical() { return this == NORTH_SOUTH || this == SOUTH_NORTH; }
}