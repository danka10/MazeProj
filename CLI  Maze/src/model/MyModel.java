package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

import algorithms.demo.MazeAdapter;
import algorithms.io.MyCompressorOutputStream;
import algorithms.io.MyDecompressorInputStream;
import algorithms.mazaGeneratios.GrowingTreeGenerator;
import algorithms.mazaGeneratios.Maze3d;
import algorithms.mazaGeneratios.Maze3dGenerator;
import algorithms.mazaGeneratios.Position;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Solution;
import controller.Command;
import controller.Controller;

/**
 * Class MyModel
 * @author  Dan Khrakovsky & Barak Eduard
 * @version 1.0
 * @since   2016-13-09
 */

public class MyModel implements Model {
	
	private List<GenerateMazeRunnable> generateMazeTasks = new ArrayList<GenerateMazeRunnable>();
	
	private MazeAdapter adapter, adapter2;
	private BFS<Position> bfs;
	private DFS<Position> dfs;
	private Solution<Position> solution, solution2;
	
	class GenerateMazeRunnable implements Runnable {

		private int method, floors, rows, cols;
		private String name;
		private GrowingTreeGenerator generator;
		//private Maze3dGenerator generatorr;
		
		/**
		 * Instantiates a new generate maze runnable.
		 *
		 * @param floors
		 *            the floors
		 * @param rows
		 *            the rows
		 * @param columns
		 *            the columns
		 * @param name
		 *            the name of the maze
		 */
		
		public GenerateMazeRunnable(int method, int floors, int rows, int cols, String name) {
			this.floors=floors;
			this.rows = rows;
			this.cols = cols;
			this.name = name;
			this.method = method;
			
		}
		
		@Override
		public void run() {
			generator = new GrowingTreeGenerator();
			Maze3d maze = generator.generate(method, floors, rows, cols);
			mazes.put(name, maze);
			
			controller.notifyMazeIsReady(name);			
		}
		
		public void terminate() {
			generator.setDone(true);
		}		
	}
	
	private Controller controller;	
	private Map<String, Maze3d> mazes = new ConcurrentHashMap<String, Maze3d>();
	private Map<String, Solution<Position>> solutions = new ConcurrentHashMap<String, Solution<Position>>();
	private List<Thread> threads = new ArrayList<Thread>();
	
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void generateMaze(String name, int method, int floors, int rows, int cols) {
		GenerateMazeRunnable generateMaze = new GenerateMazeRunnable(method, floors, rows, cols, name);
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
	public void solveMaze(Maze3d maze, String alg, String name) {
    	adapter = new MazeAdapter(maze);
	    try {
            switch(alg) {
                case "BFS": 
                	System.out.println("BFS");
        			bfs = new BFS<Position>();
        			solution = bfs.search(adapter);
        			solutions.put(name, solution);
        			controller.notifyMazeIsSolved(name);
        			break;
                case "DFS": 
                	System.out.println("DFS");
        			dfs = new DFS<Position>();
        			solution = dfs.search(adapter);
        			solutions.put(name, solution);
        			controller.notifyMazeIsSolved(name);
        			break;
            }
	    } catch (Exception e) {
            System.out.print("RuntimeException: ");
        } 

	}


	@Override
	public Solution<Position> getSolution(String name) {
		return solutions.get(name);
	}

	@Override
	public void saveToFile(Maze3d maze, String filename, String name) {
		OutputStream out;
		try {
			 out=new MyCompressorOutputStream(new FileOutputStream(filename + ".maz"));
				out.write(maze.toByteArray());
				out.flush();
				out.close();
			//	mazes.put(name, maze);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		controller.notifyMazeIsCompressed(name);
	}

	@Override
	public void loadFromFile(String filename, String name) {
		InputStream in;
		// use to check how many files are open for a proper exit
		//openFileCount++;
		try {
			
			File fileinst = new File(filename + ".maz");
			in = new MyDecompressorInputStream(new FileInputStream(fileinst));
			byte b[] = new byte[(int) fileinst.length()+1];
			in.close();
			Maze3d loaded = new Maze3d(b);
			mazes.put(name, loaded);
			controller.notifyMazeIsReady(name);
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace(); 
		} 
		catch (IOException e) {
			e.printStackTrace();
	}
		
	}

	
	
}
