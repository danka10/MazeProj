package model;

import algorithms.mazaGeneratios.Maze3d;
import algorithms.mazaGeneratios.Position;
import algorithms.search.Solution;
import controller.Controller;

/**
 * Model interface
 * @author  Dan Khrakovsky & Barak Eduard
 * @version 1.0
 * @since   2016-13-09
 */


public interface Model {
	void generateMaze(String name, int method, int floors, int rows, int cols);
	
	Maze3d getMaze(String name);
	Solution<Position> getSolution(String name);
	
	void solveMaze(Maze3d maze, String alg, String name);
	void saveToFile(Maze3d maze, String filename, String name);
	void loadFromFile(String filename, String name);
	void exit();
	void setController(Controller controller);
	
}
