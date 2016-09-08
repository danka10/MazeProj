package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * This class extends CommonSearcher and is used for a DFS method solution
 * @author DKhrakovsky
 * @version 1.0
 * @since 31-08-16
 * 
 */

public class DFS<T> extends CommonSearcher<T> {

	private PriorityQueue<State<T>> openList = new PriorityQueue<State<T>>();
	//private Set<State<T>> closedList = new HashSet<State<T>>();
	
	/**
	 * This method is implements a DFS solution.
	 * It runs a recursive DFS method to find the goal state.
	 * @param startState gets the start position. 
	 * @return backTrace of goal to start
	 */
	
	@Override
	public Solution<T> search(Searchable<T> s) {
		State<T> startState = s.getStartState();
		funcDFS(s, startState);
		//State<T> currState = openList.poll();		
		return backTrace(s.getGoalState());
	}
	
	/**
	 * This method is a recursive  DFS method.
	 * Runs until the state is the goal state.
	 * the for loop run for all the position 
	 * neighbors and adds a neighbor if it's no in the list.
	 * @return when state is goal 
	 * 
	 */
	public void funcDFS(Searchable<T> sc, State<T> st){
		evaluatedNodes++;
		if (st.equals(sc.getGoalState())){
			sc.setGoalState(st);
			return;
		}
		List<State<T>> neighbors = sc.getAllPossibleStates(st);
		for (State<T> neighbor : neighbors){
				
			if (!openList.contains(neighbor)) {
				neighbor.setCameFrom(st);
				neighbor.setCost(st.getCost() + sc.getMoveCost(st, neighbor));
				openList.add(neighbor);
				funcDFS(sc,neighbor);
			}
			
		}
	}
}


