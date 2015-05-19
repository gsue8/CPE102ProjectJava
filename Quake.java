
public class Quake
   extends Subject
{
   private int animation_rate;

   public Quake(String name, Point p, int ani_r)
   {
      super(name, p);
      this.animation_rate = ani_r;
   }

   public int getAnimationRate()
   {
      return this.animation_rate;
   }
}