package Vseros_2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Problem8_Idea {
	ArrayList<Character>c;
	Scanner sc;
	int m,n;
	private void initC() {
		int size=0;
		while(sc.hasNextLine()) {
			int command = sc.nextInt();
			if (command==1) {
				size+=sc.next().length();
				System.out.println(size);
			}
		}
	}
	public Problem8_Idea(String filename){
		try {
			sc = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		m=sc.nextInt();
		n=sc.nextInt();
		String p = sc.next();
		System.out.println(p+" "+m+" "+n);
		c = new ArrayList<Character>(n);
		initC();
		System.out.println(c.size());
	}
	public static void main(String[] args) {
		Problem8_Idea idea = new Problem8_Idea("src/Problem8_Tests/01");
	}
}

//abcdef
// bcd
//2 1 5
