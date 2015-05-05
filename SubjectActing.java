public class SubjectActing
   extends Subject
{
   private int rate;
   
   public SubjectActing(String name, Point p, int r)
   {
      super(name, p);
      this.rate = r;
   }

   public int getRate()
   {
      return this.rate;
   }
}
