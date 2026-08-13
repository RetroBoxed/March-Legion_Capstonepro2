package com.march.legion.algorithm;
import com.march.legion.model.Troop;
import java.util.List;

public class HeapSort implements SortStrategy {
    @Override
    public void sort(List<Troop> troops) {
        int n = troops.size();
        for (int i = n / 2 - 1; i >= 0; i--) heapify(troops, n, i);
        for (int i = n - 1; i > 0; i--) {
            Troop t = troops.get(0);
            troops.set(0, troops.get(i));
            troops.set(i, t);
            heapify(troops, i, 0);
        }
    }

    private void heapify(List<Troop> troops, int n, int i) {
        int max = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && troops.get(left).compareTo(troops.get(max)) > 0) max = left;
        if (right < n && troops.get(right).compareTo(troops.get(max)) > 0) max = right;

        if (max != i) {
            Troop t = troops.get(i);
            troops.set(i, troops.get(max));
            troops.set(max, t);
            heapify(troops, n, max);
        }
    }
}