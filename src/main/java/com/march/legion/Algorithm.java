package com.march.legion;

public enum Algorithm {
    QUICK('Q', "Quick Sort"),
    MERGE('M', "Merge Sort"),
    HEAP('H', "Heap Sort"),
    COUNTING('C', "Counting Sort");

    private final char code;
    private final String name;

    Algorithm(char code, String name) {
        this.code = code;
        this.name = name;
    }

    public char getCode() { return code; }
    public String getName() { return name; }

    public static Algorithm fromCode(char c) {
        for (Algorithm a : Algorithm.values()) {
            if (a.code == c) return a;
        }
        throw new IllegalArgumentException("Invalid algorithm: " + c);
    }
}
