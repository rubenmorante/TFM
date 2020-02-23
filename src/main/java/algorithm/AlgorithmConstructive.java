package algorithm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

import constructive.Constructive;
import improvement.Improvement;
import structures.Instance;
import structures.Result;
import structures.Solution;

public class AlgorithmConstructive implements Algorithm {
	
	private final Constructive constructive;
	private final int nTimesRepeat;
	private Improvement improvement_1;
	private Improvement improvement_2;
	
	//concurrent variable
	private volatile Solution bestSolution = null;
	private volatile double bestQuality = Double.MAX_VALUE;
	private CountDownLatch countDownLatch;
	private final ReentrantLock reentrantLock = new ReentrantLock();
	
	public AlgorithmConstructive(Constructive constructive, int nTimesRepeat) {
		super();
		this.constructive = constructive;
		this.nTimesRepeat = nTimesRepeat;
		this.improvement_1 = null;
		this.improvement_2 = null;
		
		this.countDownLatch = new CountDownLatch(this.nTimesRepeat);
	}
	
	public AlgorithmConstructive(Constructive constructive, Improvement improvement_1, int nTimesRepeat) {
		this(constructive, nTimesRepeat);
		this.improvement_1 = improvement_1;
		this.improvement_2 = null;
	}
	
	public AlgorithmConstructive(Constructive constructive, Improvement improvement_1, Improvement improvement_2, int nTimesRepeat) {
		this(constructive, improvement_1, nTimesRepeat);
		this.improvement_2 = improvement_2;
	}	
	
	@Override
	public Result executeSequential(Instance instance) {
		long startTime = System.currentTimeMillis();
		for(int x = 0; x < this.nTimesRepeat; ++x) {
			this.generateSolutionSequential(instance);
		}	
		long time = (System.currentTimeMillis() - startTime);
		return  new Result(this.getBestSolution(), time);
	}
	
	@Override
	public Result executeParallel(Instance instance) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		long startTime = System.currentTimeMillis();
		for(int x = 0; x < this.nTimesRepeat; ++x) {
			executor.execute(() -> this.generateSolutionParallel(instance));
		}
		executor.shutdown();

		try {
			this.countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long time = (System.currentTimeMillis() - startTime);
		
		return new Result(this.getBestSolution(), time);
	}

	private void generateSolutionParallel(Instance instance) {		
		Solution candidateSolution = this.generateSolution(instance);		
		double candidateQuality = candidateSolution.getQuality();
		
		this.reentrantLock.lock();		
		this.updateQualitySolution(candidateQuality, candidateSolution);
		this.reentrantLock.unlock();		
		
		this.countDownLatch.countDown();
	}
	
	private void generateSolutionSequential(Instance instance) {		
		Solution candidateSolution = this.generateSolution(instance);		
		double candidateQuality = candidateSolution.getQuality();
		this.updateQualitySolution(candidateQuality, candidateSolution);
	}
	
	private Solution generateSolution(Instance instance) {
		Solution candidateSolution = this.constructive.construct(instance);
		if(this.improvement_1 != null) {
			this.improvement_1.improve(candidateSolution);
		}		
		if(this.improvement_2 != null) {
			this.improvement_2.improve(candidateSolution);
		}
		return candidateSolution;
	}
	
	private void updateQualitySolution(double candidateQuality, Solution candidateSolution) {
		if(this.getBestQuality() > candidateQuality) {
			this.setBestQuality(candidateQuality);
			this.setBestSolution(candidateSolution);
		}
	}

	private Solution getBestSolution() {
		return this.bestSolution;
	}

	private synchronized void setBestSolution(Solution bestSolution) {
		this.bestSolution = bestSolution;
	}

	private double getBestQuality() {
		return this.bestQuality;
	}

	private synchronized void setBestQuality(double bestQuality) {
		this.bestQuality = bestQuality;
	}		
}