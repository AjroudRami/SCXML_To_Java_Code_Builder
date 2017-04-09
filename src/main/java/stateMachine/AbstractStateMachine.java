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
    protected EventHandler eventHandler;

    public AbstractStateMachine(){
        this.eventHandler = new EventHandler(this);
        this.stateList = new ArrayList<State>();
    }

    public List<State> getStateList(){
        return this.stateList;
    }

    public State getInitState(){
        return this.initState;
    }

    public State getCurrentState(){
        return this.currentState;
    }

    protected void init(){
        if(initState == null && stateList.size() != 0) {
            initState = stateList.get(0);
        }
        this.currentState = initState;
    }

    public void start(){
        Thread t = new Thread(eventHandler);
        t.start();
    }

    public void stop(){
        eventHandler.stop();
    }

    public void notifyEvent(Event e){
        this.eventHandler.notifyEvent(e);
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

    public void linkStates(){
        for(State state: this.stateList){
            for(Transition transition : state.getTransitions()){
                transition.setFrom(state);
                for(State state1 : this.stateList){
                    if(transition.to().getId().equals(state1.getId())){
                        transition.setTo(state1);
                    }
                }
            }
        }
    }

    public void triggerEvent(String name) {
        this.currentState = this.currentState.notifyEvent(name);
    }
}
