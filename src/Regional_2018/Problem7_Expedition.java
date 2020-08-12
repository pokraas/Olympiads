package Regional_2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

import testing.Testable;
import testing.Tester;
import testing.TestingProcedures;

public class Problem7_Expedition implements Testable {
	
	private class Node implements Comparable<Node>{
		Node out;
		HashSet<Node> in = new HashSet<Node>();
		final int id;
		Node(int id){
			nodes[id] = this;
			this.id=id;
		}
		
		void addOut(int outId) {
			if (outId==-1) return;
			Node out = nodes[outId];
			if (out==null || this.out!=null) throw new IllegalArgumentException();
			this.out=out;
			out.in.add(this);
		}
		
		/** Remove references to this node from everywhere except for members */
		void destroy() {
			pq.remove(this);
			nodes[this.id]=null;
			for (Node n : this.in) n.out=null;
		}
		
		/** If a member's inDegree is 0, takes their node into the team,
		 * deletes its out node and repeats the process from out.out node,
		 * practically with a DFS */
		void takeMember() {
			//System.out.println("Starting to take members...");
			Node result = this;
			while (result!=null) {
				result = takeMember(result);
			}
		}
		
		Node takeMember(Node toAdd) {
			//System.out.println("called takeMember with argument "+toAdd);
			if (toAdd.inDegree()!=0) {
				//System.out.println("...exiting: toAdd.inDegree != 0");
				return null;
			}
			//take this member to the team
			//members.add(toAdd);
			totalMembers++;
			//System.out.println(toAdd+" "+totalMembers);
			toAdd.destroy();
			
			//remove this member's neighbor from the team
			if (toAdd.out==null) {
				//System.out.println("... exiting: toAdd.out==null");
				return null;
			}
			Node OUT = toAdd.out;
			toAdd.out.destroy();
			
			//check this member's neighbor's neighbor
			if (OUT.out==null || OUT.out==toAdd) {
				//System.out.println("... exiting: toAdd.out.out==null or ==toAdd");
				return null;
			}
			OUT.out.in.remove(OUT);
			//System.out.println();
			return OUT.out;
		}
		
		int inDegree() {
			return in.size();
		}
		
		@Override
		public int compareTo(Node other) {
			return ((Integer)other.inDegree()).compareTo(this.inDegree());
		}
		
		@Override
		public String toString() {
			String outString = "null";
			if (out != null) outString = out.id+"";
			return "("+id+" -> "+ outString+": indegree "+inDegree()+")";
		}
	}
	
	private int totalMembers=0;
	private Node[] nodes;
	private PriorityQueue<Node> pq;
	
	public Problem7_Expedition(String filename) {
		try {
			Scanner sc = new Scanner(new File(filename));
			int n = sc.nextInt();
			
			//create all vertices
			nodes = new Node[n+1];
			pq = new PriorityQueue<Node>();
			
			for (int id=1; id<=n; id++) {
				new Node(id);
			}
			
			//create all edges
			for (int id=1; id<=n; id++) {
				nodes[id].addOut(sc.nextInt());
			}
			
			
			//add all nodes to the pq
			for (int id=1; id<=n; id++) {
				pq.add(nodes[id]);
			}
			
			//remove members with indegree=0 from the pq (run takeMember for each of them)
			for (int id=n; id>0; id--) {
				if (nodes[id] == null) continue;
				nodes[id].takeMember();
			}
			
			//remove the first member (the member with the biggest indegree) from the pq,
			//takeMember for its neighbor (if needed)
			while (!pq.isEmpty()) {
				//System.out.println("Ходило в цикл");
				Node first = pq.poll();
				if (first.out==null) continue;
				first.destroy(); 
				first.out.in.remove(first);
				first.out.takeMember();
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	

	public static void main(String[] args) throws Exception {
//		Problem7_Expedition p = new Problem7_Expedition("src/Regional_2018/Problem7_Tests/03");
//		System.out.println(p.getAnswer()+" "+p.members);
		Tester t = new Tester(Problem7_Expedition.class,"src/Regional_2018/Problem7_Tests");
		t.test(1,60);
		t.printTestingTimes();
	}


	@Override
	public String getAnswer() {
		return Integer.toString(this.totalMembers);
	}


}
