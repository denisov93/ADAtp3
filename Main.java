import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] readIt = getSplited(input);
        int people = Integer.parseInt(readIt[0]);
        String source = readIt[1];
        String target = readIt[2];
        int noise = Integer.parseInt(readIt[3]);
        
        Tale tale = new Tale(people,source,target,noise);
        
        while(people-->0) {
        	tale.addPerson(getSplited(input));
        }
        
        int friendships = Integer.parseInt(getSplited(input)[0]);
        
        while(friendships-->0) {
        	tale.addFriendship(getSplited(input));
        }
        
        
        
        //TODO
        
        result(tale.result());
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
