package structures;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Rubén Morante González
 *
 */
public class Solution {

	public static final int ALPHA = 2; // 1..2
	private List<Node> listNode;
	private List<Node> notInSolution;
	private double quality;
	private Instance instance;
	
	
	public Solution(List<Node> listNode, List<Node> notInSolution, Instance instance) {
		super();
		this.listNode = listNode;
		this.notInSolution = notInSolution;
		this.instance = instance;
		this.updateQuality();
	}
	
	public Solution(List<Node> listNode, List<Node> notInSolution, Instance instance, double quality) {
		super();
		this.listNode = listNode;
		this.notInSolution = notInSolution;
		this.instance = instance;
		this.quality = quality;
	}
	
	/**
	 * Update Solution
	 * @return
	 */
	public double updateQuality() {
		double[] closeDistance = {0, 0};		
		for(Node nodeSolution : this.listNode){
			for(Node node : this.notInSolution){
				double distance = this.instance.getEuclideanDistance(nodeSolution, node);
				if(closeDistance[0] < distance){
					closeDistance[1] = closeDistance[0];
					closeDistance[0] = distance;
				} else if(closeDistance[1] < distance){
					closeDistance[1] = distance;
				}
			}
		}
		this.setQuality(closeDistance[Solution.ALPHA - 1]);
		return this.getQuality();
	}
	
	public Solution clone() {
		List<Node> listNode = cloneListNode();
		List<Node> notInSolution = cloneNotInSolution();
		return new Solution(listNode, notInSolution, this.getInstance(), this.getQuality());
	}

	@SuppressWarnings("unchecked")
	private List<Node> cloneListNode(){		
		return (List<Node>) ((ArrayList<Node>) this.getListNode()).clone();
	}
	
	@SuppressWarnings("unchecked")
	private List<Node> cloneNotInSolution(){		
		return (List<Node>) ((ArrayList<Node>) this.getNotInSolution()).clone();
	}
	
	
	/** Better False = this, True = parameter */
	public boolean isBetterSolution(double quality) {
		return quality < this.getQuality();
	}	
	
	public boolean isWorseSolution(double quality) {
		return this.quality < quality;
	}
	
	public int size(){
		return this.listNode.size();
	}
	
	public int sizeNotInSolution(){
		return this.notInSolution.size();
	}

	@Override
	public String toString() {
		return this.listNode.toString();
	}
	
	/**
	 * Add node in solution
	 * @param node
	 * @return
	 */
	public boolean addNodeSolution(Node node) {
		return this.listNode.add(node);
	}
	
	public boolean removeNodeSolution(Node node) {
		return this.listNode.remove(node);
	}
	
	/**
	 * Add node in notInSolution
	 * @param node
	 * @return
	 */
	public boolean addNodeNotInSolution(Node node) {
		return this.notInSolution.add(node);
	}
	
	/**
	 * Remove node in notInSolution
	 * @param node
	 * @return
	 */
	public boolean removeNodeNotInSolution(Node node) {
		return this.notInSolution.remove(node);
	}
	
	/**
	 * Remove node in solution and add node in notInSolution
	 * @param node
	 */
	public void moveToNotSolution(Node node) {
		this.removeNodeSolution(node);
		this.addNodeNotInSolution(node);
	}
	
	/**
	 * Remove node in notInSolution and add node in solution
	 * @param node
	 */
	public void moveToSolution(Node node) {
		this.removeNodeNotInSolution(node);
		this.addNodeSolution(node);
	}
	
	
	/** El primer nodo lo manda a notInSolution y el segundo a solution */
	public void exchangeNode(Node node1, Node node2) {
		this.moveToNotSolution(node1);
		this.moveToSolution(node2);
		this.updateQuality();
	}
	
	public Node getNodeNotInSolution(int pos) {		
		return this.notInSolution.get(pos);
	}
	
	public Node getNodeSolution(int pos) {
		return this.listNode.get(pos);
	}
	

	public List<Node> getListNode() {
		return listNode;
	}
	
	public double getQuality() {
		return quality;
	}

	public void setQuality(double quality) {
		this.quality = quality;
	}

	public List<Node> getNotInSolution() {
		return notInSolution;
	}

	public Instance getInstance() {
		return instance;
	}
	
	public void setSolution(Solution solution) {
		this.listNode = solution.getListNode();
		this.notInSolution = solution.getNotInSolution();
		this.instance = solution.getInstance();
		this.quality = solution.getQuality();
	}
}
