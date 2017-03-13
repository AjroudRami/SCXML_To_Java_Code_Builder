package stateMachine;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rami on 08/03/2017.
 */
public class State implements Serializable {
    private String id;
    private boolean isInit;
    private List<Transition> transitions;
    private List<Event> onEntry;
    private List<Event> onExit;

    State(){
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

    private void enter() {
        for(Event e: onEntry){
            e.trigger();
        }
        System.out.println("enter state " + this.id);
    }

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

    //TODO
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

    //TODO
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

    //TODO
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

    //TODO
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
