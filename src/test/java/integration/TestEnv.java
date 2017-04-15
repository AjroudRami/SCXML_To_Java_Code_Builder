package integration;

import generator.SCXMLToJava;
import org.junit.BeforeClass;
import org.xml.sax.SAXException;
import stateMachine.AbstractStateMachine;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Rami on 15/04/2017.
 */
public class TestEnv {
    static String ressourceDir = "src\\main\\resources\\";
    static String integrationTestDir = "integrationTests\\";
    static AbstractStateMachine stateMachine;

    public static void prepareEnv(String filename){
        File scxml = new File(ressourceDir + integrationTestDir + filename);
        try {
            SCXMLToJava builder = new SCXMLToJava(scxml);
            builder.generateJavaCode(new File(System.getProperty("user.dir")));
            File javaCode = builder.getJavaCodeFile();
            File root = javaCode.getAbsoluteFile().getParentFile();
            System.out.println(root);
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            compiler.run(null, System.out, System.err, javaCode.getPath());
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{root.toURI().toURL()});
            Class<?> cls = Class.forName("GStateMachine", true, classLoader);
            stateMachine = (AbstractStateMachine) cls.newInstance();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
