package main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import algorithm.AlgorithmConstructive;
import constructive.GRASP;
import constructive.Random;
import improvement.Improvement1x1;
import improvement.StrategicOscillation;
import structures.Instance;
import structures.Result;
import utils.IO;

@SpringBootApplication
public class Main {
	
	//Folder and output
	public static final String FOLDER = "converted";
	public static final String OUTPUT = "Results.csv";
	
	//Configuration of the algorithm
	public static final Random CONSTRUCTIVE_RANDOM = new Random();
	public static final GRASP CONSTRUCTIVE_GRASP = new GRASP();
	public static final Improvement1x1 IMPROVEMENT_LS1x1 = new Improvement1x1();
	public static final StrategicOscillation IMPROVEMENT_SO = new StrategicOscillation(IMPROVEMENT_LS1x1);
	public static final int N_TIMES = 100;	
	public static final Choise CHOISE = Choise.GRASP_PAR_LS_SO;
	
	
	public static Result menu(Instance instance) {
		switch(Main.CHOISE) {
		case RANDOM_SEQ: 		return new AlgorithmConstructive(Main.CONSTRUCTIVE_RANDOM, Main.N_TIMES).executeSequential(instance);
		case GRASP_SEQ: 		return new AlgorithmConstructive(Main.CONSTRUCTIVE_GRASP,  Main.N_TIMES).executeSequential(instance);
		case RANDOM_SEQ_LS:		return new AlgorithmConstructive(Main.CONSTRUCTIVE_RANDOM, Main.IMPROVEMENT_LS1x1, Main.N_TIMES).executeSequential(instance);
		case GRASP_SEQ_LS: 		return new AlgorithmConstructive(Main.CONSTRUCTIVE_GRASP,  Main.IMPROVEMENT_LS1x1, Main.N_TIMES).executeSequential(instance);
		case RANDOM_PAR_LS:		return new AlgorithmConstructive(Main.CONSTRUCTIVE_RANDOM, Main.IMPROVEMENT_LS1x1, Main.N_TIMES).executeParallel(instance);
		case GRASP_PAR_LS: 		return new AlgorithmConstructive(Main.CONSTRUCTIVE_GRASP,  Main.IMPROVEMENT_LS1x1, Main.N_TIMES).executeParallel(instance);
		case GRASP_PAR_LS_SO: 	return new AlgorithmConstructive(Main.CONSTRUCTIVE_GRASP,  Main.IMPROVEMENT_LS1x1, Main.IMPROVEMENT_SO, Main.N_TIMES).executeParallel(instance);
		default: break;
		}
		return null;
	}
	
	public static void main(String[] args) {
//		String result = executeFolder();
		String result = executeFile("eil101_101_30.txt");

		System.out.println(result);
	}
	
	private static String executeFolder() {
		String[] files = IO.readFolder(Main.FOLDER);
		StringBuilder stringBuilder = new StringBuilder();
		
		if(files != null) {			
			List<String[]> results = new ArrayList<String[]>();
			String[] titles = {"Instance", "Time", "Seed", "Quality", "Result"};
			results.add(titles);	

			for (String file : files) {
				Instance instance = IO.readFile(Main.FOLDER + "/" + file);
				
				if(instance != null){				
					Result result = Main.menu(instance);
					stringBuilder.append(file + "\n" + result + "\n");
					System.out.println(file);
					results.add(result.toStringArray());
				}			
			}			
			IO.writeCSV(Main.OUTPUT, results);			
		}
		return stringBuilder.toString();
	}
	
	private static String executeFile(String file) {
		StringBuilder stringBuilder = new StringBuilder();
		List<String[]> results = new ArrayList<String[]>();
		String[] titles = {"Instance", "Time", "Seed", "Quality", "Result"};
		results.add(titles);			
		Instance instance = IO.readFile(Main.FOLDER + "/" + file);		
		if(instance != null){				
			Result result = Main.menu(instance);
			stringBuilder.append(file + "\n" + result + "\n");
			System.out.println(file);
			results.add(result.toStringArray());
		}				
		IO.writeCSV(Main.OUTPUT, results);
		return stringBuilder.toString();
	}
}