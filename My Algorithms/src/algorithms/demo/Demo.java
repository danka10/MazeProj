package algorithms.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazaGeneratios.GrowingTreeGenerator;
import algorithms.mazaGeneratios.Maze3d;
import algorithms.mazaGeneratios.Maze3dGenerator;
import algorithms.mazaGeneratios.Position;
import algorithms.search.BFS;
import algorithms.search.Solution;
import algorithms.io.MyCompressorOutputStream;
import algorithms.io.MyDecompressorInputStream;
import algorithms.search.Solution;

import java.io.OutputStream;

/**
 * Main Demo
 * Creates maze and runs BFS and DFS solutions 
 * @author DKhrakovsky
 * @version 1.0
 * @since 31-08-16
 * 
 */


public class Demo {

	public void run() {		
		Maze3dGenerator generator = new GrowingTreeGenerator();
		Maze3d maze = generator.generate(3, 10, 10);
		System.out.println(maze);
		
		MazeAdapter adapter = new MazeAdapter(maze);
		BFS<Position> bfs = new BFS<Position>();
		Solution<Position> solution = bfs.search(adapter);
		System.out.println(solution);
		System.out.println(bfs.getNumberOfNodesEvaluated());
		
		// save it to a file
				OutputStream out;
				try {
					 out=new MyCompressorOutputStream(new FileOutputStream("1.maz"));
						out.write(maze.toByteArray());
						out.flush();
						out.close();

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				
				InputStream in;
				try {
					 in=new MyDecompressorInputStream(new FileInputStream("1.maz"));
						byte b[]=new byte[maze.toByteArray().length];
						in.read(b);
						in.close();
						Maze3d loaded=new Maze3d(b);
						System.out.println(loaded.equals(maze));

					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}

}
