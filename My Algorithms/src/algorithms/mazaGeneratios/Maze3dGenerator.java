package algorithms.mazaGeneratios;

public interface Maze3dGenerator {
	Maze3d generate(int metho, int floors, int rows, int cols);
	String measureAlgorithmTime(int metho, int floors, int rows, int cols);
	void setDone(boolean isDone);
}
