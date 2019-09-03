package test;

import static org.junit.Assert.*;

import org.junit.Test;

import structures.Node;

public class NodeTest {
	
	@Test
	public void testNode() {
		Node node1 = null;
		assertNull(node1);
		node1 = new Node(5f, 4f);
		assertNotNull(node1);
	}

	@Test
	public void testGetX() {
		Node node1 = new Node(5f, 4f);
		Node node2 = new Node(9f, 7f);
		
		assertTrue(node1.getX() == 5.0f);
		assertTrue(node2.getX() == 9.0f);
	}
	
	@Test
	public void testGetY() {
		Node node1 = new Node(5f, 4f);
		Node node2 = new Node(9f, 7f);
		
		assertTrue(node1.getY() == 4.0f);
		assertTrue(node2.getY() == 7.0f);
	}
	
	@Test
	public void testToString() {
		Node node1 = new Node(5f, 4f);
		Node node2 = new Node(9f, 7f);
		
		assertEquals(node1.toString(), "(x=5.0, y=4.0)");
		assertEquals(node2.toString(), "(x=9.0, y=7.0)");
	}
	
	@Test
	public void testEquals() {
		Node node1 = new Node(5f, 4f);
		Node node2 = new Node(9f, 7f);
		Node node3 = node1;
		
		assertTrue(node1.equals(node1));
		assertFalse(node1.equals(node2));
		assertTrue(node2.equals(node2));
		assertTrue(node1.equals(node3));
		assertFalse(node2.equals(node3));
		assertTrue(node3.equals(node3));
	}
	
	@Test
	public void test1EuclideanDistance() {		
		
		double euclideanDistance1 =  Node.euclideanDistance(5f, 4f, 9f, 7f);
		assertTrue(euclideanDistance1 == 5);
	}
	
	@Test
	public void test2EuclideanDistance() {
		Node node1 = new Node(5f, 4f);
		Node node2 = new Node(9f, 7f);
		
		double euclideanDistance2 =  Node.euclideanDistance(node1, node2);
		assertTrue(euclideanDistance2 == 5);
	}
	
	@Test
	public void test3EuclideanDistance() {
		Node node1 = new Node(5f, 4f);
		
		double euclideanDistance3 =  node1.euclideanDistance(9f, 7f);
		assertTrue(euclideanDistance3 == 5);
	}
	
	@Test
	public void test4EuclideanDistance() {
		Node node1 = new Node(5f, 4f);
		Node node2 = new Node(9f, 7f);
		
		double euclideanDistance4 =  node1.euclideanDistance(node2);
		assertTrue(euclideanDistance4 == 5);
	}

}
