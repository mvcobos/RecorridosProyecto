package controllers.interfaces;

import java.util.List;

import models.Cell;
import models.Maze;

/*INTERFACE:
 * Indica cuales son los metodos que deberian tener las otras clases
 */
public interface MazeSolver {
    public List<Cell>getPath(Maze maze, boolean[][] grid, Cell start, Cell end);


}
