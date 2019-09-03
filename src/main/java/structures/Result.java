package structures;

public class Result {

	private final Solution solution;
	private final long time;

	public Result(Solution solution, long time) {
		super();
		this.solution = solution;
		this.time = time;
	}

	@Override
	public String toString() {
		return "solution = " + this.solution + "\ntime = " + this.time + "\nquality = " + this.getQuality();
	}
	
	public String[] toStringArray() {
		String[] arrayString = {this.solution.getInstance().getName(), this.time + "", RandomManager.getSeed() + "", this.getQuality() + "", this.solution.toString()};
		return arrayString;
	}
	
	public double getQuality() {
		return this.solution.getQuality();
	}
}
