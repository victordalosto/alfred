package alfred;
import alfred.concierge.Alfred;


public class Main {
    
    public static void main(String[] args) throws Exception {
        if (args.length == 0)
            Alfred.start("alfredconfig.properties");
        else
            Alfred.start(args[0]);
    }
}
