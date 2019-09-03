package structures;

public class NodeSpecial {
	
	private Node node;
	private double distance;
	
	public NodeSpecial(Node node, double distance) {
		super();
		this.node = node;
		this.distance = distance;
	}
	
	public Node getNode() {
		return node;
	}
	
	public void setNode(Node node) {
		this.node = node;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	@Override
	public String toString() {
		return "NodeSpecial [node=" + node + ", distance=" + distance + "]";
	}	
	
	public int compareTo(NodeSpecial nodeSpecial) {
		if(this.getDistance() < nodeSpecial.getDistance()) {
			return -1;
		} 
		if(this.getDistance() > nodeSpecial.getDistance()) {
			return 1;
		} 
		return 0;	
	}
		
}
