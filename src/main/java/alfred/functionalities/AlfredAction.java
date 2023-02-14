package alfred.functionalities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import alfred.models.AlfredBackpack;
import alfred.service.PurgeService;
import alfred.service.UnzipDirectory;
import alfred.service.ZipDirectory;


public class AlfredAction {


    public static void start() {
        AlfredBackpack.getListOfProgramsToStart().forEach(filePath -> {
            try {
                System.out.println("  > Executando programa: " + filePath);
                ProcessBuilder builder;
                if (filePath.toString().toLowerCase().endsWith(".bat"))
                    builder = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/c", filePath);
                else
                    builder = new ProcessBuilder(Arrays.asList("cmd", "/c", "start", filePath));
                builder.directory(new File(filePath).getParentFile());
                builder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("  > Iniciado Alfred Programs [" + AlfredBackpack.getMonitoredDirectories() +"]");
    }



    public static void monitor() {
        AlfredBackpack.setMonitored(true);
        System.out.println("  > Iniciado Alfred Monitor [" + AlfredBackpack.getMonitoredDirectories() +"]");
    }



    public static void purge() {
        AlfredBackpack.setMonitored(false);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PurgeService.limpa();
        System.out.println("  > Parado Alfred Monitor [" + AlfredBackpack.getMonitoredDirectories() + "]");
        System.out.println("  > Diretorios deletado");
    }



    public static void zipa(String input) {
        ZipDirectory.executa(input);
    }



    public static void unzipa(String target) {
        UnzipDirectory.executa(target);
    }
    
}
