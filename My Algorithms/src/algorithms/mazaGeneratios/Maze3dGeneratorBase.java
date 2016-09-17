package algorithms.mazaGeneratios;

public abstract class Maze3dGeneratorBase implements Maze3dGenerator {
	
	@Override
	public String measureAlgorithmTime(int metho, int floors, int rows, int cols) {
		long startTime = System.currentTimeMillis();
		this.generate(metho,floors,rows,cols);
		long endTime = System.currentTimeMillis();
		return String.valueOf(endTime - startTime);		
	}
	
	protected boolean isDone = false;
	
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
}
