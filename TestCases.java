import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.Before;

public class TestCases
{  
   private Point orept = new Point(1, 2);
   private Point minernfpt = new Point(2, 2);
   private Point veinpt = new Point(3, 2);
   private Point quakept = new Point(2, 0);
   private Point obstaclept = new Point(0, 0);
   private Point minerfpt = new Point(1, 0);
   private Point oreblobpt = new Point(1, 1);
   private Point blacksmithpt = new Point(2, 1);
   private Background background = new Background("poop");
   private Background background2 = new Background("electric boogaloo");
   private Ore ore = new Ore("iron", orept); 
   private MinerNotFull bob = new MinerNotFull("bob", minernfpt, 5, 10, 1); 
   private Vein veintest = new Vein("lode", veinpt, 5);
   private Quake quaketest = new Quake("richter 5", quakept, 4);
   //private Obstacle obstest = new Obstacle("huge boulder", obstaclept);
   //private MinerFull steve = new MinerFull("steve", minerfpt, 9, 3, 1);
   //private OreBlob blob = new OreBlob("hatless", oreblobpt, 7, 6);
   private Blacksmith tom = new Blacksmith("Thomas Davenport", blacksmithpt,
     2, 1); 
   private WorldModel testWorld = new WorldModel(3, 3, background);
 
   @Test
   public void testBackground()
   {
      assertEquals("poop", background.getName());
   }
  
   @Test
   public void testOre()
   {
      Point testOP = new Point(1, 2);
      assertEquals("iron", ore.getName());
      assertEquals(testOP.xCoord(), ore.getPosition().xCoord());
      assertEquals(testOP.yCoord(), ore.getPosition().yCoord());
      assertEquals(5000, ore.getRate());
      assertEquals("ore iron 1 2 5000", ore.entityString());
   }

   @Test
   public void testMinerNF()
   {
      assertEquals("bob", bob.getName());
      assertEquals(2, bob.getPosition().xCoord());
      assertEquals(2, bob.getPosition().yCoord());
      assertEquals(0, bob.getResourceCount());
      bob.setResourceCount(1);
      assertEquals(1, bob.getResourceCount());
      assertEquals("miner bob 2 2 1 5 10", bob.entityString()); 
   }

   @Test
   public void testVein()
   {
      assertEquals("vein lode 3 2 5 1", veintest.entityString());
   }

   @Test
   public void testBlacksmith()
   {
      assertEquals(0, tom.getResourceCount());
      tom.setResourceCount(5);
      assertEquals(5, tom.getResourceCount());
      assertEquals("Thomas Davenport", tom.getName());
   }   

   @Test
   public void testWorldModel()
   {
      Point pt = new Point(2, 2);
      Point newpt = new Point(2, 1);
      testWorld.addEntity(bob);
      assertTrue(testWorld.isOccupied(pt));
      assertEquals(bob, testWorld.getOccupant(pt));
      testWorld.moveEntity(bob, newpt);
      assertEquals(1, bob.getPosition().yCoord());
      assertEquals(2, bob.getPosition().xCoord());
      assertTrue(testWorld.isOccupied(newpt));
      assertFalse(testWorld.isOccupied(pt));
      assertEquals(bob, testWorld.getOccupant(newpt));
      testWorld.removeEntity(bob);
      assertFalse(testWorld.isOccupied(newpt));
      assertFalse(testWorld.isOccupied(pt));
      assertEquals(null, testWorld.getOccupant(pt));
      assertFalse(testWorld.withinBounds(veintest.getPosition()));
      assertEquals(background, testWorld.getBackground(pt));
      assertEquals(null, testWorld.getBackground(veintest.getPosition()));
      testWorld.setBackground(pt, background2);
      assertEquals(background2, testWorld.getBackground(pt));
   }

}

