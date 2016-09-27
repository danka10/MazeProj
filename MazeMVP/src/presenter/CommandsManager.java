package presenter;

import java.util.HashMap;

import algorithms.mazaGeneratios.Maze3d;
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
		commands.put("generate_maze", new GenerateMazeCommand());
		commands.put("display", new DisplayMazeCommand());
		commands.put("maze_ready", new MazeReadyCommand());
		commands.put("exit", new ExitCommand());
		
		return commands;
	}
	
	class GenerateMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			
			String name = args[0];
			int method = Integer.parseInt(args[1]);
			int floors = Integer.parseInt(args[2]);
			int rows = Integer.parseInt(args[3]);
			int cols = Integer.parseInt(args[4]);
			model.generateMaze(name, method, floors, rows, cols);
		}	
	}
	
	class DisplayMazeCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];
			Maze3d maze = model.getMaze(name);
			view.displayMaze(maze);
		}		
	}
	
	class ExitCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			model.exit();
		}		
	}
	
	class MazeReadyCommand implements Command {

		@Override
		public void doCommand(String[] args) {
			String name = args[0];			
			view.notifyMazeIsReady(name);
		}		
	}
	
	
}
