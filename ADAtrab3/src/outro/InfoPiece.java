package outro;


public class InfoPiece implements Comparable<InfoPiece>{

	long length;
	int node;
	
	public InfoPiece(long length, int node) {
		this.length = length;
		this.node = node;
	}
	

	public int compareTo(InfoPiece arg0) {
		return (this.length==arg0.length) ? 0 : this.length<arg0.length ? -1 : 0;
	}

}
