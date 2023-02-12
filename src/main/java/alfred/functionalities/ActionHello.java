package alfred.functionalities;
import alfred.interfaces.AlfredActionInterface;


public class ActionHello implements AlfredActionInterface {

    public void executa() {
        System.out.println("===========================================================================");
        System.out.println("  > ALFRED is up and Running at [" + System.getProperty("user.dir") + "]");
        System.out.println("  > Commands: [  start  |  zip <password>  |  unzip <password>  |  purge  ]");
        System.out.println("===========================================================================");
    }
    
}
