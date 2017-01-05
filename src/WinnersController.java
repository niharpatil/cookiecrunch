import java.io.*;

public class WinnersController {
	private FileOutputStream winners;
	private BufferedWriter bw;
	private static final String FILENAME = "src/winners.txt";
	
	public void addWinner(String username, int level){
		try {
			winners = new FileOutputStream(FILENAME, true);
			bw = new BufferedWriter(new OutputStreamWriter(winners));
			bw.write(username + " completed level " + Integer.toString(level));
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
