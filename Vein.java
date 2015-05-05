public class Vein
   extends SubjectWRD
{
   public Vein(String name, Point p, int r)
   {
      super(name, p, r);
   }

   public String entityString()
   {
      return "vein " + this.getName() + " " + this.getPosition().xCoord()
         + " " + this.getPosition().yCoord() + " " + this.getRate()
         + " " + this.getResourceDistance();
   }
}
