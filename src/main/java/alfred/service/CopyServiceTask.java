package alfred.service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import alfred.models.AlfredBackpack;


public class CopyServiceTask extends TimerTask {

    @Override
    public void run() {
        if (AlfredBackpack.isMonitored()) {
            Map<String, String> filesToCopy = mapValidFilesToCopy(AlfredBackpack.getMonitoredDirectories());
            Map<String, String> filesRemove = mapValidFilesToCopy(AlfredBackpack.getDestinyDirectories());
            filesToCopy.keySet().removeAll(filesRemove.keySet());
            copyContent(filesToCopy);
        }
    }



    private Map<String, String> mapValidFilesToCopy(Set<String> setOfPaths) {
        Map<String, String> map = new HashMap<>();
        setOfPaths.forEach(path -> map.putAll(getValidFiles(path)));
        return map;
    }



    private Map<String, String> getValidFiles(String dir) {
        try (Stream<Path> stream = Files.walk(Paths.get(dir))) {
            return stream
              .filter(file -> Files.isRegularFile(file))
              .filter(file -> AlfredBackpack.getListOfFormatFiles().stream()
                                           .anyMatch(filter -> file.toString().toLowerCase()
                                           .endsWith(filter.toString().toLowerCase())))
              .collect(Collectors.toMap(entry -> entry.getFileName().toString(), 
                                        entry -> entry.toString(),
                                        (a1, a2) -> a1)); // Avoid duplicateKeyException
        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    

    private void copyContent(Map<String, String> filesToCopy) {
        filesToCopy.forEach((key, filePath) -> {
            AlfredBackpack.getDestinyDirectories().forEach(dest -> {
                try {
                    Files.copy(Paths.get(filePath), 
                               Paths.get(dest, "monitor", Paths.get(filePath).getFileName().toString()), 
                               StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });

    }
    
}
