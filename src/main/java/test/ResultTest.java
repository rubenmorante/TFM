package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import structures.Instance;
import structures.Node;
import structures.Result;
import structures.Solution;

public class ResultTest {

	final float X1 = 5f;
	final float Y1 = 4f;
	final float X2 = 9f;
	final float Y2 = 7f;
	final long TIME = 1000;  
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
	
	private Result createProofResult() {		
		ArrayList<Node> list = new ArrayList<Node>();
		Node node1 = new Node(X1, Y1);
		Node node2 = new Node(X2, Y2);
		list.add(node1);
		list.add(node2);
		ArrayList<Node> notInList = new ArrayList<Node>();
		

		Instance instance = createProofInstance();
		
		Solution solution = new Solution(list, notInList, instance);
		return new Result(solution, TIME);
	}
	
	@Test
	public void testResult() {
		Result result1 = null;
		assertNull(result1);
		result1 = createProofResult();
		assertNotNull(result1);
	}

}
