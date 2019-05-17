package datastructures;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

@SuppressWarnings("rawtypes")
public class AdaptMinHeap implements AdaptMinPriQueue{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<Entry<Integer,Integer>> map = new LinkedList<Entry<Integer,Integer>>();
	
	public boolean isEmpty() {
		return size()==0;
	}

	public int size() {
		return map.size();
	}

	public Entry minEntry() throws EmptyQueueException {
		
		return null;
	}

	public void insert(Comparable key, Object value) {
		
	}

	public Entry removeMin() throws EmptyQueueException {
		return null;
	}

	public Comparable decreaseKey(Object value, Comparable newKey) throws InvalidKeyException {
		
		return null;
	}

}
