package org.logic;

import java.util.*;

import org.logic.Coordinate;
import org.logic.enums.Cell;

public class ChaseLogic {
    public static Coordinate enemyStep(Cell[][] cells,
                                       Coordinate start,
                                       Coordinate end) {
        List<Coordinate> path = findPath(cells, start, end);
        if ((path != null) && (path.size() >= 2)) {
            return path.get(1);
        } else {
            return start;
        }
    }

    public static List<Coordinate> findPath(Cell[][] cells,
                                            Coordinate start,
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

        List<Coordinate> path = new ArrayList<>();
        path.add(start);
        return path;
    }

    private static List<Coordinate> getNeighbors(Cell[][] cells,
                                                 Coordinate coordinate) {
        List<Coordinate> neighbors = new ArrayList<>();

        int size = cells.length;

        Coordinate[] directions = {coordinate.up(),
                coordinate.down(),
                coordinate.left(),
                coordinate.right()};

        for (Coordinate direction : directions) {
            if (direction.isValidRange(0, size)) {
                Cell cell = cellType(cells, direction);
                if ((cell != Cell.OBSTACLE) && (cell != Cell.TARGET)) {
                    neighbors.add(direction);
                }
            }
        }

        return neighbors;
    }

    private static List<Coordinate> buildPath(
            Map<Coordinate, Coordinate> parentMap,
            Coordinate start,
            Coordinate end) {

        List<Coordinate> path = new ArrayList<>();
        Coordinate current = end;

        while (current != start) {
            path.add(current);
            current = parentMap.get(current);
        }

        path.add(start);
        Collections.reverse(path);
        return path;
    }

    private static Cell cellType(Cell[][] cells, Coordinate coordinate) {
        return cells[coordinate.getRow()][coordinate.getColumn()];
    }
}
