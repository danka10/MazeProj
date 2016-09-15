package model;

import algorithms.mazaGeneratios.Maze3d;
import controller.Controller;

public interface Model {
	void generateMaze(String name, int floors, int rows, int cols);
	Maze3d getMaze(String name);
	void exit();
	void setController(Controller controller);
	
}
