
public class MinerFull
   extends Miner
{
  // private int resource_count;
  
   public MinerFull(String name, Point p, int r, int ani_r, int r_Lim)
   {
      super(name, p, r, ani_r, r_Lim);
      this.resource_count = r_Lim;
   }

   public void setResourceCount(int n)
   {
      this.resource_count = n;
   }

   public int getResourceCount()
   {
      return this.resource_count;
   }
}