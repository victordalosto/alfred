package alfred.service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DirectoryService {


    public static void createDirectoryIfNotExists(String path) {
        if (!new File(path).isDirectory())
            try {
                Files.createDirectory(Paths.get(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    

    public static String getValidName(String path) {
        for (int i=0; i<256; i++) {
            if (!pathIsValidToBeCreated(new File(path))) {
                return path;
            }
            Path curPath = Paths.get(path);
            path = Paths.get(curPath.getParent().toString(), 
                   i + curPath.getFileName().toString()).toString();
        }
        throw new RuntimeException("Não foi possivel obter um valido nome para rodar arquivo.");
    }


    private static boolean pathIsValidToBeCreated(File file) {
        return (file.isDirectory() || file.isFile());
    }

}
