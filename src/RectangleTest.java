import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RectangleTest {
  Rectangle myRectangle = new Rectangle(5,6);

  @Test
  public void testGetArea() {
    assertEquals(myRectangle.getArea(), 30);
  }

  @Test
  public void testGetPerimeter() {
    assertEquals(myRectangle.getPerimeter(), 22);
  }

  @Test
  public void testLength() {
    assertEquals(myRectangle.length, 5);
  }

  @Test
  public void testWidth() {
    assertEquals(myRectangle.width, 6);
  }
}
