package Vseros_2018;

import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem1_Photo {
	
	private String filename;
	
	private int n;
	private int m;

	
	private int[] l;
	private int[] r;
	private int[] o;

	private int[] a;
	private int[] b;
	
	private void fillLR() {
		Arrays.fill(l, -1);
		Arrays.fill(r, -1);
		
		for (int i = 0;i<o.length;i++) {
			if (l[o[i]]==-1) l[o[i]] = i;
		}
		for (int i = o.length-1;i>=0;i--) {
			if (r[o[i]]==-1) r[o[i]] = i;
		}
		
	}
	
	private void fillA() {
		for (int i = 0;i<l.length;i++) {
			if (l[i]==-1) continue;
			a[l[i]] = i;
			//a[r[i]] = i;
			
		}
		
	}
	
	private String output = "";
	private int count = 0;
	
	private void fillB() {
		
		for (int x:a) {
			if (x==0) continue;
			for (int i = l[x];i<=r[x];i++) {
				b[i] = x;
			}
			output = output + x + " " + (l[x]+1) + " " + (r[x]+1) + "\n";
			count++;
			
		}
		
		output = count + "\n" + output;
		
		for (int i = 0; i<a.length;i++) {
			if (b[i]!=o[i]) {
				output = "-1"+"\n";
				break;
			}
		}
		
		
	}
	
	public String getOutput() {
		algorithm1();
		return output;
	}
	


	
	public void algorithm1(){
		
		try {
			Scanner sc = new Scanner(new File(filename));
			m = sc.nextInt();
			n = sc.nextInt();
			
			o = new int[m];
			a = new int[m];
			b = new int[m];
			
			l = new int[n+1];
			r = new int[n+1];
			
			for (int i = 0;i<m;i++) {
				o[i] = sc.nextInt();
				}
			}catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		fillLR();
		//System.out.println(Arrays.toString(l));
		//System.out.println(Arrays.toString(r));
		fillA();
		//System.out.println(Arrays.toString(a));
		fillB();
		//System.out.println(Arrays.toString(b));
		//System.out.println(output);		
	}
//=============Algorithm 2==========================================================================
	
	

	private int[] stack;
	private int[] p; 
	private int[][] requests;	//0 - color, 1 - left chair, 2 - right chair
	private int[] helper;
	private int topOfRequests=1;
	
	private void stackMethod() {
		int topOfStack=1;
		
		for (int i=0;i<m;i++) {
			int x = o[i];
			if (p[x]==0){
				p[x] = 1;
				stack[topOfStack] = x;
				topOfStack++;
				
				requests[topOfRequests][0] = x;
				requests[topOfRequests][1] = i+1;
				requests[topOfRequests][2] = i+1;				
				helper[x] = topOfRequests;
				topOfRequests++;				
			} else if (p[x]==1) {
				int j = topOfStack-1;
				requests[helper[x]][2] = i+1;
				//int iterations=0;
				while(stack[j] != x) {
					int y = stack[j];
					requests[helper[y]][2]=i;
					p[y]=2;
					stack[j]=0;
					j--;
					//iterations++;
				}
				//System.out.print(iterations+" ");
			} else {
				requests[0][0] = -1;
				break;
			}
			//requests[helper[o[o.length-1]]][2] = n;
			
		}
	}
	
	public String getOutputAlg2() {
		algorithm2();
		if (requests[0][0]==-1) return "-1";
		String outp = topOfRequests-1+"\n";
		for (int i=1;i<topOfRequests;i++) {
			outp = outp+requests[i][0]+" "+requests[i][1]+" "+requests[i][2]+"\n";
		}
		return outp;
	}
	
	public void algorithm2() {
		
		try {
			Scanner sc = new Scanner(new File(filename));
			m = sc.nextInt();
			n = sc.nextInt();
			o = new int[m];
			stack = new int[n+1];
			requests = new int[n+2][3];
			helper = new int [n+1];
			p = new int[n+1];
			
			
			for (int i=0;i<m;i++) {
				o[i] = sc.nextInt();
			}
			
			stackMethod();
			
		} catch (FileNotFoundException e){
			System.out.println("File not found");
			
		}
	}
	
	//==================================================================================================
	
	Problem1_Photo(String filename){
		this.filename = filename;
		//System.out.println(getOutput());
		//System.out.println(getOutputAlg2());
		
		//algorithm2(filename);
		//System.out.println(Arrays.deepToString(requests));
		//System.out.println(Arrays.toString(helper));
		//System.out.println(Arrays.toString(stack));
		//System.out.println(Arrays.toString(helperStack));
		//System.out.println(getOutputAlg2());
		
	}
	
	public static void main(String[] args) {
		Problem1_Photo p1 = new Problem1_Photo("Problem1_Tests/03");
		//System.out.println(p1.getOutput());
	}

}
