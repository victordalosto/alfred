package alfred.functionalities;
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


public class ActionCopy extends TimerTask {


    @Override
    public void run() {
        Map<String, String> filesToCopy = mapValidFilesToCopy(AlfredBackpack.getMonitoredDirectories());
        Map<String, String> filesRemove = mapValidFilesToCopy(AlfredBackpack.getDestinyDirectories());
        filesToCopy.keySet().removeAll(filesRemove.keySet());
        copy(filesToCopy);
    }



    private Map<String, String> mapValidFilesToCopy(Set<String> paths) {
        Map<String, String> map = new HashMap<>();
        paths.forEach(p ->  {
            map.putAll(getSetOfValidFiles(p));
        });
        return map;
    }



    private Map<String, String> getSetOfValidFiles(String dir) {
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
            throw new RuntimeException(e);
        }
    }


    private void copy(Map<String, String> filesToCopy) {
        filesToCopy.forEach((key, filePath) -> {
            AlfredBackpack.getDestinyDirectories().forEach(dest -> {
                try {
                    Files.copy(Paths.get(filePath), 
                               Paths.get(dest, Paths.get(filePath).getFileName().toString()), 
                               StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });

    }


    

    
}
