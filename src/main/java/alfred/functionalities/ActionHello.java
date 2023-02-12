package alfred.functionalities;
import alfred.interfaces.AlfredActionInterface;


public class ActionHello implements AlfredActionInterface {

    public void executa() {
        System.out.println("===============================================================================================");
        System.out.println("  ALFRED is up and Running at [" + System.getProperty("user.dir") + "]");
        System.out.println("  Commands:   [ start ]  : Alfred starts the programs listed in configurations.properties.");
        System.out.println("              [ monitor] : Alfred starts monitoring and copying files into destination folder.");
        System.out.println("              [ zip <password> ]  : Compress and encrypt the DESTINY folder in a ZIP file.");
        System.out.println("              [ unzip <password> ]  : Unzip and decrypt ZIP folder listed in DESTINY.");
        System.out.println("              [ purge ]  : Delete all monitored end points and files listed in purge.");
        System.out.println("===============================================================================================");
    }
    
}
