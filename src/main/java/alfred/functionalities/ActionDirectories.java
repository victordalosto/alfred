package alfred.functionalities;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import alfred.interfaces.AlfredAction;
import alfred.models.AlfredBackpack;


public class ActionDirectories implements AlfredAction {

    public void executa() {
        AlfredBackpack.getMonitoredDirectories().forEach(dir -> {
            if (!new File(dir).isDirectory())
                try {
                    Files.createDirectory(Paths.get(dir));
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
        AlfredBackpack.getDestinyDirectories().forEach(dir -> {
            if (!new File(dir).exists())
                try {
                    Files.createDirectory(Paths.get(dir));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (!Paths.get(dir, "monitor").toFile().exists())
            try {
                Files.createDirectory(Paths.get(dir, "monitor"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public static String getValidName(String path) {
        for (int i=0; i<256; i++) {
            if (!checkForExistance(path))
                return path;
            path = Paths.get(Paths.get(path).getParent().toString(), 
                   i + Paths.get(Paths.get(path).getFileName().toString()).toString()).toString();
        }
        return "256_" + path;
    }


    private static boolean checkForExistance(String path) {
        return new File(path).isDirectory() || new File(path).isFile();
    }
    
}
