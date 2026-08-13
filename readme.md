# March of the Legion ⚔️

**Author:** Nestor Nicolas Cenardo Castro  
**Institution:** Jala University

> A Command Line Interface (CLI) Real-Time Strategy (RTS) troop simulator that implements four advanced sorting algorithms to organize military units on a virtual battlefield.

## 🛠 Tech Stack

* **Language:** Java 26
* **Main Pattern:** Strategy Pattern + Factory Pattern
* **Algorithms:** Quick Sort, Merge Sort, Heap Sort, Counting Sort
* **Paradigm:** Object-Oriented Programming

##  Main Components

### Layer 1 - Input (`src/main/java/com/march/legion/Main.java`)
* CLI parameter parser
* Input validation
* Flow orchestrator

### Layer 2 - Model (`src/main/java/com/march/legion/model`)
* **Troop:** Base abstract class
* **Polymorphic implementations:** Commander, Medic, Tank, Sniper, Infantry
* **Validation:** Rank validation by type

### Layer 3 - Domain (`src/main/java/com/march/legion/battlefield`)
* **Battlefield:** Game "map"
* **Orientation:** Enum for directions
* **TroopType:** Enum for types and symbols
* **Management:** NxN matrix management, positioning, and alignment

### Layer 4 - Algorithms (`src/main/java/com/march/legion/algorithm`)
* **SortStrategy:** Base interface
* **Implementations:** QuickSort, MergeSort, HeapSort, CountingSort
* **Design:** Strategy pattern for interchangeability

## ️ Problem Analysis

* **INPUT (CLI Parameters):** Syntactic validation.
* **PROCESSING (Domain Logic):** Random troop generation, sorting algorithm selection, alignment according to orientation, spatial constraints validation.
* **OUTPUT (Visualization):** Initial matrix (chaos) ->️ Final matrix (order).

##  Decisions & Alternatives

| Decision                        | Alternative         | Chosen Reason                                                                    |
|:--------------------------------|:--------------------|:---------------------------------------------------------------------------------|
| **Abstract Class Troop**        | Interface + Records | Shared state (rank, row, col, type)                                              |
| **Strategy Pattern**            | Direct Polymorphism | Algorithm interchangeability                                                     |
| **HashSet for occupy**          | Boolean matrix      | $O(1)$ lookup vs $O(n)$ initialization (Memory optimization for Sparse Matrices) |
| **TreeMap for alignment**       | Manual sorting      | Automatic sorting C M T S I                                                      |
| **Random without seed**         | Fixed seed          | Realistic variability vs reproducibility                                         |
| **Dual comparison (type+rank)** | Type only           | Guarantees correct alignment                                                     |
| **Checked exceptions**          | Unchecked           | Main can propagate them to the user                                              |

## Complexity Analysis

| Algorithm         | Best          | Average       | Worst         | Space       | Stable | Optimal Case         |
|:------------------|:--------------|:--------------|:--------------|:------------|:-------|:---------------------|
| **Quick Sort**    | $O(n \log n)$ | $O(n \log n)$ | $O(n^2)$      | $O(\log n)$ | No     | Random pivot         |
| **Merge Sort**    | $O(n \log n)$ | $O(n \log n)$ | $O(n \log n)$ | $O(n)$      | Yes    | Always predictable   |
| **Heap Sort**     | $O(n \log n)$ | $O(n \log n)$ | $O(n \log n)$ | $O(1)$      | No     | No extra space       |
| **Counting Sort** | $O(n+k)$      | $O(n+k)$      | $O(n+k)$      | $O(k)$      | Yes    | $k=50$ (fixed range) |

##  Performance Metrics (Empirical Tests)

* **Quick Sort:** 1-3 ms (best case)
* **Merge Sort:** 2-4 ms (predictable, stable)
* **Heap Sort:** 2-3 ms (no extra space)
* **Counting Sort:** 0.5-1 ms (optimal for our range)

### Algorithm Selection Criteria
* **Counting Sort:** For small fields ($n<100$)
* **Merge Sort:** To guarantee stability.
* **Quick Sort:** For general average performance.
* **Heap Sort:** When space is critical.

## 🏁 Final Conclusions

The development of March of the Legion successfully demonstrates the practical application of advanced sorting algorithms within a structured, object-oriented Java environment. By leveraging the Strategy and Factory design patterns, the architecture achieves a high degree of modularity and maintainability, allowing for seamless interchangeability of sorting behaviors (Quick, Merge, Heap, and Counting Sort) during runtime.

The complexity analysis highlights the inherent trade-offs in algorithm selection: while Counting Sort offers superior time complexity for fixed-range properties, Merge Sort provides essential stability for predictable alignment. Furthermore, the decision to utilize a HashSet for spatial constraints effectively optimizes memory usage in sparse battlefield scenarios compared to traditional boolean matrices.

Class diagram below illustrates the relationships between the main components of the system, emphasizing the use of polymorphism and strategy pattern for sorting algorithms.

![mermaid-diagram-2026-08-07-160407.png](../../Downloads/mermaid-diagram-2026-08-07-160407.png)