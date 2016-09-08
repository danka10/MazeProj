package algorithms.search;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a Solution and creates list of State type
 * @author DKhrakovsky
 * @version 1.0
 * @since 31-08-16
 * 
 */

public class Solution<T> {
	private List<State<T>> states = new ArrayList<State<T>>();

	public List<State<T>> getStates() {
		return states;
	}

	public void setStates(List<State<T>> states) {
		this.states = states;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (State<T> s : states) {
			sb.append(s.toString()).append(" ");
		}
		return sb.toString();
	}
}
