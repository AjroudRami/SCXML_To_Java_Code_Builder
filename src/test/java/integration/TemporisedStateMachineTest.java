package integration;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rami on 15/04/2017.
 */
public class TemporisedStateMachineTest extends TestEnv {


    @BeforeClass
    public static void prep(){
        prepareEnv("test2.scxml");
    }
    @Test
    public void testTemporisedTransition(){

        super.stateMachine.start();
        super.stateMachine.notifyEvent("event1");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("State2", super.stateMachine.getCurrentState().getId());
        double currentTime = System.currentTimeMillis();
        double startTime = System.currentTimeMillis();
        while( currentTime - startTime < 1200){
            currentTime = System.currentTimeMillis();
        }
        assertEquals("State1", super.stateMachine.getCurrentState().getId());

    }

}
