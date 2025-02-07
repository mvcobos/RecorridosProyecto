package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import controllers.interfaces.MazeSolver;
import models.Cell;
import models.Maze;

public class MazeSolverRecursivo implements MazeSolver{
// Todas las celdad = path
// Solo las recorridas = visitadas
// visitadas.remove(cell) backTracking

/*
 * En el caso de encontrar un camino true: path & visitadas son iguales
 * Path solo guarda, visitadas como es un Set sirve para que no se generen bucles.
 * 
 * Visitadas guarda solo el camino correcto
 * 
 * Habra ocaciones en que visitadas sea mas corto que visitadas
 * 
 * Visitadas elimina la celda de su LinkedHashSet en el caso de no poder avanzar por ahi 
 * PathRecorrido guarda por todos los caminos por donde fue 
 * 
 */

//Unicamente cuando es true 
    @Override
    public List<Cell> getPath(Maze maze, boolean[][] grid, Cell start, Cell end) {
        System.out.println("Implementacion Recursiva");
            List<Cell> path = new ArrayList<>();
            Set<Cell> visitadas = new LinkedHashSet<>();
            Set<Cell> pathRecorrido = new HashSet<>();

            if(grid == null || grid.length == 0){
                return path;
            }

            if(findPath(maze, grid, start.row, start.col, end, start, path, visitadas, pathRecorrido)){
                return path;
            }
            List<Cell> listaRecorrido = new ArrayList<>(pathRecorrido);
            return listaRecorrido;
    }

    private boolean findPath(Maze maze, boolean[][] grid, int row, int col, Cell end, Cell start, List<Cell> path, Set<Cell> visitadas, Set<Cell> pathRecorrido) {
        if (row < 0 || col < 0 ||row >= grid.length || col >= grid[0].length || !grid[row] [col]) {
            return false;
        }

        Cell cell = new Cell(row, col);

        if (visitadas.contains(cell)) {
            return false;
        }
        visitadas.add(cell);
        pathRecorrido.add(cell);
        maze.updateMaze(cell, start, end);
        

        //Explorar direcciones
        if (row == end.row && col == end.col) {
            pathRecorrido.add(cell);
            path.add(cell);
            return true;
        }
        if (findPath(maze, grid, row, col + 1, end, start, path,visitadas, pathRecorrido)) {
            path.add(cell);
            pathRecorrido.add(cell);
            return true;
        }
        if (findPath(maze, grid, row + 1, col, end, start, path,visitadas, pathRecorrido)) {
            path.add(cell);
            pathRecorrido.add(cell);
            return true;
        }

        if (findPath(maze, grid, row, col - 1, end, start, path,visitadas, pathRecorrido)) { //Izquierda
            path.add(cell);
            pathRecorrido.add(cell);
            return true;
        }

        if (findPath(maze, grid, row - 1, col, end, start, path,visitadas, pathRecorrido)) { //Arriba
            path.add(cell);
            pathRecorrido.add(cell);
            return true;
        }   

        
        visitadas.remove(cell);
        path.remove(cell);
        return false;
    }
}
