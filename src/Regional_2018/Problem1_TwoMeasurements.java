package Regional_2018;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import testing.*;


public class Problem1_TwoMeasurements implements Testable,TestingProcedures{
	Scanner sc;
	long l;
	long r;
	long a;
	
	public Problem1_TwoMeasurements(String filename) throws IOException {
		sc = new Scanner(new File(filename));
		l = sc.nextLong();
		r = sc.nextLong();
		a = sc.nextLong();
		//System.out.println(count2()+" "+count3());
		
	}
	
	public long count() {
		long count=0;
		for (long i=l;i<r;i++) {
			for (long j=r;j>i;j--) {
				if (((j-i)%a)==0) {
					//System.out.println(i+ " "+j);
					count++;
				}
			}
		}
		return count;
	}
	
	public long count2() {
		if (l>r||a>r-l)return 0;
		long count=0;
		for (long i=l;i<r;i++) {
			count+=(r-i)/a;
			//System.out.println(i+" "+count);
		}
		return count;
	}
	
	public long count3() {
		if (l>r||a>r-l)return 0;
		long count=0;
		if ((r-l)%a==0) {
			count+=(r-l)/a;
			//System.out.println("IF "+count);
			l++;
		}
		for (long i=l;i<r;i+=a) {
			long temp = (r-i)/a;
			count+=temp*a;
			//System.out.println(i+" "+count);
		}
		return count;
	}

	

	public static void main(String[] args) throws Exception {
		//Problem1_TwoMeasurements p1 = new Problem1_TwoMeasurements("Regional 2018/Problem1_Tests/myTest");
		Tester t = new Tester(Problem1_TwoMeasurements.class,"src/Regional_2018/Problem1_Tests");
		t.test(41);
		t.printTestingTimes();
	}

	@Override
	public void runOnFailed(String filename, int testNumber, String answer, String rightAnswer) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return count3()+"";
	}

}
