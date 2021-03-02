import org.json.simple.JSONArray;

import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        JSONArray CritExcList = new JSONArray();
        CritExcList.add(NullPointerException.class);
        CritExcList.add(NumberFormatException.class);
        CritExcList.add(IllegalArgumentException.class);

        //Write JSON file
        try (FileWriter file = new FileWriter("critExc.json")) {
            file.write(CritExcList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
