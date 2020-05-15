package Vseros_2018;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem6_Tester {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i=10;i<=157;i++) {
			System.out.println(i);
			String filename = "Problem6_Tests/"+i;
			try {
				Scanner sc = new Scanner(new File(filename+".a"));
				
				long time = System.currentTimeMillis();

				Problem6_ClassDesks p6 = new Problem6_ClassDesks(filename);
				boolean ans = p6.getP() == sc.nextLong();
				
				long timeFinish = System.currentTimeMillis() - time;
				
				if (!ans || timeFinish>10) System.out.println(filename+" "+ans+" "+timeFinish);
				//System.out.print('\r'+i);
				sc.close();
			}catch (FileNotFoundException e) {
				System.out.println("File not found");
			}
		}
	}

}
