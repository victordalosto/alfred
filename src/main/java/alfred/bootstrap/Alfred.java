package alfred.bootstrap;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import alfred.models.AlfredBackpack;


public class Alfred {

    
    public static void start(String path) throws Exception {
        Properties properties = getAlfredProperties(path);
        AlfredBackpack.init(properties);
        AlfredFunctionalities.init();
    }



    private static Properties getAlfredProperties(String path) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(path));
            return properties;
        } catch (FileNotFoundException e) {
            System.out.println(" \n\nError: Couldn't find alfredconfig.property");
        } catch (IOException e) {
            System.out.println(" \n\nError: Couldn't read the content of alfredconfig.properties");
        }
        throw new RuntimeException();
    }




}
