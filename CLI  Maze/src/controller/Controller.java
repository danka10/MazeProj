package controller;

public interface Controller {
	void notifyMazeIsReady(String name);
	//void displaySolution(Maze3d maze);
	//void notifyMazeIsCompressed(String name);
	void notifyMazeIsSolved(String name);
}
