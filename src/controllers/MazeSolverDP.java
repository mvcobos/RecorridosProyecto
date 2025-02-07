package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import controllers.interfaces.MazeSolver;
import models.Cell;
import models.Maze;

public class MazeSolverDP implements MazeSolver {

    private Map<Cell, Boolean> memoria = new HashMap<>();

    @Override
    public List<Cell>getPath(Maze maze, boolean[][] grid, Cell start, Cell end){
        System.out.println("Implementacion con DP");
        List<Cell> path = new ArrayList<>();
        Set<Cell> visitadas = new HashSet<>();
        Set<Cell> pathRecorrido = new HashSet<>();


        if(grid == null || grid.length == 0) return path;

        if(findPath(grid, start.row, start.col, end, path, visitadas, pathRecorrido))
            return path;


        return new ArrayList<>();
    }

    private boolean findPath(boolean[][] grid, int row, int col, Cell end, List<Cell> path, Set<Cell> visitadas, Set<Cell> pathRecorrido) {
        Cell cell = new Cell(row, col);
        
        if (row < 0 || col < 0 || row >= grid.length || col>= grid[0].length || !grid[row][col] || memoria.containsKey(cell)) {
            return false;
        }

        if (visitadas.contains(cell)) {
            return false;
        }
        visitadas.add(cell);
        pathRecorrido.add(cell);

        //Validamos la llegada al fin
        if (row == end.row && col == end.col) {
            path.add(cell);
            
            return true;
        }

        if (findPath(grid, row + 1, col, end, path, visitadas, pathRecorrido) || findPath(grid, row, col + 1, end, path, visitadas, pathRecorrido) ) {
            path.add(cell);
            pathRecorrido.add(cell);
            memoria.put(cell, true);
            return true;
        }

        if (findPath(grid, row, col - 1, end, path, visitadas, pathRecorrido) || findPath(grid, row - 1, col, end, path, visitadas, pathRecorrido) ) {
            path.add(cell);
            pathRecorrido.add(cell);
            memoria.put(cell, true);

            return true;
        }

        memoria.put(cell, false);
        visitadas.remove(cell);
        path.remove(cell);
        return false;

    }


}
