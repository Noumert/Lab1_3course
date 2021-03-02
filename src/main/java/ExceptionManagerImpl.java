import java.util.List;

public class ExceptionManagerImpl implements ExceptionManager {

    JsonExcReader jsonExcReader;
    ServerManager serverManager;

    public ExceptionManagerImpl(JsonExcReader jsonExcReader, ServerManager serverManager) {
        this.jsonExcReader = jsonExcReader;
        this.serverManager = serverManager;
    }

    int critExcCount = 0;
    int noCritExcCount = 0;
    int sendCount = 0;

    public int getNoCritExcCount() {
        return noCritExcCount;
    }

    public int getSendCount() {
        return sendCount;
    }

    public int getCritExcCount() {
        return critExcCount;
    }

    @Override
    public boolean isCritExc(Exception e) {
        List<String> criticalExceptions = jsonExcReader.readFromFile("critExc.json");
        return criticalExceptions.contains(e.getClass().toString());
    }

    @Override
    public void countCritExc(Exception e) {
        if (isCritExc(e)) {
            this.critExcCount++;
            if (serverManager.toServer(e)) {
                this.sendCount++;
            }
        } else {
            this.noCritExcCount++;
        }
    }
}
