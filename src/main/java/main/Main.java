package main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import algorithm.AlgorithmConstructive;
import constructive.Constructive;
import constructive.GRASP;
import constructive.Random;
import improvement.Improvement;
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
	public static final Constructive CONSTRUCTIVE = new Random();
	//public static final Constructive CONSTRUCTIVE = new GRASP();
	public static final Improvement IMPROVEMENT_1 = new Improvement1x1();
	public static final Improvement IMPROVEMENT_2 = new StrategicOscillation();
	public static final int N_TIMES = 100;
	
	public static final int ALPHA = 2; // 1..2
	public static final float DELTA = 0.10f; // {0'10, 0'20, 0'30}

	public static void main(String[] args) {		

		String[] files = IO.readFolder(Main.FOLDER);
		
		if(files != null) {
			StringBuffer stringBuffer = new StringBuffer();
			
			List<String[]> results = new ArrayList<String[]>();
			String[] titles = {"Instance", "Time", "Seed", "Quality", "Result"};
			results.add(titles);	

			for (String file : files) {
				Instance instance = IO.readFile(Main.FOLDER + "/" + file);
				
				if(instance != null){				
					Result result = new AlgorithmConstructive(Main.CONSTRUCTIVE, Main.IMPROVEMENT_1, Main.N_TIMES).execute(instance);
					//Result result = new AlgorithmConstructive(Main.CONSTRUCTIVE, Main.IMPROVEMENT_1, Main.IMPROVEMENT_2, Main.N_TIMES).execute(instance);
					stringBuffer.append(file + "\n" + result + "\n");
					System.out.println(file);
					results.add(result.toStringArray());
				}				
				//break;//TODO
			}
			
			IO.writeCSV(Main.OUTPUT, results);
			
			System.out.println(stringBuffer);	
			
			//SpringApplication.run(Main.class, args);
		}
	}

}
