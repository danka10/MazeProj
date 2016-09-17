package controller;

/**
 * class MyController.
 * @author  Dan Khrakovsky & Barak Eduard
 * @version 1.0
 * @since   2016-13-09
 */
public interface Controller {
	void notifyMazeIsReady(String name);
	//void displaySolution(Maze3d maze);
	void notifyMazeIsCompressed(String name);
	void notifyMazeIsSolved(String name);
}
