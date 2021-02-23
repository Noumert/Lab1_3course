import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExceptionManager {
    public final List criticalExceptions = Arrays.asList(NullPointerException.class,NumberFormatException.class,IllegalArgumentException.class);

    private int critExcCount = 0;

    public int getCritExcCount() {
        return critExcCount;
    }

    public void setCritExcCount(int critExcCount) {
        this.critExcCount = critExcCount;
    }

    public boolean isCritExc(Exception e){
        return criticalExceptions.contains(e.getClass());
    }

    public void countCritExc(Exception e){
        if(isCritExc(e)){
            this.critExcCount++;
        }
    }
}
