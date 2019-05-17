import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Tale{
	
	Map<Integer, LinkedList<NodeAndDistance>> friendships;
	int P;
	int source;
	int target;
	int N;
	boolean[] selected;
	int[] length;
	int[] via;
	Queue<NodeAndDistance> connected;
	int counter;
	int[] degrees;
	
	@SuppressWarnings("unchecked")
	public Tale(int people,String source,String target,int noise) {
		counter = 0;
		P = people;
		this.source = Integer.parseInt(source); 
		this.target = Integer.parseInt(target);
		N = noise;
		friendships = new HashMap<>(P);
		selected = new boolean[P];
		length = new int[P];
		via = new int[P];
		connected = new PriorityQueue<NodeAndDistance>(P, new NodeComparator());
		
		degrees =new int[P];
		friendships.put(0, new LinkedList<NodeAndDistance>());
	}
	
	public void addPerson(String[] splited) {
		Integer degree = Integer.parseInt(splited[0]);
		degrees[counter] = degree;
		LinkedList<NodeAndDistance> ls = new LinkedList<NodeAndDistance>();
		friendships.put(counter, ls);
		
		length[counter]=Integer.MAX_VALUE;
		selected[counter]=false;
		counter++;
	}

	public void addFriendship(String[] splited) {
		Integer person = Integer.parseInt(splited[0]);
		int friend = Integer.parseInt(splited[1]);
		
		LinkedList<NodeAndDistance> ls = friendships.get(person);
		if(ls.size()!=0) {
		NodeAndDistance nd = ls.removeFirst();
		NodeAndDistance	newNode = new NodeAndDistance(person,friend);				
			ls.addFirst(nd);
			ls.addLast(newNode);
		}
		else {
			NodeAndDistance	newNode = new NodeAndDistance(person,friend);
			ls.add(newNode);
		}
	}
	
	public void dikstra() {
		length[source] = 0;
		via[source] = source;
		NodeAndDistance node = friendships.get(source).getFirst(); 
		connected.add(node);
		do {
			node = connected.remove();
			int to = node.getNodeTo();
			selected[to] = true;
			exploreNode(node);
			
		}while(!connected.isEmpty());
	}
	
	public void exploreNode(NodeAndDistance node) {
		int to = node.getNodeTo();
		List<NodeAndDistance>ls = friendships.get(to);
		for(NodeAndDistance n : ls) {
			int nD = n.getNodeTo();
			if(!selected[nD]) {
				int newLength = length[n.getNodeFrom()] + degrees[n.getNodeTo()];
				if(newLength<length[nD]) {
					boolean nodeIsInQueue = length[nD] < Integer.MAX_VALUE;
					length[nD] = newLength;
					via[nD]=node.getNodeFrom();
					if(nodeIsInQueue) {
						NodeAndDistance no = new NodeAndDistance(nD, to);
						no.setDintance(newLength);
						connected.add(no);
					}
					else {
						NodeAndDistance no = new NodeAndDistance(nD,to);
						connected.add(no);
					}
				}
			}
		}
	}
	
	public int[] result() {
		dikstra();
		
		
		return via;
	}
	
	
	
}
