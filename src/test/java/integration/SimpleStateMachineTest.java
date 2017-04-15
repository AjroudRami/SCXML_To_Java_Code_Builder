package integration;
import org.junit.BeforeClass;
import org.junit.Test;
import stateMachine.Event;
import stateMachine.State;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rami on 08/04/2017.
 */
public class SimpleStateMachineTest  extends TestEnv{

    @BeforeClass
    public static void prep(){
        prepareEnv("test1.scxml");
    }

    @Test
    public void testInitState(){
        //Test if the proper state has been set to initial (State1 without prior indication)
        assertEquals(super.stateMachine.getInitState().getId(), "State1");

        //Test if initial state is current state if we do not start the machine
        assertEquals(super.stateMachine.getInitState(), super.stateMachine.getCurrentState());
    }

    @Test
    public void testNbOfStates(){
        assertEquals(super.stateMachine.getStateList().size(), 2);
    }

    @Test
    public void testStatesNames(){
        List<State> states =  super.stateMachine.getStateList();
        List<State> expected = new ArrayList<State>();
        expected.add(new State("State1"));
        expected.add(new State("State2"));
        for(int i = 0; i < states.size(); i++){
            assertEquals(states.get(i).getId(), expected.get(i).getId());
        }
    }

    @Test
    public void testNBOfTransitions(){
        List<State> states =  super.stateMachine.getStateList();
        for(State state: states){
            assertEquals(state.getTransitions().size(), 1);
        }
    }

    @Test
    public void testTransition(){
        List<State> states =  super.stateMachine.getStateList();
        assertEquals(states.get(0).getTransitions().get(0).from(), states.get(0));
        assertEquals(states.get(0).getTransitions().get(0).to(), states.get(1));

        assertEquals(states.get(1).getTransitions().get(0).from(), states.get(1));
        assertEquals(states.get(1).getTransitions().get(0).to(), states.get(0));

    }

    @Test
    public void testSendingEvent(){
        super.stateMachine.start();
        super.stateMachine.notifyEvent(new Event("event1"));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("State2", super.stateMachine.getCurrentState().getId());

        super.stateMachine.start();
        super.stateMachine.notifyEvent(new Event("event2"));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("State1", super.stateMachine.getCurrentState().getId());
    }
}
