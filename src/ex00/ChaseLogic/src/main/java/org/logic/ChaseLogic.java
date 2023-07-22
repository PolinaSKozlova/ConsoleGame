package org.logic;

import java.util.*;

public class ChaseLogic {
    public static List<Coordinate> findPath(Cell[][] cells, Coordinate start,
                                            Coordinate end) {
        Queue<Coordinate> queue = new LinkedList<>();
        Map<Coordinate, Coordinate> parentMap = new HashMap<>();
        Set<Coordinate> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if (current == end) {
                return buildPath(parentMap, start, end);
            }

            for (Coordinate neighbor : getNeighbors(cells, current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        return null;
    }

    private static List<Coordinate> getNeighbors(Cell[][] cells,
                                                 Coordinate coordinate) {
        List<Coordinate> neighbors = new ArrayList<>();

        int x = coordinate.getColumn();
        int y = coordinate.getRow();
        int size = cells.length;


        // Check right neighbor
        if ((x + 1 < size) && (cells[x + 1][y] != Cell.OBSTACLE)) {
            neighbors.add(new Coordinate(y, x + 1));
        }

        // Check left neighbor
        if ((x - 1 >= 0) && (cells[x - 1][y] != Cell.OBSTACLE)) {
            neighbors.add(new Coordinate(y, x - 1));
        }

        // Check bottom neighbor
        if ((y + 1 < size) && (cells[x][y + 1] != Cell.OBSTACLE)) {
            neighbors.add(new Coordinate(y + 1, x));
        }

        // Check top neighbor
        if ((y - 1 >= 0) && (cells[x][y - 1] != Cell.OBSTACLE)) {
            neighbors.add(new Coordinate(y - 1, x));
        }
        return neighbors;
    }

    private static List<Coordinate> buildPath(Map<Coordinate, Coordinate> parentMap,
                                              Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate current = end;

        while (current != start) {
            path.add(current);
            current = parentMap.get(current);
        }

        Collections.reverse(path);
        return path;
    }

}
