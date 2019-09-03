package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import structures.Instance;
import structures.Node;
import utils.IO;

public class IOTest {

	private final String FOLDER = "converted";
	private final String FILE = "tests/att48_48_10.txt";
	
	@Test
	public void testReadFolder() {
		String[] files0 = IO.readFolder("");
		assertNull(files0);

		String[] files1 = IO.readFolder("tests");
		assertEquals(files1.length, 2);
	}

	
	@Test
	public void testRreadFile() {
		Instance instance1 = IO.readFile("");
		assertNull(instance1);

		Instance instance2 = IO.readFile(FILE);
		assertEquals(instance2.getNumNodes(), 48);
		assertEquals(instance2.getP(), 10);
		assertEquals(instance2.getListNodes().size(), 48);
		assertEquals(instance2.getNumNodes(), instance2.getListNodes().size());

		Node node1 = instance2.getListNodes().get(0);
		assertTrue(node1.getX() == 6734f);
		assertTrue(node1.getY() == 1453f);

		Node node2 = instance2.getListNodes().get(24);
		assertTrue(node2.getX() == 4307f);
		assertTrue(node2.getY() == 2322f);
		
		// Doesn't exist
		Instance instance3 = IO.readFile("tests/bla.txt");
		assertNull(instance3);

		// It doesn't have the same value of size
		Instance instance4 = IO.readFile("tests/pirata.txt");
		assertNull(instance4);
	}

	
	// It reads all files and checks if the number of the nodes is correct
	@Test
	public void testGlobalReadFolder() {
		String folder = "tests";
		String[] files = IO.readFolder(folder);
		for (String file : files) {
			Instance instance = IO.readFile(folder + "/" + file);
			if (instance != null) {
				if (instance.getNumNodes() != instance.getListNodes().size()) {
					System.err.println(folder + "/" + file + " doesn't have the same value of size");
					fail();
				}
			} else {
				assertEquals(folder + "/" + file, "tests/pirata.txt");
			}
		}
	}
	
	
	// It reads all files and checks if the number of the nodes is correct
	@Test
	public void testGlobalRead() {	
		String[] files = IO.readFolder(FOLDER);
		for (String file : files) {
			Instance instance = IO.readFile(FOLDER + "/" + file);
			assertNotNull(instance);
			if (instance != null) {
				if (instance.getNumNodes() != instance.getListNodes().size()) {
					System.err.println(FOLDER + "/" + file + " doesn't have the same value of size");
					fail();
				}
			}				
		}
	}
	
	@Test
	public void testWriteCSV() {
		List<String[]> list = new ArrayList<String[]>();
		String route = "proof.csv";
		File file = new File(route);
		assertFalse(file.exists());
		IO.writeCSV(route,list);
		assertTrue(file.exists());
		
		//clean the proof
		assertTrue(file.delete());
		assertFalse(file.exists());
	}
}
