import java.util.Comparator;

public class NodeComparator implements Comparator<NodeAndDistance>{

	public int compare(NodeAndDistance arg0,NodeAndDistance arg1) {
		if(arg0.getNodeFrom() == arg1.getNodeFrom())
		{
			if(arg0.getNodeTo()==arg1.getNodeTo())
				return 0;
			if(arg0.getNodeTo() > arg1.getNodeTo())
				return 1;
			else return -1;
		}
		else if(arg0.getNodeFrom() > arg1.getNodeFrom())
		return 1;
		else return -1;
	}

}
