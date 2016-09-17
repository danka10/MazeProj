package algorithms.mazaGeneratios;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GrowingTreeGenerator extends Maze3dGeneratorBase {

	private Random rand = new Random();
	private Scanner in;	
	
	@Override
	public Maze3d generate(int metho, int floors, int rows, int cols) {		
		Maze3d maze = new Maze3d(floors, rows, cols);
		List<Position> cells = new ArrayList<Position>();
		ChooseMethod userSelection = null;
		// Initialize all the maze with walls
		initialize(maze);
		
		// Choose a random starting cell (must be in an even row and column)		
		Position startPos = chooseRandomPosition(maze);
		maze.setStartPosition(startPos);
		maze.setFree(startPos.x, startPos.y, startPos.z);

		cells.add(startPos);
		
		if(metho == 1)
			userSelection = new ChooseRandom();
		else if(metho == 2)
			userSelection = new ChooseLastFromString();
		
		while (!cells.isEmpty() && !isDone) {
			// Choose the last cell from the list
			Position pos = cells.get(cells.size() - 1);	
			//System.out.println("cells.isEmpty"); 
			
			// Find the unvisited neighbors of this cell
			List<Position> neighbors = findUnvisitedNeighbors(maze, pos);
			
			if (!neighbors.isEmpty()) {
				int idx = userSelection.chooseMethod(neighbors);
				Position neighbor = neighbors.get(idx);
				
				// Carve a passage between current cell and the neighbor
				carvePassageBetweenCells(maze, pos, neighbor);				
				cells.add(neighbor);
			} 
			else {
				cells.remove(pos);
			}	
		}		
		
		// Choose a random exit 
		Position goalPos = chooseRandomGoalPosition(maze);
		maze.setGoalPosition(goalPos);
		
		return maze;
	}
	
	private void initialize(Maze3d maze) {
		for (int x = 0; x < maze.getFloors(); x++) {
			for (int y = 0; y < maze.getRows(); y++) {
				for (int z = 0; z < maze.getCols(); z++) {
					maze.setWall(x, y, z);
				}
			}
		}
	}
	
	private Position chooseRandomPosition(Maze3d maze3d) {	
		int x = rand.nextInt(maze3d.getFloors());
		while (x % 2 != 0) {
			x = rand.nextInt(maze3d.getFloors());
		}

		int y = rand.nextInt(maze3d.getRows());
		while (y % 2 != 0) {
			y = rand.nextInt(maze3d.getRows());
		}
		
		int z = rand.nextInt(maze3d.getCols());
		while (z % 2 != 0) {
			z = rand.nextInt(maze3d.getCols());
		}
		
		return new Position(x, y, z);
	}
	
	private List<Position> findUnvisitedNeighbors(Maze3d maze, Position pos) {
		int[][][] mat = maze.getMaze();
		List<Position> neighbors = new ArrayList<Position>();
		
		if (pos.x - 2 >= 1 && mat[pos.x - 2][pos.y][pos.z] == Maze3d.WALL) {
			neighbors.add(new Position(pos.x - 2, pos.y, pos.z));
		}
		
		if (pos.x + 2 <= maze.getFloors() && mat[pos.x + 2][pos.y][pos.z] == Maze3d.WALL) {
			neighbors.add(new Position(pos.x + 2, pos.y, pos.z));
		}
		
		if (pos.y - 2 >= 1 && mat[pos.x][pos.y - 2][pos.z] == Maze3d.WALL) {
			neighbors.add(new Position(pos.x, pos.y - 2, pos.z));
		}	
		
		if (pos.y + 2 < maze.getRows() - 1 && mat[pos.x][pos.y + 2][pos.z] == Maze3d.WALL) {
			neighbors.add(new Position(pos.x, pos.y + 2, pos.z));
		}
		
		if (pos.z - 2 >= 1 && mat[pos.x][pos.y][pos.z - 2] == Maze3d.WALL) {
			neighbors.add(new Position(pos.x, pos.y, pos.z - 2));
		}
		
		if (pos.z + 2 < maze.getCols() - 1 && mat[pos.x][pos.y][pos.z + 2] == Maze3d.WALL) {
			neighbors.add(new Position(pos.x, pos.y, pos.z + 2));
		}
		
		return neighbors;
	}	
	
	private void carvePassageBetweenCells(Maze3d maze, Position pos, Position neighbor) {
		if (neighbor.x == pos.x + 2) {
			maze.setFree(pos.x + 1, pos.y, pos.z);
			maze.setFree(pos.x + 2, pos.y, pos.z);
		}
		else if (neighbor.x == pos.x - 2) {
			maze.setFree(pos.x - 1, pos.y, pos.z);
			maze.setFree(pos.x - 2, pos.y, pos.z);
		}
		else if (neighbor.y == pos.y + 2) {
			maze.setFree(pos.x, pos.y + 1, pos.z);
			maze.setFree(pos.x, pos.y + 2, pos.z);
		}
		else if (neighbor.y == pos.y - 2) {
			maze.setFree(pos.x, pos.y - 1, pos.z);
			maze.setFree(pos.x, pos.y - 2, pos.z);
		}
		else if (neighbor.z == pos.z + 2) {
			maze.setFree(pos.x, pos.y, pos.z + 1);
			maze.setFree(pos.x, pos.y, pos.z + 2);
		}
		else if (neighbor.z == pos.z - 2) {
			maze.setFree(pos.x, pos.y, pos.z - 1);
			maze.setFree(pos.x, pos.y, pos.z - 2);
		}
	}
	
	
	private Position chooseRandomGoalPosition(Maze3d maze) {	
		int[][][] mat = maze.getMaze();
		
		int x = rand.nextInt(maze.getFloors());
		int y = rand.nextInt(maze.getRows());
		int z = rand.nextInt(maze.getCols());
		while (mat[x][y][z] == Maze3d.WALL) {
			x = rand.nextInt(maze.getFloors());
			y = rand.nextInt(maze.getRows());
			z = rand.nextInt(maze.getCols());
		}		
						
		return new Position(x, y, z);	
	}	
	
}
