package model;

import algorithms.mazaGeneratios.Maze3d;

public interface Model {
	void generateMaze(String name, int method, int floors, int rows, int cols);
	Maze3d getMaze(String name);
	void exit();	
}
