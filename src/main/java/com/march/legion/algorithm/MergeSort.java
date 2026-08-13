package com.march.legion.algorithm;
import com.march.legion.model.Troop;
import java.util.ArrayList;
import java.util.List;

public class MergeSort implements SortStrategy {
    @Override
    public void sort(List<Troop> troops) {
        if (troops.size() <= 1) return;
        mergeSort(troops, 0, troops.size() - 1);
    }

    private void mergeSort(List<Troop> troops, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(troops, left, mid);
            mergeSort(troops, mid + 1, right);
            merge(troops, left, mid, right);
        }
    }

    private void merge(List<Troop> troops, int left, int mid, int right) {
        List<Troop> temp = new ArrayList<>();
        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (troops.get(i).compareTo(troops.get(j)) <= 0) {
                temp.add(troops.get(i++));
            } else {
                temp.add(troops.get(j++));
            }
        }
        while (i <= mid) temp.add(troops.get(i++));
        while (j <= right) temp.add(troops.get(j++));

        for (int k = 0; k < temp.size(); k++) {
            troops.set(left + k, temp.get(k));
        }
    }
}
