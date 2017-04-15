package stateMachine;

import java.lang.reflect.Method;

/**
 * Created by Rami on 15/04/2017.
 */
public interface StateMachine {

    void start();
    void stop();
    void notifyEvent(String event);
    void notifyEvent(Event e);
    void connectToEvent(String eventName, Callable callable);
    void connectToEvent(String eventName, Object object, Method method);
    void connectToEvent(String eventName, Object object, Method method, Object[] args);
}
