package improvement;

import structures.Solution;

public class StrategicOscillation implements Improvement{

	@Override
	public void improve(Solution solution) {
		int p = solution.size();
		boolean improve = true;
		while(improve) {
			improve = false;
			improve = solution.updateQuality();
		}		
	}

}
