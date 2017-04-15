package integration;

import org.junit.BeforeClass;
import org.junit.Test;
import stateMachine.Event;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 15/04/2017.
 */
public class TestPriorityStack  extends TestEnv{

    @BeforeClass
    public static void prepenv(){
        prepareEnv("test3.scxml");
    }

    @Test
    public void testStack(){

        /**We fill the stack with this two events
        *If the stack uses the priority SEND > RAISE then the first event (event1) will be discarded the second too
         * Note the machine is stopped otherwise the events would be triggered.
         * */
        super.stateMachine.notifyEvent(new Event("event1").setType(Event.Type.RAISE));
        super.stateMachine.notifyEvent(new Event("event2").setType(Event.Type.SEND));

        assertEquals("State1", super.stateMachine.getCurrentState().getId());
        super.stateMachine.start();
        assertEquals("State1", super.stateMachine.getCurrentState().getId());

    }
}
