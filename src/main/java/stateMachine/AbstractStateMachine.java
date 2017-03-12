package stateMachine;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rami on 08/03/2017.
 */
public abstract class AbstractStateMachine implements Serializable {

    protected List<State> stateList;
    protected State initState;
    protected State currentState;

    public AbstractStateMachine(){
        this.stateList = new ArrayList<State>();
    }

    protected void init(){
        if(initState == null && stateList.size() != 0) {
            initState = stateList.get(0);
        }
        this.currentState = initState;
    }

    public void notifyEvent(String event){
        this.currentState = this.currentState.notifyEvent(event);
    }

    public void connectToEvent(String eventName, Object object, Method method){
        for(State state : stateList){
            if(state.hasSentEvent(eventName)){
                state.connectToEvent(eventName, object, method);
            }
        }
    }

    public void connectToEvent(String eventName, Object object, Method method, Object[] args){
        for(State state : stateList){
            if(state.hasSentEvent(eventName)){
                state.connectToEvent(eventName, object, method, args);
            }
        }
    }

    public void connectToEvent(String eventName, Callable callable){
        for(State state : stateList){
            if(state.hasSentEvent(eventName)){
                state.connectToEvent(eventName, callable);
            }
        }
    }
}
