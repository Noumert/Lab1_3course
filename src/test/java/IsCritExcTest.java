import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class IsCritExcTest {
    @Parameterized.Parameters
        public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new NullPointerException(), true}, {new NumberFormatException(), true},
                {new IllegalArgumentException(), true}, {new ClassCastException(), false}, {new ArithmeticException(), false}
        });
    }

    private Exception input;

    private boolean expected;

    public IsCritExcTest(Exception input, boolean expected) {
        this.input = input;
        this.expected = expected;
    }


    @Test
    public void IsCritExcTests() {
        ExceptionManager exceptionManager = new ExceptionManager();
        assertEquals(expected, exceptionManager.isCritExc(input));
    }
}