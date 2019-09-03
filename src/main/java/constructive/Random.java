package constructive;

import java.util.ArrayList;
import java.util.Collections;

import structures.Instance;
import structures.Node;
import structures.RandomManager;
import structures.Solution;

public class Random implements Constructive {
	
	public Random() {
		super();
	}

	@Override
	public Solution construct(Instance instance) {
		
		ArrayList<Node> nodeList = instance.getCloneListNodes();
		Collections.shuffle(nodeList, RandomManager.getRandom());

		int p = instance.getP();
		ArrayList<Node> solutionList = new ArrayList<Node>();
		ArrayList<Node> notInSolutionList = new ArrayList<Node>();
		
		for (int i = 0; i < p; ++i) {
			solutionList.add(nodeList.get(i));
		}
		
		for (int i = p; i < instance.getNumNodes(); ++i) {
			notInSolutionList.add(nodeList.get(i));
		}
		
		return new Solution(solutionList, notInSolutionList, instance);
	}
	
	/*@Override
	public Solution construct(Instance instance) {
		
		ArrayList<Node> nodeList = instance.getListNodes();

		int p = instance.getP();
		ArrayList<Node> solutionList = new ArrayList<Node>();
		
		for (int i = 0; i < p; ++i) {
			boolean repeat = true;
			while(repeat){
				
				int intRandom = RandomManager.nextInt(nodeList.size());				
				Node node = nodeList.get(intRandom);
				
				if(!solutionList.contains(node)){
					solutionList.add(node);
					repeat = false;
				}
			}
		}
		
		return new Solution(solutionList, instance);
	}*/
}
