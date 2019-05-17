import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Tale {

	HashMap<Integer,List<Integer>>friends;
	int[] degree;
	boolean[] selected;
	int[] length;
	int[] via;
	Queue<int[]> connected;
	int P;
	int S;
	int T;
	int N;
	public Tale(int[] degree,int people,int source,int target,int noise) {
		P = people;
		S = source;
		T = target;
		N = noise;
		this.degree = degree;
		friends = new HashMap<Integer,List<Integer>>();
		selected = new boolean[P];
		length = new int[P];
		via = new int[P];
		connected = new PriorityQueue<int[]>(P,new CompareLengths());
		for(int i = 0;i<P;i++) {
			friends.put(i, new LinkedList<Integer>());
			selected[i]=false;
			length[i] = Integer.MAX_VALUE;
		}
	}

	public void addFriendship(String[] splited) {
		int a = Integer.parseInt(splited[0]);
		int b = Integer.parseInt(splited[1]);
		friends.get(a).add(b);
		friends.get(b).add(a);
	}
	public int[] result() {
		dijkstra();
		
		return length;
		
	}
	
	private void dijkstra() {
		length[S]=degree[S];
		int[]a = {S,degree[S]};
		connected.add(a);
		do {
			a = connected.remove();
			selected[a[0]] = true;
			exploreNode(a[0]);
		}while(!connected.isEmpty());
	}

	private void exploreNode(int a) {
		List<Integer> ls = friends.get(a);
		for(int i : ls) {System.out.println(i);
			if(!selected[i]) {
				int newL = length[a] + degree[i];
				if(newL < length[i]) {
					boolean flag = length[i]<Integer.MAX_VALUE;
					length[i] = newL;
					//via[i]=a;
					if(!flag) {
						int[]b = {length[i],i};
						connected.add(b);
					}
					else {
						int[]b = {i,length[i]};
						connected.add(b);
					}
					
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
