package structures;

import java.util.ArrayList;

import main.Main;

public class Solution {

	private final ArrayList<Node> listNode;
	private final ArrayList<Node> notInSolution;
	private double quality;
	private final Instance instance;
	
	
	public Solution(ArrayList<Node> listNode, ArrayList<Node> notInSolution, Instance instance) {
		super();

		this.listNode = listNode;
		this.notInSolution = notInSolution;
		this.instance = instance;
		this.quality = this.calculateQuality();
	}
	

	private double calculateQuality() {
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
		return closeDistance[Main.ALPHA - 1];
	}	
	
	
	public boolean updateQuality() {
		double oldQuality = this.getQuality();
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
		this.quality = closeDistance[Main.ALPHA - 1];
		return this.quality < oldQuality;
	}
	
	
	/** Better False = this, True = parameter */
	public boolean improvesSolution(double quality) {
		return quality < this.quality;
	}	
	
	public int size(){
		return this.listNode.size();
	}
	
	public int sizeNotInSolution(){
		return this.notInSolution.size();
	}

	@Override
	public String toString() {
		return listNode.toString();
	}
	
	public boolean addNodeSolution(Node node) {
		return this.listNode.add(node);
	}
	
	public boolean removeNodeSolution(Node node) {
		return this.listNode.remove(node);
	}
	
	public boolean addNodeNotInSolution(Node node) {
		return this.notInSolution.add(node);
	}
	
	public boolean removeNodeNotInSolution(Node node) {
		return this.notInSolution.remove(node);
	}
	
	public void moveToNotSolution(Node node) {
		this.removeNodeSolution(node);
		this.addNodeNotInSolution(node);
	}
	
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
	

	public ArrayList<Node> getListNode() {
		return listNode;
	}
	
	public double getQuality() {
		return quality;
	}

	public void setQuality(double quality) {
		this.quality = quality;
	}

	public ArrayList<Node> getNotInSolution() {
		return notInSolution;
	}

	public Instance getInstance() {
		return instance;
	}
}
