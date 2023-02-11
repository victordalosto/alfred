package alfred.models;
import java.util.Properties;
import java.util.Set;
import alfred.service.InputService;
import lombok.Getter;


public class AlfredBackpack {

    public static void init(Properties properties) {
        listOfFormatFiles = InputService.getInput(properties, ModelConfig.FORMAT);
        destinyDirectories = InputService.getInput(properties, ModelConfig.DESTINY);
        monitoredDirectories = InputService.getInput(properties, ModelConfig.MONITOR);
        loopTime = (long) (InputService.getInput(properties, ModelConfig.TIME)
                                       .stream().mapToLong(Long::parseLong)
                                       .min().getAsLong() * 1000);
    }

    @Getter
    private static long loopTime = 60;
    @Getter
    private static Set<String> listOfFormatFiles;
    @Getter
    private static Set<String> destinyDirectories;
    @Getter
    private static Set<String> monitoredDirectories;


}
