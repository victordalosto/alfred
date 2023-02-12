package alfred.functionalities;
import java.util.Timer;
import alfred.interfaces.AlfredAction;
import alfred.models.AlfredBackpack;
import alfred.service.CopyService;


public class ActionMonitorAndCopy implements AlfredAction {

    public void executa() {
        new Timer().schedule(new CopyService(), 0, AlfredBackpack.getLoopTime());
    }
    
}
