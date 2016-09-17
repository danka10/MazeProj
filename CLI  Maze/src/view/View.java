package view;

import java.util.HashMap;

import algorithms.mazaGeneratios.Maze3d;
import algorithms.mazaGeneratios.Position;
import algorithms.search.Solution;
import controller.Command;
import controller.Controller;

public interface View {
	void notifyMazeIsReady(String name);
	void notifyMazeIsSolved(String name);
	void displayMaze(Maze3d maze);
	void setCommands(HashMap<String, Command> commands);
	void displayPath(String path);
	void displaySolution(Solution<Position> sol);
	void printError(String[] msg);
	void start();
	void setController(Controller controller);
	void displayMaze(int[][] maze2d);
}
