package improvement;

import structures.Node;
import structures.Solution;

public class Improvement1x1 implements Improvement {

	public Improvement1x1() {
		super();
	}
	
	@Override
	public void improve(Solution solution) {
		boolean improve;
		do {
			improve = false;

			int posFirstSolution = 0;
			int posLastSolution = solution.size();
			double oldQuality = solution.getQuality();
			while (!improve && (posFirstSolution < posLastSolution)) {
				Node nodeInSolution = this.removeNodeFromSolution(solution, posFirstSolution);				
				int posFirstNotInSolution = 0;
				int posLastNotInSolution = solution.sizeNotInSolution();
				while (!improve && (posFirstNotInSolution < posLastNotInSolution)) {
					Node candidateNode = this.addCandidateNodeToSolution(solution, posFirstNotInSolution, nodeInSolution);					
					improve = this.isBetterSolution(solution, oldQuality);
					if(!improve) {
						--posLastNotInSolution;
						this.removeCandidateNodeFromSolution(solution, candidateNode, oldQuality, nodeInSolution);						
					}
				}	
				
				if(!improve) {
					--posLastSolution;
					solution.addNodeSolution(nodeInSolution);
				}
			}//while
		} while(improve);
	}
	
	private Node removeNodeFromSolution(Solution solution, int posFirstSolution) {
		Node nodeInSolution = solution.getNodeSolution(posFirstSolution);		
		solution.removeNodeSolution(nodeInSolution);
		return nodeInSolution;
	}
	
	private Node addCandidateNodeToSolution(Solution solution, int posFirstNotInSolution, Node nodeInSolution) {
		Node nodeNotInSolution = solution.getNodeNotInSolution(posFirstNotInSolution);
		solution.addNodeNotInSolution(nodeInSolution);
		solution.moveToSolution(nodeNotInSolution);
		solution.updateQuality();
		return nodeNotInSolution;
	}
	
	private void removeCandidateNodeFromSolution(Solution solution, Node node, double oldQuality, Node nodeInSolution) {
		solution.moveToNotSolution(node);
		solution.removeNodeNotInSolution(nodeInSolution);
		solution.setQuality(oldQuality);
	}
	
	private boolean isBetterSolution(Solution solution, double oldQuality) {
		return solution.isWorseSolution(oldQuality);
	}	
}
