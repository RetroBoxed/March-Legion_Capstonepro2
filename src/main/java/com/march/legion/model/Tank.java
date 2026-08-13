package com.march.legion.model;
import com.march.legion.battlefield.TroopType;

public class Tank extends Troop {
    public Tank(int rank) { super(TroopType.TANK, rank); }
    @Override public String getUnitName() { return "Tank"; }
}