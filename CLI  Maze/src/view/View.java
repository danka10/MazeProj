package view;

import java.util.HashMap;

import algorithms.mazaGeneratios.Maze3d;
import controller.Command;
import controller.Controller;

public interface View {
	void notifyMazeIsReady(String name);
	void displayMaze(Maze3d maze);
	void setCommands(HashMap<String, Command> commands);
	void displayPath(String path);
	void displaySection(String index, Maze3d maze, int num);
	void start();
	void setController(Controller controller);
}
