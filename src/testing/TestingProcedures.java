package testing;
/**
 * Implement this interface in your solution class for the following
 * procedures to be called after each test:
 * <ul>
 * <li><b>runOnFailed</b> runs if your solution has failed a test
 * <li><b>runOnTimeFailed</b> runs if your solution has elapsed too much time
 * <li><b>runOnPassed</b> runs if your solution has passed a test
 * </ul>
 * @author alexanderpokras
 *
 */
public interface TestingProcedures {
	/**
	 * The Tester will call this function if your solution has failed a test.
	 * @param filename path to file
	 * @param testNumber number of file
	 * @param answer your answer
	 * @param rightAnswer the right answer
	 */
	public abstract void runOnFailed(String filename, int testNumber, 
			String answer, String rightAnswer);
	/**
	 * The Tester will call this function if your solution has elapsed too much time.
	 * @param filename path to file
	 * @param testNumber number of file
	 * @param answer your answer
	 * @param rightAnswer the right answer
	 */
	public abstract void runOnTimeFailed(String filename, int testNumber, 
			String answer, String rightAnswer);
	/**
	 * The Tester will call this function if your solution has passed a test.
	 * @param filename path to file
	 * @param testNumber number of file
	 * @param answer your answer
	 * @param rightAnswer the right answer
	 */
	public abstract void runOnPassed(String filename, int testNumber, 
			String answer, String rightAnswer);
}
