package Regional_2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import testing.Testable;
import testing.Tester;
import testing.TestingProcedures;


public class Problem3_Automation_Newest implements Testable, TestingProcedures {
	private final int n,m;	//size constants
	private final int[] a;	//to store orders
	private final int[] b;	//to store cards
	private Queue[] aux;	//auxiliary: to store occurrences of each card in the //TODO 
	private LinkedList<Integer> orderOfTaking;
	
	public Problem3_Automation_Newest(String filename) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(filename));
		n = sc.nextInt();
		m = sc.nextInt();
		a = new int[m+1];
		b = new int[n+1];
		aux = new Queue[n+1];
		orderOfTaking = new LinkedList<Integer>();
		
		//filling the aux array
		for (int i=1;i<=n;i++) aux[i] = new LinkedList<Integer>();
		
		//filling the array of orders (a[])
		for (int i=1;i<=m;i++) {
			int order = sc.nextInt();
			a[i] = order;
		}
		
		//filling the array of cards (b[])
		for (int i=1;i<=n;i++) {
			int card = sc.nextInt();
			b[i] = card;
		}
		
		sc.close();
		
		//Phase I of the algorithm
		int numberOfVisitedCards=0;
		int indexInOrderOfTaking=0;
		int indexInB=1;
		//iterate over all orders
		for (int order : a) {
			if (order==0) continue;
			if (aux[order].isEmpty()) {
				//if we need a not yet visited card for the new order
				for (int i = indexInB; i<n+1; i++) {
					int card = b[i];
					
					//don't add an already visited card to orderOfTaking
					if (!aux[card].isEmpty()) continue;
					
					//add a card to orderOfTaking if it's not already been visited
					indexInOrderOfTaking++;
					orderOfTaking.add(card);
					aux[card].add(indexInOrderOfTaking);
					numberOfVisitedCards++;
					
					indexInB++;
					//break if found the right card for the order
					if (card==order) break;
				}
			} else {
				//if we need an already visited card for the new order
				indexInOrderOfTaking++;
				orderOfTaking.add(order);
				aux[order].add(indexInOrderOfTaking);
			}
		}
//		output();
//		System.out.println(calculateAnswer());
	}
	
	private LinkedList<Integer> calculateAnswer() {
		LinkedList<Integer> answer = new LinkedList<Integer>();
		for (int card : orderOfTaking) {
			if (aux[card].size()>1) {
				int delta = - (int)aux[card].poll() + (int)aux[card].peek();
				answer.add(Math.min(delta, n));
			} else {
				answer.add(n);
			}
		}
		return answer;
	}
	
	public String getAnswer() {
		LinkedList<Integer> answer = calculateAnswer();
		StringBuilder sb = new StringBuilder();
		sb.append(answer.size());
		sb.append((char)13+"\n");
		for (int i : answer) sb.append(i+" ");
		return sb.toString();
	}
	
	private void output() {
		System.out.println(orderOfTaking+"\naux\n");
		for (Queue q : aux) System.out.println(q);
	}
	
	public static void main(String[] args) throws Exception {
		Tester t = new Tester(Problem3_Automation_Newest.class, "src/Regional_2018/Problem3_Tests");
		t.test(11,11);
		//t.printTestingTimes();
	}

	
	@Override
	public void runOnFailed(String filename, int testNumber, String answer, String rightAnswer) {
//		char[]answerArray = answer.toCharArray();
//		for (int c : answerArray) System.out.print(c+" ");
//		System.out.println();
//		char[]rightAnswerArray = rightAnswer.toCharArray();
//		for (int c : rightAnswerArray) System.out.print(c+" ");
//		System.out.println();
		
		System.out.println(answer+"\n"+rightAnswer+"\n"+answer.equals(rightAnswer));
	}

	@Override
	public void runOnTimeFailed(String filename, int testNumber, String answer, String rightAnswer) {
		//System.out.println(answer.split("\n")[0]);
	}

	@Override
	public void runOnPassed(String filename, int testNumber, String answer, String rightAnswer) {
		// TODO Auto-generated method stub
		
	}
}
