package Vseros_2018;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Problem5_Garland {
	
	public 		ArrayList<Integer> answer = new ArrayList<Integer>();
	private ArrayList<Integer> a = new ArrayList<Integer>();
	//private  String garland = "00101110000";
	private ArrayList<Integer> p = new ArrayList<Integer>(1);
	private int[] experiments;
	private int thrownOut = 0;
	private int garlandLength = 0;
	private String answerForCheck;
	
	private void throwNextFlagOut(int index) {		
		int temp = p.get(index)+p.get(index+1);
		p.set(index, temp);
		p.remove(index+1);
	}
	
	private void bringAllElementsToPower(int power) {		
		for (int i=0;i<p.size();i++) {
			//System.out.print(p.toString()+thrownOut);
			if (p.get(i)<power) {
				if (i == p.size()-1) {
					thrownOut += 1+p.get(i);
					p.remove(i);
				} else {
					throwNextFlagOut(i);
					thrownOut++;
					bringAllElementsToPower(power);
				}
				//System.out.println(" if "+i);
			} else {
				thrownOut += p.get(i)-power;
				p.set(i, power);
				//System.out.println(" else "+i);
			}			
		}
	}
	
	/*private void writeInList() {
		for (String s:garland.split("")) {
			a.add(Integer.parseInt(s));
		}
	}
	*/
	
	public void readListFromFile (String filename) {
		try {
			Scanner sc = new Scanner(new File(filename));
			garlandLength = sc.nextInt();
			String s = sc.next();
			//s = sc.nextLine();
			//System.out.println(s);
			for (String z:s.split("")) {
				a.add(Integer.parseInt(z));
			}			
			sc.close();
		}catch (FileNotFoundException e) {
			//System.out.println("File not found");
		};
	}
	
	private void powerOfFlags() {
		
		int j = 0;
		p.add(0);
		for (int e : a) {
			if (e==1) {
				p.add(0);
				j++;
			}
			else p.set(j, p.get(j)+1);
		}
	}
	
	private int maxPower() {
		int max = 0;
		for (int e:p) {
			if (e>max) max = e;
		}
		return max;
	}
	
	private int minInArray(int[] experiments) {
		int minIndex = 0;
		int min = a.size();
		for (int i = 0;i<experiments.length;i++) {
			if (experiments[i]<min) {
				min = experiments[i];
				minIndex = i;
			}
			//System.out.println(minIndex);
		}
		return minIndex;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Problem5_Garland("Problem5_Tests/16");
	}
	
	public Problem5_Garland (String filename){
		//writeInList();
		readListFromFile(filename);
		//System.out.println(a);
		powerOfFlags();
		//System.out.println(p.toString());
		ArrayList<Integer>pCopy = new ArrayList<Integer>(p);

		experiments = new int[maxPower()+1];	
		
		for (int power = 0; power<=maxPower();power++) {
			bringAllElementsToPower(power);
			if(p.size() == 1) {
				experiments[power]=a.size();
			} else {			
			experiments[power]=thrownOut;
			}
			thrownOut=0;
			p = new ArrayList<Integer>(pCopy);
			//System.out.println(p);
		}

		//System.out.println(Arrays.toString(experiments));
		//System.out.println(minInArray(experiments)+" is the best power");
		bringAllElementsToPower(minInArray(experiments));
		boolean flag = false;
		for (int e : p) {
			if (flag) {
				//System.out.print(1);
				answer.add(1);
			}
			//else System.out.println();
			for (int j=1;j<=e;j++) {
				//System.out.print(0);
				answer.add(0);
			}
			flag = true;
		}
		answerForCheck = "";
		for (int value : answer) {
			answerForCheck = answerForCheck + value;
		}
		
		//System.out.println("\n"+answer);
			getAnswerForCheck();	
	}
	
	public String getAnswerForCheck() {
		//System.out.println(answerForCheck);
		return answerForCheck;
	}
	
	
	}
	
	

