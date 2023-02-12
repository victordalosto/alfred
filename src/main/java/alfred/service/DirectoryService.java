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



    public static void renameFile(String target) {
        if (new File(target).isFile()) {
            String renameTarget = getValidName(target);
            try {
                Files.move(Paths.get(target), Paths.get(renameTarget));
                System.out.println("Renaming old: " + target + " -> " + renameTarget);
            } catch (IOException e) {
                System.out.println("Não foi possível zipar o arquivo!!");
                e.printStackTrace();
            }
        }
    }
    


    public static String getValidName(String path) {
        String temp = path;
        for (int i=0; i<256; i++) {
            if (pathDontExists(new File(temp)))
                return temp;
            Path curPath = Paths.get(path);
            temp = Paths.get(curPath.getParent().toString(), 
                   i + "___" +curPath.getFileName().toString()).toString();
        }
        throw new RuntimeException("  > Não foi possivel obter um valido nome para rodar arquivo.");
    }


    private static boolean pathDontExists(File file) {
        if (file.isDirectory() || file.isFile() || file.exists())
            return false;
        return true;
    }

}
