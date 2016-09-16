package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import algorithms.demo.MazeAdapter;
import algorithms.mazaGeneratios.GrowingTreeGenerator;
import algorithms.mazaGeneratios.Maze3d;
import algorithms.mazaGeneratios.Position;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Solution;
import controller.Command;
import controller.Controller;

public class MyModel implements Model {
	
	private List<GenerateMazeRunnable> generateMazeTasks = new ArrayList<GenerateMazeRunnable>();
	
	private MazeAdapter adapter, adapter2;
	private BFS<Position> bfs;
	private DFS<Position> dfs;
	private Solution<Position> solution, solution2;
	
	class GenerateMazeRunnable implements Runnable {

		private int floors, rows, cols;
		private String name;
		private GrowingTreeGenerator generator;
		
		
		public GenerateMazeRunnable(int floors, int rows, int cols, String name) {
			this.floors=floors;
			this.rows = rows;
			this.cols = cols;
			this.name = name;
		}
		
		@Override
		public void run() {
			generator = new GrowingTreeGenerator();
			Maze3d maze = generator.generate(floors, rows, cols);
			mazes.put(name, maze);
			
			controller.notifyMazeIsReady(name);			
		}
		
		public void terminate() {
			generator.setDone(true);
		}		
	}
	
	private Controller controller;	
	private Map<String, Maze3d> mazes = new ConcurrentHashMap<String, Maze3d>();
	
	private List<Thread> threads = new ArrayList<Thread>();
	
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void generateMaze(String name, int floors, int rows, int cols) {
		GenerateMazeRunnable generateMaze = new GenerateMazeRunnable(floors, rows, cols, name);
		generateMazeTasks.add(generateMaze);
		Thread thread = new Thread(generateMaze);
		thread.start();
		threads.add(thread);		
	}

	@Override
	public Maze3d getMaze(String name) {
		return mazes.get(name);
	}
	
	public void exit() {
		for (GenerateMazeRunnable task : generateMazeTasks) {
			task.terminate();
		}
	}

	@Override
	public void solveMaze(Maze3d maze, String alg) {
    	adapter = new MazeAdapter(maze);
	    try {
            switch(alg) {
                case "BFS": 
                	System.out.println("BFS");
        			bfs = new BFS<Position>();
        			solution = bfs.search(adapter);
        			System.out.println(solution);
        			System.out.println(bfs.getNumberOfNodesEvaluated());
        			break;
                case "DFS": 
                	System.out.println("DFS");
        		    //adapter2 = new MazeAdapter(maze);
        			dfs = new DFS<Position>();
        			solution2 = dfs.search(adapter);
        			System.out.println(solution2);
        			System.out.println(dfs.getNumberOfNodesEvaluated());
        			break;
            }
	    } catch (Exception e) {
            System.out.print("RuntimeException: ");
        } 

	}

	
	
}
