package Moscow_2018;
import java.util.Scanner;
import java.lang.Math;


public class Problem4_Old {
	private int N,a,b,c;
	
	
	public Problem4_Old() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
	//maxT(5);
		System.out.println("Result " + binSearch(1,N));
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
	
	private int count = 0;
	
	private int binSearch(int low,int high) {
		
		count++;
		
		int l = (low+high)/2;
		System.out.println("Searching... "+low + " "+l+" " + high+" ");
		System.out.println("Times: "+maxT(low)+" "+maxT(l)+" "+maxT(high));
		
		//if (maxT(high)<maxT(l)) l = high;
		//if (maxT(low)<maxT(l)) l = low;
		
		if (maxT(l)<maxT(low) && maxT(high)<=maxT(low)) low = l;
		if (maxT(l)<maxT(high)&& maxT(low)<=maxT(high)) high = l;
		System.out.println("Found   ... "+low + " "+l+" " + high+" ");

		if ((low==l && high==l) || count == 10) {
			System.out.println("Я натурал "+maxT(high));
			return 100;
		} else {
		binSearch(low,high);
		System.out.println(count+" Я гей, счас верну -10 - смотрите, как это было: ");
		return -10;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem4_Old p4 = new Problem4_Old();
		

	}

}
