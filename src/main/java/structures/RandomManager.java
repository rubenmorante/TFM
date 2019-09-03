package structures;

import java.util.Random;

public class RandomManager {

	private static long SEED = 1234;
	private static final Random RANDOM = new Random(RandomManager.SEED);	
	
	public static Random getRandom() {
		return RandomManager.RANDOM;
	}

	public static void setSeed(long seed) {
		RandomManager.SEED = seed;
		RandomManager.RANDOM.setSeed(RandomManager.SEED);
	}
	
	public static long getSeed() {
		return RandomManager.SEED;
	}
	
	public static int nextInt(int bound) {
		return RANDOM.nextInt(bound);
	}
}
