package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import structures.Instance;
import structures.Node;

public class InstanceTest {
	
	final float X1 = 5f;
	final float Y1 = 4f;
	final float X2 = 9f;
	final float Y2 = 7f;
	final int NUM_NODES = 2;
	final int P = 1;
	final String NAME = "NAME";

	private Instance createProofInstance() {
		ArrayList<Node> list = new ArrayList<Node>();
		Node node1 = new Node(X1, Y1);
		Node node2 = new Node(X2, Y2);
		list.add(node1);
		list.add(node2);
		
		HashMap<Node, Integer> hashMap = new HashMap<Node, Integer>();
		hashMap.put(node1, 0);
		hashMap.put(node2, 1);
		
		return new Instance(NAME, NUM_NODES, P, list, hashMap);
	}
	
	@Test
	public void testInstance() {
		Instance instance1 = null;
		assertNull(instance1);
		instance1 = createProofInstance();
		assertNotNull(instance1);
	}
	
	@Test
	public void testGetListNodes() {
		Instance instance = createProofInstance();
		
		ArrayList<Node> list = instance.getListNodes();
		Node node1 = list.get(0);
		assertTrue(node1.getX() == X1);
		assertTrue(node1.getY() == Y1);
		
		Node node2 = list.get(1);
		assertTrue(node2.getX() == X2);
		assertTrue(node2.getY() == Y2);
	}
	
	@Test
	public void testGetEuclideanDistance() {
		
		Instance instance = createProofInstance();
		ArrayList<Node> list = instance.getListNodes();
		Node node1 = list.get(0);
		Node node2 = list.get(1);
		
		double euclideanDistance = instance.getEuclideanDistance(node1, node2);
		assertTrue(euclideanDistance == 5);
	}
	
	@Test
	public void testGetMinEuclideanDistance() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetMaxEuclideanDistance() {
		fail("Not yet implemented");
	}
	

	@Test
	public void testGetNumNodes() {
		Instance instance = createProofInstance();
		assertTrue(instance.getNumNodes() == NUM_NODES);
	}

	@Test
	public void testGetP() {
		Instance instance = createProofInstance();
		assertTrue(instance.getP() == P);
	}
	
	@Test
	public void testGetName() {
		Instance instance = createProofInstance();
		assertTrue(instance.getName() == NAME);
	}
	
	@Test
	public void testGetCloneListNodes() {
		Instance instance = createProofInstance();
		ArrayList<Node> listNode = instance.getListNodes();
		ArrayList<Node> listNodeClone = instance.getCloneListNodes();
		
		assertFalse(listNode == listNodeClone);
		assertTrue(listNode.size() == listNodeClone.size());
		for(int x = 0; x < listNode.size(); ++x) {
			assertTrue(listNode.get(x) == listNodeClone.get(x));
			assertTrue(listNode.get(x).equals(listNodeClone.get(x)));
		}
	}
}
