package algorithms.mazaGeneratios;

import java.util.List;
import java.util.Random;

public class ChooseRandom implements ChooseMethod {

	private Random rand = new Random();	
	
	@Override
	public int chooseMethod(List<Position> list) {
		int idx = rand.nextInt(list.size());
		return idx;
	}

}
