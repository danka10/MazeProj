package book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.Model;
import model.MyModel;
import presenter.Presenter;
import view.MazeWindow;
import view.MyView;
import view.View;

public class RunNew {

	public static void main(String[] args) {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
				
		//MyView view = new MyView(in, out);
		MazeWindow view = new MazeWindow();
		MyModel model = new MyModel();
		
		Presenter presenter = new Presenter(model, view);
		model.addObserver(presenter);
		view.addObserver(presenter);
				
		view.start();
	}

}
