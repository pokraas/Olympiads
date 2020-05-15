package Regional_2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import testing.*;

public class Problem2_Squares implements Testable, TestingProcedures {
	Scanner sc;
	long k;
	public Problem2_Squares(String filename) throws FileNotFoundException {
		sc = new Scanner(new File(filename));
		k = sc.nextLong();
	}
	
	
	
	public static void main (String[] args) throws Exception {
		Tester t = new Tester(Problem2_Squares.class,"src/Regional_2018/Problem2_Tests");
		t.test(61);
		//t.printTestingTimes();
	}



	@Override
	public void runOnFailed(String filename, int testNumber, String answer, String rightAnswer) {
		// TODO Auto-generated method stub
		System.out.println("Failed test "+testNumber+", k="+k
				+": got "+answer+" right: "+rightAnswer);
	}



	@Override
	public void runOnTimeFailed(String filename, int testNumber, String answer, String rightAnswer) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void runOnPassed(String filename, int testNumber, String answer, String rightAnswer) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public String getAnswer() {
		long min = Long.MAX_VALUE;
		if (k==0) return 0+"";
		
		if(k>0) {
			for(long i=1;i<=Math.sqrt(k);i++) {
			if (k%i!=0) continue;
			
			long n = i + k/i;
			if (n%2==0 && n<min) min = n;
			}
		}	
		
		else {
			for(long i=-(long)Math.sqrt(-k);i<0;i++) {
			if (k%i!=0) continue;
			
			long n = i + k/i; 
			if (n%2==0 && n<min && n>=0) min = n;
			}
		}
		
		if (min==Long.MAX_VALUE) return "none";
		return min/2+"";
	}

}
