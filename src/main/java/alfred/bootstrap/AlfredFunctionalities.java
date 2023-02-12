package alfred.bootstrap;
import alfred.functionalities.ActionDirectories;
import alfred.functionalities.ActionHello;
import alfred.functionalities.ActionMonitorAndCopy;
import alfred.functionalities.ActionPrompt;


public class AlfredFunctionalities {

    public static void init() throws Exception {
        new ActionHello().executa();
        new ActionDirectories().executa();
        new ActionMonitorAndCopy().executa();
        new ActionPrompt().executa();
    }

}
