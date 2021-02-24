import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExceptionManager {
    public final List criticalExceptions = Arrays.asList(NullPointerException.class,NumberFormatException.class,IllegalArgumentException.class);

    private int critExcCount = 0;
    private  int noCritExcCount = 0;

    public int getNoCritExcCount() {
        return noCritExcCount;
    }

    public void setNoCritExcCount(int noCritExcCount) {
        this.noCritExcCount = noCritExcCount;
    }

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
        }else {
            this.noCritExcCount++;
        }
    }
}
