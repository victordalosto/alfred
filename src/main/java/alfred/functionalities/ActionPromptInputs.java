package alfred.functionalities;
import java.util.Scanner;

import alfred.interfaces.AlfredActionInterface;


public class ActionPromptInputs implements AlfredActionInterface {

    public void executa() {
        new Thread(new Runnable() {@Override public void run() {
            try (Scanner scanner = new Scanner(System.in)) {
                while(scanner.hasNextLine()) {
                    String input = scanner.nextLine();
                    start(input);
                    zip(input);
                    unzip(input);
                    purge(input);
        }}}})
        .start();
    }


    public static void start(String input) {
        if (input.toLowerCase().startsWith("start"))
            AlfredAction.start();
    }


    public static void monitor(String input) {
        if (input.toLowerCase().startsWith("monitor"))
            AlfredAction.monitor();
    }


    public static void purge(String input) {
        if (input.toLowerCase().startsWith("purge"))
            AlfredAction.purge();
    }



    public static void zip(String input) {
        if (input.toLowerCase().startsWith("zip ")) {
            String target = input.replaceAll("zip ", "");
            AlfredAction.zipa(target);
        }
    }



    public static void unzip(String input) {
        if (input.toLowerCase().startsWith("unzip ")) {
            String target = input.replaceAll("unzip ", "");
            AlfredAction.unzipa(target);
        }
    }
}
