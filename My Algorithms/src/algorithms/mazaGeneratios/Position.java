package algorithms.mazaGeneratios;

public class Position {
	public int x;
	public int y;
	public int z;
	
	public Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public String toString() {
		return "(" + (x+1) + "," + (y+1) + "," + (z+1) + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		Position pos = (Position)obj;
		return (this.x == pos.x && this.y == pos.y && this.z==pos.z);
	}
}
