import exceptions.NoPathE;
import exceptions.OutsideGridException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Grid {
    private final Optional<Cell>[][] grid; // indexed by [x][y]
    private final ArrayList<Cell> start;
    private final ArrayList<Cell> end;
    private final int columns;
    private final int rows;
    private HashMap<Car,PList<Cell>> hm;

    static Grid fromFile (String filename) throws IOException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            int columns = scanner.nextInt();
            int rows = scanner.nextInt();
            ArrayList<Cell> start = new ArrayList<>();
            ArrayList<Cell> end = new ArrayList<>();
            ArrayList<Cell> cells = new ArrayList<>();
            int x = 0, y = 0;
            while (scanner.hasNext()) {
                switch (scanner.next().charAt(0)) {
                    case 'S' -> start.add(new Cell(x,y));
                    case 'E' -> end.add(new Cell(x,y));
                    case '.' -> cells.add(new Cell(x, y));
                }
                x++;
                if (x == columns) {
                    x = 0;
                    y++;
                }
            }
            return new Grid(columns, rows, start, end, cells);
        }
    }
    @SuppressWarnings("unchecked")
    Grid (int columns, int rows, ArrayList<Cell> start, ArrayList<Cell> end, ArrayList<Cell> cells) {
        this.columns = columns; // indexed by x
        this.rows = rows; // indexed y
        this.start = start;
        this.end = end;
        grid = (Optional<Cell>[][]) Array.newInstance(Optional.class, columns, rows);
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                grid[x][y] = Optional.empty();
            }
        }
        for (Cell cell : start) {
            grid[cell.x()][cell.y()] = Optional.of(cell);
        }
        for (Cell cell : end) {
            grid[cell.x()][cell.y()] = Optional.of(cell);
        }
        for (Cell cell : cells) {
            grid[cell.x()][cell.y()] = Optional.of(cell);
        }
    }
    Cell at (int x, int y) throws OutsideGridException {
        if (x < 0 || x >= columns || y < 0 || y >= rows) throw new OutsideGridException(x,y);
        return grid[x][y].orElseThrow(() -> new OutsideGridException(x,y));
    }

    ArrayList<Car> newCars () {
        ArrayList<Car> cars = new ArrayList<>();
        for (Cell cell : start) {
            cars.add(new Car(cell, 0, 0));
        }
        return cars;
    }

    void clearCache () {
        hm = new HashMap<>();
    }

    boolean validMove (int deltax, int deltay) {
        if (deltax == 0 && deltay == 0) return false; // car must move
        return deltax >= 0; // car must move forward
    }

    PList<Cell> computePathH (Car car) throws NoPathE {

        /*
        Pseudocode:
        if car is in hashmap:
            return the value of the car in the hashmap (this will be the base case)
        node x is the car and node y is the end (want to calculate the shortest path from x to y)
        want to calculate all neighbors of x, that lead to y
         */
        if (hm.containsKey(car)) return hm.get(car);

        if (end.contains(car.getPosition())) {
            PList<Cell> result = new NEmptyPList<>(car.getPosition(), new EmptyPList<>());
            hm.put(car, result);
            return result;
        }

        // Initialize a queue for breadth-first search
        Queue<Cell> queue = new LinkedList<>();
        queue.add(car.getPosition());

        // Keep track of visited cells to avoid cycles
        Set<Cell> visited = new HashSet<>();
        visited.add(car.getPosition());

        // Initialize the path map for backtracking
        HashMap<Cell, PList<Cell>> pathMap = new HashMap<>();
        pathMap.put(car.getPosition(), new EmptyPList<>());

        while (!queue.isEmpty()) {
            Cell currentCell = queue.poll();
            PList<Cell> currentPath = pathMap.get(currentCell);

            // Check all neighboring cells
            for (int deltax = -1; deltax <= 1; deltax++) {
                for (int deltay = -1; deltay <= 1; deltay++) {
                    // Skip the case where deltax and deltay are both 0 (no movement)
                    if (deltax == 0 && deltay == 0) continue;

                    int newX = currentCell.x() + deltax;
                    int newY = currentCell.y() + deltay;

                    try {
                        Cell newCell = at(newX, newY);

                        // Check if the move is valid
                        if (validMove(deltax, deltay) && !visited.contains(newCell)) {
                            queue.add(newCell);
                            visited.add(newCell);
                            PList<Cell> newPath = new NEmptyPList<>(newCell, currentPath);
                            pathMap.put(newCell, newPath);
                        }
                    } catch (OutsideGridException ignored) {
                    }
                }
            }
        }

        // Check if a path to an end cell was found
        for (Cell endCell : end) {
            if (pathMap.containsKey(endCell)) {
                PList<Cell> result = pathMap.get(endCell);
                hm.put(car, result);
                return result;
            }
        }

        // If we reach here, there is no valid path to an end cell.
        throw new NoPathE();
    }


    void race () {
        ArrayList<Car> cars = newCars();
        Optional<PList<Cell>> path = Optional.empty();
        for (Car car : cars) {
            try {
                clearCache();
                PList<Cell> p = computePathH(car);
                if (path.isEmpty() || p.size() < path.get().size()) {
                    path = Optional.of(p);
                }
            } catch (NoPathE e) {
            }
        }
        path.ifPresentOrElse(
                this::printPath,
                () -> System.out.println("No path found")
        );
    }

    public void printPath (PList<Cell> path) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                grid[x][y].ifPresentOrElse(
                        cell -> {
                            if (path.contains(cell)) {
                                sb.append('*');
                            } else if (start.contains(cell)) {
                                sb.append('S');
                            } else if (end.contains(cell)) {
                                sb.append('E');
                            } else {
                                sb.append('.');
                            }
                        },
                        () -> sb.append(' ')
                );
                sb.append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d %d\n", columns, rows));
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                grid[x][y].ifPresentOrElse(
                        cell -> {
                            if (start.contains(cell)) {
                                sb.append('S');
                            } else if (end.contains(cell)) {
                                sb.append('E');
                            } else {
                                sb.append('.');
                            }
                        },
                        () -> sb.append(' ')
                );
                sb.append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
