import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Tale {

	Map<Integer,List<Integer>>friends;
	int[] degree;
	boolean[] selected;
	int[] length;
	int[] noise;
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
		this.noise = new int[P];
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
		length[S]=0;
		int[]a = {S,-1};
		connected.add(a);
		do {
			a = connected.remove();
			selected[a[0]] = true;
			exploreNode(a);
		}while(!connected.isEmpty());
	}

	private void exploreNode(int[] a) {
		List<Integer> ls = friends.get(a[0]);
		for(int i : ls) {   //System.out.println(i);
			if(!selected[i]) {
				int newL = 0;
					if(a[0]==S) { //amigo de source		
						newL = 0;
						noise[i] = degree[a[0]];
					}
					
					else {
						if(a[1]==S) {//amigo de amigo de source
							newL = degree[a[0]];
							noise[i] = degree[a[1]]*N+degree[a[0]];							
						}
						else { //sem cunha
							
								noise[i] = degree[a[1]]*noise[a[0]]+degree[a[0]];
								newL = noise[a[0]] + degree[a[0]] ;
							
						}
					}
				
				if(newL < length[i]) {
					boolean flag = length[i]<Integer.MAX_VALUE;
					
					//via[i]=a;
					if(!flag) {
						length[i] = newL;
						int[]b = {i,a[0]};
						connected.add(b);
					}
					else {
						length[i] = newL;
						int[]b = {i,a[0]};
						int[] f = connected.remove();
						if(i!=f[0])System.out.println("Isto é diferente "+i+" de  "+f[0]);
						System.out.println("ponho "+i);
						connected.add(b);
					}
					
				}
			}
		}
	}
	
	
	
	
	
	
	
}
