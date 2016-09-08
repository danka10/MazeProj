package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * This class extends CommonSearcher and is used for a BFS method solution
 * @author DKhrakovsky
 * @version 1.0
 * @since 31-08-16
 * 
 */

public class BFS<T> extends CommonSearcher<T> {

	private PriorityQueue<State<T>> openList = new PriorityQueue<State<T>>();
	private Set<State<T>> closedList = new HashSet<State<T>>();
	
	/**
	 * This method is implements a BFS solution by looking for the best pass so solve the maze.
	 * It runs while the next state is the goal state.
	 * @param currState get the next position to prior. 
	 * @param currState when it equals goal position, back tracing is initialized 
	 * @return backTrace of last state (should be goal) until the start.
	 */
	@Override
	public Solution<T> search(Searchable<T> s) {
		State<T> startState = s.getStartState();
		openList.add(startState);
		
		while (!openList.isEmpty()) {
			State<T> currState = openList.poll();
			evaluatedNodes++;
			closedList.add(currState);
			
			State<T> goalState = s.getGoalState();
			if (currState.equals(goalState)) {
				return backTrace(currState);
			}
			
			List<State<T>> neighbors = s.getAllPossibleStates(currState);
			
			/**
			 * Checks possible neighbors of the current position.
			 * Runs a loop and adds neighbor if it's not in the list.
			 * Calculates the cost of each move.
			 * @param neighbor every neighbor in the neighbors list
			 * 
			 */
			for (State<T> neighbor : neighbors) {
				if (!openList.contains(neighbor) && !closedList.contains(neighbor)) {
					neighbor.setCameFrom(currState);
					neighbor.setCost(currState.getCost() + s.getMoveCost(currState, neighbor));
					openList.add(neighbor);
				}
				else {
					double newPathCost = currState.getCost() + s.getMoveCost(currState, neighbor);
					if (neighbor.getCost() > newPathCost) {
						neighbor.setCost(newPathCost);
						neighbor.setCameFrom(currState);
						
						if (!openList.contains(neighbor)) {
							openList.add(neighbor);
						} 
						else { // must notify the priority queue about the change of cost
							openList.remove(neighbor);
							openList.add(neighbor);
						}
					}
				}			
			}
		}
		return null;
	}

}
