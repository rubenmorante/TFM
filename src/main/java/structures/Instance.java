package structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Instance {

	private final String name;
	private final int numNodes;
	private final int p;
	private final ArrayList<Node> listNodes;
	private final HashMap<Node, Integer> hashmapNodes;
	private final double[][] euclideanDistancesMatrix;

	public Instance(String name, int numNodes, int p, ArrayList<Node> listNodes, HashMap<Node, Integer> hashMapNodes) {
		super();
		this.name = name;
		this.numNodes = numNodes;
		this.p = p;
		this.listNodes = listNodes;
		this.hashmapNodes = hashMapNodes;
		this.euclideanDistancesMatrix = initializeEuclideanDistancesMatrix(this.listNodes, this.hashmapNodes);
	}

	private double[][] initializeEuclideanDistancesMatrix(List<Node> listNodes, HashMap<Node, Integer> hashmapNodes) {

		double[][] euclideanDistancesMatrix = new double[listNodes.size()][listNodes.size()];

		for (int x = 0; x < listNodes.size(); ++x) {
			for (int y = x; y < listNodes.size(); ++y) {
				Node node1 = listNodes.get(x);
				Node node2 = listNodes.get(y);
				double euclideanDistance = Node.euclideanDistance(node1, node2);

				int node1Num = hashmapNodes.get(node1);
				int node2Num = hashmapNodes.get(node2);
								
				euclideanDistancesMatrix[node1Num][node2Num] = euclideanDistance;
				euclideanDistancesMatrix[node2Num][node1Num] = euclideanDistance;				
			}
		}
		return euclideanDistancesMatrix;
	}

	
	//Return the value of the Euclidean Distance between two nodes 
	public double getEuclideanDistance(Node node1, Node node2) {
		return this.euclideanDistancesMatrix[this.hashmapNodes.get(node1)][this.hashmapNodes.get(node2)];		
	}
	
	//Return the min value of the Euclidean Distance with one node 
	public double getMinEuclideanDistance(Node node1) {
		double[] euclideanDistanceList = this.euclideanDistancesMatrix[this.hashmapNodes.get(node1)].clone();
		Arrays.sort(euclideanDistanceList);
		return euclideanDistanceList[0];		
	}
	
	//Return the max value of the Euclidean Distance with one node
	public double getMaxEuclideanDistance(Node node1) {
		double[] euclideanDistanceList = this.euclideanDistancesMatrix[this.hashmapNodes.get(node1)].clone();
		Arrays.sort(euclideanDistanceList);
		return euclideanDistanceList[euclideanDistanceList.length-1];	
	}

	public int getNumNodes() {
		return numNodes;
	}

	public int getP() {
		return p;
	}

	public ArrayList<Node> getListNodes() {
		return listNodes;
	}

	public String getName() {
		return name;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Node> getCloneListNodes() {
		return (ArrayList<Node>) listNodes.clone();
	}
}
