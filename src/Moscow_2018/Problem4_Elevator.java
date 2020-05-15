package Moscow_2018;
import java.util.Scanner;
import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;


public class Problem4_Elevator {
	private int N,a,b,c,ans;
	
	
	public Problem4_Elevator() {
		try {
			
			for (int i = 1;i<=4;i++) {
				String filename = "src/Problem4_Tests/";
				if (i<10) filename = filename + "0"+i;
				else filename = filename + i;
				
				Scanner sc = new Scanner(new File(filename));
				N = sc.nextInt();
				a = sc.nextInt();
				b = sc.nextInt();
				c = sc.nextInt();
			//maxT(5);
				binSearch(1,N);
				System.out.println("Result: " + ans);
				sc.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int maxT(int l) {
		int kmin = Math.round(l*((c+b)/(float)(a+b)));
		
		int kmax = Math.round(((c+b)*l+a)/(float)(a+b));
		//System.out.println(kmin + " "+ kmax);
		int[]ar;
		if (kmin == kmax) {
		ar = new int[3];
		ar[0] = (kmin-1)*a;	//upstairs from the 1st floor
		ar[1] = c*l+(l-kmin)*b;	//downstairs from the elevator
		ar[2] = c*l+(N-l)*a;	//upstairs from the elevator
		
		} else {
		ar = new int[5];
		ar[0] = (kmin-1)*a;	//upstairs from the 1st floor
		ar[1] = (kmax-1)*a;
		ar[2] = c*l+(l-kmin)*b;	//downstairs from the elevator
		ar[3] = c*l+(l-kmax)*b;
		ar[4] = c*l+(N-l)*a;	//upstairs from the elevator
		}
		
		int max = 0;
		
		for(int x:ar) {
			if (x>max) max=x;
			//System.out.print(x+" ");
		}
		//System.out.println("\n"+max);
		return max;
	}
	
	
	private void binSearch(int low,int high) {

		int l = (low+high)/2;
		System.out.println("Searching... "+low + " "+l+" " + high+" "+(high==low));
		System.out.println("Times: "+maxT(low)+" "+maxT(l)+" "+maxT(high));
		if (maxT(l)<maxT(low) && maxT(high)<=maxT(low)) low = l;
		if (maxT(l)<maxT(high)&& maxT(low)<=maxT(high)) high = l;
		if (high==low) {
			ans = maxT(low);
			return;
		} else {
		binSearch(low,high);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem4_Elevator p4 = new Problem4_Elevator();
		

	}

}
