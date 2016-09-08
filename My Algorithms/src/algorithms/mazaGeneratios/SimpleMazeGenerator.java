package algorithms.mazaGeneratios;

import java.util.Random;

public class SimpleMazeGenerator extends Maze3dGeneratorBase {

	private Random rand = new Random();
	private static final float WALLS_RATIO = 0.5F;

	private Position chooseRandomPosition(Maze3d maze3d) {
		int[][][] mat = maze3d.getMaze();
		
		int x = rand.nextInt(maze3d.getFloors());
		int y = rand.nextInt(maze3d.getRows());
		int z = rand.nextInt(maze3d.getCols());
		
		while (mat[x][y][z] == Maze3d.WALL) {
			x = rand.nextInt(maze3d.getFloors());
			y = rand.nextInt(maze3d.getRows());
			z = rand.nextInt(maze3d.getCols());
		}
		
		return new Position(x, y, z);
	}
	
	@Override
	public Maze3d generate(int floors, int rows, int cols) {
		Maze3d maze3d = new Maze3d(floors,rows,cols);
		
		int wallsNum = (int)(WALLS_RATIO * rows * cols * floors);
		for (int i = 0; i < wallsNum; i++) {			
			int x = rand.nextInt(floors);
			int y = rand.nextInt(rows);	
			int z = rand.nextInt(cols);
			
			maze3d.setWall(x, y, z);
		}
				
		// Choose a random entrance on the bottom level, first row
		Position startPos = chooseRandomPosition(maze3d);
		maze3d.setStartPosition(startPos);	
				
		// Choose a random exit on the top level, last row
		Position goalPos = chooseRandomPosition(maze3d);
		maze3d.setGoalPosition(goalPos);	
		
		return maze3d;		
	}

}
