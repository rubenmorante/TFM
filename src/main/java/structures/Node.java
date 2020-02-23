package structures;

public class Node {

	private final float x;
	private final float y;
	
	public Node(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	@Override
	public String toString() {
		return "(x=" + x + ", y=" + y + ")";
	}
	
	public boolean equals(Node node) {
		return ((this.getX() == node.getX()) && (this.getY() == node.getY()));
	}
	
	public static double euclideanDistance(Node node1, Node node2){
		return Math.sqrt((Math.pow((node2.getX() - node1.getX()), 2) + Math.pow((node2.getY() - node1.getY()), 2)));
	}
}