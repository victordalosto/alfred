package alfred.service;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import alfred.models.ModelConfig;


public class InputService {


    public static Set<String> getInput(Properties properties, ModelConfig modelConfig) {
        String rawInput = properties.getProperty(modelConfig.toString());
        return convertRawInputToSet(rawInput);
    }


    private static Set<String> convertRawInputToSet(String input) {
        input = input.replaceAll("\\s+", "")
                     .replace(",", ";")
                     .replace("{", "")
                     .replace("}", "")
                     .replace("]", "")
                     .replace("[", "");
        return new HashSet<String>(Arrays.asList(input.split(";")));
    }
    
}
