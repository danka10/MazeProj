package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;

import algorithms.mazaGeneratios.Maze3d;
import algorithms.mazaGeneratios.Position;
import algorithms.search.Solution;
import controller.Command;
import controller.Controller;

/**
 * Class MyView - handles display on screen requests
 * @author  Dan Khrakovsky & Barak Eduard
 * @version 1.0
 * @since   2016-13-09
 */

public class MyView implements View {
	
	private BufferedReader in;
	private PrintWriter out;
	private CLI cli;
	private Controller controller;

	public MyView(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
				
		cli = new CLI(in, out);
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void notifyMazeIsReady(String name) {
		out.println("maze " + name + " is ready");
		out.flush();
	}

	@Override
	public void notifyMazeIsSolved(String name) {
		out.println("maze " + name + " was solved");
		out.flush();
	}
	
	@Override
	public void displayMaze(Maze3d maze) {
		out.println(maze);
		out.flush();
	}

	@Override
	public void setCommands(HashMap<String, Command> commands) {
		cli.setCommands(commands);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		cli.start();
	}
	
	@Override
	public void displayPath(String path) {
		File folder = new File(path);
	    File[] filesList = folder.listFiles();
        for(File f : filesList){
            if(f.isDirectory())
                System.out.println(f.getName());
            if(f.isFile()){
                System.out.println(f.getName());
            }
        }
	}

	@Override
	public void displayMaze(int[][] maze2d) {
		for (int i = 0; i < maze2d.length; i++) {
			for (int j = 0; j < maze2d[i].length; j++) {
				System.out.print(maze2d[i][j]);
			}	
			System.out.println();
		}
	}

	@Override
	public void displaySolution(Solution<Position> sol) {
			System.out.println(sol);
		
	}

	@Override
	public void printError(String[] msg) {
		for (String line : msg) {
			out.println(line);
		}
	}

	@Override
	public void notifyMazeIsCompressed(String name) {
		out.println("maze " + name + " was Compressed");
		out.flush();
		
	}
}