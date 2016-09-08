package algorithms.demo;

/**
 * Main Demo
 * Creates maze and runs BFS and DFS solutions 
 * @author DKhrakovsky
 * @version 1.0
 * @since 31-08-16
 * 
 */

import algorithms.mazaGeneratios.GrowingTreeGenerator;
import algorithms.mazaGeneratios.Maze3d;
import algorithms.mazaGeneratios.Maze3dGenerator;
import algorithms.mazaGeneratios.Position;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Solution;

public class Demo {

	public void run() {		
		Maze3dGenerator generator = new GrowingTreeGenerator();
		Maze3d maze = generator.generate(3, 10, 10);
		System.out.println(maze);
		
		MazeAdapter adapter = new MazeAdapter(maze);
		BFS<Position> bfs = new BFS<Position>();
		Solution<Position> solution = bfs.search(adapter);
		System.out.println(solution);
		System.out.println(bfs.getNumberOfNodesEvaluated());
		DFS<Position> dfs = new DFS<Position>();
		Solution<Position> solution2 = dfs.search(adapter);
		System.out.println(solution2);
		System.out.println(dfs.getNumberOfNodesEvaluated());
	}

}
