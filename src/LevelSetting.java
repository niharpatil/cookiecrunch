import java.io.*;
import java.util.LinkedList;

public class LevelSetting {
	private Location targetLocation;
	private int targetSize = 0;
	private int levelID;
	private LinkedList<Location> obstacleLocations;
	private BufferedReader br;
	private FileInputStream levelFile;
	private InputStreamReader in;
	private LinkedList<Integer> levels;
	private String levelFileLocation = "src/levelSettings.txt";
	
	public LevelSetting(int levelID) throws Exception, BadLevelException{
		String line;
		//get level Setting
		obstacleLocations = new LinkedList<Location>();
		levelFile = new FileInputStream(levelFileLocation);
		in = new InputStreamReader(levelFile);
		br = new BufferedReader(in);
		boolean levelExists = false;
		while((line = br.readLine()) != null){
			if(line.contains("levelID:"+Integer.toString(levelID))){
				levelExists = true;
				this.levelID = levelID;
				while((line = br.readLine()) != null){
					if(line.contains("targetLocation:")){
						String[] location = line.substring(15, line.length()).split(",");
						int loc_x = new Integer(location[0]);
						int loc_y = new Integer(location[1]);
						targetLocation = new Location(loc_x, loc_y);
					} else if(line.contains("targetSize:")){
						String size = line.substring(11,line.length());
						int tgtSize = new Integer(size);
						targetSize = tgtSize;
					} else if (line.contains("obstacleLocation:")){
						String[] location = line.substring(17, line.length()).split(",");
						int loc_x = new Integer(location[0]);
						int loc_y = new Integer(location[1]);
						obstacleLocations.add(new Location(loc_x, loc_y));
					} else if (line.contains("END")){
						break;
					}
				}
				if(targetSize == 0 || targetLocation == null){
					throw new BadLevelException();
				}
				break;
			}
		}
		if(!levelExists){
			System.out.println("Enter a valid level number");
			throw new BadLevelException();
		}
		
	}
	
	public LevelSetting() {
		// TODO Auto-generated constructor stub
	}

	public Location getTargetLocation() {
		return targetLocation;
	}

	public void setTargetLocation(Location targetLocation) {
		this.targetLocation = targetLocation;
	}

	public int getTargetSize() {
		return targetSize;
	}

	public void setTargetSize(int targetSize) {
		this.targetSize = targetSize;
	}

	public LinkedList<Location> getObstacleLocations() {
		return obstacleLocations;
	}

	public void setObstacleLocations(LinkedList<Location> obstacleLocations) {
		this.obstacleLocations = obstacleLocations;
	}

	public int getLevelID() {
		return levelID;
	}
	
	public String getSource(){
		return this.levelFileLocation;
	}
	
	public LinkedList<Integer> getLevels(){
		String line;

		//get levels
		levels = new LinkedList<Integer>();
		try {
			levelFile = new FileInputStream(levelFileLocation);
			in = new InputStreamReader(levelFile);
			br = new BufferedReader(in);
			while((line = br.readLine()) != null){
				if(line.contains("levelID:")){
					int level = new Integer(line.substring(8));
					levels.add(level);
				}
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.levels;
	}
}
