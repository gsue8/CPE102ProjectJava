public class MinerNotFull
   extends Miner
{
   private int resource_count;

   public MinerNotFull(String name, Point p, int r, int ani_r, int r_lim)
   {
      super(name, p, r, ani_r, r_lim);
      this.resource_count = 0;
   }

   public void setResourceCount(int n)
   {
      this.resource_count = n;
   }

   public int getResourceCount()
   {
      return this.resource_count;
   }

   public String entityString()
   {
      return "miner " + this.getName() + " " + this.getPosition().xCoord()
         + " " + this.getPosition().yCoord() + " " + this.getResourceLimit() 
         + " " + this.getRate() + " " + this.getAnimationRate();
   }
}
