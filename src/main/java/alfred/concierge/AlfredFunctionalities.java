package alfred.concierge;
import java.util.Properties;
import alfred.functionalities.ActionBootstrap;
import alfred.functionalities.ActionHello;
import alfred.functionalities.ActionMonitor;
import alfred.functionalities.ActionPromptInputs;
import alfred.interfaces.AlfredActionInterface;


public class AlfredFunctionalities {

    public static void startExecuting(Properties properties) throws Exception {
        run(new ActionBootstrap(properties));
        run(new ActionHello());
        run(new ActionMonitor());
        run(new ActionPromptInputs());
    }


    private static void run(AlfredActionInterface action) {
        action.executa();
    }
}
