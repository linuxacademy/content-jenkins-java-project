import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RectangleTest {
    Rectangle tester=new Rectangle(5,6);
    
    @Test
    public void testGetArea() {
    assertEquals("Success",tester.getArea(),30);
    }
 
    @Test
    public void testGetPerimeter() {
    assertEquals("This Step is Failed ",tester.getPerimeter(),30);
    }

}
