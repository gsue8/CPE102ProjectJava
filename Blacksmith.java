
public class Blacksmith
   extends SubjectWRD
{
   private int resource_count;
   private int resource_limit;
   public Blacksmith(String name, Point p, int r, int r_Lim)
   {
      super(name, p, r);
      this.resource_count = 0;
      this.resource_limit = r_Lim;
   }

   public void setResourceCount(int n)
   {
      this.resource_count = n;
   }

   public int getResourceCount()
   {
      return this.resource_count;
   }

   public int getResourceLimit()
   {
      return this.resource_limit;
   }
}