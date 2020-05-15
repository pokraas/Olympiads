package Vseros_2018;

import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
//import java.lang.Math;


public class Problem6_ClassDesks {
	private int m;
	private int n;
	private int k;
	private int[] l;
	private int[] tempL;
	
	private int[] r;
	private int[] tempR;

	private int[][] groups;
	private long P;

	private void parser(String filename) {
		try {
			Scanner sc = new Scanner(new File(filename));
			m = sc.nextInt();
			n = sc.nextInt();
			k = sc.nextInt();
			tempL = new int[k];
			tempR = new int[k];
			groups = new int[m][2*n];
			
			for (int i=0;i<k;i++) {
				tempL[i] = sc.nextInt();
				tempR[i] = sc.nextInt();
			}
			
			for (int i=0;i<m;i++) {
				for (int j=0;j<2*n;j++) {
					groups[i][j]=sc.nextInt();
				}
				Arrays.sort(groups[i]);

			}

			sc.close();	
		}catch (Exception ignore) {}
	}
	
	private int countUnc(int left, int right, int height) {
		if (left<=height && right>=height) return 0;
		if (height<left) return left-height;
		else return height-right;
	}
	
	public long findLeastUnc(int index) {
		long[] uncs = new long[k];
		for (int i=0; i<k;i++) {
			int left = l[i];
			int right = r[i];
			for (int q=0; q<m;q++) {
				uncs[i] += countUnc(left,right,groups[q][index]);
				uncs[i] += countUnc(left,right,groups[q][index+1]);				
			}
		}
		
		long min = uncs[0];
		for (int i=0;i<k;i++) {
			if (uncs[i]<min) min = uncs[i];
		}
		return min;
	}
	
	public void optimizeLR() {
		boolean[] toThrowOut = new boolean[k];
		int thrownOut = 0;
		
		//System.out.println(Arrays.toString(tempL));
		//System.out.println(Arrays.toString(tempR));
		
		for (int i=0;i<k;i++) {
			for (int j=0;j<k;j++) {
				if (j==i || toThrowOut[j] || toThrowOut[i]) continue;
				if(tempL[i]<=tempL[j] && tempR[i]>=tempR[j]) {
					toThrowOut[j] = true;
					thrownOut++;
				}
				//System.out.println(thrownOut);
			}
		}
		//System.out.println(Arrays.toString(toThrowOut));
		k -= thrownOut;
		
		l = new int[k];
		r = new int[k];		
		
		int j=0;
		for (int i=0;i<tempL.length;i++) {
			if(!toThrowOut[i]) {
				l[j]=tempL[i];
				r[j]=tempR[i];
				j++;
			}
		}
		//System.out.println(Arrays.toString(l));
		//System.out.println(Arrays.toString(r));

	}
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		new Problem6_ClassDesks();
		long timeFinish = System.currentTimeMillis() - time;
		System.out.println("Elapsed time: "+timeFinish+" ms");
	}
	
	public Problem6_ClassDesks() {
		parser("Problem6_Tests/81");
		System.out.println(m+" "+n+" "+k+
		//"\n"+Arrays.toString(l)+
		//"\n"+Arrays.toString(r)+
		//"\n"+Arrays.deepToString(groups)+
		"\n");
		optimizeLR();
		for (int pair =0; pair<2*n;pair+=2) {
			//System.out.println(findLeastUnc(pair)+" P: "+P);
			P+=findLeastUnc(pair);
		}
		System.out.println(P);
	}
	
	public Problem6_ClassDesks(String filename) {
		parser(filename);
		/*System.out.println(m+" "+n+" "+k+"\n"+Arrays.toString(l)+"\n"+Arrays.toString(r)
		+"\n"+Arrays.deepToString(groups)
		+"\n"+Arrays.toString(mutants));*/
		optimizeLR();
		for (int pair =0; pair<2*n;pair+=2) {
			//System.out.println(findLeastUnc(pair)+" P: "+P);
			P+=findLeastUnc(pair);
		}
		//System.out.println(P);
	}
	
	public long getP () {
		return P;
	}
	
}
