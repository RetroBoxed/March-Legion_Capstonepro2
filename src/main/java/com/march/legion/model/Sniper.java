package com.march.legion.model;
import com.march.legion.battlefield.TroopType;

public class Sniper extends Troop {
    public Sniper(int rank) { super(TroopType.SNIPER, rank); }
    @Override public String getUnitName() { return "Sniper"; }
}