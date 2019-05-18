import java.util.Comparator;

public class CompareLengths implements Comparator<int[]> {
	
	public int compare(int[] arg0, int[] arg1) {
			if(arg0[1] == arg1[1])
				return 0;
			if(arg0[1] > arg1[1])
				return 1;
			else return -1;
	
	}
}