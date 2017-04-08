package integration;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;
import stateMachine.AbstractStateMachine;
import stateMachine.GStateMachine;
import stateMachine.SCXMLToJava;
import stateMachine.State;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rami on 08/04/2017.
 */
public class SimpleStateMachineTest {

    static String ressourceDir = "src\\main\\resources\\";
    static String integrationTestDir = "integrationTests\\";
    static AbstractStateMachine stateMachine;

    @BeforeClass
    public static void prepareEnv(){
        File scxml = new File(ressourceDir + integrationTestDir +"test1.scxml");
        try {
            SCXMLToJava builder = new SCXMLToJava(scxml);
            builder.generateJavaCode();
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


    @Test
    public void testInitState(){
        //Test if the proper state has been set to initial (State1 without prior indication)
        assertEquals(this.stateMachine.getInitState().getId(), "State1");

        //Test if initial state is current state if we do not start the machine
        assertEquals(this.stateMachine.getInitState(), this.stateMachine.getCurrentState());
    }

    @Test
    public void testNbOfStates(){
        assertEquals(this.stateMachine.getStateList().size(), 2);
    }

    @Test
    public void testStatesNames(){
        List<State> states =  this.stateMachine.getStateList();
        List<State> expected = new ArrayList<State>();
        expected.add(new State("State1"));
        expected.add(new State("State2"));
        for(int i = 0; i < states.size(); i++){
            assertEquals(states.get(i).getId(), expected.get(i).getId());
        }
    }

    @Test
    public void testNBOfTransitions(){
        List<State> states =  this.stateMachine.getStateList();
        for(State state: states){
            assertEquals(state.getTransitions().size(), 1);
        }
    }

    @Test
    public void testTransition(){
        List<State> states =  this.stateMachine.getStateList();
        System.out.println(states.get(0).getTransitions().get(0));
        assertEquals(states.get(0).getTransitions().get(0).from(), states.get(0));
        assertEquals(states.get(0).getTransitions().get(0).to(), states.get(1));

        assertEquals(states.get(1).getTransitions().get(0).from(), states.get(1));
        assertEquals(states.get(1).getTransitions().get(0).to(), states.get(0));

    }
}
