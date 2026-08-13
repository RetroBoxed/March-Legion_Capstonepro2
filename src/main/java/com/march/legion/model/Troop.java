package com.march.legion.model;

import com.march.legion.battlefield.TroopType;

public abstract class Troop implements Comparable<Troop> {
    protected int rank;
    protected int row;
    protected int col;
    protected TroopType type;

    public Troop(TroopType type, int rank) {
        this.type = type;
        this.rank = rank;
        this.row = -1;
        this.col = -1;
    }

    public abstract String getUnitName();

    public int getRank() { return rank; }
    public void setRank(int rank) {
        if (rank < 1 || rank >= type.getMaxRank()) {
            throw new IllegalArgumentException("Invalid rank for " + type);
        }
        this.rank = rank;
    }

    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }

    public int getCol() { return col; }
    public void setCol(int col) { this.col = col; }

    public TroopType getType() { return type; }
    public char getSymbol() { return type.getSymbol(); }

    @Override
    public int compareTo(Troop other) {
        int cmp = Integer.compare(this.type.getIndex(), other.type.getIndex());
        return cmp != 0 ? cmp : Integer.compare(this.rank, other.rank);
    }

    @Override
    public String toString() {
        return String.format("[%s R:%d(%d,%d)]", type.name(), rank, row, col);
    }
}