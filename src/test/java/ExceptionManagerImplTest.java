import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ExceptionManagerImplTest {

    @Mock
    JsonExcReader jsonExcReader;
    @Mock
    ServerManager serverManager;
    @InjectMocks
    ExceptionManagerImpl exceptionManagerImpl;

    @ParameterizedTest
    @MethodSource("exceptionProviderCheck")
    void isCritExcTests(Exception exception, boolean expected) {
        when(jsonExcReader.readFromFile(any()))
                .thenReturn(List.of("class java.lang.NullPointerException", "class java.lang.NumberFormatException", "class java.lang.IllegalArgumentException"));

        boolean actual = exceptionManagerImpl.isCritExc(exception);
        assertEquals(actual, expected);
    }

    private static Stream<Arguments> exceptionProviderCheck() {
        return Stream.of(
                Arguments.of(new NullPointerException(), true),
                Arguments.of(new NumberFormatException(), true),
                Arguments.of(new IllegalArgumentException(), true),
                Arguments.of(new ClassCastException(), false),
                Arguments.of(new ArithmeticException(), false)
        );
    }

    @ParameterizedTest
    @MethodSource("exceptionProviderCount")
    void countCritExcTests(Collection<Exception> exceptions, int expectedCrit, int expectedNoCrit, int expectedSend) {
        lenient().when(jsonExcReader.readFromFile(any()))
                .thenReturn(List.of("class java.lang.NullPointerException",
                        "class java.lang.NumberFormatException",
                        "class java.lang.IllegalArgumentException"));
        lenient().when(serverManager.toServer(any()))
                .thenReturn(true);

        for (Exception e : exceptions
        ) {
            exceptionManagerImpl.countCritExc(e);
        }
        assertAll(
                () -> assertEquals(expectedCrit, exceptionManagerImpl.getCritExcCount()),
                () -> assertEquals(expectedNoCrit, exceptionManagerImpl.getNoCritExcCount()),
                () -> assertEquals(expectedSend, exceptionManagerImpl.getSendCount())
        );
    }

    private static Stream<Arguments> exceptionProviderCount() {
        return Stream.of(
                Arguments.of(List.of(new NullPointerException(),
                        new NumberFormatException(),
                        new IllegalArgumentException(),
                        new NumberFormatException(),
                        new IllegalArgumentException()), 5, 0, 5),
                Arguments.of(List.of(new NullPointerException(),
                        new ArithmeticException(),
                        new ClassCastException(),
                        new NumberFormatException(),
                        new IllegalArgumentException()), 3, 2, 3),
                Arguments.of(List.of(), 0, 0, 0),
                Arguments.of(List.of(new ClassCastException(),
                        new ArithmeticException(),
                        new ClassCastException(),
                        new ArithmeticException(),
                        new IllegalArgumentException()), 1, 4, 1),
                Arguments.of(List.of(
                        new ClassCastException(),
                        new ArithmeticException(),
                        new ClassCastException(),
                        new ArithmeticException(),
                        new ClassCastException()), 0, 5, 0)
        );
    }
}