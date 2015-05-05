public class Subject
   extends Entity
{
   private Point position;
   
   public Subject(String name, Point p)
   {
      super(name);
      this.position = p;
   }

   
   public Point getPosition()
   {
      return this.position;
   }
     
   public void setPosition(Point setP)
   {
      this.position = setP;
   }
}
