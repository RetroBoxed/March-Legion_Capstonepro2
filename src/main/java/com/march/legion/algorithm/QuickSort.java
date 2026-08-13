package com.march.legion.algorithm;
import com.march.legion.model.Troop;
import java.util.List;

public class QuickSort implements SortStrategy {
    @Override
    public void sort(List<Troop> troops) {
        if (troops.size() <= 1) return;
        quickSort(troops, 0, troops.size() - 1);
    }

    private void quickSort(List<Troop> troops, int low, int high) {
        if (low < high) {
            int pi = partition(troops, low, high);
            quickSort(troops, low, pi - 1);
            quickSort(troops, pi + 1, high);
        }
    }

    private int partition(List<Troop> troops, int low, int high) {
        Troop pivot = troops.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (troops.get(j).compareTo(pivot) < 0) {
                i++;
                Troop t = troops.get(i);
                troops.set(i, troops.get(j));
                troops.set(j, t);
            }
        }
        Troop t = troops.get(i + 1);
        troops.set(i + 1, troops.get(high));
        troops.set(high, t);
        return i + 1;
    }
}