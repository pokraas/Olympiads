package Moscow_2018;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import testing.*;

public class Problem5_House implements Testable, TestingProcedures {
	BufferedReader reader;
	char[][]l;
	char[][]h;
	int n;
	int m;
	String answer;
	
	public Problem5_House(String filename) throws IOException {
		reader = new BufferedReader(new FileReader(filename));	
		n = Integer.parseInt(reader.readLine());
		m = Integer.parseInt(reader.readLine());
		l=new char[n*3][m*3];
		h=new char[n][m];
		for (int i=0;i<n;i++) {
			for (int j=0;j<m+1;j++) {
				char c = (char)reader.read();
				if (c=='H') 
					h[i][j]=c;
			}
		}
		for (int i=0;i<n;i++) {
			for (int j=0;j<m+1;j++) {
				char c = (char)reader.read();
				if (c=='W') {
					l[n+i][m+j]=c;
				}
			}
			//System.out.println(Arrays.toString(l[n+i]));
		}
		
		//System.out.println(Arrays.deepToString(h));
		answer = ""+countBorderLength();
	}
	
	public int countBorderLength(){
		int max=0;
		for (int offsetY=0;offsetY<2*n;offsetY++) {	
			for (int offsetX=0;offsetX<2*m;offsetX++) {
				int count=0;
				abandonHouse: 
				for (int y=0;y<n;y++) {
					for (int x=0;x<m;x++) {
						if (h[y][x]=='H') {
							int oy = y+offsetY;
							int ox = x+offsetX;
							if (l[oy][ox]=='W') {
								//System.out.println("Offset: "+offsetX+" "+offsetY+" Found water at "+ox+" "+oy);
								count=0;
								break abandonHouse;
							} else {
								if (oy!=0) if (l[oy-1][ox]=='W') count++;
								if (oy!=n*3-1) if (l[oy+1][ox]=='W') count++;
								if (ox!=0) if (l[oy][ox-1]=='W') count++;
								if (ox!=m*3-1) if (l[oy][ox+1]=='W') count++;
								//System.out.println(count);
							}
						}
					}
				}
				
				if (count>max) {
					max=count;
				}
			}
		}
		return max;
	}
	

	
	public static void main(String[] args) throws Exception {
		//Problem5_House p5 = new Problem5_House("src/Moscow_2018/Problem5_Tests/10");
		Tester t = new Tester(Problem5_House.class,"src/Moscow_2018/Problem5_Tests");
		//t.TIME_THRESHOLD=0.001;
		t.test(22);
		//t.printTestingTimes();
	}

	@Override
	public String getAnswer() {
		// TODO Auto-generated method stub
		return answer;
	}

	
	public void runOnFailed(String filename, int testNumber, String answer, String rightAnswer) {
		// TODO Auto-generated method stub
		System.out.println("Failed on test "+testNumber+", got: "+answer+", right: "+rightAnswer);
	}

	
	public void runOnTimeFailed(String filename, int testNumber, String answer, String rightAnswer) {
		// TODO Auto-generated method stub
		
	}

	
	public void runOnPassed(String filename, int testNumber, String answer, String rightAnswer) {
		// TODO Auto-generated method stub
		
	}
}
