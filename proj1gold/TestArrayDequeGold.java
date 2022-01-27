import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testRandom() {
        ArrayDequeSolution<Integer> E = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> A = new StudentArrayDeque<>();
        String errorMessage = "";

        for (int i=0; i<50; i++) {
            double random = StdRandom.uniform();

            if (random < 0.5) {
                E.addFirst(i);
                A.addFirst(i);
                errorMessage += ("addFirst(" + i + ")\n");
            } else {
                E.addLast(i);
                A.addLast(i);
                errorMessage += ("addLast(" + i + ")\n");
            }
        }

        for (int i=0; i<50; i++) {
            double random = StdRandom.uniform();
            if (random < 0.5) {
                errorMessage += ("removeFirst()\n");
                assertEquals(errorMessage, E.removeFirst(), A.removeFirst());
            } else {
                errorMessage += ("removeLast()\n");
                assertEquals(errorMessage, E.removeLast(), A.removeLast());
            }
        }
    }
}
