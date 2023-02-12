package alfred.functionalities;
import java.util.Properties;
import alfred.interfaces.AlfredActionInterface;
import alfred.models.AlfredBackpack;
import alfred.service.DirectoryService;


public class ActionBootstrap implements AlfredActionInterface {

    private Properties properties;

    public ActionBootstrap(Properties properties) {
        this.properties = properties;
    }


    public void executa() {
        AlfredBackpack.initializeDependences(properties);

        AlfredBackpack.getMonitoredDirectories().forEach(dir -> {
            DirectoryService.createDirectoryIfNotExists(dir);
        });

        AlfredBackpack.getDestinyDirectories().forEach(dir -> {
            DirectoryService.createDirectoryIfNotExists(dir);
            DirectoryService.createDirectoryIfNotExists(dir+"\\monitor");
        });
    }
    
}
