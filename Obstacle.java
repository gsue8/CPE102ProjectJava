public class Obstacle
   extends Subject
{
   public Obstacle(String name, Point p)
   {
      super(name, p);
   }

   public String entityString()
   {
      return "obstacle " + this.getName() + " " + this.getPosition().xCoord() 
         + " " + this.getPosition().yCoord();
   }
}
