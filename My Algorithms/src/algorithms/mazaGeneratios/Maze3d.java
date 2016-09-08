package algorithms.mazaGeneratios;

import java.util.ArrayList;
import java.util.List;

public class Maze3d {
	private int[][][] maze;
	private int rows;
	private int cols;
	private int floors;
	private Position startPosition;
	private Position goalPosition;
	
	public static final int FREE = 0;
	public static final int WALL = 1;
	
	public Maze3d(int floors, int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.floors = floors;
		maze = new int[floors][rows][cols];
		init();
		//this.startPosition = new Position(1, 1, 1);
		//this.goalPosition = new Position(1, 1, 1);
	}
	
	public Maze3d(byte[] arr) {
		int k = 0;
		this.floors = arr[k++];
		this.rows = arr[k++];
		this.cols = arr[k++];
		maze = new int[floors][rows][cols];		
		
		Position startPos = new Position(arr[k++], arr[k++], arr[k++]);
		this.setStartPosition(startPos);
		Position goalPos = new Position(arr[k++], arr[k++], arr[k++]);
		this.setGoalPosition(goalPos);
		
		for (int x = 0; x < floors; x++) {
			for (int y = 0; y < rows; y++) {
				for (int z = 0; z < cols; z++) {
				maze[x][y][z] = arr[k++];
				}			
			}
		}
	}
	public byte[] toByteArray() {
		ArrayList<Byte> arr = new ArrayList<Byte>();
		arr.add((byte)rows);
		arr.add((byte)cols);
		arr.add((byte)startPosition.x);
		arr.add((byte)startPosition.y);
		arr.add((byte)goalPosition.x);
		arr.add((byte)goalPosition.y);
		
		for (int x = 0; x < floors; x++) {
			for (int y = 0; y < rows; y++) {
				for (int z = 0; z < cols; z++) {
				arr.add((byte)maze[x][y][z]);
				}
			}			
		}
		
		byte[] bytes = new byte[arr.size()];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte)arr.get(i);
		}
		return bytes;
	}

	
	public void init(){
		for (int x = 0; x < floors; x++) {
			for (int y = 0; y < rows; y++) {
				for (int z = 0; z < cols; z++) {
					if (z == 0 || z == cols - 1 )
						maze[x][y][z] = 1;
					else if (y == 0 || y == rows - 1)
						maze[x][y][z] = 1;
					else
						maze[x][y][z] = 0;
				}
			}
		}		
	}

	public int[][][] getMaze() {
		return maze;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getFloors() {
		return floors;
	}

	public void setFloors(int floors) {
		this.floors = floors;
	}

	public Position getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
	}

	public Position getGoalPosition() {
		return goalPosition;
	}

	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
	}
	
	public void setWall(int x, int y, int z) {
		maze[x][y][z] = WALL;
	}
	
	public void setFree(int x, int y, int z) {
		maze[x][y][z] = FREE;
	}
	
	@Override
	public String toString() {		
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < floors; x++) {
			for (int y = 0; y < rows; y++) {
				for (int z = 0; z < cols; z++) {
					if (y == startPosition.y && x == startPosition.x && z == startPosition.z) 
						sb.append("E");
					else if (y == goalPosition.y && x == goalPosition.x && z == goalPosition.z)
						sb.append("X");
					else
						sb.append(maze[x][y][z]);
					}
				sb.append("\n");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public ArrayList<Position> getPossibleMoves(Position pos) {
		ArrayList<Position> neigh = new ArrayList<Position>();
		if(pos.x + 2 <= floors && maze[pos.x+1][pos.y][pos.z] == 0)
			neigh.add(new Position(pos.x+1, pos.y, pos.z));
		
		if (pos.x - 2 >= 0 && maze[pos.x - 1][pos.y][pos.z] == 0) 
			neigh.add(new Position(pos.x - 1, pos.y, pos.z));
			
		if (pos.y - 2 >= 0 && maze[pos.x][pos.y - 1][pos.z] == 0) 
			neigh.add(new Position(pos.x, pos.y - 1, pos.z));	
			
		if (pos.y + 2 <= rows - 1 && maze[pos.x][pos.y + 1][pos.z] == 0) 
			neigh.add(new Position(pos.x, pos.y + 1, pos.z));
			
		if (pos.z - 2 >= 0 && maze[pos.x][pos.y][pos.z - 1] == 0) 
			neigh.add(new Position(pos.x, pos.y, pos.z - 1));

		if (pos.z + 2 <= cols - 1 && maze[pos.x][pos.y][pos.z + 1] == 0) 
			neigh.add(new Position(pos.x, pos.y, pos.z + 1));
		
		return neigh;
	}

	public int[][] getCrossSectionByX(int i) {
		if (i >= this.floors || i < 0)
			throw new IndexOutOfBoundsException();
		int mas[][] = new int[this.rows][this.cols];
		for (int y = 0; y < rows; y++) {
			for (int z = 0; z < cols; z++) {
				
				mas[i][y] = maze[this.floors][y][z];
			}
		}
		return mas;
	}

	public int[][] getCrossSectionByY(int i) {
		if (i >= this.rows || i < 0)
			throw new IndexOutOfBoundsException();
		int mas[][] = new int[this.cols][this.floors];
		for (int z = 0; z < cols; z++) {
			for (int x = 0; x < floors; x++) {
				mas[i][z] = maze[x][this.rows][z];
			}
		}
		return mas;
	}

	public int[][] getCrossSectionByZ(int i) {
		if (i >= this.cols || i < 0)
			throw new IndexOutOfBoundsException();
		int mas[][] = new int[this.floors][this.rows];
		for (int x = 0; x < floors; x++) {
			for (int y = 0; y < rows; y++) {
				mas[i][x] = maze[x][y][this.cols];
			}
		}
		return mas;
	}

}
