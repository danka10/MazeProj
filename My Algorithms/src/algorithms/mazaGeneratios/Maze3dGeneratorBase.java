package algorithms.mazaGeneratios;

public abstract class Maze3dGeneratorBase implements Maze3dGenerator {
	
	@Override
	public String measureAlgorithmTime(int floors, int rows, int cols) {
		long startTime = System.currentTimeMillis();
		this.generate(floors,rows,cols);
		long endTime = System.currentTimeMillis();
		return String.valueOf(endTime - startTime);		
	}
}
