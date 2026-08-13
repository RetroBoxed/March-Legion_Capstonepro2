package com.march.legion.algorithm;

import com.march.legion.model.Troop;
import java.util.List;

public interface SortStrategy {
    void sort(List<Troop> troops);
}
