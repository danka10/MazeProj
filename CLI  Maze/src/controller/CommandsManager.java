package controller;

import java.util.HashMap;

import algorithms.mazaGeneratios.Maze3d;
import algorithms.mazaGeneratios.Position;
import algorithms.search.Solution;
import model.Model;
import view.View;

/**
 * class CommandsManager.
 * @author  Dan Khrakovsky & Barak Eduard
 * @version 1.0
 * @since   2016-13-09
 */


public class CommandsManager {
	
	private Model model;
	private View view;
		
	public CommandsManager(Model model, View view) {
		this.model = model;
		this.view = view;		
	}
	
	/**
	 * Creates husmap of the commands.
	 */

	
	public HashMap<String, Command> getCommandsMap() {
		HashMap<String, Command> commands = new HashMap<String, Command>();
		commands.put("generate_3d_maze", new GenerateMazeCommand());
		commands.put("display", new DisplayMazeCommand());
		commands.put("dir", new DisplayDirectory());
		commands.put("display_cross_section", new DisplayCrossSection());
		commands.put("solve", new MazeSolver());
		commands.put("display_solution", new DisplaySolution());
		commands.put("load_maze", new LoadMazeFromFile());
		commands.put("save_maze", new SaveMazeToFile());
		commands.put("exit", new Exit());
		
		return commands;
	}
	
	/**
	 * The following classes are commands implementing command class
	 */

	
	public class GenerateMazeCommand implements Command {
		
		@Override
		public void doCommand(String[] args) {
			if (args.length != 5) {
				view.printError(new String[] { "Arguments Error",
						"Please enter: maze_name method floors rows cols" });
				return;
			}
			
			String name = args[0];
			int method = Integer.parseInt(args[1]);
			int floors = Integer.parseInt(args[2]);
			int rows = Integer.parseInt(args[3]);
			int cols = Integer.parseInt(args[4]);
			model.generateMaze(name, method, floors, rows, cols);
		}		
	}
	
	public class SaveMazeToFile implements Command {

		@Override
		public void doCommand(String[] args) {
			if (args.length != 2) {
				view.printError(new String[] { "Arguments Error",
						"Please enter: maze_name file_name" });
				return;
			}
			String name = args[0];
			String filename = args[1];
			Maze3d maze = model.getMaze(name);
			model.saveToFile(maze, filename, name);
		}
		
	}
	
	public class LoadMazeFromFile implements Command {

		@Override
		public void doCommand(String[] args) {
			if (args.length != 2) {
				view.printError(new String[] { "Arguments Error",
						"Please enter: file_name maze_name" });
				return;
			}
			String filename = args[0];
			String name = args[1];
			Maze3d maze = model.getMaze(name);
			model.loadFromFile(filename, name);
		}
		
	}
	
	public class MazeSolver implements Command {

		@Override
		public void doCommand(String[] args) {
			if (args.length != 2) {
				view.printError(new String[] { "Arguments Error",
						"Please enter: file_name maze_name" });
				return;
			}
			String name = args[0];
			String alg = args[1];
			Maze3d maze = model.getMaze(name);
			model.solveMaze(maze, alg, name);
		}
		
	}
	
	public class DisplayMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			if (args.length != 1) {
				view.printError(new String[] { "Arguments Error",
						"Please enter: maze_name" });
				return;
			}
			String name = args[0];
			Maze3d maze = model.getMaze(name);
			view.displayMaze(maze);
		}
		
	}
	
	public class DisplayDirectory implements Command {

		@Override
		public void doCommand(String[] args) {
			if (args.length != 1) {
				view.printError(new String[] { "Arguments Error",
						"Please enter: dir_path" });
				return;
			}
			String path = args[0];
			view.displayPath(path);
		}
		
	}
	
	public class DisplaySolution implements Command {

		@Override
		public void doCommand(String[] args) {
			if (args.length != 1) {
				view.printError(new String[] { "Arguments Error",
						"Please enter: maze_name" });
				return;
			}
			String name = args[0];
			Solution<Position> sol = model.getSolution(name);
			view.displaySolution(sol);
		}
		
	}
	
	public class Exit implements Command {

		@Override
		public void doCommand(String[] args) {
			model.exit();
			System.exit(0);
		}
		
	}
	
	public class DisplayCrossSection implements Command {

		@Override
		public void doCommand(String[] args) {
			if (args.length != 3) {
				view.printError(new String[] { "Arguments Error",
						"Please enter: maze_name index" });
				return;
			}
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
