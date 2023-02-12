package alfred.functionalities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import alfred.interfaces.AlfredAction;
import alfred.models.AlfredBackpack;

public class ActionPurge implements AlfredAction {


    @Override
    public void executa() {
        AlfredBackpack.getPurgeDirectories().forEach(dir -> {
            walkPurgeDirectories(dir).forEach(file -> {
                safePurgeDir(file.toFile());
            });
            deleteEmptyDirectories(dir);
        });
        AlfredBackpack.getDestinyDirectories().forEach(dir -> {
            deleteFolder(new File(dir));
        });
    }


    

    private List<Path> walkPurgeDirectories(String dir) {
        try (Stream<Path> stream = Files.walk(Paths.get(dir))) {
            return stream
              .filter(file -> AlfredBackpack.getListOfFormatFiles().stream()
                                           .anyMatch(filter -> file.toString().toLowerCase()
                                           .endsWith(filter.toString().toLowerCase())))
              .collect(Collectors.toList());
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private void safePurgeDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (! Files.isSymbolicLink(f.toPath())) {
                    safePurgeDir(f);
                }
            }
        }
        file.delete();
    }
    

    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) { //some JVMs return null for empty dirs
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }


    public static void deleteEmptyDirectories(String path) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            if (children.length == 0) {
                dir.delete();
                return;
            }
            for (File child : children) {
                deleteEmptyDirectories(child.getAbsolutePath());
            }
            if (dir.listFiles().length == 0) {
                dir.delete();
            }
        }
    }
}
