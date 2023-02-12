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
        System.out.print("  > Iniciando Alfred Monitor [" + AlfredBackpack.getMonitoredDirectories() +"]");
        AlfredBackpack.setMonitored(true);
        AlfredBackpack.getListOfProgramsToStart().forEach(filePath -> {
            try {
                ProcessBuilder builder;
                if (filePath.toString().toLowerCase().endsWith(".bat"))
                    builder = new ProcessBuilder("cmd.exe", "/c", "start", "cmd.exe", "/c", filePath);
                else
                    builder = new ProcessBuilder(Arrays.asList("cmd", "/c", "start", filePath));
                builder.directory(new File(filePath).getParentFile());
                builder.start();
                System.out.println("  > Executando programa: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    public static void purge() {
        System.out.println("  > Parando Alfred Monitor [" + AlfredBackpack.getMonitoredDirectories() + "]");
        AlfredBackpack.setMonitored(false);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PurgeService.limpa();
        System.out.print("  > Diretorios deletado");
    }



    public static void zipa(String input) {
        ZipDirectory.executa(input);
    }



    public static void unzipa(String target) {
        UnzipDirectory.executa(target);
    }
    
}
