package algorithms.mazaGeneratios;

import java.util.List;

public class ChooseLastFromString implements ChooseMethod {

	@Override
	public int chooseMethod(List<Position> list) {
		int idx = (list.size() - 1);
		return idx;
	}

}
