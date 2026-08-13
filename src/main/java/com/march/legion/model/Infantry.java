package com.march.legion.model;
import com.march.legion.battlefield.TroopType;

public class Infantry extends Troop {
    public Infantry(int rank) { super(TroopType.INFANTRY, rank); }
    @Override public String getUnitName() { return "Infantry"; }
}