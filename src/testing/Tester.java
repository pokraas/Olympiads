package testing;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * A class to test a solution of a problem from Russian Olympiads in Informatics.
 * @author alexanderpokras
 */

public class Tester {
	private Class testedClass;
	private String testsLocation;
	private int from;
	private double[] testingTimes;
	public static double TIME_THRESHOLD = 1.0;
	
	/**
	 * Test the solution class on files from the file package,
	 * path to which has been specified in the constructor.
	 * The solution class must have a public constructor which takes the file name
	 * parameter of type {@linkplain java.lang.String String} as a parameter.
	 * @param from number of first file to test on
	 * @param to number of last file to test on
	 * @throws Exception in particular if the solution class doesn't have a 
	 * constructor that takes the file name of type String as a parameter
	 * or an FileNotFoundException if a file *.a has not been found
	 */
	@SuppressWarnings("unchecked")
	public void test(int from, int to) throws Exception {
		this.from=from;
		System.out.println("Starting to test "+testedClass.getName()
				+" on test files "+from+"-"+to+"...\n");
		
		ArrayList<Integer> failedTests = new ArrayList<Integer>();
		ArrayList<Integer> timeFailedTests = new ArrayList<Integer>();
		ArrayList<Integer> passedTests = new ArrayList<Integer>();
		testingTimes = new double[to-from+1];

		for (int testNumber=from;testNumber<=to;testNumber++) {
			String filename = testsLocation+ (testNumber<10?"/0":"/") +testNumber;
			String answerFilename = filename+".a";
			Constructor testedConstructor=testedClass.getConstructor(String.class); 
			
			long startTime = System.nanoTime();
			Testable testedObject = (Testable)testedConstructor.newInstance(filename);
			String answer = testedObject.getAnswer();
			long stopTime = System.nanoTime();
			double time = (stopTime-startTime)/1000000000d;
			testingTimes[testNumber-from]=time;
			
			//System.out.println(answer);
			Scanner sc = new Scanner(new File(answerFilename));
			sc.useDelimiter("\\Z");
			String rightAnswer = sc.next();
			sc.close();
			if (rightAnswer.charAt(rightAnswer.length()-1)=='\n') {
				rightAnswer = rightAnswer.substring(0,rightAnswer.length()-1);
			}
			//System.out.println(rightAnswer+" "+time);
			
			//if failed a test
			if (!rightAnswer.equals(answer)) {
				failedTests.add(testNumber);
				if (testedObject instanceof testing.TestingProcedures) {
					((testing.TestingProcedures) testedObject).runOnFailed(
							answerFilename, testNumber, answer, rightAnswer);
				} else System.out.println("Test "+testNumber+": FAILED in "+time+" s\n");
			//if time-failed a test
			} else if (time>TIME_THRESHOLD) {
				timeFailedTests.add(testNumber);
				if (testedObject instanceof testing.TestingProcedures) {
					((testing.TestingProcedures) testedObject).runOnTimeFailed(
							answerFilename, testNumber, answer, rightAnswer);
				} else System.out.println("Test "+testNumber+": TIME-FAILED: "+time+" s\n");
			//if passed a test
			} else {
				passedTests.add(testNumber);
				if (testedObject instanceof testing.TestingProcedures) {
					((testing.TestingProcedures) testedObject).runOnPassed(
							answerFilename, testNumber, answer, rightAnswer);
				} else System.out.println("Test "+testNumber+": PASSED in "+time+" s\n");
			}
		}
		System.out.print("\nPassed tests: ");
		for (int t:passedTests) System.out.print(t+" ");
		System.out.print("\nFailed tests: ");
		for (int t:failedTests) System.out.print(t+" ");
		System.out.print("\nTime-failed tests: ");
		for (int t:timeFailedTests) System.out.print(t+" ");
		System.out.println("\n");
		System.out.println((to-from+1)+" tests complete. "
				+passedTests.size()+" tests passed, "
				+failedTests.size()+" tests failed, "
				+timeFailedTests.size()+" tests time-failed.");
	}
	
	/**
	 * Test the solution class on files from the file package,
	 * path to which has been specified in the constructor
	 * @param to number of last file to test on
	 * @throws Exception in particular if the solution class doesn't have a 
	 * constructor that takes the file name of type String as a parameter
	 */
	public void test(int to) throws Exception {
		test(1,to);
	}
	
	/**
	 * Output the time elapsed on each test.
	 * @throws IllegalStateException if test() hasn't been called first.
	 */
	public void printTestingTimes() {
		if (testingTimes==null) throw new 
				IllegalStateException("Call test() first");
		for (int i=0;i<testingTimes.length;i++) {
			if (i<9) System.out.print(" ");
			System.out.printf("Test %d: %f s\n",i+from,testingTimes[i]);
		}
	}
	/**
	 * Get the time elapsed on a specified test.
	 * @throws IllegalStateException if test() hasn't been called first.
	 */
	public double getTestingTime(int testNumber) {
		if (testingTimes==null) throw new 
				IllegalStateException("Call test() first");
		return testingTimes[testNumber];
	}
	
	/**
	 * Initialize Tester.
	 * @param testedClass The class that has to be tested. It must implement 
	 * {@link Testable} interface and have a constructor that takes
	 * the file name of type {@linkplain java.lang.String String} as a parameter. 
	 * It can implement the {@link TestingProcedures} interface to run 
	 * {@linkplain TestingProcedures#runOnPassed(String, int, String, String)
	 *  a}
	 * {@linkplain TestingProcedures#runOnTimeFailed(String, int, String, String)
	 *  certain} 
	 * {@linkplain TestingProcedures#runOnFailed(String, int, String, String)
	 *  procedure} after each test depending on the test result.
	 *  
	 * @param testsLocation path to package with test files (without slash at the end),
	 *  e.g. "src/Regional_2018/Problem1_Tests"
	 */	
	public Tester (Class testedClass, String testsLocation){
		if (!Testable.class.isAssignableFrom(testedClass)){
			throw new IllegalArgumentException("This class can't be tested:\n"
					+ "check if it implements the Testable interface correctly");
		}
		this.testedClass=testedClass;
		this.testsLocation=testsLocation;
//		System.out.println("Starting to test "+testedClass.getName()
//					+" with test files at location\n"+testsLocation+" ...\n");
	}
}
