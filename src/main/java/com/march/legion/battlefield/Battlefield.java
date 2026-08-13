package com.march.legion.battlefield;

import com.march.legion.model.Troop;
import java.util.*;

public class Battlefield {
    private final int size;
    private final Troop[][] matrix;
    private final Orientation orientation;
    private final List<Troop> troops;
    private Random random;

    public Battlefield(int size, Orientation orientation, List<Troop> troops) {
        if (size < 5 || size > 1000) {
            throw new IllegalArgumentException("invalid battlefield size");
        }
        this.size = size;
        this.orientation = orientation;
        this.troops = new ArrayList<>(troops);
        this.matrix = new Troop[size][size];
        this.random = new Random();
    }

    public void initializeRandomPositions() {
        if (troops.size() > size * size) {
            throw new IllegalArgumentException("invalid battlefield size");
        }

        Set<String> occupied = new HashSet<>();
        for (Troop troop : troops) {
            int row, col;
            do {
                row = random.nextInt(size);
                col = random.nextInt(size);
            } while (occupied.contains(row + "," + col));

            troop.setRow(row);
            troop.setCol(col);
            matrix[row][col] = troop;
            occupied.add(row + "," + col);
        }
    }

    public void sortTroops() {
        troops.sort(Troop::compareTo);
        validateSortedPlacement();
        placeTroopsAligned();
    }

    private void validateSortedPlacement() {
        Map<TroopType, Integer> typeCount = new HashMap<>();
        for (Troop troop : troops) {
            typeCount.merge(troop.getType(), 1, Integer::sum);
        }

        for (int count : typeCount.values()) {
            if (count > size) {
                throw new IllegalArgumentException("invalid battlefield size");
            }
        }
    }

    private void placeTroopsAligned() {
        clearMatrix();
        if (orientation.isVertical()) {
            placeVertical();
        } else {
            placeHorizontal();
        }
    }

    private void placeVertical() {
        Map<TroopType, List<Troop>> grouped = new TreeMap<>(
                (a, b) -> Integer.compare(a.getIndex(), b.getIndex())
        );

        for (Troop t : troops) {
            grouped.computeIfAbsent(t.getType(), k -> new ArrayList<>()).add(t);
        }

        int row = orientation == Orientation.SOUTH_NORTH ? size - 1 : 0;
        int inc = orientation == Orientation.SOUTH_NORTH ? -1 : 1;

        for (List<Troop> group : grouped.values()) {
            if (group.size() > size) throw new IllegalArgumentException("invalid battlefield size");
            for (int col = 0; col < group.size(); col++) {
                group.get(col).setRow(row);
                group.get(col).setCol(col);
                matrix[row][col] = group.get(col);
            }
            row += inc;
        }
    }

    private void placeHorizontal() {
        Map<TroopType, List<Troop>> grouped = new TreeMap<>(
                (a, b) -> Integer.compare(a.getIndex(), b.getIndex())
        );

        for (Troop t : troops) {
            grouped.computeIfAbsent(t.getType(), k -> new ArrayList<>()).add(t);
        }

        int col = orientation == Orientation.WEST_EAST ? 0 : size - 1;
        int inc = orientation == Orientation.WEST_EAST ? 1 : -1;

        for (List<Troop> group : grouped.values()) {
            if (group.size() > size) throw new IllegalArgumentException("invalid battlefield size");
            for (int row = 0; row < group.size(); row++) {
                group.get(row).setRow(row);
                group.get(row).setCol(col);
                matrix[row][col] = group.get(row);
            }
            col += inc;
        }
    }

    private void clearMatrix() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = null;
            }
        }
    }

    public String printMatrix() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(matrix[i][j] == null ? "." : matrix[i][j].getSymbol());
                if (j < size - 1) sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getSize() { return size; }
    public Orientation getOrientation() { return orientation; }
    public List<Troop> getTroops() { return troops; }
}