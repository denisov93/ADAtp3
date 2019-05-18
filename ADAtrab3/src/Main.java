import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] readIt = getSplited(input);
        int people = Integer.parseInt(readIt[0]);
        int source = Integer.parseInt(readIt[1]);
        int target = Integer.parseInt(readIt[2]);
        int noise = Integer.parseInt(readIt[3]);
        
        int[] degree = new int[people];
        for(int i = 0; i<people; i++)
        	degree[i] = Integer.parseInt(getSplited(input)[0]);

        Tale tale = new Tale(degree, people, source, target, noise);
        
        int friendships = Integer.parseInt(getSplited(input)[0]);
        
        while(friendships-->0) {
        	tale.addFriendship(getSplited(input));
        }
        
        result(tale.result());
        //System.out.println(tale.result());
        input.close();
	}

    static private String[] getSplited(BufferedReader input) throws IOException{
        return input.readLine().split(" ");
    }
    
    static private void result(int[] result) {
    	for(int i: result)
    		System.out.println("Nodo-->"+i);
    }
}
