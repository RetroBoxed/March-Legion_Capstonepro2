package com.march.legion;

import com.march.legion.model.*;
import com.march.legion.battlefield.*;
import com.march.legion.algorithm.*;
import java.util.*;

public class Main {

    static Map<String, String> parseArgs(String[] args) {
        Map<String, String> params = new HashMap<>();
        for (String arg : args) {
            if (arg.contains("=")) {
                String[] parts = arg.split("=");
                if (parts.length == 2) {
                    params.put(parts[0].toLowerCase(), parts[1]);
                }
            }
        }
        return params;
    }

    static void validateParams(Map<String, String> params) {
        String[] required = {"a", "t", "o", "u", "f"};
        for (String req : required) {
            if (!params.containsKey(req)) {
                throw new IllegalArgumentException("Missing required parameter: " + req);
            }
        }
    }

    static String getAlgorithmName(char code) {
        return switch (code) {
            case 'Q' -> "Quick Sort";
            case 'M' -> "Merge Sort";
            case 'H' -> "Heap Sort";
            case 'C' -> "Counting Sort";
            default -> "Unknown";
        };
    }

    static String getOrientationName(char code) {
        return switch (code) {
            case 'n' -> "North";
            case 's' -> "South";
            case 'e' -> "East";
            case 'w' -> "West";
            default -> "Unknown";
        };
    }

    static String getTypeName(String type) {
        return type.equals("n") ? "Numeric" : "Character";
    }

    static Battlefield createBattlefield(Map<String, String> params) {
        try {
            char orientation = params.get("o").charAt(0);
            int fieldSize = Integer.parseInt(params.get("f"));

            if (fieldSize < 5 || fieldSize > 1000) {
                throw new IllegalArgumentException("invalid battlefield size");
            }

            Orientation orient = Orientation.fromCode(orientation);

            String[] units = params.get("u").split(",");
            if (units.length != 5) {
                throw new IllegalArgumentException("Units must have 5 values");
            }

            List<Troop> troops = new ArrayList<>();
            String type = params.get("t");

            if (type.equals("c")) {
                generateCharacterTroops(troops, units);
            } else if (type.equals("n")) {
                generateNumericTroops(troops, units);
            } else {
                throw new IllegalArgumentException("Invalid type: " + type);
            }

            Battlefield battlefield = new Battlefield(fieldSize, orient, troops);
            battlefield.initializeRandomPositions();
            return battlefield;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid battlefield size");
        }
    }

    static void generateCharacterTroops(List<Troop> troops, String[] units) {
        int[] counts = new int[5];
        for (int i = 0; i < 5; i++) {
            try {
                counts[i] = Integer.parseInt(units[i]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid unit count");
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < counts[i]; j++) {
                int rank = (j % 49) + 1;
                Troop t = switch (i) {
                    case 0 -> new Commander(rank);
                    case 1 -> new Medic(rank);
                    case 2 -> new Tank(rank);
                    case 3 -> new Sniper(rank);
                    case 4 -> new Infantry(rank);
                    default -> null;
                };
                if (t != null) troops.add(t);
            }
        }
    }

    static void generateNumericTroops(List<Troop> troops, String[] units) {
        int[][] ranges = {{1, 10}, {11, 20}, {21, 30}, {31, 40}, {41, 50}};

        int[] counts = new int[5];
        for (int i = 0; i < 5; i++) {
            try {
                counts[i] = Integer.parseInt(units[i]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid unit count");
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < counts[i]; j++) {
                int min = ranges[i][0];
                int max = ranges[i][1];
                int rank = min + (j % (max - min + 1));

                Troop t = switch (i) {
                    case 0 -> new Commander(rank);
                    case 1 -> new Medic(rank);
                    case 2 -> new Tank(rank);
                    case 3 -> new Sniper(rank);
                    case 4 -> new Infantry(rank);
                    default -> null;
                };
                if (t != null) troops.add(t);
            }
        }
    }

    static SortStrategy getSortStrategy(char alg) {
        return switch (alg) {
            case 'Q' -> new QuickSort();
            case 'M' -> new MergeSort();
            case 'H' -> new HeapSort();
            case 'C' -> new CountingSort();
            default -> throw new IllegalArgumentException("Invalid algorithm: " + alg);
        };
    }

    public static void main(String[] args) {
        try {
            Map<String, String> params = parseArgs(args);
            validateParams(params);

            char algCode = Character.toUpperCase(params.get("a").charAt(0));
            char typeCode = params.get("t").charAt(0);
            char orientCode = params.get("o").charAt(0);

            int totalTroops = 0;
            for (String unit : params.get("u").split(",")) {
                totalTroops += Integer.parseInt(unit);
            }

            System.out.println("Algorithm: [" + getAlgorithmName(algCode) + "]");
            System.out.println("Type: [" + getTypeName(String.valueOf(typeCode)) + "]");
            System.out.println("Orientation: [" + getOrientationName(orientCode) + "]");
            System.out.println("Troops: [" + totalTroops + "]");
            System.out.println("Battlefield: [" + params.get("f") + " x " + params.get("f") + "]");
            System.out.println();

            Battlefield battlefield = createBattlefield(params);

            System.out.println("Initial Position:");
            System.out.println(battlefield.printMatrix());

            long startTime = System.nanoTime();
            SortStrategy sorter = getSortStrategy(algCode);
            sorter.sort(battlefield.getTroops());
            battlefield.sortTroops();
            long endTime = System.nanoTime();

            System.out.println("Final Position:");
            System.out.println(battlefield.printMatrix());

            long timeMs = (endTime - startTime) / 1_000_000;
            System.out.println("Execution Time: " + timeMs + " ms");

        } catch (IllegalArgumentException e) {
            System.err.println("ERROR: " + e.getMessage());
            printUsage();
            System.exit(1);
        } catch (Exception e) {
            System.err.println("FATAL ERROR: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    static void printUsage() {
        System.err.println("\nUsage: java Troops a=<alg> t=<type> o=<orient> u=<units> f=<size>");
        System.err.println("\nParameters:");
        System.err.println("  a=  Algorithm: Q=Quick, M=Merge, H=Heap, C=Counting");
        System.err.println("  t=  Type: n=Numeric, c=Character");
        System.err.println("  o=  Orientation: n=North, s=South, e=East, w=West");
        System.err.println("  u=  Units: C,M,T,S,I (Commander,Medic,Tank,Sniper,Infantry)");
        System.err.println("  f=  Battlefield size (5-1000)\n");
        System.err.println("Example: java Troops a=q t=c o=n u=1,2,5,5,10 f=10\n");
    }
}