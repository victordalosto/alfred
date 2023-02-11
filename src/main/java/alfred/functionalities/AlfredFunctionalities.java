package alfred.functionalities;

import java.util.Timer;

import alfred.models.AlfredBackpack;

public class AlfredFunctionalities {


    public static void init() {

        new Timer().schedule(new ActionCopy(), 0, AlfredBackpack.getLoopTime());

    }
    
}
