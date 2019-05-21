import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Tale {

	Map<Integer,List<Integer>>friends;
	static final BigInteger W = BigInteger.valueOf(2).pow(45);
	static final BigInteger SI = BigInteger.valueOf(-1);
	BigInteger[] degree;
	boolean[] selected;
	BigInteger[] length;
	int[] via;
	Queue<InfoPiece> connected;
	int P;
	int S;
	int T;
	BigInteger N;
	public Tale(BigInteger[] degree,int people,int source,int target,int noise) {
		P = people;
		S = source;
		T = target;
		N = BigInteger.valueOf(noise);
		this.degree = degree;
		friends = new HashMap<>();
		selected = new boolean[P];
		length = new BigInteger[P];
		via = new int[P];

		connected = new PriorityQueue<InfoPiece>(P);
		fill();
	}
	
	private void fill() {
		for(int i = 0;i<P;i++) {
			friends.put(i, new LinkedList<Integer>());
			selected[i]=false;
			length[i] = SI;
		}
	}

	public void addFriendship(String[] splited) {
		int a = Integer.parseInt(splited[0]);
		int b = Integer.parseInt(splited[1]);
		friends.get(a).add(b);
		friends.get(b).add(a);
	}
	public BigInteger result() {
		dijkstra();
		
		return  length[T].remainder(W);
		
	}
	
	private void dijkstra() {
		addSourceFriends();
		InfoPiece a;

		do {
			a = connected.remove();
			selected[a.node] = true;
			BigInteger newL = getNoise(a.node);
			exploreNode(a, newL);
		}while(!connected.isEmpty() && !selected[T]);
	}

	private void addSourceFriends() {
		int pos = S;
		length[pos]=BigInteger.ZERO;
		via[pos]=S;
		List<Integer> ls = friends.get(S);
		for(Integer i : ls) {
			length[i]=BigInteger.ZERO;
			via[i]=S;
			connected.add(new InfoPiece(length[i], i));
		}
	}
	
	private void exploreNode(InfoPiece a, BigInteger newL) {
		List<Integer> ls = friends.get(a.node);
		if(selected[T])
			return;
		for(Integer i : ls)
			if(!selected[i]) {
				if(length[i].equals(SI)) {
					length[i] = newL;
					via[i]=a.node;
					connected.add(new InfoPiece(newL, i));	
				}
				else if(newL.compareTo(length[i]) < 0) {
					length[i] = newL;
					via[i]=a.node;
					connected.add(new InfoPiece(newL, i));
				}
			}
	}
	
	private BigInteger getNoise(int s) {
		return length[s].multiply(N).add(degree[s]);
	}
}
