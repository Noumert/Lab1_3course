import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonExcReaderImpl implements JsonExcReader {
    @Override
    public List<String> readFromFile(String filename){
        List<String> criticalExceptions = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        JSONArray excCritList;

        try (FileReader reader = new FileReader(filename)) {

            Object obj = jsonParser.parse(reader);
            excCritList = (JSONArray) obj;
            criticalExceptions.addAll(excCritList);

        } catch (IOException | ParseException exc) {
            exc.printStackTrace();
        }
        return criticalExceptions;
    }
}
