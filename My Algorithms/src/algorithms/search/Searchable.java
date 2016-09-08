package algorithms.search;

import java.util.List;
/**
 * This interface implements a generic Searchable object with start and goal states
 * @author DKhrakovsky
 * @version 1.0
 * @since 31-08-16
 * 
 */
public interface Searchable<T> {

	State<T> getStartState();
	void setGoalState(State<T> state);
	State<T> getGoalState();

	List<State<T>> getAllPossibleStates(State<T> s);
	double getMoveCost(State<T> currState, State<T> neighbor);
}
