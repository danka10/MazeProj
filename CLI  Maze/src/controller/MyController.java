package controller;

import algorithms.mazaGeneratios.Maze3d;
import model.Model;
import view.View;

/**
 * class MyController.
 * @author  Dan Khrakovsky & Barak Eduard
 * @version 1.0
 * @since   2016-13-09
 */

public class MyController implements Controller {

	private View view;
	private Model model;
	private CommandsManager commandsManager;
	
	/**
	 * Controller c'tor
	 * @param view
	 * @param model
	 */
	public MyController(View view, Model model) {
		this.view = view;
		this.model = model;
		
		commandsManager = new CommandsManager(model, view);
		view.setCommands(commandsManager.getCommandsMap());
	}
	
	
	@Override
	public void notifyMazeIsReady(String name) {
		view.notifyMazeIsReady(name);
	}


	@Override
	public void notifyMazeIsSolved(String name) {
		view.notifyMazeIsSolved(name);
		
	}


	@Override
	public void notifyMazeIsCompressed(String name) {
		view.notifyMazeIsCompressed(name);
		
	}


}
