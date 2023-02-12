package alfred.functionalities;
import java.util.Timer;
import alfred.interfaces.AlfredActionInterface;
import alfred.models.AlfredBackpack;
import alfred.service.CopyServiceTask;


public class ActionMonitor implements AlfredActionInterface {
    
    private static Timer timer = new Timer();

    public void executa() {
        long timeToLoop = AlfredBackpack.getLoopTimeOfMonitor();
        timer.schedule(new CopyServiceTask(), 0, timeToLoop);
    }
    
}
