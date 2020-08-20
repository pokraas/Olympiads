package Regional_2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import testing.Testable;
import testing.Tester;
import testing.TestingProcedures;
import testing.Timer;

public class Problem3_Automation_New implements Testable, TestingProcedures {
	private final int n,m;	//size constants
	private final int[] a;	//to store orders
	private Node head,tail;	//linked list (b[]) to store the cards
	private TreeSet<Integer>[] c;	//auxiliary array to store when each order occurs
	private int numOfOrder;			//on which order in a[] the robot is working
	private int numOfActions;		//how many times the robot has already taken the first card
	private List<Integer> ans;	//for the answer
	
	private class Node {
		int card;
		Node next;
		Node(int card){
			this.card = card;
		}
		Node(int card, Node next){
			this(card);
			this.next=next;
		}
		void insertAfter(int toInsert) {
			Node grandchild = this.next;
			this.next = new Node(toInsert,grandchild);
			if (this==tail) tail = this.next;
		}
		void destroyNext() {
			if (this.next==tail) throw new IllegalStateException();
			this.next = this.next.next;			
		}
		@Override
		public String toString() {
			return "<"+card+">";
		}
	}
	
	public Problem3_Automation_New(String filename) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(filename));
		n = sc.nextInt();
		m = sc.nextInt();
		a = new int[m+1];

		c = (TreeSet<Integer>[]) new TreeSet[n+1];
		for (int i=1; i<=n; i++) c[i] = new TreeSet<Integer>();
		
		//filling the array of orders (a[]) and the array of occurrences of each order (c[])
		for (int i=1;i<=m;i++) {
			int order = sc.nextInt();
			a[i] = order;
			c[order].add(i);
		}
		
		//filling the linked list of cards
		head = new Node(-1);
		Node curr = head;
		for (int i=1;i<=n;i++) {
			curr.insertAfter(sc.nextInt());
			curr = curr.next;
			tail = curr;
		}
		
		sc.close();
		//inputTest();
		
		//completing orders and inserting cards
		numOfOrder = 1;
		numOfActions = 0;
		ans = new LinkedList<Integer>();
		while (numOfOrder <= m) {
			completeOrder(head.next.card);
			int action = insertCard(head.next.card);
			head.destroyNext();
			numOfActions++;
			ans.add(action);
			//printLinkedList();
		}
		//System.out.println("\n\nTerminated in: "+numOfActions+"\n"+ans.toString());
	}
	
	/** Test that the input has been loaded correctly*/
	private void inputTest() {
		System.out.println(Arrays.toString(a));
		for (int i=1; i<=n; i++) {
			if (c[i]==null) throw new IllegalStateException(i+"!!!");
			System.out.printf("Order %d occurs at %s\n", i,c[i].toString());
		}
		printLinkedList();
		System.out.println("\n");
	}
	
	private void printLinkedList() {
		Node curr = head;
		while (curr != tail) {
			curr = curr.next;
			System.out.print(curr+" ");
		}
		System.out.println();
	}
	
	private boolean completeOrder(int firstCard) {
		int order = a[numOfOrder]; 
		if (order == firstCard) {
			//if the order can be completed with this card
			if (numOfOrder != c[order].pollFirst()) throw new IllegalStateException();
			numOfOrder++;
			return true;
		}
		return false;
	}
	
	private int insertCard(int cardToInsert) {
		//insert the card to the end of the deck if it won't be used for future orders
		if (c[cardToInsert].isEmpty()) {
			tail.insertAfter(cardToInsert);
			return n;
		}
		
		int nextTimeThisOrderAppears = c[cardToInsert].first();
		//insert the card on the top of the deck if it will be needed for the very next order
		if (nextTimeThisOrderAppears==numOfOrder) {
			head.insertAfter(cardToInsert);
			return 1;
		}
		
		//add all the orders that have to be completed before this card is needed to the helper set
		Set<Integer> helper = new HashSet<Integer>();
		for (int i=numOfOrder; i<nextTimeThisOrderAppears; i++) helper.add(a[i]);
		
		//move through the linked list of the cards, remove the already seen cards from the helper set
		//and insert the card right away once removed all predecessors from the helper set
		int ret=0;
		Node curr = head;
		while (!helper.isEmpty()) {
			ret++;
			curr = curr.next;
			helper.remove(curr.card);
		}
		curr.insertAfter(cardToInsert);
		return ret;
	}
	
	public String getAnswer() {
		String actions="";
		for (int action : ans) actions+=(action+" ");
		//(char)13 is the carriage return symbol that is in all answer files for some reason
		return numOfActions+((char)13+"\n")+actions;
	}
	
	public static void main(String[] args) throws Exception {
		Tester t = new Tester(Problem3_Automation_New.class, "src/Regional_2018/Problem3_Tests");
		t.test(1, 10);
		t.printTestingTimes();
		
//		for (int i=19; i<35; i++) {
//			Timer timer = new Timer();
//			timer.start();
//			Problem3_Automation_New p3 = new Problem3_Automation_New("src/Regional_2018/Problem3_Tests/"+i);
//			timer.stop();
//			System.out.println(i+";"+p3.n+";"+p3.m+";"+timer.time());
//		}
	}

	@Override
	public void runOnFailed(String filename, int testNumber, String answer, String rightAnswer) {
		char[]answerArray = answer.toCharArray();
		for (int c : answerArray) System.out.print(c+" ");
		System.out.println();
		char[]rightAnswerArray = rightAnswer.toCharArray();
		for (int c : rightAnswerArray) System.out.print(c+" ");
		System.out.println();
		
		//System.out.println(answer+"\n"+rightAnswer+"\n"+answer.equals(rightAnswer));
	}

	@Override
	public void runOnTimeFailed(String filename, int testNumber, String answer, String rightAnswer) {
		System.out.println(answer.split("\n")[0]);
	}

	@Override
	public void runOnPassed(String filename, int testNumber, String answer, String rightAnswer) {
		// TODO Auto-generated method stub
		
	}
}
