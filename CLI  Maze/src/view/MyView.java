package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;

import algorithms.mazaGeneratios.Maze3d;
import controller.Command;
import controller.Controller;

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
	public void displaySection(String index, Maze3d maze, int num) {
		switch(index)
				{
					//case "z" : out.print(maze.printCross(maze.getCrossSectionByZ(num)));; break;
				//	case "y" : out.print(maze.printCross(maze.getCrossSectionByY(num)));; break;
					//case "x" : out.print(maze.printCross(maze.getCrossSectionByX(num)));; break;
					//default : throw new IllegalArgumentException("No such index: " + index);
				}

	}
	
}