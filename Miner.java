
public class Miner
   extends SubjectMobile
{
   private int resource_limit;
   protected int resource_count;
  

   public Miner(String name, Point p, int r, int ani_r, int r_lim)
   {
      super(name, p, r, ani_r);
      this.resource_limit = r_lim;
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