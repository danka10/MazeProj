package controller;

import java.util.HashMap;

import algorithms.mazaGeneratios.Maze3d;
import algorithms.mazaGeneratios.Position;
import algorithms.search.Solution;
import model.Model;
import view.View;

public class CommandsManager {
	
	private Model model;
	private View view;
		
	public CommandsManager(Model model, View view) {
		this.model = model;
		this.view = view;		
	}
	
	public HashMap<String, Command> getCommandsMap() {
		HashMap<String, Command> commands = new HashMap<String, Command>();
		commands.put("generate_3d_maze", new GenerateMazeCommand());
		commands.put("display", new DisplayMazeCommand());
		commands.put("dir", new DisplayDirectory());
		commands.put("display_cross_section", new DisplayCrossSection());
		commands.put("solve", new MazeSolver());
		commands.put("display_solution", new DisplaySolution());
		
		return commands;
	}
	
	public class GenerateMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			int floors = Integer.parseInt(args[1]);
			int rows = Integer.parseInt(args[2]);
			int cols = Integer.parseInt(args[3]);
			model.generateMaze(name, floors, rows, cols);
		}		
	}
	
	public class MazeSolver implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			String alg = args[1];
			Maze3d maze = model.getMaze(name);
			model.solveMaze(maze, alg, name);
		}
		
	}
	
	public class DisplayMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			Maze3d maze = model.getMaze(name);
			view.displayMaze(maze);
		}
		
	}
	
	public class DisplayDirectory implements Command {

		@Override
		public void doCommand(String[] args) {
			String path = args[0];
			view.displayPath(path);
		}
		
	}
	
	public class DisplaySolution implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			Solution<Position> sol = model.getSolution(name);
			view.displaySolution(sol);
		}
		
	}
	
	public class DisplayCrossSection implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			String index = args[1];
			int sector = Integer.parseInt(args[2]);
			Maze3d maze = model.getMaze(name);
			
			int[][] maze2d = null;
			try
			{
				switch(index){
					case "X":maze2d = maze.getCrossSectionByX(sector);; break;
					case "Y":maze2d = maze.getCrossSectionByY(sector);; break;
					case "Z":maze2d = maze.getCrossSectionByZ(sector);; break;
					default:throw new Exception("Sector choice Error");
				}
			}catch (Exception e){
				
			}
			view.displayMaze(maze2d);
		}
		
	}
	
}
