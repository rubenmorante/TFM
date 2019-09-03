package constructive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import structures.Instance;
import structures.Node;
import structures.NodeSpecial;
import structures.RandomManager;
import structures.Solution;

public class GRASP implements Constructive {

	public static final float BETA = 0.5f; // 0..1
	
	public GRASP() {
		super();
	}

	@Override
	public Solution construct(Instance instance) {

		ArrayList<Node> CL = instance.getCloneListNodes();
		ArrayList<Node> S = new ArrayList<Node>();
		int P = instance.getP();
		
		int v = RandomManager.nextInt(CL.size());
		Node nodeV = CL.remove(v);
		S.add(nodeV);
		
		while(S.size() < P) {
			
			ArrayList<NodeSpecial> auxiliarList = sacarLista(CL, S, instance);
			
			double gMin = auxiliarList.get(0).getDistance();
			double gMax = auxiliarList.get(auxiliarList.size()-1).getDistance();
			double mu = gMax - GRASP.BETA * (gMax - gMin);
			
			ArrayList<Node> RCL = RCL(mu, auxiliarList);	
			
			int u = RandomManager.nextInt(RCL.size());
			Node nodeU = RCL.get(u);

			S.add(nodeU);
			CL.remove(nodeU);			
		}		
		
		return new Solution(S, CL, instance);
	}
	
	//Return a list with the nodes between mu and gMax
	private ArrayList<Node> RCL(double mu, ArrayList<NodeSpecial> auxiliarList) {
		ArrayList<Node> RCL = new ArrayList<Node>(); 
		
		int numNode = auxiliarList.size() - 1;
		while(auxiliarList.get(numNode).getDistance() > mu && numNode >= 0) {
			RCL.add(auxiliarList.get(numNode).getNode());
			--numNode;
		}
		
		return RCL;
	}
	
	
	//Return a list with nodes in CL and the min distance in a Special Node. The list is sorted
	private ArrayList<NodeSpecial> sacarLista(ArrayList<Node> CL, ArrayList<Node> S, Instance instance) {
		
		ArrayList<NodeSpecial> solutionCL = new ArrayList<NodeSpecial>(); 
		
		for(int numNodeCL = 0; numNodeCL < CL.size(); ++numNodeCL) {
			Node node1 = CL.get(numNodeCL);
			NodeSpecial nodeSpecial = null;
			
			for(int numNodeS = 0; numNodeS < S.size(); ++numNodeS) {
				Node node2 = S.get(numNodeS);
				double distance = instance.getEuclideanDistance(node1, node2);
				
				if(nodeSpecial != null) {
						if(distance < nodeSpecial.getDistance()) {
							nodeSpecial.setDistance(distance);
						}					
				} else {
					nodeSpecial = new NodeSpecial(node1, distance); 
				}
			}	
			solutionCL.add(nodeSpecial);
		}
		
		Collections.sort(solutionCL, new Comparator<NodeSpecial>() {
			@Override
			public int compare(NodeSpecial p1, NodeSpecial p2) {
				return new Double(p1.getDistance()).compareTo(new Double(p2.getDistance()));
			}
		});
		
		return solutionCL;
	}

}
