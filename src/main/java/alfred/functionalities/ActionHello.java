package alfred.functionalities;

import alfred.interfaces.AlfredAction;
import alfred.models.AlfredBackpack;

public class ActionHello implements AlfredAction {

    public void executa() {
        System.out.println("Alfred is running");
        System.out.println("Running for: " + AlfredBackpack.getDestinyDirectories());
        System.out.println("COMMANDS: start  |  zip <password>  |  unzip <password>  |  purge");
        System.out.println();
    }
    
}
