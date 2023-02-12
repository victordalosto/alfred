package alfred.functionalities;
import java.io.IOException;
import java.util.Scanner;

import alfred.interfaces.AlfredAction;
import alfred.models.AlfredBackpack;
import alfred.service.UnzipDirectory;
import alfred.service.ZipDirectory;


public class ActionPrompt implements AlfredAction {

    public void executa() {
        new Thread(new Runnable() {
            public void run() {
                try (Scanner scanner = new Scanner(System.in)) {
                    while(scanner.hasNext()) {
                        String input = scanner.nextLine();
                        handlesstart(input);
                        handleszip(input);
                        handlesunzip(input);
                        handlespurge(input);
                    }
                }
            }
        }).start();
    }




    public static void handlesstart(String input) {
        if (input.toLowerCase().startsWith("start")) {
            System.out.print("Iniciando Alfred Monitor\n");
            AlfredBackpack.setRunMonitor(true);
            AlfredBackpack.getListOfStartPrograms().forEach(p -> {
                String cmd = p;
                if (p.toString().toLowerCase().endsWith(".bat")) {
                    cmd = "cmd /c start " + cmd;
                System.out.println("Rodando: " + cmd);
                try {
                    Runtime.getRuntime().exec(cmd);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }
            });
        }
    }



    public static void handleszip(String input) {
        if (input.toLowerCase().startsWith("zip ")) {
            input = input.replaceAll("zip ", "");
            ZipDirectory.executa(input);
        }
    }



    public static void handlesunzip(String input) {
        if (input.toLowerCase().startsWith("unzip ")) {
            input = input.replaceAll("unzip ", "");
            UnzipDirectory.executa(input);
        }
    }




    public static void handlespurge(String input) {
        if (input.toLowerCase().startsWith("purge")) {
            System.out.println("Parando Alfred Monitor");
            AlfredBackpack.setRunMonitor(false);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new ActionPurge().executa();
            System.out.print("Diretorios deletado");
        }
    }
}
