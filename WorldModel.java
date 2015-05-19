
import java.util.List;
import java.util.ArrayList;
public class WorldModel
   
{
   private Background[][] bg;
   private Subject[][] entities;
   private int num_rows;
   private int num_cols;
   private List<Subject> entityList;

   //public WorldModel(int num_rows, int num_cols, Background background)
   public WorldModel(int num_rows, int num_cols)
   {
      this.num_rows = num_rows;
      this.num_cols = num_cols;
     this.bg = new Background[num_rows][num_cols];
      for(int i = 0; i < num_rows; i++)
      {
         for(int j = 0; j < num_cols; j++)
         {
            //this.bg[i][j] = background;
        	 this.bg[i][j] = null;
         }
      }
      this.entities = new Subject[num_rows][num_cols];   
      for(int a = 0; a < num_rows; a++)
      {
         for(int b = 0; b < num_cols; b++)
         {
            this.entities[a][b] = null;
         }
      } 
      this.entityList = new ArrayList<Subject>();
   }

   public boolean withinBounds(Point pt)
   {
	  if((pt.xCoord() >= 0) && (pt.xCoord() < this.num_cols) 
		   && (pt.yCoord() >= 0) && (pt.yCoord() < this.num_rows))
      {
         return true;
      }
      return false;
   }

   public void addEntity(Subject thing)
   {
      Point pt = thing.getPosition();
      if(this.withinBounds(pt))
      {
         this.entities[pt.yCoord()][pt.xCoord()] = thing;
      }
      this.entityList.add(thing);     
   }

   public boolean isOccupied(Point pt)
   {
      if(this.withinBounds(pt))
      {
         if(entities[pt.yCoord()][pt.xCoord()] != null)
         {
            return true;
         }
      }
      return false;
   }

   public void setBackground(Point pt, Background bgnd)
   {
      if(this.withinBounds(pt))
      {
         this.bg[pt.yCoord()][pt.xCoord()] = bgnd;
      }
   }
 
   public Background getBackground(Point pt)
   {
      if(this.withinBounds(pt))
      {
         return this.bg[pt.yCoord()][pt.xCoord()];
      }
      return null;
   }

   public Subject getOccupant(Point pt)
   {
      if(this.withinBounds(pt))
      {
         return this.entities[pt.yCoord()][pt.xCoord()];
      }
      return null;
   }
  
   public void removeEntityAt(Point pt)
   {
      if(this.withinBounds(pt))
      {
         if(this.entities[pt.yCoord()][pt.xCoord()] != null)
         {
            this.entities[pt.yCoord()][pt.xCoord()] = null;
         }
      }
   }

   public void removeEntity(Subject thing)
   {
      removeEntityAt(thing.getPosition());
   }

   public void moveEntity(Subject thing, Point pt)
   {
      if(this.withinBounds(pt))
      {
         Point oldpt = thing.getPosition();
         entities[oldpt.yCoord()][oldpt.xCoord()] = null;
         thing.setPosition(pt);
         entities[pt.yCoord()][pt.xCoord()] = thing;        
      }
   }   
  
   public List<Subject> getEntities()
   {
      return this.entityList;
   }
  
   public static int sign(int x)
   {
      if(x < 0)
      {
         return -1;
      }
      else if(x > 0)
      {
         return 1;
      }
      else
      {
         return 0;
      }
   }

   public Point nextPosition(Point entpt, Point destpt)
   {
      int horiz = sign(destpt.xCoord() - entpt.xCoord());
      Point newpt = new Point(entpt.xCoord() + horiz, entpt.yCoord());
      if((horiz == 0) || (this.isOccupied(newpt)))
      {
         int vert = sign(destpt.yCoord() - entpt.yCoord());
         newpt = new Point(entpt.xCoord(), entpt.yCoord());
         if(vert == 0 || (this.isOccupied(newpt)))
         {
            newpt = new Point(entpt.xCoord(), entpt.yCoord());
         }
      }
      return newpt;
   }

   public Point blobNextPostion(Point entpt, Point destpt)
   {    
      int horiz = sign(destpt.xCoord() - entpt.xCoord());
      Point newpt = new Point(entpt.xCoord() + horiz, entpt.yCoord());
      if((horiz == 0) || (this.isOccupied(newpt)) && (this.getOccupant(newpt)
         instanceof Ore))
      {
         int vert = sign(destpt.yCoord() - entpt.yCoord());
         newpt = new Point(entpt.xCoord(), entpt.yCoord());
         if(vert == 0 || (this.isOccupied(newpt)) && !(this.getOccupant(newpt)
            instanceof Ore))
         {
            newpt = new Point(entpt.xCoord(), entpt.yCoord());
         }
      }
      return newpt;
   }
   
   public boolean minerToOre(Miner m, Ore ore)
   {
	   Point m_pt = m.getPosition();
	   Point ore_pt = ore.getPosition();
	   if(m_pt.adjacent(ore_pt))
	   {
		   m.setResourceCount(1 + m.getResourceCount());
		   this.removeEntity(ore);
		   return true;
	   }
	   else
	   {
		   Point new_pt = this.nextPosition(m_pt, ore_pt);
		   this.moveEntity(m, new_pt);
		   return false;
	   }
   }
   
   public boolean minerToSmith(Miner m, Blacksmith s)
   {
	   Point m_pt = m.getPosition();
	   Point s_pt = s.getPosition();
	   if(m_pt.adjacent(s_pt))
	   {
		   s.setResourceCount(s.getResourceCount() + m.getResourceCount());
		   m.setResourceCount(0);
		   return true;
	   }
	   else
	   {
		   Point new_pt = this.nextPosition(m_pt, s_pt);
		   this.moveEntity(m, new_pt);
		   return false;
	   }
   }
   
   public boolean blobToVein(OreBlob b, Vein v)
   {
	   Point b_pt = b.getPosition();
	   Point v_pt = v.getPosition();
	   if(b_pt.adjacent(v_pt))
	   {
		   this.removeEntity(v);
		   return true;
	   }
	   else
	   {
		   Point new_pt = this.nextPosition(b_pt, v_pt);
		   Subject oldent = this.getOccupant(new_pt);
		   if(oldent instanceof Ore)
		   {
			   this.removeEntity(oldent);
		   }
		   this.moveEntity(b, new_pt);
		   return false;
	   }
   }
   
   
}