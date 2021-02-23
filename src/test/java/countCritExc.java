import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class countCritExc {
        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new Exception[]
                            {new NullPointerException(),
                                    new NumberFormatException(),
                                    new IllegalArgumentException(),
                                    new NumberFormatException(),
                                    new IllegalArgumentException()}, 5},
                    {new Exception[]
                            {new NullPointerException(),
                                    new ArithmeticException(),
                                    new ClassCastException(),
                                    new NumberFormatException(),
                                    new IllegalArgumentException()}, 3},
                    {new Exception[]
                            {new ClassCastException(),
                                    new ArithmeticException(),
                                    new ClassCastException(),
                                    new ArithmeticException(),
                                    new IllegalArgumentException()}, 1},
                    {new Exception[]
                            {new ClassCastException(),
                                    new ArithmeticException(),
                                    new ClassCastException(),
                                    new ArithmeticException(),
                                    new ClassCastException()}, 0}
            });
        }

        private Exception[] input;

        private int expected;

        public countCritExc(Exception[] input, int expected) {
            this.input = input;
            this.expected = expected;
        }

    @Test
    public void countCritExcTests() {
        ExceptionManager exceptionManager = new ExceptionManager();
        for (Exception e: input) {
            exceptionManager.countCritExc(e);
        }
        assertEquals(expected, exceptionManager.getCritExcCount());
    }
}