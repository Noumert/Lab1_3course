import java.util.List;

public interface ExceptionManager {

    boolean isCritExc(Exception e);

    void countCritExc(Exception e);

}
