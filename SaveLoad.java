import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class SaveLoad 
{
	private static int PROPERTY_KEY = 0;
	private static String BGND_KEY = "background";
	private static int BGND_NUM_PROPERTIES = 4;
	private static int BGND_NAME = 1;
	private static int BGND_COL = 2;
	private static int BGND_ROW = 3;

	private static String MINER_KEY = "miner";
	private static int MINER_NUM_PROPERTIES = 7;
	private static int MINER_NAME = 1;
	private static int MINER_LIMIT = 4;
	private static int MINER_COL = 2;
	private static int MINER_ROW = 3;
	private static int MINER_RATE = 5;
	private static int MINER_ANIMATION_RATE = 6;

	private static String OBSTACLE_KEY = "obstacle";
	private static int OBSTACLE_NUM_PROPERTIES = 4;
	private static int OBSTACLE_NAME = 1;
	private static int OBSTACLE_COL = 2;
	private static int OBSTACLE_ROW = 3;
	
	private static String ORE_KEY = "ore";
	private static int ORE_NUM_PROPERTIES = 5;
	private static int ORE_NAME = 1;
	private static int ORE_COL = 2;
	private static int ORE_ROW = 3;
	private static int ORE_RATE = 4;

	private static String SMITH_KEY = "blacksmith";
	private static int SMITH_NUM_PROPERTIES = 7;
	private static int SMITH_NAME = 1;
	private static int SMITH_COL = 2;
	private static int SMITH_ROW = 3;
	private static int SMITH_LIMIT = 4;
	private static int SMITH_RATE = 5;
	private static int SMITH_REACH = 6;

	private static String VEIN_KEY ="vein";
	private static int VEIN_NUM_PROPERTIES = 6;
	private static int VEIN_NAME = 1;
	private static int VEIN_RATE = 4;
	private static int VEIN_COL = 2;
	private static int VEIN_ROW = 3;
	private static int VEIN_REACH = 5;
	
	public static void loadWorld(WorldModel world, File f)
	{
		try
		{
			Scanner in = new Scanner(f);
			while(in.hasNextLine())
			{
				String [] properties = in.nextLine().split("\\s");
				if(properties[PROPERTY_KEY].equals(BGND_KEY))
				{
					addBackground(world, properties);
				}
				else
				{
					addEntity(world, properties);
				}
				
			}
		}
		catch(FileNotFoundException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	private static void addBackground(WorldModel world, String [] properties)
	{
		Point p = new Point(Integer.parseInt(properties[BGND_COL]), Integer.parseInt(properties[BGND_ROW]));
		String name = properties[BGND_NAME];
		Background b = new Background(name);
		world.setBackground(p, b);
	}
	
	private static void addEntity(WorldModel world, String [] properties)
	{
		Entity new_entity = createFromProperties(properties);
		world.addEntity((Subject) new_entity);
	}
	
	private static Entity createFromProperties(String [] properties)
	{
		String key = properties[PROPERTY_KEY];
		if(key.equals(MINER_KEY))
		{
			Entity e = createMiner(properties);
			return e;
		}
		else if(key.equals(VEIN_KEY))
		{
			Entity e = createVein(properties);
			return e;
		}
		else if(key.equals(ORE_KEY))
		{
			Entity e = createOre(properties);
			return e;
		}
		else if(key.equals(SMITH_KEY))
		{
			Entity e = createSmith(properties);
			return e;
		}
		else if(key.equals(OBSTACLE_KEY))
		{
			Entity e = createObstacle(properties);
			return e;
		}
		return null;
	}
	
	private static Miner createMiner(String [] properties)
	{
		Point p = new Point(Integer.parseInt(properties[MINER_COL]), 
				Integer.parseInt(properties[MINER_ROW]));
		Miner m = new MinerNotFull(properties[MINER_NAME], p, 
				Integer.parseInt(properties[MINER_RATE]),
				Integer.parseInt(properties[MINER_ANIMATION_RATE]),
				Integer.parseInt(properties[MINER_LIMIT]));	
		return m;
	}
	
	private static Vein createVein(String [] properties)
	{
		Point p = new Point(Integer.parseInt(properties[VEIN_COL]), 
				Integer.parseInt(properties[VEIN_ROW]));
		Vein v = new Vein(properties[VEIN_NAME], p, 
				Integer.parseInt(properties[VEIN_RATE]));
		return v;
	}
	
	private static Ore createOre(String[] properties)
	{
		Point p = new Point(Integer.parseInt(properties[ORE_COL]), 
				Integer.parseInt(properties[ORE_ROW]));
		Ore o = new Ore(properties[ORE_NAME], p);
		return o;
	}
	
	private static Blacksmith createSmith(String [] properties)
	{
		Point p = new Point(Integer.parseInt(properties[SMITH_COL]), 
				Integer.parseInt(properties[SMITH_ROW]));
		Blacksmith b = new Blacksmith(properties[SMITH_NAME], p,
				Integer.parseInt(properties[SMITH_RATE]),
				Integer.parseInt(properties[SMITH_LIMIT]));
		return b;
	}
	
	private static Obstacle createObstacle(String [] properties)
	{
		Point p = new Point(Integer.parseInt(properties[OBSTACLE_COL]), 
				Integer.parseInt(properties[OBSTACLE_ROW]));
		Obstacle ob = new Obstacle(properties[OBSTACLE_NAME], p);
		return ob;
	}
}
