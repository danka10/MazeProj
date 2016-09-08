package algorithms.demo;

import java.util.ArrayList;
import java.util.List;

import algorithms.mazaGeneratios.Maze3d;
import algorithms.mazaGeneratios.Position;
import algorithms.search.Searchable;
import algorithms.search.State;

/**
 * This class implements a Searchable of Position type 
 * @author DKhrakovsky
 * @version 1.0
 * @since 31-08-16
 * 
 */

public class MazeAdapter implements Searchable<Position> {

	private Maze3d maze;
	State<Position> startState;
	State<Position> goalState;
	
	/**
	 * Constructor of MazeAdapter
	 * @param maze of maze3d type
	 * @param goalpos and startpos
	 * 
	 */
	
	public MazeAdapter(Maze3d maze) {
		this.maze = maze;
		Position startPos = maze.getStartPosition();
		this.startState = new State<Position>(startPos);
		Position goalPos = maze.getGoalPosition();
		this.goalState = new State<Position>(goalPos);
	}	


	@Override
	public State<Position> getStartState() {
		return startState;	
	}

	@Override
	public State<Position> getGoalState() {
		return goalState;	
	}

	@Override
	public List<State<Position>> getAllPossibleStates(State<Position> s) {
		Position currPos = s.getValue();
		
		List<Position> moves = maze.getPossibleMoves(currPos);
		List<State<Position>> states = new ArrayList<State<Position>>();
		
		for (Position pos: moves) {
			states.add(new State<Position>(pos));
		}
		return states;		
	}

	@Override
	public double getMoveCost(State<Position> currState, State<Position> neighbor) {		
		return 1; // in the maze all moves have the same cost
	}


	@Override
	public void setGoalState(State<Position> state) {
		this.goalState = state;
	}

}
