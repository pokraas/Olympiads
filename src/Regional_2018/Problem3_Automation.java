package Regional_2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import testing.*;

public class Problem3_Automation implements Testable,TestingProcedures {
	
	private Scanner sc;
	private int n,m;
	private Node head;
	private int[] orders;
	private String answer="";
	private int k;
	
	private class Node{
		int data;
		Node next;
		Node(int data){
			this.data=data;
		}
		
		@SuppressWarnings("unused")
		void printSuccessors() {
			Node curr = this;
			System.out.printf("List starting from node %d: ",curr.data);
			while(curr.next!=null) {
				curr = curr.next;
				System.out.print(curr.data+" ");
			}
			System.out.print("\t");
		}
		
		void insertAfter(int data) {
			Node toInsert = new Node(data);
			toInsert.next = this.next;
			this.next = toInsert;
		}
	}
	
	private int removeFirstCard() {
		k++;
		int ret = head.next.data;
		Node second = head.next.next;
		head.next=second;
		System.out.print(ret+" ");
		return ret;
	}
	
	public Problem3_Automation(String filename) throws FileNotFoundException {
		sc = new Scanner(new File(filename));
		n = sc.nextInt();
		m = sc.nextInt();
		k=0;
		//filling orders array
		orders = new int[m];
		for (int i=0;i<m;i++) {
			orders[i]=sc.nextInt();
		}
		//filling the linked list
		head = new Node(-1);
		Node curr=head;
		for (int i=0;i<n;i++) {
			Node node = new Node(sc.nextInt());
			curr.next=node;
			curr=node;
		}
		//System.out.println(removeFirst()+" "+removeFirst());
		//head.printSuccessors();
		
	}
	
	private void robot() {
		int currentOrder = 0;
		boolean pred[] = new boolean[n+1];
		label:
		while (currentOrder<orders.length) {
			int card = removeFirstCard();
			if (card==orders[currentOrder]) currentOrder++;

			//deciding where to stick the card			
			int numPred=0;
			for (int i=currentOrder;i<orders.length;i++) {
				if (orders[i]==card) {
					head.insertAfter(card);
					answer+="1 ";
					continue label;
				}
				pred[orders[i]]=true;
				numPred++;
			}

			//sticking the card
			int position=1;
			Node curr=head;
			while(true) {
				curr = curr.next;
				position++;
				if (pred[curr.data]) {
					pred[curr.data]=false;
					numPred--;
				}
				if(numPred==0||curr.next==null) {
					numPred=0;
					curr.insertAfter(card);
					answer+=position+" ";
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Tester t = new Tester(Problem3_Automation.class,"src/Regional_2018/Problem3_Tests");
		t.test(2,2);
	}

	@Override
	public String getAnswer() {
		// TODO Auto-generated method stub
		robot();
		return k+"\n"+answer;
	}

	@Override
	public void runOnFailed(String filename, int testNumber, String answer, String rightAnswer) {
		// TODO Auto-generated method stub
		System.out.println("\nAnswer: "+answer+"\nRight answer: "+rightAnswer);
	}

	@Override
	public void runOnTimeFailed(String filename, int testNumber, String answer, String rightAnswer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runOnPassed(String filename, int testNumber, String answer, String rightAnswer) {
		// TODO Auto-generated method stub
		
	}

}
