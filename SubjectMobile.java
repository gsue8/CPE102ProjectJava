public class SubjectMobile
   extends SubjectActing
{
   private int animation_rate;
   
   public SubjectMobile(String name, Point p, int r, int ani_r)
   {
      super(name, p, r);
      this.animation_rate = ani_r;
   }

   public int getAnimationRate()
   {
      return this.animation_rate;
   }
}
