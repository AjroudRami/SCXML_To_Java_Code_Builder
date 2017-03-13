import org.xml.sax.SAXException;
import stateMachine.SCXMLToJava;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Rami on 09/03/2017.
 */
public class Main {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

        SCXMLToJava builder = new SCXMLToJava(new File("src\\main\\resources\\test.scxml"));
        builder.generateJavaCode();
    }
}
