public class SubjectWRD
   extends SubjectActing
{
   private int resource_distance;

   public SubjectWRD(String name, Point p, int r)
   {
      super(name, p, r);
      this.resource_distance = 1;
   }

   public int getResourceDistance()
   {
      return this.resource_distance;
   }
}
