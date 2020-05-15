package Vseros_2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem5_Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i=1;i<=80;i++) {
			System.out.println(i);
			String filename = "Problem5_Tests/"+i;
			if (i<10) filename = "Problem5_Tests/0"+i;
			try {
				Scanner sc = new Scanner(new File(filename+".a"));
				
				int length = sc.nextInt();
				
				long time = System.currentTimeMillis();

				Problem5_Garland p5 = new Problem5_Garland(filename);
				boolean ans = p5.getAnswerForCheck().equals(sc.nextLine());
				System.out.println(sc.nextLine());
				
				long timeFinish = System.currentTimeMillis() - time;
				
				if (!ans || timeFinish>10) System.out.println(filename+" " + ans + " "+timeFinish);
				//System.out.print('\r'+i);
				sc.close();
			}catch (FileNotFoundException e) {
				System.out.println("File not found");
			}
		}
	}
}
