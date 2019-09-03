package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVWriter;

import structures.Instance;
import structures.Node;

public class IO {

	public static String[] readFolder(String route) {
		File dir = new File(route);
		String[] files = dir.list();
		
		if (files == null) {
			System.err.println("There are no files in the directory: " + route);
		}
		return files;
	}
	
	
	public static Instance readFile(String route) {		
		int numNodes = -1;
		int numP = -1;
		ArrayList<Node> listNodes = new ArrayList<Node>();
		HashMap<Node, Integer> hashMapNodes = new HashMap<Node, Integer>();
		String nameFile = "";
		
		try {
			String line;
			nameFile = new File(route).getName();
			FileReader fileReader = new FileReader(route);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			// First line, num nodes and num points
			if((line = bufferedReader.readLine()) != null){
				String[] coordinates = line.split(" ");
				numNodes = Integer.parseInt(coordinates[0]);
				numP = Integer.parseInt(coordinates[1]);
			}
			
			int hashMapCount = 0;
			//Rest of the lines
			while ((line = bufferedReader.readLine()) != null) {
				String[] coordinates = line.split(" ");
				Node node = new Node(Float.parseFloat(coordinates[1]), Float.parseFloat(coordinates[2]));
				listNodes.add(node);
				hashMapNodes.put(node, hashMapCount++);
			}
			bufferedReader.close();
			
		} catch (FileNotFoundException e) {
			System.err.println("File not found, File name: " + route);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(numNodes != listNodes.size()){
			System.err.println("Corrupted Instance, File name: " + route);
			return null;
		}
		return new Instance(nameFile ,numNodes, numP, listNodes, hashMapNodes);
	}
	
	
	public static void writeCSV(String route, List<String[]> results) {	
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(route));
			writer.writeAll(results);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
