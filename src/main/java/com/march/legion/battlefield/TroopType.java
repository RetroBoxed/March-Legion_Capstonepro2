package com.march.legion.battlefield;

public enum TroopType {
    COMMANDER(0, 'C', 11),
    MEDIC(1, 'M', 21),
    TANK(2, 'T', 31),
    SNIPER(3, 'S', 41),
    INFANTRY(4, 'I', 51);

    private final int index;
    private final char symbol;
    private final int maxRank;

    TroopType(int index, char symbol, int maxRank) {
        this.index = index;
        this.symbol = symbol;
        this.maxRank = maxRank;
    }

    public int getIndex() { return index; }
    public char getSymbol() { return symbol; }
    public int getMaxRank() { return maxRank; }

    public static TroopType fromIndex(int idx) {
        for (TroopType t : TroopType.values()) {
            if (t.index == idx) return t;
        }
        throw new IllegalArgumentException("Invalid type: " + idx);
    }
}