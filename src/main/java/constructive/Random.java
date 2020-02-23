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
}
