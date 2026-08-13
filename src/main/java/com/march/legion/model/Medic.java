package com.march.legion.model;
import com.march.legion.battlefield.TroopType;

public class Medic extends Troop {
    public Medic(int rank) { super(TroopType.MEDIC, rank); }
    @Override public String getUnitName() { return "Medic"; }
}