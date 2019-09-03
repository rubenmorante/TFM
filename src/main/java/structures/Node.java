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
	
	public static double euclideanDistance(float x1, float y1, float x2, float y2){
		double result = Math.sqrt((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));
		return result;
	}
	
	public static double euclideanDistance(Node node1, Node node2){
		return euclideanDistance(node1.getX(), node1.getY(), node2.getX(), node2.getY());
	}
	
	public double euclideanDistance(float x2, float y2){
		double result = Math.sqrt((Math.pow((x2 - this.x), 2) + Math.pow((y2 - this.y), 2)));
		return result;
	}
	
	public double euclideanDistance(Node node){
		return this.euclideanDistance(node.getX(), node.getY());
	}	
}
