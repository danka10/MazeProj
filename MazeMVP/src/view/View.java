package view;

import java.util.HashMap;

import algorithms.mazaGeneratios.Maze3d;

public interface View {
	void notifyMazeIsReady(String name);
	void displayMaze(Maze3d maze);
	void displayMessage(String msg);	
	void start();	
}
