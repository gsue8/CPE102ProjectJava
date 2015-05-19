
public class Ore
   extends Subject
{
   private int rate;

   public Ore(String name, Point p)
   {
      super(name, p);
      this.rate = 5000;
   }
   
   public int getRate()
   {
      return this.rate;
   }

   public String entityString()
   {
      return "ore " + this.getName() + " " + this.getPosition().xCoord() 
         + " " + this.getPosition().yCoord() + " " + this.getRate();
   }   
}