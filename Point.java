
public class Point 
{
   private int x;
   private int y;

   public Point(int x, int y)
   {
      this.x = x;
      this.y = y;
   }
   
   public boolean adjacent(Point p2)
   {
     if(this.x == p2.x)
     { 
        if(Math.abs(this.y - p2.y) == 1) 
        {
           return true;
        }
     }
     else if(this.y == p2.y) 
     {
        if(Math.abs(this.x - p2.x) == 1)
        {
           return true;
        }
     }
     return false;
   }

   public double distance_sq(Point p2)
   {
      return (((this.x - p2.x) * (this.x - p2.x)) + 
         ((this.y - p2.y) * (this.y - p2.y)));
   }

   public int xCoord()
   {
      return this.x;
   }
 
   public int yCoord()
   {
      return this.y;
   }
}