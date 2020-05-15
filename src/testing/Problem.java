package testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * A mock solution class to represent a solution to an Olympiad problem.
 * Used it to test the {@link Tester} class (how ironic).
 * @author alexanderpokras
 *
 */
public class Problem implements Testable,TestingProcedures {
	private String filename;
	public Problem(String filename) throws InterruptedException{
		//Thread.sleep(1000);
		this.filename=filename;
		//System.out.println("Created an instance of Problem with the argument "+filename);

	}
	public Problem() {
		System.out.println("Created an instance of Problem using default constructor");
	}
	@Override
	public String getAnswer() {
		try {
			Scanner sc = new Scanner(new File(filename));
			return "The solution is "+sc.next();
		} catch (FileNotFoundException e) {
			System.out.println("Exception: Problem didn't find file "+filename);
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return "whatever";
	}
	@Override
	public void runOnFailed(String filename, int testNumber, String answer, String rightAnswer) {
		System.out.println("Oops! Failed on test "+testNumber);
		System.out.println("Right answer: "+rightAnswer);
		System.out.println("My answer: "+answer);
		
	}
	@Override
	public void runOnTimeFailed(String filename, int testNumber, String answer, String rightAnswer) {
		System.out.println("Zzz... Time-failed on test "+testNumber);

	}
	@Override
	public void runOnPassed(String filename, int testNumber, String answer, String rightAnswer) {
		System.out.println("Alright! Passed on test "+testNumber);
		
	}
	public static void main(String[] args) {
		// TODO remove this method once done with Tester class
		Tester t = new Tester(Problem.class,"src/testing/Problem_Tests");
		try {
			t.test(9,11);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
