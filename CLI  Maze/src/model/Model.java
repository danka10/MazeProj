package model;

import algorithms.mazaGeneratios.Maze3d;
import algorithms.mazaGeneratios.Position;
import algorithms.search.Solution;
import controller.Controller;

public interface Model {
	void generateMaze(String name, int floors, int rows, int cols);
	Maze3d getMaze(String name);
	Solution<Position> getSolution(String name);
	void solveMaze(Maze3d maze, String alg, String name);
	//void displaySolution(Maze3d maze);
	void exit();
	void setController(Controller controller);
	
}
