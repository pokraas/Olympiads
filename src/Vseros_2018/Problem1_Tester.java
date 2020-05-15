package Vseros_2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem1_Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i=1;i<=90;i++) {
			//System.out.println(i);
			String filename = "Problem1_Tests/"+i;
			if (i<10) filename = "Problem1_Tests/0"+i;
			try {
				Scanner sc = new Scanner(new File(filename+".a"));
				
				long time = System.currentTimeMillis();

				Problem1_Photo p1 = new Problem1_Photo(filename);
				
				//String out1 = p1.getOutput();
				long time1 = System.currentTimeMillis() - time;
				//String out2 = p1.getOutputAlg2();
				p1.algorithm2();
				long time2 = System.currentTimeMillis() - (time+time1);
				
				//String rightAnswer = sc.useDelimiter("\\Z").next()+"\n";
				//boolean flag = out1.equals(out2);
				System.out.println(filename+"  "+time1+" vs  "+time2);
				//System.out.println(rightAnswer);
				
				//long timeFinish = System.currentTimeMillis() - time;
				
				//if (!ans || timeFinish>100) System.out.println(filename+" "+ans+" "+timeFinish+" ms");
				//System.out.print('\r'+i);
				sc.close();
			}catch (FileNotFoundException e) {
				System.out.println("File not found");
			}
		}
	}

}
