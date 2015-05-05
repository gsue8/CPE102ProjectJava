public class Miner
   extends SubjectMobile
{
   private int resource_limit;
  

   public Miner(String name, Point p, int r, int ani_r, int r_lim)
   {
      super(name, p, r, ani_r);
      this.resource_limit = r_lim;
   }

   public int getResourceLimit()
   {
      return this.resource_limit;
   }
}
