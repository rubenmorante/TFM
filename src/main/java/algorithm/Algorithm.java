package algorithm;

import structures.Instance;
import structures.Result;

public interface Algorithm {
	
	public Result executeSequential(Instance instance);
	public Result executeParallel(Instance instance);
}
