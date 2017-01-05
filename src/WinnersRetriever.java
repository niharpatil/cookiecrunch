import java.io.*;
import java.util.LinkedList;
public class WinnersRetriever {
	private BufferedReader br;
	private FileInputStream levelFile;
	private InputStreamReader in;
	private String winnersFileLocation = "src/winners.txt";
	private LinkedList<String> winners;
	
	public WinnersRetriever(){
		winners = new LinkedList<String>();
		try {
			in = new InputStreamReader(new FileInputStream(winnersFileLocation));
			br = new BufferedReader(in);
			String line;
			while((line = br.readLine()) != null){
				winners.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public LinkedList<String> getWinners(){
		return this.winners;
	}

}
