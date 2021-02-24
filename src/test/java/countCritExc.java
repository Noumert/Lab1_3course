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
                                    new IllegalArgumentException()}, 5,0},
                    {new Exception[]
                            {new NullPointerException(),
                                    new ArithmeticException(),
                                    new ClassCastException(),
                                    new NumberFormatException(),
                                    new IllegalArgumentException()}, 3,2},
                    {new Exception[]
                            {new ClassCastException(),
                                    new ArithmeticException(),
                                    new ClassCastException(),
                                    new ArithmeticException(),
                                    new IllegalArgumentException()}, 1,4},
                    {new Exception[]
                            {new ClassCastException(),
                                    new ArithmeticException(),
                                    new ClassCastException(),
                                    new ArithmeticException(),
                                    new ClassCastException()}, 0,5}
            });
        }

        private Exception[] input;

        private int expected1;

        private int expected2;

        public countCritExc(Exception[] input, int expected1, int expected2) {
            this.input = input;
            this.expected1 = expected1;
            this.expected2 = expected2;
        }

    @Test
    public void countCritExcTests() {
        ExceptionManager exceptionManager = new ExceptionManager();
        for (Exception e: input) {
            exceptionManager.countCritExc(e);
        }
        assertTrue(expected1 == exceptionManager.getCritExcCount() && expected2 == exceptionManager.getNoCritExcCount());
//        assertEquals(expected, exceptionManager.getCritExcCount());
    }
}