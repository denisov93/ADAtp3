
public class NodeAndDistance{

	private int nodeFrom;
	private int distance;
	private int nodeTo;
	
	public NodeAndDistance(int nodeFrom, int nodeTo){
		setNodeFrom(nodeFrom);
		setNodeTo(nodeTo);
		setUnreacheable();
	}
	
	public void setUnreacheable() {
		this.distance = Integer.MAX_VALUE;
	}
	
	public int getNodeFrom() {
		return this.nodeFrom;
	}
	
	public int getNodeTo() {
		return this.nodeTo;
	}
	
	public int getDistance() {
		return this.distance;
	}
	
	public void setNodeFrom(int nodeFrom) {
		this.nodeFrom = nodeFrom;
	}
	
	public void setNodeTo(int nodeTo) {
		this.nodeTo = nodeTo;
	}
	
	public void setDintance(int distance) {
		this.distance = distance;
	}
	
}