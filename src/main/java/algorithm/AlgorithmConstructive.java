package algorithm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

import constructive.Constructive;
import improvement.Improvement;
import structures.Instance;
import structures.Result;
import structures.Solution;

public class AlgorithmConstructive implements Algorithm {
	
	private final Constructive constructive;
	private final Improvement improvement_1;
	private Improvement improvement_2;
	private final int nTimesRepeat;
	
	//concurrent variable
	private volatile Solution bestSolution = null;//TODO
	private volatile double bestQuality = Double.MAX_VALUE;//TODO
	private CountDownLatch countDownLatch;//TODO
	private final ReentrantLock reentrantLock = new ReentrantLock();//TODO
	
	
	public AlgorithmConstructive(Constructive constructive, Improvement improvement_1, int nTimesRepeat) {
		super();
		this.constructive = constructive;
		this.improvement_1 = improvement_1;
		this.nTimesRepeat = nTimesRepeat;
		this.improvement_2 = null;
		
		this.countDownLatch = new CountDownLatch(this.nTimesRepeat);//TODO
	}
	
	public AlgorithmConstructive(Constructive constructive, Improvement improvement_1, Improvement improvement_2, int nTimesRepeat) {
		this(constructive, improvement_1, nTimesRepeat);
		this.improvement_2 = improvement_2;
	}



	//Part of the code that it works in a thread
	public void executeThread(Instance instance) {//TODO
		Solution candidateSolution = this.constructive.construct(instance);
		//double firstQuality = candidateSolution.getQuality();//TODO
		this.improvement_1.improve(candidateSolution);
		
		if(this.improvement_2 != null) {
			this.improvement_2.improve(candidateSolution);
		}
		
		double candidateQuality = candidateSolution.getQuality();
		
		this.reentrantLock.lock();		
		if(this.getBestQuality() > candidateQuality) {
			this.setBestQuality(candidateQuality);
			this.setBestSolution(candidateSolution);
		}
		this.reentrantLock.unlock();
		//System.out.println("First quality:\t" + firstQuality + "\tSecond quality:\t" + candidateQuality);//TODO
		
		this.countDownLatch.countDown();
	}
	
	
	@Override
	public Result execute(Instance instance) {//TODO

		long startTime = System.currentTimeMillis();
		for(int x = 0; x < this.nTimesRepeat; ++x) {
			new Thread(() -> this.executeThread(instance)).start();
		}

		try {
			this.countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long time = (System.currentTimeMillis() - startTime);
		
		return new Result(this.getBestSolution(), time);
	}



	private Solution getBestSolution() {//TODO
		return this.bestSolution;
	}



	private synchronized void setBestSolution(Solution bestSolution) {//TODO
		this.bestSolution = bestSolution;
	}



	private double getBestQuality() {//TODO
		return this.bestQuality;
	}



	private synchronized void setBestQuality(double bestQuality) {//TODO
		this.bestQuality = bestQuality;
	}
	
	/*@Override
	public Result execute(Instance instance) {
		
		Solution bestSolution = null;
		double bestQuality = Double.MAX_VALUE;

		long startTime = System.currentTimeMillis();
		for(int x = 0; x < this.nTimesRepeat; ++x) {
			Solution candidateSolution = this.constructive.construct(instance);
			//double firstQuality = candidateSolution.getQuality();//TODO
			this.improvement.improve(candidateSolution);
			double candidateQuality = candidateSolution.getQuality();
			
			if(bestQuality > candidateQuality) {
				bestQuality = candidateQuality;
				bestSolution = candidateSolution;
			}
			
			//System.out.println("First quality:\t" + firstQuality + "\tSecond quality:\t" + candidateQuality);//TODO
		}
		long time = (System.currentTimeMillis() - startTime);
		
		return new Result(bestSolution, time);
	}*/
	
	
}
