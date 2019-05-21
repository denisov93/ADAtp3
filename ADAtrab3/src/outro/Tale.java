package outro;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Tale {

	Map<Integer,List<Integer>>friends;
	static final long W = (long)Math.pow(2 , 45);
	int[] degree;
	boolean[] selected;
	long[] length;
	int[] noise;
	int[] via;
	int[] steps;
	Queue<InfoPiece> connected;
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
		friends = new HashMap<>();
		selected = new boolean[P];
		length = new long[P];
		via = new int[P];
		steps = new int[P];
		this.noise = new int[P];
		connected = new PriorityQueue<InfoPiece>(P);
		fill();
	}
	
	private void fill() {
		for(int i = 0;i<P;i++) {
			friends.put(i, new LinkedList<Integer>());
			selected[i]=false;
			length[i] = Long.MAX_VALUE;
		}
	}

	public void addFriendship(String[] splited) {
		int a = Integer.parseInt(splited[0]);
		int b = Integer.parseInt(splited[1]);
		friends.get(a).add(b);
		friends.get(b).add(a);
	}
	public long result() {
		dijkstra();
		
		return length[T];
		
	}
	
	private void dijkstra() {
		length[S]=0;
		via[S]=S;
		steps[S]=0;
		InfoPiece a = new InfoPiece(0L,S);
		connected.add(a);
		do {
			a = connected.remove();
			selected[a.node] = true;
			long newL = getNoise(a.node, steps[a.node]+1);
			exploreNode(a, newL);
		}while(!connected.isEmpty() && !selected[T]);
	}

	private void exploreNode(InfoPiece a, long newL) {
		List<Integer> ls = friends.get(a.node);;
		if(selected[T])
			return;
		for(Integer i : ls)
			if(!selected[i]) {
				if(newL < length[i]) {
					length[i] = newL;
					via[i]=a.node;
					steps[i]=steps[a.node]+1;
					connected.add(new InfoPiece(newL, i));
				}
			}
	}
	
	private long getNoise(int s, int m) {
		long result = 0;
		int source = s;

		if(m<2)
			return 0;
		for(int i = m-1; i>0; i--) {
			result += degree[source] * Math.pow(N , (m-1-i)) % W;
			source = via[source];
		}
		return result;
	}
}
