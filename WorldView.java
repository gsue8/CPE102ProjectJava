import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class WorldView extends PApplet
{
	File f = new File("gaia.txt");
	private final int BGND_COLOR = color(220, 230, 245);
	private static int num_rows = 30;
	private static int num_cols = 40;
	private WorldModel world = new WorldModel(num_rows, num_cols);
	private PImage grass;
	private PImage blacksmith;
	private PImage ore;
	private PImage rock;
	private PImage vein;
	private PImage obstacle;
	private List<PImage> minerimgs;
	private List<PImage> blobimgs;
	private List<PImage> quakeimgs;
	private long next_miner_time;
	private int current_img;
	private int shift_horiz = 0;
	private int shift_vert = 0;
	private String g = "grass";
	private String r = "rocks";
	private static final int COLOR_MASK = 0xffffff;
			
	//private Point p = new Point(3,10);
	//private Miner m = new Miner("bob", p, 5, 150, 1);
	//private int colhigh = 20;
	//private int collow = 0;
	//private int rowhigh = 15;
	
	
	
	
	public void setup()
	   {
	      size(640,480);
	      background(BGND_COLOR);
	      SaveLoad.loadWorld(world, f);
	      /*Point p = new Point(2, 2);
	      if(world.getBackground(p) != null)
	      {
	    	  System.out.println("true");
	      }*/
	      
	      minerimgs = new ArrayList<PImage>();
	      minerimgs.add(loadImage("miner1.bmp"));
	      minerimgs.add(loadImage("miner2.bmp"));
	      minerimgs.add(loadImage("miner3.bmp"));
	      minerimgs.add(loadImage("miner4.bmp"));
	      minerimgs.add(loadImage("miner5.bmp"));
	      
	      grass = loadImage("grass.bmp");
	      blobimgs = new ArrayList<PImage>();
	      blobimgs.add(loadImage("blob1.bmp"));
	      blobimgs.add(loadImage("blob2.bmp"));
	      blobimgs.add(loadImage("blob3.bmp"));
	      blobimgs.add(loadImage("blob4.bmp"));
	      blobimgs.add(loadImage("blob5.bmp"));
	      blobimgs.add(loadImage("blob6.bmp"));
	      blobimgs.add(loadImage("blob7.bmp"));
	      blobimgs.add(loadImage("blob8.bmp"));
	      blobimgs.add(loadImage("blob9.bmp"));
	      blobimgs.add(loadImage("blob10.bmp"));
	      blobimgs.add(loadImage("blob11.bmp"));
	      blobimgs.add(loadImage("blob12.bmp"));
	      quakeimgs = new ArrayList<PImage>();
	      quakeimgs.add(loadImage("quake1.bmp"));
	      quakeimgs.add(loadImage("quake2.bmp"));
	      quakeimgs.add(loadImage("quake3.bmp"));
	      quakeimgs.add(loadImage("quake4.bmp"));
	      quakeimgs.add(loadImage("quake5.bmp"));
	      quakeimgs.add(loadImage("quake6.bmp"));
	      ore = loadImage("ore.bmp");
	      rock = loadImage("rock.bmp");
	      vein = loadImage("vein.bmp");
	      obstacle = loadImage("obstacle.bmp");
	      
	      blacksmith = loadImage("blacksmith.bmp");
	      
	     /** for(int a = 0; a < num_cols; a++)
	      {
	    	  for(int b = 0; b < num_rows; b++)
	    	  {
	    		  Point bgpt = new Point(a, b);
	    		  world.setBackground(bgpt, g);
	    	  }
	      }**/
	  //    world.addEntity(b);
	    //  world.addEntity(dude);
	      //world.addEntity(m);
	      current_img = 0;
	      next_miner_time = System.currentTimeMillis();
	   }
	private void next_image()
	{
		current_img = (current_img + 1) % minerimgs.size();
	}
	
	
	public void draw()
	{
		background(BGND_COLOR);
		long time = System.currentTimeMillis();
	      if (time >= next_miner_time)
	      {
	         next_image();
	         next_miner_time = time + 150;
	      }
	    /*Point p = new Point(3,3);
	    if(world.getBackground(p).getName().equals(g))
	    {
	    	image(grass, 3*32, 3*32);
	    }*/
		for(int i = 0 + shift_horiz; i < 20 + shift_horiz; i++)
		{
			for(int j = 0 + shift_vert; j < 15 + shift_vert; j++)
			{
				Point pt = new Point(i, j);
				if(world.getBackground(pt).getName().equals(g))
				{
					image(grass, (i-shift_horiz)*32, (j-shift_vert)*32);
				}
				else if(world.getBackground(pt).getName().equals(r))
				{
					image(rock, (i-shift_horiz)*32, (j-shift_vert)*32);
				} 
				
				if(world.getOccupant(pt) instanceof Blacksmith)
				{
					image(blacksmith, (i-shift_horiz)*32, (j-shift_vert)*32);
				}
				else if(world.getOccupant(pt) instanceof Miner)
				{
					//Miner m = (Miner) world.getOccupant(pt);
					image(minerimgs.get(current_img), (i-shift_horiz)*32, (j-shift_vert)*32);

				}
				else if(world.getOccupant(pt) instanceof Ore)
				{
					//Ore o = (Ore) world.getOccupant(pt);
					image(ore, (i-shift_horiz)*32, (j-shift_vert)*32);
				}
				else if(world.getOccupant(pt) instanceof OreBlob)
				{
					//OreBlob ob = (OreBlob) world.getOccupant(pt);
					image(blobimgs.get(current_img), (i-shift_horiz)*32, (j-shift_vert)*32);
				}
				else if(world.getOccupant(pt) instanceof Vein)
				{
					//Vein v = (Vein) world.getOccupant(pt);
					image(vein, (i-shift_horiz)*32, (j-shift_vert)*32);
				}
				else if (world.getOccupant(pt) instanceof Obstacle)
				{
					image(obstacle, (i-shift_horiz)*32, (j-shift_vert)*32);
				}
			}
		}
	}
	
	public void keyPressed()
	{
		switch(key)
		{
			case'a':
			//shift view right aka go left
				if(shift_horiz > 0)
				{
					shift_horiz = shift_horiz - 1;
					//System.out.println(shift_horiz);
				}
				break;
			case 'd':
			//shift view left aka go to the right
				if(shift_horiz < num_cols-20)
				{
					shift_horiz = shift_horiz + 1;
					//System.out.println(shift_horiz);
				}
				break;
			case 'w':
			//shift view down aka go up
				if(shift_vert > 0)
				{
					shift_vert = shift_vert -1;
				}
				break;
			case 's':
			//shift view up aka go down
				if(shift_vert < num_rows -15)
				{
					shift_vert = shift_vert + 1;
				}
		}
	}

public static void main(String[] args)
	{
	      PApplet.main("WorldView.java");
	}
}
