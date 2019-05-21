import java.math.BigInteger;

public class InfoPiece implements Comparable<InfoPiece>{

	BigInteger length;
	int node;
	
	public InfoPiece(BigInteger length, int node) {
		this.length = length;
		this.node = node;
	}
	

	public int compareTo(InfoPiece arg0) {
		return length.compareTo(arg0.length);
	}

}
