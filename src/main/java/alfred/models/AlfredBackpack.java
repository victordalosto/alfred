package alfred.models;
import java.util.Properties;
import java.util.Set;
import alfred.service.InputService;
import lombok.Getter;
import lombok.Setter;


public class AlfredBackpack {

    public static void initializeDependences(Properties properties) {
        purgeDirectories = InputService.getInput(properties, ModelConfig.PURGE);
        listOfFormatFiles = InputService.getInput(properties, ModelConfig.FORMAT);
        destinyDirectories = InputService.getInput(properties, ModelConfig.DESTINY);
        monitoredDirectories = InputService.getInput(properties, ModelConfig.MONITOR);
        listOfProgramsToStart = InputService.getInput(properties, ModelConfig.START);
        loopTimeOfMonitor = (long) (InputService.getInput(properties, ModelConfig.TIME)
                                       .stream().mapToLong(Long::parseLong)
                                       .min().getAsLong() * 1000);
    }


    @Getter
    private static long loopTimeOfMonitor = -1;
    @Getter
    private static Set<String> listOfProgramsToStart;
    @Getter
    private static Set<String> listOfFormatFiles;
    @Getter
    private static Set<String> destinyDirectories;
    @Getter
    private static Set<String> monitoredDirectories;
    @Getter
    private static Set<String> purgeDirectories;
    @Getter @Setter
    private static boolean isMonitored = false;

}
