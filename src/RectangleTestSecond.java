import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RectangleTestSecond {
    Rectangle tester=new Rectangle(9,10);
    
   @Test
   public void testGetLength() {
      System.out.println("Inside testGetLength of RectangleTestSecond ");
      assertEquals("Checking Length",9,tester.length);
      }
 
  @Test
  public void testGetWidth() {
      System.out.println("Inside testGetWidth of RectangleTestSecond ");
      assertEquals("Checking Width",10,tester.width);
  }
} 
