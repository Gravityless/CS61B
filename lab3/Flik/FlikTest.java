import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void testFlik(){
        assertEquals(true, Flik.isSameNumber(128, 128));
    }
}
