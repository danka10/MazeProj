package algorithms.search;

import java.util.List;

/**
 * This class implements a generic Searcher object.
 * @author DKhrakovsky
 * @version 1.0
 * @since 31-08-16
 * 
 */

public abstract class CommonSearcher<T> implements Searcher<T> {

	protected int evaluatedNodes;

	@Override
	public int getNumberOfNodesEvaluated() {		
		return evaluatedNodes;
	}
	
	/**
	 * This method back traces all the moves from the goal to start.
	 * @param sol Solution type
	 * @param currState gets goal
	 * @return sol Solution type array list
	 * 
	 */
	
	protected Solution<T> backTrace(State<T> goalState) {
		Solution<T> sol = new Solution<T>();
		
		State<T> currState = goalState;
		List<State<T>> states = sol.getStates();
		while (currState != null) {		
			states.add(0, currState);
			currState = currState.getCameFrom();
		}
		return sol;
	}

}
