package stateMachine;

import java.lang.reflect.Method;

/**
 * Created by Rami on 10/04/2017.
 *
 * This class defines the global behavior of a finite state machine
 */
public interface StateMachine {

    /**
     * This methods starts a Thread running the event handler. If the machine is not started,
     * the stack will be filled with event but no one will be fired.
     * The machine can't switch states if not started.
     */
    void start();

    /**
     * This method stops the eventHandler and return to the stopped state.
     */
    void stop();

    /**
     * notify the machine with the event e. The event is considered to be a "send".
     * The event is passed to the event handler which will decide when to fire it.
     * To outpass the eventHandler us a direct method like triggerEvent(Event e) in AbstractStateMachine
     * @param event : the event name
     */
    void notifyEvent(String event);

    /**
     * Same behavior as notifyEvent(String event)
     * Here you can choose the event priority (send or raise)
     * @param e the event to be sent
     */
    void notifyEvent(Event e);

    /**
     * connect a state machine event to a java method by using a callable
     * @param eventName
     * @param callable
     */
    void connectToEvent(String eventName, Callable callable);

    /**
     * connect a state machine event to a java method by giving the object and method
     * The method will be called when the eventHandler of the state machine triggers the event
     * @param eventName
     * @param Object object on which we will call the method
     */
    void connectToEvent(String eventName, Object object, Method method);

    /**
     * Connect a state machine event to a java method by giving the object, method and arguments of the method.
     * The method will be called when the eventHandler of the state machine triggers the event
     * @param eventName
     * @param object object on which the method will be called
     * @param method the method to be called
     * @param args the arguments of the method
     */
    void connectToEvent(String eventName, Object object, Method method, Object[] args);
}
