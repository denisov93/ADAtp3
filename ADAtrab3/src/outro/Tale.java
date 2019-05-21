import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Tale {

	Map<Integer,List<Integer>>friends;
	static final BigInteger W = new BigInteger("2").pow(45);
	int[] degree;
	boolean[] selected;
	BigInteger[] length;
	int[] noise;
	int[] via;
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
		length = new BigInteger[P];
		via = new int[P];
		this.noise = new int[P];
		connected = new PriorityQueue<InfoPiece>(P);
		fill();
	}
	
	private void fill() {
		for(int i = 0;i<P;i++) {
			friends.put(i, new LinkedList<Integer>());
			selected[i]=false;
			length[i] = W;
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
		
		return length[T];
		
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
				if(newL.compareTo(length[i]) < 0) {
					length[i] = newL;
					via[i]=a.node;
					connected.add(new InfoPiece(newL, i));
				}
			}
	}
	
	private BigInteger getNoise(int s) {
		BigInteger result = BigInteger.ZERO;
		int source = s;  
		result = (length[source].multiply(BigInteger.valueOf(N)).add(BigInteger.valueOf(degree[source])))
				.mod(W);
		return result;
	}
}
