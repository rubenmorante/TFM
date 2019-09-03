package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashSet;

import org.junit.Test;

import constructive.Random;
import structures.Instance;
import structures.Node;
import structures.Solution;
import utils.IO;

public class RandomTest {

	private final String FOLDER = "converted";
	private final String FILE = "tests/att48_48_10.txt";
	
	@Test
	public void testRandom() {
		Random random1 = null;
		assertNull(random1);
		random1 = new Random();
		assertNotNull(random1);
	}
	
	@Test
	public void testConstruct() {
		Random random = new Random();
		Instance instance = IO.readFile(FILE);
		assertNotNull(instance);
		
		if(instance != null){
			Solution solution = random.construct(instance);
			// Check that there are the same number of solutions as the p value
			assertEquals(instance.getP(), solution.getListNode().size());
			
			HashSet<Node> recount = new HashSet<Node>();
			for(Node node : solution.getListNode()){
				recount.add(node);
			}
			// Check that the solutions are not repeated
			assertEquals(recount.size(), solution.getListNode().size());
		}
	}
	
	
	@Test
	public void testGlobalConstruct() {
		Random random = new Random();
		
		String[] files = IO.readFolder(FOLDER);
		
		for (String file : files) {
			Instance instance = IO.readFile(FOLDER + "/" + file);
			assertNotNull(instance);
		
			if(instance != null){
				Solution solution = random.construct(instance);
				// Check that there are the same number of solutions as the p value
				assertEquals(instance.getP(), solution.getListNode().size());
				
				HashSet<Node> recount = new HashSet<Node>();
				for(Node node : solution.getListNode()){
					recount.add(node);
				}
				// Check that the solutions are not repeated
				assertEquals(recount.size(), solution.getListNode().size());
			}
		}
		
	}

}