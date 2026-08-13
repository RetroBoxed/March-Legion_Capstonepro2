package com.march.legion.algorithm;
import com.march.legion.model.Troop;
import java.util.*;

public class CountingSort implements SortStrategy {
    @Override
    public void sort(List<Troop> troops) {
        if (troops.isEmpty()) return;

        List<Troop>[] buckets = new ArrayList[51];
        for (int i = 0; i < 51; i++) buckets[i] = new ArrayList<>();

        for (Troop t : troops) buckets[t.getRank()].add(t);

        troops.clear();
        for (List<Troop> bucket : buckets) troops.addAll(bucket);
        troops.sort((a, b) -> Integer.compare(a.getType().getIndex(), b.getType().getIndex()));
    }
}

