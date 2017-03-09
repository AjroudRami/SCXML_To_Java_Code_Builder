package stateMachine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rami on 08/03/2017.
 */
public class State implements Serializable {
    private String id;
    private boolean isInit;
    private List<Transition> transitions;
    private List<Callable> onEntry;
    private List<Callable> onExit;
    private boolean init;

    State(){
        this.id = "";
        this.isInit = false;
        this.transitions = new ArrayList<Transition>();
        this.onEntry = new ArrayList<Callable>();
        this.onExit = new ArrayList<Callable>();
    }

    State(String id,boolean isInit, List<Transition> transitions, List<Callable> onEntry, List<Callable> onExit){
        this.id = id;
        this.isInit = isInit;
        this.transitions = transitions;
        this.onEntry = onEntry;
        this.onExit = onExit;
    }

    State(String id){
        this();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }

    public boolean isInit() {
        return isInit;
    }

    public State notifyEvent(String event) {
        for(Transition t : transitions){
            if(t.raisedFor(event)){
                this.exit();
                t.to().enter();
                return t.to();
            }
        }
        return this;
    }

    //TODO
    private void enter() {
    }

    //TODO
    private void exit(){

    }
}
