package improvement;

import structures.Node;
import structures.Solution;

public class Improvement1x1 implements Improvement {

	public Improvement1x1() {
		super();
	}
	
	@Override
	public void improve(Solution solution) {
		boolean improve = true;
		while(improve) {
			improve = false;

			int posFirstSolution = 0;
			int posLastSolution = solution.size();
			while (!improve && (posFirstSolution < posLastSolution)) {
				Node nodeInSolution = solution.getNodeSolution(posFirstSolution);
				
				solution.removeNodeSolution(nodeInSolution);//remove node in solution
				double oldQuality = solution.getQuality();
				
				
				int posFirstNotInSolution = 0;
				int posLastNotInSolution = solution.sizeNotInSolution();
				while (!improve && (posFirstNotInSolution < posLastNotInSolution)) {
					Node nodeNotInSolution = solution.getNodeNotInSolution(posFirstNotInSolution);
					
					solution.addNodeNotInSolution(nodeInSolution);//add node in notInSolution
					solution.moveToSolution(nodeNotInSolution);//remove node in notInSolution and add node in solution
										
					improve = solution.updateQuality();
					if(!improve) {
						--posLastNotInSolution;
						solution.moveToNotSolution(nodeNotInSolution);//remove node in solution and add node in notInSolution												
						solution.removeNodeNotInSolution(nodeInSolution);//remove node in notInSolution
						solution.setQuality(oldQuality);
					}
				}	
				
				if(!improve) {
					--posLastSolution;
					solution.addNodeSolution(nodeInSolution);//add node in solution
				}
			}//while
		}//while
	}//improve
}//class
