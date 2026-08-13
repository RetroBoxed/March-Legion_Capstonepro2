package com.march.legion.model;
import com.march.legion.battlefield.TroopType;

public class Commander extends Troop {
    public Commander(int rank) { super(TroopType.COMMANDER, rank); }
    @Override public String getUnitName() { return "Commander"; }
}