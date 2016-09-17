package boot;

import algorithms.mazaGeneratios.GrowingTreeGenerator;
import algorithms.mazaGeneratios.Maze3d;
//import algorithms.mazaGeneratios.SimpleMazeGenerator;

public class Run {

	public static void main(String[] args) {
		//Maze3d maze = new Maze3d(3, 6, 6);
		//maze.setStartPosition(new Position(2, 2));
		//maze.setGoalPosition(new Position(5, 5));
		
		//SimpleMazeGenerator gen = new SimpleMazeGenerator();
		GrowingTreeGenerator gen = new GrowingTreeGenerator();
		Maze3d maze = gen.generate(1, 3, 6, 6);
		System.out.println(maze);
	}
}
