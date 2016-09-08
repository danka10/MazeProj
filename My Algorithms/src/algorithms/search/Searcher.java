package algorithms.search;

/**
 * This interface implements a generic Searcher object with search method
 * @author DKhrakovsky
 * @version 1.0
 * @since 31-08-16
 * 
 */

public interface Searcher<T> {
    // the search method
    public Solution<T> search(Searchable<T> s);
    
    // get how many nodes were evaluated by the algorithm
    public int getNumberOfNodesEvaluated();
}
