package stateMachine;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rami on 08/03/2017.
 */
public class State implements Serializable {

    //event name
    private String id;
    // is the state an initial state
    private boolean isInit;
    private List<Transition> transitions;
    //Events triggered on entry
    private List<Event> onEntry;
    //Event triggered on exit
    private List<Event> onExit;

    public State(){
        this.id = "";
        this.isInit = false;
        this.transitions = new ArrayList<Transition>();
        this.onEntry = new ArrayList<Event>();
        this.onExit = new ArrayList<Event>();
    }

    public State(String id){
        this();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isInit() {
        return isInit;
    }

    public State notifyEvent(String event) {
        for(Transition t : transitions){
            if(t.raisedFor(event)){
                this.exit();
                t.trigger();
                t.to().enter();
                return t.to();
            }
        }
        return this;
    }

    /**
     * This method is called when entering the state and triggers all the event that need to be triggered at this point
     */
    private void enter() {
        for(Event e: onEntry){
            e.trigger();
        }
        System.out.println("enter state " + this.id);
    }

    /**
     * This method is called when exiting the state and triggers all the event that need to be triggered at this point
     */
    private void exit(){
        for(Event e: onExit){
            e.trigger();
        }
        System.out.println("exit state " + this.id);
    }

    public State setId(String id){
        this.id = id;
        return this;
    }

    public State setIsInit(boolean isInit){
        this.isInit = isInit;
        return this;
    }

    public State addTransition(Transition transition){
        this.transitions.add(transition);
        return this;
    }

    public State addOnEntry(Event e){
        this.onEntry.add(e);
        return this;
    }

    public State addOnExit(Event e){
        this.onExit.add(e);
        return this;
    }


    public void connectToEvent(String eventName, Object object, Method method){
        for(Event e : onEntry){
            if(e.getName().equals(eventName)){
                int delay = e.getDelay();
                e.addCallable(new Caller(object, method, delay));
            }
        }
        for(Event e : onExit){
            if(e.getName().equals(eventName)){
                int delay = e.getDelay();
                e.addCallable(new Caller(object, method, delay));
            }
        }
        for(Transition t : this.getTransitions()){
            t.connectToEvent(eventName, object, method);
        }
    }

    public void connectToEvent(String eventName, Object object, Method method, Object[] args){
        for(Event e : onEntry){
            if(e.getName().equals(eventName)){
                int delay = e.getDelay();
                e.addCallable(new Caller(object, method, args, delay));
            }
        }
        for(Event e : onExit){
            if(e.getName().equals(eventName)){
                int delay = e.getDelay();
                e.addCallable(new Caller(object, method, args, delay));
            }
        }
        for(Transition t : this.getTransitions()){
            t.connectToEvent(eventName, object, method, args);
        }}

    public void connectToEvent(String eventName, Callable callable){
        for(Event e : onEntry){
            if(e.getName().equals(eventName)){
                int delay = e.getDelay();
                e.addCallable(callable);
            }
        }
        for(Event e : onExit){
            if(e.getName().equals(eventName)){
                int delay = e.getDelay();
                e.addCallable(callable);
            }
        }
        for(Transition t : this.getTransitions()){
            t.connectToEvent(eventName, callable);
        }
    }

    public boolean hasSentEvent(String name) {
        for (Event e : onEntry) {
            if (e.getName().equals(name)) {
                return true;
            }
        }
        for (Event e : onExit) {
            if (e.getName().equals(name)) {
                return true;
            }
        }
        for (Transition t : this.getTransitions()) {
            if (t.hasSentEvent(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Transition> getTransitions() {
        return this.transitions;
    }
}
