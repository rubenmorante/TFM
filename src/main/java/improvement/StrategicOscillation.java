package improvement;

import constructive.GRASP;
import structures.Node;
import structures.RandomManager;
import structures.Solution;

public class StrategicOscillation implements Improvement{
	
	public static final float DELTA = 0.10f; // {0'10, 0'20, 0'30}	
	public static final float INCREASE_DELTA = 0.05f;	
	
	private final Improvement improvement;
		
	public StrategicOscillation(Improvement improvement) {
		super();
		this.improvement = improvement;
	}

	@Override
	public void improve(Solution solution) {
		int p = solution.size();
		int countOscilation = 0;
		
		while(countOscilation < 3) {
//			long start = System.currentTimeMillis();
			
			int pImprove = (int) (p * (StrategicOscillation.DELTA + (StrategicOscillation.INCREASE_DELTA * countOscilation)));
			if(!isItPossibleToOscillate(solution, pImprove)) {
				break;
			}
			
			Solution solutionClone = solution.clone();			
						
			this.addNode(pImprove, solutionClone);
			this.improvement.improve(solutionClone);
			solutionClone.updateQuality();
			
			this.removeNode(pImprove, solutionClone);
			this.improvement.improve(solutionClone);
			solutionClone.updateQuality();
			
			boolean improve = solution.isBetterSolution(solutionClone.getQuality());
			if(improve) {
				solution.setSolution(solutionClone);
			}
			
			solutionClone = solution.clone();
			
			this.removeNode(pImprove, solutionClone);
			this.improvement.improve(solutionClone);
			solutionClone.updateQuality();
			
			this.addNode(pImprove, solutionClone);
			this.improvement.improve(solutionClone);
			solutionClone.updateQuality();
			
			improve = solution.isBetterSolution(solutionClone.getQuality());
			if(improve) {
				countOscilation = 0;
				solution.setSolution(solutionClone);
			} else {
				++countOscilation;
			}
//			System.out.println("Construct solution: "  + (System.currentTimeMillis() - start));			
		}		
	}
	
	/**
	 * if pImprove is bigger than any size of the list
	 * @param solution
	 * @param pImprove
	 * @return if pImprove is bigger than any size of the list return false
	 */
	private boolean isItPossibleToOscillate(Solution solution, int pImprove) {
		return ((pImprove < solution.sizeNotInSolution()) && (pImprove < solution.size()));		
	}
	
	
	/**
	 * Add nodes to the solution
	 * @param pImprove
	 * @param solutionClone
	 */
	private void addNode(int pImprove, Solution solutionClone) {		
		for(int i = 0; i < pImprove; ++i) {	
			Node nodeNotInSolution = GRASP.takeFurthestNode(solutionClone.getNotInSolution(), solutionClone.getListNode(), solutionClone.getInstance());
			solutionClone.moveToSolution(nodeNotInSolution);//remove node in notInSolution and add node in solution
		}		
	}

	
	/**
	 * Remove nodes to the solution
	 * @param pImprove
	 * @param solutionClone
	 */
	private void removeNode(int pImprove, Solution solutionClone) {		
		for(int i = 0; i < pImprove; ++i) {
			int posNode = RandomManager.nextInt(solutionClone.getListNode().size());
			Node nodeInSolution = solutionClone.getNodeSolution(posNode);
			solutionClone.moveToNotSolution(nodeInSolution);//remove node in solution and add node in notInSolution							
		}
	}
}
