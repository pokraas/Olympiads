package Regional_2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import testing.Testable;
import testing.Tester;
import testing.TestingProcedures;

public class Problem5_DefectiveMarsRover implements Testable, TestingProcedures {
	Scanner sc;
	private long a;
	private long b;
	private long c;

	public Problem5_DefectiveMarsRover(String filename) throws FileNotFoundException {
		sc = new Scanner(new File(filename));
		 a = sc.nextLong();
		 b = sc.nextLong();
		 c = sc.nextLong();
	
	}
	
	private long numCommands(long k,long l) {
		
		return (l-k+1)/2;
	}
	
	private long solve(long a, long b, long c) {
		if (c>b) return numCommands(a,b);
		long nc = c;
		long ans = 0;
		for(nc=(a/c)*c+c;nc<b;nc+=c) { // nc+c<b didn't count all the operations (always one not enough)
			if (nc<a) continue;
			if (nc-c<a) ans+= numCommands(a,nc-1);
			else ans+=numCommands(nc-1,nc+c-1);
		}
		if (nc-c-1<a) return ans+=numCommands(a,b);
		return ans+=numCommands(nc-c-1,b); // nc-1 was too little operations
	
	}

	@Override
	public void runOnFailed(String filename, int testNumber, String answer, String rightAnswer) {
		System.out.println("Failed test "+testNumber +" a="+a+" b="+b+" c="+c
				+": got "+answer+" right: "+rightAnswer);
	
	}

	@Override
	public void runOnTimeFailed(String filename, int testNumber, String answer, String rightAnswer) {

	}

	@Override
	public void runOnPassed(String filename, int testNumber, String answer, String rightAnswer) {
	}

	@Override
	public String getAnswer() {
		return solve(a,b,c)+"";
	}
	public static void main (String[] args) throws Exception {
		Tester t = new Tester(Problem5_DefectiveMarsRover.class,"src/Regional_2018/Problem5_Tests");
		t.test(47);
		//t.printTestingTimes();
	}

}
